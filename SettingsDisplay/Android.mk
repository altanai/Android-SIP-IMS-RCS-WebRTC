# Software Name : RCS IMS Stack
#
# Copyright (C) 2010 France Telecom S.A.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

LOCAL_PATH := $(call my-dir)

##########################################################################
# Build the application : RcsCore.apk
##########################################################################

include $(CLEAR_VARS)

# This is the target being built. (Name of APK)
LOCAL_PACKAGE_NAME := RcsCore

# 
LOCAL_STATIC_JAVA_LIBRARIES := android-common

# Only compile source java files in this apk.
LOCAL_SRC_FILES := \
    $(call all-java-files-under, src)

# Link against the current Android SDK.
LOCAL_SDK_VERSION := current

# Add AIDL files (the parcelable must not be added in SRC_FILES, but included in LOCAL_AIDL_INCLUDES)
LOCAL_SRC_FILES += \
    src/com/orangelabs/rcs/service/api/client/IImsApi.aidl \
    src/com/orangelabs/rcs/service/api/client/terms/ITermsApi.aidl \
    src/com/orangelabs/rcs/service/api/client/capability/ICapabilityApi.aidl \
    src/com/orangelabs/rcs/service/api/client/media/IMediaEventListener.aidl \
    src/com/orangelabs/rcs/service/api/client/media/IMediaPlayer.aidl \
    src/com/orangelabs/rcs/service/api/client/media/IMediaRenderer.aidl \
    src/com/orangelabs/rcs/service/api/client/messaging/IChatEventListener.aidl \
    src/com/orangelabs/rcs/service/api/client/messaging/IChatSession.aidl \
    src/com/orangelabs/rcs/service/api/client/messaging/IMessageDeliveryListener.aidl \
    src/com/orangelabs/rcs/service/api/client/messaging/IFileTransferEventListener.aidl \
    src/com/orangelabs/rcs/service/api/client/messaging/IFileTransferSession.aidl \
    src/com/orangelabs/rcs/service/api/client/messaging/IMessagingApi.aidl \
    src/com/orangelabs/rcs/service/api/client/presence/IPresenceApi.aidl \
    src/com/orangelabs/rcs/service/api/client/richcall/IImageSharingEventListener.aidl \
    src/com/orangelabs/rcs/service/api/client/richcall/IImageSharingSession.aidl \
    src/com/orangelabs/rcs/service/api/client/richcall/IRichCallApi.aidl \
    src/com/orangelabs/rcs/service/api/client/richcall/IVideoSharingEventListener.aidl \
    src/com/orangelabs/rcs/service/api/client/richcall/IVideoSharingSession.aidl \
    src/com/orangelabs/rcs/service/api/client/sip/ISipApi.aidl \
    src/com/orangelabs/rcs/service/api/client/sip/ISipSession.aidl \
    src/com/orangelabs/rcs/service/api/client/sip/ISipSessionEventListener.aidl

# FRAMEWORKS_BASE_JAVA_SRC_DIRS comes from build/core/pathmap.mk
LOCAL_AIDL_INCLUDES += $(FRAMEWORKS_BASE_JAVA_SRC_DIRS)
LOCAL_AIDL_INCLUDES += \
    src/com/orangelabs/rcs/service/api/client/capability/Capabilities.aidl \
    src/com/orangelabs/rcs/service/api/client/messaging/InstantMessage.aidl \
    src/com/orangelabs/rcs/service/api/client/media/MediaCodec.aidl \
    src/com/orangelabs/rcs/service/api/client/presence/FavoriteLink.aidl \
    src/com/orangelabs/rcs/service/api/client/presence/Geoloc.aidl \
    src/com/orangelabs/rcs/service/api/client/presence/PhotoIcon.aidl \
    src/com/orangelabs/rcs/service/api/client/presence/PresenceInfo.aidl

# Add classes used by reflexion
LOCAL_PROGUARD_FLAG_FILES := proguard.cfg

# Tell it to build an APK
include $(BUILD_PACKAGE)


##########################################################################
# Build the RCS API : rcs_api.jar
##########################################################################

RcsApi: TEMP_DEST := $(call local-intermediates-dir,RcsCore)
RcsApi: RCSCORE_JAR := $(TEMP_DEST)/classes.jar
RcsApi: RCSAPI_JAR_DEST := $(call intermediates-dir-for,APPS,RcsCore)
RcsApi: RCSAPI_JAR_NAME := rcs_api.jar
RcsApi: FILES_TO_INCLUDE:= com/orangelabs/rcs/utils/ \
    com/orangelabs/rcs/service/api/client/ \
    com/orangelabs/rcs/provider/ \
    com/orangelabs/rcs/platform/ \
    com/orangelabs/rcs/core/ims/protocol/rtp/ \
    com/orangelabs/rcs/core/ims/service/richcall/ContentSharingError.class \
    com/orangelabs/rcs/core/ims/service/im/filetransfer/FileSharingError.class\
    com/orangelabs/rcs/core/ims/service/im/chat/ChatError.class \
    com/orangelabs/rcs/core/ims/service/sip/SipSessionError.class \
    com/orangelabs/rcs/core/ims/service/im/chat/imdn/ImdnDocument.class \
    com/orangelabs/rcs/core/ims/service/im/chat/event/User.class
RcsApi:
	@echo "#### Build rcsapi.jar ####"
	$(call unzip-jar-files,$(RCSCORE_JAR),$(TEMP_DEST))
	$(hide) cd $(TEMP_DEST) && rm -f $(RCSAPI_JAR_NAME)
	$(hide) cd $(TEMP_DEST) && jar cvf $(RCSAPI_JAR_NAME) $(FILES_TO_INCLUDE)
	@echo $(RCSAPI_JAR_NAME) "created in " $(TEMP_DEST)



