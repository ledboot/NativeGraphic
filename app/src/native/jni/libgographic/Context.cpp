//
// Created by SuperTerry on 15/9/13.
//

#include "BaseDef.h"
#include <jni.h>
#include <android/bitmap.h>
#include "stackblur.c"

#define LOG_TAG "jni_context"


#define RGB565_R(p) ((((p) & 0xF800) >> 11) << 3)
#define RGB565_G(p) ((((p) & 0x7E0 ) >> 5)  << 2)
#define RGB565_B(p) ( ((p) & 0x1F  )        << 3)
#define MAKE_RGB565(r,g,b) ((((r) >> 3) << 11) | (((g) >> 2) << 5) | ((b) >> 3))

#define RGBA_A(p) (((p) & 0xFF000000) >> 24)
#define RGBA_R(p) (((p) & 0x00FF0000) >> 16)
#define RGBA_G(p) (((p) & 0x0000FF00) >>  8)
#define RGBA_B(p)  ((p) & 0x000000FF)
#define MAKE_RGBA(r,g,b,a) (((a) << 24) | ((r) << 16) | ((g) << 8) | (b))


jint gray(JNIEnv *env, jobject object,jobject bitmap) {
    if(bitmap == NULL){
        LOG_DEBUG("______bitmap is null");
        return -1;
    }else{
        LOG_DEBUG("______bitmap not null");
    }

    AndroidBitmapInfo info;
    AndroidBitmap_getInfo(env, bitmap, &info);
    LOG_DEBUG("_____bitmap width=%d,___height=%d", info.width, info.height);

    void *pixels = NULL;
    AndroidBitmap_lockPixels(env,bitmap,&pixels);

    int x=0,y=0;

    for(y=0;y< info.height;y++){
        for(x=0;x< info.width;x++){
            int a=0,r=0,g=0,b=0;
            void *pixel = NULL;
            if(info.format == ANDROID_BITMAP_FORMAT_RGB_565){
                pixel = ((uint16_t *)pixels) + y * info.width + x;
                uint16_t v = *(uint16_t *)pixel;
                r = RGB565_R(v);
                g = RGB565_G(v);
                b = RGB565_B(v);
            }else{
                pixel = ((uint32_t *)pixels) + y * info.width + x;
                uint32_t v = *(uint32_t *)pixel;
                a = RGBA_A(v);
                r = RGBA_R(v);
                g = RGBA_G(v);
                b = RGBA_B(v);
            }

            // Grayscale
            int gray = (r * 38 + g * 75 + b * 15) >> 7;

            // Write the pixel back
            if (info.format == ANDROID_BITMAP_FORMAT_RGB_565) {
                *((uint16_t *)pixel) = MAKE_RGB565(gray, gray, gray);
            } else {// RGBA
                *((uint32_t *)pixel) = MAKE_RGBA(gray, gray, gray, a);
            }
        }
    }


    AndroidBitmap_unlockPixels(env,bitmap);
    return 199;
}

jint blurBitmap(JNIEnv*env,jclass obj, jobject bitmap,jint r){

    AndroidBitmapInfo info;
    void *pixels = NULL;

    AndroidBitmap_getInfo(env, bitmap, &info);
    int h = info.height;
    int w = info.width;

    LOG_DEBUG("_____bitmap width=%d,___height=%d", info.width, info.height);


    AndroidBitmap_lockPixels(env,bitmap,&pixels);

    pixels = blur((int *)pixels,w,h,r);

    AndroidBitmap_unlockPixels(env,bitmap);
}





//for jni register -------------------------------------------

#define CLASS_MSG_HANDLER  "com/ledboot/gographic/GraphicManager"

static JNINativeMethod gMethods[] = {
        // name,              signature,         funcPtrs
        {"nativeGray",        "(Landroid/graphics/Bitmap;)I",                       (void*) gray},
        {"nativeBlur",        "(Landroid/graphics/Bitmap;I)I",                      (void*) blurBitmap},
};

#ifndef NELEM
# define NELEM(x) ((int) (sizeof(x) / sizeof((x)[0])))
#endif

int register_jni(JNIEnv *env)
{
    jclass handlerClass = env->FindClass(CLASS_MSG_HANDLER);
    if (handlerClass == NULL)
    {
        LOG_ERROR("Can't find %s", CLASS_MSG_HANDLER);
        return -1;
    }

    int ret = env->RegisterNatives(handlerClass, gMethods, NELEM(gMethods));
    env->DeleteLocalRef(handlerClass);
    return ret;
}

JNIEXPORT jint JNI_OnLoad(JavaVM* jvm, void* reserved)
{
    JNIEnv *env = NULL;

    if (jvm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_4) != JNI_OK)    {
        return -1;
    }

    register_jni(env);

    return JNI_VERSION_1_4;
}

JNIEXPORT void JNI_OnUnload(JavaVM* jvm, void* reserved)
{

}
