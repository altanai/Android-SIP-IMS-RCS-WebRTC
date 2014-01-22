/* ------------------------------------------------------------------
 * Copyright (C) 2009 OrangeLabs
 * -------------------------------------------------------------------
 */
#define LOG_TAG "NativeH264Decoder"
#include "android/log.h"
#include "NativeH264Decoder.h"
#include "pvavcdecoder.h"
#include "3GPVideoParser.h"

#include "avcapi_common.h"
#include "oscl_mem.h"
#include "avcdec_api.h"
#include "yuv2rgb.h"

#define MB_BASED_DEBLOCK

/* ------------------------------------------------------------------------------------- */
/*  Callback declaration for pvAvcDecoder                                                */
/* ------------------------------------------------------------------------------------- */

#define AVC_DEC_TIMESTAMP_ARRAY_SIZE 17 // don't ask me why :) look at avc_dev.h

typedef struct s_decoderData {
    uint32 FrameSize;
    uint8* pDpbBuffer;
    uint32 DisplayTimestampArray[AVC_DEC_TIMESTAMP_ARRAY_SIZE];
    uint32 CurrInputTimestamp;
    PVAVCDecoder * pvAvDec;
    AVCDecSPSInfo SeqInfo;
} DecoderData;

DecoderData gDecodeData = { 0 };


int cb_AVC_init_SPS(void *userData, uint aSizeInMbs, uint aNumBuffers) {
    __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "CB init_SPS");

    DecoderData *avcDec = (DecoderData*) userData;
    if (NULL == avcDec) {
        return 0;
    }
    avcDec->pvAvDec->GetSeqInfo(&avcDec->SeqInfo);
    if (avcDec->pDpbBuffer) {
        free(avcDec->pDpbBuffer);
        avcDec->pDpbBuffer = NULL;
    }
    avcDec->FrameSize = (aSizeInMbs << 7) * 3;
    avcDec->pDpbBuffer = (uint8*) malloc(aNumBuffers * (avcDec->FrameSize));

    return 1;
}

int cb_AVC_Malloc(void *userData, int32 size, int attribute) {
    DecoderData *avcDec = (DecoderData*) userData;
    return avcDec->pvAvDec->AVC_Malloc(size, attribute);
}

void cb_AVC_Free(void *userData, int mem) {
    DecoderData *avcDec = (DecoderData*) userData;
    avcDec->pvAvDec->AVC_Free(mem);
}

int cb_AVC_DPBAlloc(void* userData, int32 i, uint8** aYuvBuffer) {
    //__android_log_print(ANDROID_LOG_INFO, LOG_TAG, "CB cb_AVC_DPBAlloc");
    DecoderData *avcDec = (DecoderData*) userData;
    if (NULL == avcDec) {
        return 0;
    }

    *aYuvBuffer = avcDec->pDpbBuffer + i * avcDec->FrameSize;
    //Store the input timestamp at the correct index
    avcDec->DisplayTimestampArray[i] = avcDec->CurrInputTimestamp;

    return 1;
}

void cb_AVC_FrameUnbind(void *userData, int indx) {
    OSCL_UNUSED_ARG(userData);
    OSCL_UNUSED_ARG(indx);
    return;
}

/* ------------------------------------------------------------------------------------- */
/* Global variables                                                                      */
/* ------------------------------------------------------------------------------------- */
/** Packet video decoder */
PVAVCDecoder *decoder;

/** Decoder is initialized ? */
bool decoderInitialized = false;

/** Buffer for the decoded pictures */
uint8* aOutBuffer;

/** Concatenated buffer */
uint8* aConcatBuf;

/** Size of the concatenated buffer */
int32 aConcatSize;

/** Return value from decoder */
int iStatus;

/** Protection mutex */
pthread_mutex_t iMutex = PTHREAD_MUTEX_INITIALIZER;

/** Rotated frame buffer */
uint32* rotatedFrame = NULL;

