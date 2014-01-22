/*******************************************************************************
 * Software Name : RCS IMS Stack
 *
 * Copyright (C) 2010 France Telecom S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

#define LOG_TAG "NativeEnc"
#include "NativeH264Encoder.h"
#include "pvavcencoder.h"
#include "android/log.h"

/* ------------------------------------------------------------------------------------- */
/* Global variables                                                                      */
/* ------------------------------------------------------------------------------------- */

/** Native encoder */
PVAVCEncoder *encoder;

/** Global size of one frame */
int iFrameSize;

/** Format of the input data */
TAVCEIInputFormat *iInputFormat;

/** Encoder settings */
TAVCEIEncodeParam *iEncodeParam;

/** Input buffer */
TAVCEIInputData *iInData;

/** Output buffer */
TAVCEIOutputData *iOutData;

/** Return value from encoder */
TAVCEI_RETVAL iStatus;

/** Protection mutex */
pthread_mutex_t iMutex = PTHREAD_MUTEX_INITIALIZER;

/** Source buffer */
uint8* iSrcBuffer = NULL;
int iSrcBufferLen = 0;

/** Destination buffer */
uint8* iDestBuffer = NULL;
int iDestBufferLen = 0;

/** Returned values. */
enum INIT_RETVAL {
    SUCCESS             = EAVCEI_SUCCESS,
    FAIL                = -1,
    GETPARAMS_FAIL      = -2,
    FRAMEWIDTH_FAIL     = -3,
    FRAMEHEIGHT_FAIL    = -4,
    FRAMERATE_FAIL      = -5,
    ENCODER_CREATE_FAIL = -6,
    MALLOC_FAIL         = -7,
    GETFIELD_FAIL       = -8
};

/** Resizing method */
void resizeFrame(uint8* srcBuffer, uint8* dstBuffer, int srcWidth, int srcHeight, int dstWidth, int dstHeight);

/** Scaling method */
void scaleFrame(uint8* sourceBuffer, uint8* destBuffer, int destWidth, int destHeight, float scalingFactor);

/** Mirroring method */
void mirrorFrame(uint8* sourceBuffer, uint8* destBuffer, int width, int height);

/* ------------------------------------------------------------------------------------- */
/* Methods                                                                               */
/* ------------------------------------------------------------------------------------- */

/*
 * Method:    InitEncoder
 */
