LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
	Context.cpp \

LOCAL_LDLIBS := -llog -pthread -ljnigraphics

LOCAL_C_INCLUDES +=


LOCAL_MODULE:= libgographic

include $(BUILD_SHARED_LIBRARY)