/** Returned values. */
enum INIT_RETVAL {
    SUCCESS                     = 0,
    SPS_DECODED                 = 1,
    PPS_DECODED                 = 2,

    FAIL                        = -1,
    FAIL_NOT_INITIALIZED        = -2,
    FAIL_TO_DECODE_SPS          = -3,
    FAIL_TO_DECODE_PPS          = -4,
    FAIL_TO_CREATE_OUTPUT       = -5,
    FAIL_TO_DECODE_AVC          = -6,
    FAIL_TO_GET_OUTPUT          = -7,
    FAIL_TO_CREATE_DIMENSIONS   = -8
};

/** Video orientation values */
typedef enum {
    NONE = 0,
    ROTATE_90_CW,
    ROTATE_180,
    ROTATE_90_CCW,
    FLIP_HORIZONTAL,
    ROTATE_90_CW_FLIP_HORIZONTAL,
    ROTATE_180_FLIP_HORIZONTAL,
    ROTATE_90_CCW_FLIP_HORIZONTAL
} VideoEncoderRotateOrientation;

/**
 * Methods
 */
void rotate90CW(uint32* sourceBuffer, uint32* targetBuffer, int width, int height);
void rotate90CCW(uint32* sourceBuffer, uint32* targetBuffer, int width, int height);
void rotate180(uint32* sourceBuffer, uint32* targetBuffer, int width, int height);
void flipHorizontal(uint32* sourceBuffer, uint32* targetBuffer, int width, int height);
void rotate90CWAndFlipHorizontal(uint32* sourceBuffer, uint32* targetBuffer, int width, int height);
void rotate180AndFlipHorizontal(uint32* sourceBuffer, uint32* targetBuffer, int width, int height);
void rotate90CCWAndFlipHorizontal(uint32* sourceBuffer, uint32* targetBuffer, int width, int height);

/* ------------------------------------------------------------------------------------- */
/* Methods                                                                               */
/* ------------------------------------------------------------------------------------- */

/**
 * Method:    InitDecoder
 */
JNIEXPORT jint JNICALL Java_com_orangelabs_rcs_core_ims_protocol_rtp_codec_video_h264_decoder_NativeH264Decoder_InitDecoder
  (JNIEnv * env, jclass clazz) {
    aOutBuffer = NULL;
    decoder = PVAVCDecoder::New();

    memset (&gDecodeData, '\0', sizeof(DecoderData));

    gDecodeData.pvAvDec = decoder;
    decoder->InitAVCDecoder(
    		&cb_AVC_init_SPS,
    		&cb_AVC_DPBAlloc,
    		&cb_AVC_FrameUnbind,
            &cb_AVC_Malloc,
            &cb_AVC_Free,
            &gDecodeData);

    decoderInitialized = (decoder!=NULL)?true:false;
    return (decoder!=NULL)?SUCCESS:FAIL;
}

/**
 * Method:    DeinitDecoder
 */
JNIEXPORT jint JNICALL Java_com_orangelabs_rcs_core_ims_protocol_rtp_codec_video_h264_decoder_NativeH264Decoder_DeinitDecoder
  (JNIEnv * env, jclass clazz) {
    pthread_mutex_lock(&iMutex);

    decoderInitialized = false;
    free(aOutBuffer);
    delete(decoder);

    pthread_mutex_unlock(&iMutex);

    return SUCCESS;
}

/**
 * Method:    DecodeAndConvert
 */