JNIEXPORT jint JNICALL Java_com_orangelabs_rcs_core_ims_protocol_rtp_codec_video_h264_encoder_NativeH264Encoder_InitEncoder
  (JNIEnv *env, jclass iclass, jobject params)
{
    jclass objClass = (env)->GetObjectClass(params);
    if (objClass == NULL) {
        return GETPARAMS_FAIL;
    }

    jfieldID iFrameWidth = (env)->GetFieldID(objClass,"frameWidth","I");
    if (iFrameWidth == NULL) {
        return FRAMEWIDTH_FAIL;
    }
    int srcWidth = (env)->GetIntField(params,iFrameWidth);

    jfieldID iFrameHeight = (env)->GetFieldID(objClass,"frameHeight","I");
    if (iFrameHeight == NULL) {
        return FRAMEHEIGHT_FAIL;
    }
    int srcHeight = (env)->GetIntField(params,iFrameHeight);

    jfieldID iFrameRate = (env)->GetFieldID(objClass,"frameRate","F");
    if (iFrameRate == NULL) {
        return FRAMERATE_FAIL;
    }
    int srcFrameRate = (env)->GetFloatField(params,iFrameRate);
    iFrameSize = (srcWidth*srcHeight*3)>>1;

    encoder = PVAVCEncoder::New();
    if (encoder==NULL) {
        return ENCODER_CREATE_FAIL;
    }

    iInputFormat = (TAVCEIInputFormat*)malloc(sizeof(TAVCEIInputFormat));
    if (iInputFormat==NULL) {
        delete(encoder);
        return MALLOC_FAIL;
    }

    iEncodeParam = (TAVCEIEncodeParam*)malloc(sizeof(TAVCEIEncodeParam));
    if (iEncodeParam==NULL) {
      free(iInputFormat);
      delete(encoder);
      return MALLOC_FAIL;
    }

    iInData = (TAVCEIInputData*)malloc(sizeof(TAVCEIInputData));
    if(iInData==NULL){
      free(iEncodeParam);
      free(iInputFormat);
      delete(encoder);
      return MALLOC_FAIL;
    }

    iOutData = (TAVCEIOutputData*)malloc(sizeof(TAVCEIOutputData));
    if(iOutData==NULL){
      free(iInData);
      free(iEncodeParam);
      free(iInputFormat);
      delete(encoder);
      return MALLOC_FAIL;
    }
    iOutData->iBitstream = (uint8*)malloc(iFrameSize);
    iOutData->iBitstreamSize = iFrameSize;

    // Set Encoder params
    iInputFormat->iFrameWidth = srcWidth;
    iInputFormat->iFrameHeight = srcHeight;
    iInputFormat->iFrameRate = (OsclFloat)(srcFrameRate);


    jfieldID iFrameOrientation = (env)->GetFieldID(objClass,"frameOrientation","I");
    if (iFrameOrientation == NULL) return GETFIELD_FAIL;
    iInputFormat->iFrameOrientation = (env)->GetIntField(params,iFrameOrientation);

    jfieldID iVideoFormat = (env)->GetFieldID(objClass,"videoFormat","I");
    if (iVideoFormat == NULL) return GETFIELD_FAIL;
    iInputFormat->iVideoFormat = (TAVCEIVideoFormat)(env)->GetIntField(params,iVideoFormat);

    jfieldID iEncodeID = (env)->GetFieldID(objClass,"encodeID","I");
    if (iEncodeID == NULL) return GETFIELD_FAIL;
    iEncodeParam->iEncodeID = (env)->GetIntField(params,iEncodeID);

    jfieldID iProfile = (env)->GetFieldID(objClass,"profile","I");
    if (iProfile == NULL) return GETFIELD_FAIL;
    iEncodeParam->iProfile = (TAVCEIProfile)(env)->GetIntField(params,iProfile);

    jfieldID iLevel = (env)->GetFieldID(objClass,"level","I");
    if (iLevel == NULL) return GETFIELD_FAIL;
    iEncodeParam->iLevel = (TAVCEILevel)(env)->GetIntField(params,iLevel);

    jfieldID iNumLayer = (env)->GetFieldID(objClass,"numLayer","I");
    if (iNumLayer == NULL) return GETFIELD_FAIL;
    iEncodeParam->iNumLayer = (env)->GetIntField(params,iNumLayer);

    iEncodeParam->iFrameWidth[0] = iInputFormat->iFrameWidth;
    iEncodeParam->iFrameHeight[0] = iInputFormat->iFrameHeight;

    jfieldID iBitRate = (env)->GetFieldID(objClass,"bitRate","I");
    if (iBitRate == NULL) return GETFIELD_FAIL;
    iEncodeParam->iBitRate[0] = (env)->GetIntField(params,iBitRate);

    iEncodeParam->iFrameRate[0] = (OsclFloat)iInputFormat->iFrameRate;

    jfieldID iEncMode = (env)->GetFieldID(objClass,"encMode","I");
    if (iEncMode == NULL) return GETFIELD_FAIL;
    iEncodeParam->iEncMode = (TAVCEIEncodingMode)(env)->GetIntField(params,iEncMode);

    jfieldID iOutOfBandParamSet = (env)->GetFieldID(objClass,"outOfBandParamSet","Z");
    if (iOutOfBandParamSet == NULL) return GETFIELD_FAIL;
    iEncodeParam->iOutOfBandParamSet = (env)->GetBooleanField(params,iOutOfBandParamSet);

    jfieldID iOutputFormat = (env)->GetFieldID(objClass,"outputFormat","I");
    if (iOutputFormat == NULL) return GETFIELD_FAIL;
    iEncodeParam->iOutputFormat = (TAVCEIOutputFormat)(env)->GetIntField(params,iOutputFormat);

    jfieldID iPacketSize = (env)->GetFieldID(objClass,"packetSize","I");
    if (iPacketSize == NULL) return GETFIELD_FAIL;
    iEncodeParam->iPacketSize = (env)->GetIntField(params,iPacketSize);

    jfieldID iRateControlType = (env)->GetFieldID(objClass,"rateControlType","I");
    if (iRateControlType == NULL) return GETFIELD_FAIL;
    iEncodeParam->iRateControlType = (TAVCEIRateControlType)(env)->GetIntField(params,iRateControlType);

    jfieldID iBufferDelay = (env)->GetFieldID(objClass,"bufferDelay","F");
    if (iBufferDelay == NULL) return GETFIELD_FAIL;
    iEncodeParam->iBufferDelay = (OsclFloat)(env)->GetFloatField(params,iBufferDelay);

    jfieldID iIquant = (env)->GetFieldID(objClass,"iquant","I");
    if (iIquant == NULL) return GETFIELD_FAIL;
    iEncodeParam->iIquant[0] = (env)->GetIntField(params,iIquant);

    jfieldID iPquant = (env)->GetFieldID(objClass,"pquant","I");
    if (iPquant == NULL) return GETFIELD_FAIL;
    iEncodeParam->iPquant[0] = (env)->GetIntField(params,iPquant);

    jfieldID iBquant = (env)->GetFieldID(objClass,"bquant","I");
    if (iBquant == NULL) return GETFIELD_FAIL;
    iEncodeParam->iBquant[0] = (env)->GetIntField(params,iBquant);

    jfieldID iSceneDetection = (env)->GetFieldID(objClass,"sceneDetection","Z");
    if (iSceneDetection == NULL) return GETFIELD_FAIL;
    iEncodeParam->iSceneDetection = (env)->GetBooleanField(params,iSceneDetection);

    jfieldID iIFrameInterval = (env)->GetFieldID(objClass,"iFrameInterval","I");
    if (iIFrameInterval == NULL) return GETFIELD_FAIL;
    iEncodeParam->iIFrameInterval = (env)->GetIntField(params,iIFrameInterval);

    jfieldID iNumIntraMBRefresh = (env)->GetFieldID(objClass,"numIntraMBRefresh","I");
    if (iNumIntraMBRefresh == NULL) return GETFIELD_FAIL;
    iEncodeParam->iNumIntraMBRefresh = (env)->GetIntField(params,iNumIntraMBRefresh);

    jfieldID iFSIBuffLength = (env)->GetFieldID(objClass,"fSIBuffLength","I");
    if (iFSIBuffLength == NULL) return GETFIELD_FAIL;
    iEncodeParam->iFSIBuffLength = (env)->GetIntField(params,iFSIBuffLength);

    if(iEncodeParam->iFSIBuffLength > 0) {
        jfieldID iFSIBuff = (env)->GetFieldID(objClass,"fSIBuff","[B");
        if (iFSIBuff == NULL) return GETFIELD_FAIL;

        jbyteArray iFSIBuffByteArray = (jbyteArray)(env)->GetObjectField(params,iFSIBuff);
        if (iFSIBuffByteArray == NULL) return GETFIELD_FAIL;

        iEncodeParam->iFSIBuff = (uint8*) malloc(iEncodeParam->iFSIBuffLength * sizeof(jbyte));
        jbyte* originaliFSIBuff = env->GetByteArrayElements(iFSIBuffByteArray, 0);
        memcpy(iEncodeParam->iFSIBuff, originaliFSIBuff, iEncodeParam->iFSIBuffLength);
        if (originaliFSIBuff)
            env->ReleaseByteArrayElements(iFSIBuffByteArray, originaliFSIBuff, JNI_ABORT);
    } else {
        iEncodeParam->iFSIBuff = NULL;
    }

    // Init encoder
    jint result = encoder->Initialize(iInputFormat,iEncodeParam);
    __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Init encoder %d", result);
    return result;
}

