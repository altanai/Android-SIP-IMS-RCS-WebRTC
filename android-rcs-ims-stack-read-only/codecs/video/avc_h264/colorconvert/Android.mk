LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_MODULE := libcolorconvert

LOCAL_SRC_FILES := \
	src/ccrgb16toyuv420.cpp \
 	src/ccrgb24torgb16.cpp \
 	src/ccyuv422toyuv420.cpp \
 	src/cczoomrotation12.cpp \
 	src/cczoomrotation16.cpp \
 	src/cczoomrotation24.cpp \
 	src/cczoomrotation32.cpp \
 	src/cczoomrotationbase.cpp \
 	src/cpvvideoblend.cpp \
 	src/ccrgb24toyuv420.cpp \
 	src/ccrgb12toyuv420.cpp \
 	src/ccyuv420semiplnrtoyuv420plnr.cpp \
 	src/ccyuv420toyuv420semi.cpp \
	../oscl/osclerror/src/oscl_error.cpp\
	../oscl/osclerror/src/oscl_error_trapcleanup.cpp\
	../oscl/osclerror/src/oscl_error_imp_jumps.cpp\
	../oscl/osclbase/src/oscl_tls.cpp\
	../oscl/osclbase/src/pvlogger.cpp\
	../oscl/osclbase/src/oscl_base.cpp\

LOCAL_CFLAGS := -DFALSE=false -DCCROTATE

# Temporary workaround
ifeq ($(strip $(USE_SHOLES_PROPERTY)),true)
LOCAL_CFLAGS += -DSHOLES_PROPERTY_OVERRIDES
endif

LOCAL_STATIC_LIBRARIES := 

LOCAL_SHARED_LIBRARIES := 

LOCAL_C_INCLUDES := \
	$(JNI_H_INCLUDE)\
	$(LOCAL_PATH)/src \
 	$(LOCAL_PATH)/include \
	$(AVC_ROOT)/oscl/osclbase/src \
	$(AVC_ROOT)/oscl/osclerror/src \
	$(AVC_ROOT)/oscl/osclmemory/src\
	$(AVC_ROOT)/oscl/osclutil/src\
	$(AVC_ROOT)/oscl/config/shared \
	$(AVC_ROOT)/oscl/config/android \
	$(AVC_ROOT)/common/include \
	$(AVC_ROOT)/colorconvert/include  

LOCAL_COPY_HEADERS_TO :=

LOCAL_COPY_HEADERS := \
	include/cczoomrotationbase.h \
 	include/cczoomrotation12.h \
 	include/cczoomrotation16.h \
 	include/cczoomrotation24.h \
 	include/cczoomrotation32.h \
 	include/ccrgb16toyuv420.h \
 	include/ccrgb24torgb16.h \
 	include/ccyuv422toyuv420.h \
 	include/colorconv_config.h \
 	include/pvvideoblend.h \
 	include/ccrgb24toyuv420.h \
 	include/ccrgb12toyuv420.h \
 	include/ccyuv420semitoyuv420.h \
 	include/ccyuv420toyuv420semi.h

include $(BUILD_STATIC_LIBRARY)