JNIEXPORT jintArray JNICALL Java_com_orangelabs_rcs_core_ims_protocol_rtp_codec_video_h264_decoder_NativeH264Decoder_DecodeAndConvert
  (JNIEnv *env, jclass clazz, jbyteArray h264Frame, int rotateOrientation, jintArray dimensions) {
    pthread_mutex_lock(&iMutex);

    int32 size = 0;
    int32 status;
    jintArray decoded;

    // Check if decoder is initialized
    if(!decoderInitialized) {
        decoded = (env)->NewIntArray(0);
        pthread_mutex_unlock(&iMutex);
        iStatus = FAIL_NOT_INITIALIZED;
        return decoded;
    }

    // Copy jbyteArray to uint8*
    jint len = env->GetArrayLength(h264Frame);
    jbyte data[len];
    env->GetByteArrayRegion(h264Frame, 0, len, data);
    uint8* aInputBuf = (uint8*)malloc(len);
    memcpy(aInputBuf, (uint8*)data, len);
    size = len;

    // Get type of NAL
    u_int8_t type = aInputBuf[0] & 0x1f;
    iStatus = FAIL;
    switch (type) {
        case 7: // SPS
            if ((status = decoder->DecodeSPS(aInputBuf, size)) != AVCDEC_SUCCESS) {
                __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, "Failed decode SPS: %ld", status);
                decoded = (env)->NewIntArray(0);
                pthread_mutex_unlock(&iMutex);
                iStatus = FAIL_TO_DECODE_SPS;
                return decoded;
            }
            iStatus = SPS_DECODED;
            decoded = (env)->NewIntArray(0);
            __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Receive SPS");
            break;

        case 8: // PPS
            if ((status = decoder->DecodePPS(aInputBuf, size)) != AVCDEC_SUCCESS) {
                __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, "Failed to decode PPS: %ld", status);
                decoded = (env)->NewIntArray(0);
                pthread_mutex_unlock(&iMutex);
                iStatus = FAIL_TO_DECODE_PPS;
                return decoded;
            }
            iStatus = PPS_DECODED;
            decoded = (env)->NewIntArray(0);
            __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Receive PPS");
            break;

        case 5: // IDR slice
        case 1: // non-IDR slice
            // Decode the buffer
            status = decoder->DecodeAVCSlice(aInputBuf, &size);
            if (status > AVCDEC_FAIL) {
                iStatus = SUCCESS;
                AVCFrameIO outVid;
                int indexFrame;
                int releaseFrame;
                if (!decoder->GetDecOutput(&indexFrame, &releaseFrame, &outVid)) {
                    decoded = (env)->NewIntArray(0);
                    pthread_mutex_unlock(&iMutex);
                    iStatus = FAIL_TO_GET_OUTPUT;
                    return decoded;
                }

                // Set width and height
                uint32* resultDimensions= (uint32*) malloc(2*sizeof(uint32));
                if (resultDimensions == NULL) {
                    decoded = (env)->NewIntArray(0);
                    pthread_mutex_unlock(&iMutex);
                    iStatus = FAIL_TO_CREATE_DIMENSIONS;
                    return decoded;
                }
                resultDimensions[0] = outVid.pitch;
                resultDimensions[1] = outVid.height;

                if(aOutBuffer == NULL) {
                    aOutBuffer = (uint8*)malloc(resultDimensions[0]*resultDimensions[1]*3/2);
                }
                decoded = (env)->NewIntArray(resultDimensions[0]*resultDimensions[1]);

                // Copy result to YUV array
                memcpy(aOutBuffer, outVid.YCbCr[0], resultDimensions[0] * resultDimensions[1]);
                memcpy(aOutBuffer + (resultDimensions[0] * resultDimensions[1]), outVid.YCbCr[1], (resultDimensions[0] * resultDimensions[1]) / 4);
                memcpy(aOutBuffer + (resultDimensions[0] * resultDimensions[1]) + ((resultDimensions[0] * resultDimensions[1]) / 4), outVid.YCbCr[2], (resultDimensions[0] * resultDimensions[1]) / 4);

                // Create the output buffer
                uint32* resultBuffer = (uint32*) malloc(resultDimensions[0] * resultDimensions[1] * sizeof(uint32));
                if (resultBuffer == NULL) {
                    decoded = (env)->NewIntArray(0);
                    pthread_mutex_unlock(&iMutex);
                    iStatus = FAIL_TO_CREATE_OUTPUT;
                    return decoded;
                }

                // Convert to RGB
                convert(resultDimensions[0], resultDimensions[1], aOutBuffer, resultBuffer);

                // Frame rotation
                bool freeResultBuffer = true;
                VideoEncoderRotateOrientation videoEncoderRotateOrientation = (VideoEncoderRotateOrientation) rotateOrientation;
                if (videoEncoderRotateOrientation != NONE) {
                    // Alloc rotated frame buffer
                    rotatedFrame = (uint32*)malloc(outVid.pitch*outVid.height*sizeof(uint32));
                    switch (videoEncoderRotateOrientation) {
                        case ROTATE_90_CW:
                            rotate90CW(resultBuffer, rotatedFrame, outVid.pitch, outVid.height);
                            break;
                        case ROTATE_180:
                            rotate180(resultBuffer, rotatedFrame, outVid.pitch, outVid.height);
                            break;
                        case ROTATE_90_CCW:
                            rotate90CCW(resultBuffer, rotatedFrame, outVid.pitch, outVid.height);
                            break;
                        case FLIP_HORIZONTAL:
                            flipHorizontal(resultBuffer, rotatedFrame, outVid.pitch, outVid.height);
                            break;
                        case ROTATE_90_CW_FLIP_HORIZONTAL:
                            rotate90CWAndFlipHorizontal(resultBuffer, rotatedFrame, outVid.pitch, outVid.height);
                            break;
                        case ROTATE_180_FLIP_HORIZONTAL:
                            rotate180AndFlipHorizontal(resultBuffer, rotatedFrame, outVid.pitch, outVid.height);
                            break;
                        case ROTATE_90_CCW_FLIP_HORIZONTAL:
                            rotate90CCWAndFlipHorizontal(resultBuffer, rotatedFrame, outVid.pitch, outVid.height);
                            break;
                    }

                    // Free original frame
                    free(resultBuffer);

                    // Update aOutBuffer variable with the rotated data
                    resultBuffer = rotatedFrame;

                    // Do not free result buffer because its the rotated frame buffer
                    freeResultBuffer = false;

                    if (videoEncoderRotateOrientation != ROTATE_180
                            && videoEncoderRotateOrientation != ROTATE_180_FLIP_HORIZONTAL
                            && videoEncoderRotateOrientation != FLIP_HORIZONTAL) {
                        // Switch width and height values
                        resultDimensions[0] = outVid.height; // width
                        resultDimensions[1] = outVid.pitch; // height
                    } else {
                        resultDimensions[0] = outVid.pitch; // width
                        resultDimensions[1] = outVid.height; // height
                    }
                }

                // Return Bitmap Dimensions
                (env)->SetIntArrayRegion(dimensions, 0, 2, (const jint*)resultDimensions);
                free(resultDimensions);

                // Return Bitmap image
                (env)->SetIntArrayRegion(decoded, 0, outVid.pitch*outVid.height, (const jint*)resultBuffer);
                if (freeResultBuffer) {
                    free(resultBuffer);
                    resultBuffer = NULL;
                }
                if (rotatedFrame != NULL) {
                    free(rotatedFrame);
                    rotatedFrame = NULL;
                }
            } else {
                __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, "Decoder error %ld", status);
                decoded = (env)->NewIntArray(0);
                pthread_mutex_unlock(&iMutex);
                iStatus = FAIL_TO_DECODE_AVC;
                return decoded;
            }
            break;
    }
    free(aInputBuf);
    pthread_mutex_unlock(&iMutex);
    return decoded;
}