/*
 * Method:    GetNAL
 */
JNIEXPORT jbyteArray JNICALL Java_com_orangelabs_rcs_core_ims_protocol_rtp_codec_video_h264_encoder_NativeH264Encoder_getNAL
  (JNIEnv *env, jclass iclass)
{
    jbyteArray result;
    int32 NalSize = 30;
    int NalType = 0;
    uint8* NalBuff = (uint8*)malloc(NalSize*sizeof(uint8));
    if (encoder->GetParameterSet(NalBuff,&NalSize,&NalType)== EAVCEI_SUCCESS) {
        result = (env)->NewByteArray(NalSize);
        (env)->SetByteArrayRegion(result, 0, NalSize, (jbyte*)NalBuff);
    } else {
        __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, "NAL fail with code: %d", iStatus);
        result = (env)->NewByteArray(0);
    }
    free(NalBuff);
    return result;
}

/*
 * Method:    EncodeFrame
 */
JNIEXPORT jbyteArray JNICALL Java_com_orangelabs_rcs_core_ims_protocol_rtp_codec_video_h264_encoder_NativeH264Encoder_ResizeAndEncodeFrame
  (JNIEnv *env, jclass iclass, jbyteArray frame, jlong timestamp, jboolean mirroring, jint srcWidth, jint srcHeight)
{
    pthread_mutex_lock(&iMutex);

    // Check frame size
    jbyteArray result;
    jint frameLength = env->GetArrayLength(frame);
    if (frameLength > 1.5 * srcWidth * srcHeight) {
        __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Encode fail - frame size not good");
        result = (env)->NewByteArray(0);
        pthread_mutex_unlock(&iMutex);
        return result;
    }
    if (frameLength > iSrcBufferLen) {
        iSrcBuffer = (uint8*)realloc(iSrcBuffer, frameLength);
        iSrcBufferLen = frameLength;
    }
    env->GetByteArrayRegion (frame, (jint)0, (jint)frameLength, (jbyte*)iSrcBuffer);

    // Resize the frame
    if ((srcWidth != iInputFormat->iFrameWidth) || (srcHeight != iInputFormat->iFrameHeight)) {
        float scaleWidth = (float)iInputFormat->iFrameWidth / srcWidth;
        float scaleHeight = (float)iInputFormat->iFrameHeight / srcHeight;
        frameLength = frameLength * scaleWidth * scaleHeight;
        if (frameLength > iDestBufferLen) {
            iDestBuffer = (uint8*)realloc(iDestBuffer, frameLength);
            iDestBufferLen = frameLength;
        }
        resizeFrame(iSrcBuffer, iDestBuffer, srcWidth, srcHeight, iInputFormat->iFrameWidth, iInputFormat->iFrameHeight);
        if (frameLength > iSrcBufferLen) {
            iSrcBuffer = (uint8*)realloc(iSrcBuffer, frameLength);
            iSrcBufferLen = frameLength;
        }
        memcpy(iSrcBuffer, iDestBuffer, frameLength);
    }

    // Mirror the frame
    if (mirroring == JNI_TRUE) {
        if (frameLength > iDestBufferLen) {
            iDestBuffer = (uint8*)realloc(iDestBuffer, frameLength);
            iDestBufferLen = frameLength;
        }

        mirrorFrame(iSrcBuffer, iDestBuffer, iInputFormat->iFrameWidth, iInputFormat->iFrameHeight);
        memcpy(iSrcBuffer, iDestBuffer, frameLength);
    }

    iInData->iSource = iSrcBuffer;
    iInData->iTimeStamp = timestamp;
    iStatus = encoder->Encode(iInData);
    if(iStatus != EAVCEI_SUCCESS){
        __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Encode fail with code: %d", iStatus);
        result=(env)->NewByteArray(0);
        pthread_mutex_unlock(&iMutex);
        return result;
    }

    int remainingByte = 0;
    iOutData->iBitstreamSize = iFrameSize;
    iStatus = encoder->GetOutput(iOutData,&remainingByte);
    if(iStatus != EAVCEI_SUCCESS){
        __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Get output fail with code: %d", iStatus);
        result=(env)->NewByteArray(0);
        pthread_mutex_unlock(&iMutex);
        return result;
    }

    // Copy aOutBuffer into result
    result=(env)->NewByteArray(iOutData->iBitstreamSize);
    (env)->SetByteArrayRegion(result, 0, iOutData->iBitstreamSize, (jbyte*)iOutData->iBitstream);
    pthread_mutex_unlock(&iMutex);
    return result;
}

