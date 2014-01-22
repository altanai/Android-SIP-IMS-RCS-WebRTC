/*
 * yuv2rgb.cpp
 *
 *  Created on: 29 juil. 2009
 *      Author: rglt1266
 */
#include <stdio.h>
#include "yuv2rgb.h"
#include <time.h>
#include "android/log.h"


void convert (int width,int height, uint8 *in,uint32 *out){

#ifdef MEASURE
	clock_t start, end;
	int cpu_time_used;

	start = clock();
	{
#endif
    uint8 *pY;
    uint8 *pU;
    uint8 *pV;

    /* Init */
    pY = in;
    pU = in + (width*height);
    pV = pU + (width*height/4);

    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            /* YUV values uint */
            int Y = *((pY) + (i*width) + j);
            int U = *(pU + (j/2) + ((width/2)*(i/2)));
            int V = *(pV + (j/2) + ((width/2)*(i/2)));
            /* RBG values */
            int Cr = V - 128;
            int Cb = U - 128;
            int R = Y + ((359*Cr)>>8);
            int G = Y - ((88*Cb+183*Cr)>>8);
            int B = Y + ((454*Cb)>>8);
            if (R > 255) {
                R=255;
            } else if (R < 0) {
                R = 0;
            }
            if (G > 255) {
                G = 255;
            } else if (G < 0) {
                G = 0;
            }
            if (B > 255) {
                B = 255;
            } else if (B < 0) {
                B = 0;
            }

            /* Write data */
            out[((i*width) + j)]=((((R & 0xFF) << 16) | ((G & 0xFF) << 8) | (B & 0xFF))& 0xFFFFFFFF);
        }
    }

#ifdef MEASURE
	}
	end = clock();
	cpu_time_used =  (end - start);
	__android_log_print(ANDROID_LOG_DEBUG, "CLOCK", "cpu_time_used %ld",cpu_time_used );
#endif
}