/*
 * Method:    getLastDecodeStatus
 */
JNIEXPORT jint JNICALL Java_com_orangelabs_rcs_core_ims_protocol_rtp_codec_video_h264_decoder_NativeH264Decoder_getLastDecodeStatus
  (JNIEnv *env, jclass clazz){
    return iStatus;
}

/**
 * This is called by the VM when the shared library is first loaded.
 */
jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env = NULL;
    jint result = -1;
    if (vm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {
        goto bail;
    }
    /* success -- return valid version number */
    result = JNI_VERSION_1_4;
bail:
    return result;
}

/**
 * Method:    rotate90CW
 */
void rotate90CW(uint32* sourceBuffer, uint32* targetBuffer, int width, int height) {

    int i, j;
    int rotValueFrom = 0;
    int rotValueTo = 0;

    for (i = 0; i < width; i++) {
        for (j = 0; j < height; j++) {
            rotValueFrom = ((height - j - 1) * width + i);
            rotValueTo = (i * height + j);
            targetBuffer[rotValueTo] = sourceBuffer[rotValueFrom];
        }
    }
}

/**
 * Method:    rotate90CW
 */
void rotate90CCW(uint32* sourceBuffer, uint32* targetBuffer, int width, int height) {
    int i, j;
    int rotValueFrom = 0;
    int rotValueTo = 0;

    for (i = 0; i < width; i++) {
        for (j = 0; j < height; j++) {
            rotValueFrom = width * j + i;
            rotValueTo = height * ( width - 1 - i) + j;
            targetBuffer[rotValueTo] = sourceBuffer[rotValueFrom];
        }
    }
}