JNIEXPORT jbyteArray JNICALL Java_com_orangelabs_rcs_core_ims_protocol_rtp_codec_video_h264_encoder_NativeH264Encoder_EncodeFrame
  (JNIEnv *env, jclass iclass, jbyteArray frame, jlong timestamp, jboolean mirroring, jfloat scalingFactor)
{
    pthread_mutex_lock(&iMutex);

    jbyteArray result;
    jint frameLength = env->GetArrayLength(frame);
    if (frameLength > iSrcBufferLen) {
        iSrcBuffer = (uint8*)realloc(iSrcBuffer, frameLength);
        iSrcBufferLen = frameLength;
    }
    env->GetByteArrayRegion (frame, (jint)0, (jint)frameLength, (jbyte*)iSrcBuffer);

    // Scale the frame
    if (scalingFactor != 1) {
        frameLength = frameLength * scalingFactor * scalingFactor;
        if (frameLength > iDestBufferLen) {
            iDestBuffer = (uint8*)realloc(iDestBuffer, frameLength);
            iDestBufferLen = frameLength;
        }
        scaleFrame(iSrcBuffer, iDestBuffer, iInputFormat->iFrameWidth, iInputFormat->iFrameHeight, scalingFactor);
        if (frameLength > iSrcBufferLen) {
            iSrcBuffer = (uint8*)realloc(iSrcBuffer, frameLength);
            iSrcBufferLen = frameLength;
        }
        memcpy(iSrcBuffer, iDestBuffer, frameLength);
    }

    // Mirror the frame
    if (mirroring == JNI_TRUE) {
        if (frameLength > iDestBufferLen) {
            iDestBuffer = (uint8*)realloc(iDestBuffer, frameLength);
            iDestBufferLen = frameLength;
        }

        mirrorFrame(iSrcBuffer, iDestBuffer, iInputFormat->iFrameWidth, iInputFormat->iFrameHeight);
        memcpy(iSrcBuffer, iDestBuffer, frameLength);
    }

    iInData->iSource = iSrcBuffer;
    iInData->iTimeStamp = timestamp;
    iStatus = encoder->Encode(iInData);
    if(iStatus != EAVCEI_SUCCESS){
        __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Encode fail with code: %d", iStatus);
        result=(env)->NewByteArray(0);
        pthread_mutex_unlock(&iMutex);
        return result;
    }

    int remainingByte = 0;
    iOutData->iBitstreamSize = iFrameSize;
    iStatus = encoder->GetOutput(iOutData,&remainingByte);
    if(iStatus != EAVCEI_SUCCESS){
        __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Get output fail with code: %d", iStatus);
        result=(env)->NewByteArray(0);
        pthread_mutex_unlock(&iMutex);
        return result;
    }

    // Copy aOutBuffer into result
    result=(env)->NewByteArray(iOutData->iBitstreamSize);
    (env)->SetByteArrayRegion(result, 0, iOutData->iBitstreamSize, (jbyte*)iOutData->iBitstream);
    pthread_mutex_unlock(&iMutex);
    return result;
}

/*
 * Method:    getLastEncodeStatus
 */
JNIEXPORT jint JNICALL Java_com_orangelabs_rcs_core_ims_protocol_rtp_codec_video_h264_encoder_NativeH264Encoder_getLastEncodeStatus
  (JNIEnv *env, jclass clazz){
    return iStatus;
}

/*
 * Method:    DeinitEncoder
 */
JNIEXPORT jint JNICALL Java_com_orangelabs_rcs_core_ims_protocol_rtp_codec_video_h264_encoder_NativeH264Encoder_DeinitEncoder
  (JNIEnv *env, jclass clazz){
    pthread_mutex_lock(&iMutex);
    delete(encoder);
    free(iInputFormat);
    if (iSrcBuffer != NULL) {
        free(iSrcBuffer);
        iSrcBuffer = NULL;
        iSrcBufferLen = 0;
    }
    if (iDestBuffer != NULL) {
        free(iDestBuffer);
        iDestBuffer = NULL;
        iDestBufferLen = 0;
    }
    free(iEncodeParam);
    free(iInData);
    free(iOutData);
    pthread_mutex_unlock(&iMutex);
    return 1;
}

/*
 * This is called by the VM when the shared library is first loaded.
 */
jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env = NULL;
    jint result = -1;

    if (vm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {
        goto bail;
    }

    // success -- return valid version number
    result = JNI_VERSION_1_4;

bail:
    return result;
}

/*
 * Resize method
 */
void resizeFrame(uint8* srcBuffer, uint8* dstBuffer, int srcWidth, int srcHeight, int dstWidth, int dstHeight) {
    float scaleW = (float)dstWidth / srcWidth;
    float scaleH = (float)dstHeight / srcHeight;
    int srcBegin = srcWidth * srcHeight;
    int dstBegin = dstWidth * dstHeight;
    int i, j, i1, j1;
    int srcOffset, dstOffset;

    // Y value
    for (i = 0; i < dstHeight; i++) {
        for (j = 0; j < dstWidth; j++) {
            i1 = i / scaleH;
            j1 = j / scaleW;
            dstOffset = i * dstWidth + j;
            srcOffset = i1 * srcWidth + j1;
            if (srcOffset < iSrcBufferLen) {
                dstBuffer[dstOffset] = srcBuffer[srcOffset];
            } else {
                dstBuffer[dstOffset] = 0;
            }
        }
    }

    // UV value
    for (i = 0; i < dstHeight / 2; i++) {
        for (j = 0; j < dstWidth / 2; j++) {
            i1 = i / scaleH;
            j1 = j / scaleW;
            dstOffset = dstBegin + i * dstWidth + 2 * j;
            srcOffset = srcBegin + i1 * srcWidth + 2 * j1;

            if (srcOffset + 1 < iSrcBufferLen) {
                // U & V value
                dstBuffer[dstOffset] = srcBuffer[srcOffset];
                dstBuffer[dstOffset + 1] = srcBuffer[srcOffset + 1];
            } else {
                dstBuffer[dstOffset] = 0;
                dstBuffer[dstOffset + 1] = 0;
            }
        }
    }
}