/**
 * Method:    rotate180
 */
void rotate180(uint32* sourceBuffer, uint32* targetBuffer, int width, int height) {
    int h;
    int pos1 = 0;
    int pos2 = 0;

    if ((height % 2) == 1) {
        h = (height / 2) - 1;
        int j = ((height - 1) / 2);

        for (int i = 0; i < (width / 2); i++) {
            pos1 = j * width + i;
            pos2 = j * width + (width - i - 1);
            targetBuffer[pos1] = sourceBuffer[pos2];
            targetBuffer[pos2] = sourceBuffer[pos1];
        }
    } else {
        h = (height / 2) - 1;
    }

    for (int i = 0; i < width; i++) {
        for (int j = 0; j <= h; j++) {
            pos1 = (j * width) + i;
            pos2 = (height - j - 1) * width + (width - i - 1);
            targetBuffer[pos1] = sourceBuffer[pos2];
            targetBuffer[pos2] = sourceBuffer[pos1];
        }
    }
}


/**
 * Method:    flipHorizontal
 */
void flipHorizontal(uint32* sourceBuffer, uint32* targetBuffer, int width, int height) {
    int pos1 = 0;
    int pos2 = 0;

    for (int i = 0; i < width / 2; i++) {
        for (int j = 0; j < height; j++) {
            pos1 = (j * width) + i;
            pos2 = j * width + (width - i - 1);
            targetBuffer[pos1] = sourceBuffer[pos2];
            targetBuffer[pos2] = sourceBuffer[pos1];
        }
    }
}

/**
 * Method:    rotate90CWAndFlipHorizontal
 */
void rotate90CWAndFlipHorizontal(uint32* sourceBuffer, uint32* targetBuffer, int width, int height) {
    uint32* aux = (uint32*)malloc(width*height*sizeof(uint32));
    rotate90CW(sourceBuffer, aux, width, height);
    flipHorizontal(aux, targetBuffer, height, width);
    free(aux);
}

/**
 * Method:    rotate180AndFlipHorizontal
 */
void rotate180AndFlipHorizontal(uint32* sourceBuffer, uint32* targetBuffer, int width, int height) {
    uint32* aux = (uint32*)malloc(width*height*sizeof(uint32));
    rotate180(sourceBuffer, aux, width, height);
    flipHorizontal(aux, targetBuffer, width, height);
    free(aux);
}

/**
 * Method:    rotate90CCWAndFlipHorizontal
 */
void rotate90CCWAndFlipHorizontal(uint32* sourceBuffer, uint32* targetBuffer, int width, int height) {
    uint32* aux = (uint32*)malloc(width*height*sizeof(uint32));
    rotate90CCW(sourceBuffer, aux, width, height);
    flipHorizontal(aux, targetBuffer, height, width);
    free(aux);
}