/*
 * Scale method
 * @author Deutsche Telekom
 */
void scaleFrame(uint8* sourceBuffer, uint8* destBuffer, int destWidth, int destHeight, float scalingFactor) {
    int i, j;

    int sourceWidth = destWidth / scalingFactor;
    int sourceHeight = destHeight / scalingFactor;

    int uvBeginSource = sourceWidth * sourceHeight;
    int uvBeginDest = destWidth * destHeight;

    int offset1 = 2 * destWidth;
    int offset2 = 4 * destWidth;

    uint8 pixel1;
    uint8 pixel2;
    uint8 pixel3;
    uint8 pixel4;

    // Y value
    for (i = 0; i < destHeight; i++) {
        for (j = 0; j < destWidth; j++) {
            pixel1 = sourceBuffer[i * offset2 + 2 * j];
            pixel2 = sourceBuffer[i * offset2 + 2 * j + 1];
            pixel3 = sourceBuffer[i * offset2 + offset1 + 2 * j];
            pixel4 = sourceBuffer[i * offset2 + offset1 + 2 * j + 1];

            // Calculate average of the 4 pixels
            destBuffer[i * destWidth + j] = (uint8)(((int)pixel1 + pixel2 + pixel3 + pixel4) / 4);
        }
    }

    // UV value
    for (i = 0; i < destHeight / 2; i++) {
        for (j = 0; j < destWidth / 2; j++) {
            // U value
            pixel1 = sourceBuffer[uvBeginSource + i * offset2 + 4 * j];
            pixel2 = sourceBuffer[uvBeginSource + i * offset2 + 4 * j + 2];
            pixel3 = sourceBuffer[uvBeginSource + i * offset2 + offset1 + 4 * j];
            pixel4 = sourceBuffer[uvBeginSource + i * offset2 + offset1 + 4 * j + 2];

            // Calculate average of the 4 pixels
            destBuffer[uvBeginDest + i * destWidth + 2 * j] = (uint8)(((int)pixel1 + pixel2 + pixel3 + pixel4) / 4);

            // V value
            pixel1 = sourceBuffer[uvBeginSource + i * offset2 + 4 * j + 1];
            pixel2 = sourceBuffer[uvBeginSource + i * offset2 + 4 * j + 3];
            pixel3 = sourceBuffer[uvBeginSource + i * offset2 + offset1 + 4 * j + 1];
            pixel4 = sourceBuffer[uvBeginSource + i * offset2 + offset1 + 4 * j + 3];

            // Calculate average of the 4 pixels
            destBuffer[uvBeginDest + i * destWidth + 2 * j + 1] = (uint8)(((int)pixel1 + pixel2 + pixel3 + pixel4) / 4);
        }
    }
}

/*
 * Mirroring method (horizontal and vertival)
 * @author Deutsche Telekom
 */
void mirrorFrame(uint8* sourceBuffer, uint8* destBuffer, int width, int height) {
    int i, j;
    int uvBegin = height * width;

    int mirYValueFrom = 0;
    int mirYValueTo = 0;
    int rotUValueFrom = 0;
    int rotUValueTo = 0;
    int rotVValueFrom = 0;
    int rotVValueTo = 0;

    int halfHeight = height >> 1;
    int halfWidth = width >> 1;

    // Y value
    for (i = 0; i < height; i++) {
        for (j = 0; j < width; j++) {
            mirYValueFrom = (height - i) * width - j - 1;
            mirYValueTo = i * width + j;

            destBuffer[mirYValueTo] = sourceBuffer[mirYValueFrom];

            // UV value
            if ((i < halfHeight) && (j < halfWidth)) {
                rotUValueFrom = uvBegin + (halfHeight - i) * width - 2 * j - 2;
                rotUValueTo = uvBegin + i * width + 2 * j;
                rotVValueFrom = uvBegin + (halfHeight - i) * width - 2 * j - 1;
                rotVValueTo = uvBegin + i * width + 2 * j + 1;

                // U value
                destBuffer[rotUValueTo] = sourceBuffer[rotUValueFrom];
                // V value
                destBuffer[rotVValueTo] = sourceBuffer[rotVValueFrom];
            }
        }
    }
}

