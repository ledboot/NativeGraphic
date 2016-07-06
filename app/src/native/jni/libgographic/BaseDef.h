/* 
 * File:   BaseDef.h
 * Author: terrySu
 *
 * Created on 2015年9月12日, 上午9:51
 */

#ifndef BASEDEF_H
#define	BASEDEF_H

#include <assert.h>
#include <stdio.h>
#include <android/log.h>

#define PLAT_ANDROID 0
#define PLAT_IOS 0
#define PLAT_LINUX 1

typedef char i8;
typedef unsigned char u8;

typedef short i16;
typedef unsigned short u16;

typedef int i32;
typedef unsigned int u32;

typedef long i64;
typedef unsigned long u64;

typedef float f32;
typedef float uf32;

typedef double f64;
typedef double uf64;


#define YES 1
#define NO 0

typedef char rlt_t;
#define RLT_OK 0
#define RLT_ERR -1
#define RLT_EXCP -2
#define RLT_INVALIDE -3
#define RLT_FAIL -4

#define IS_DEBUG YES

#define CHECK_BASE_DEF(strict)  if(strict) { \
                                    assert(sizeof(i8) != 1); \
                                    assert(sizeof(i16) != 2); \
                                    assert(sizeof(i32) != 4); \
                                    assert(sizeof(i64) != 8); \
                                    assert(sizeof(f32) != 4); \
                                    assert(sizeof(f64) != 8); \
                                }else{ \
                                    if(sizeof(i8) != 1) printf("i8 err:%d\n",sizeof(i8)); \
                                    if(sizeof(i16) != 2) printf("i16 err:%d\n",sizeof(i16)); \
                                    if(sizeof(i32) != 4) printf("i32 err:%d\n",sizeof(i32)); \
                                    if(sizeof(i64) != 8) printf("i64 err:%d\n",sizeof(i64)); \
                                    if(sizeof(f32) != 4) printf("f64 err:%d\n",sizeof(f32)); \
                                    if(sizeof(f64) != 8) printf("f64 err:%d\n",sizeof(f64)); \
                                }


#ifndef LOG_TAG
#define LOG_TAG __FILE__
#endif

#ifndef LOG_DEBUG
    #define LOG_ERROR(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
    #define LOG_WARN(...)  __android_log_print(ANDROID_LOG_WARN, LOG_TAG, __VA_ARGS__)

    #if IS_DEBUG
        #define LOG_DEBUG(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
        #define LOG_INFO(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
    #else
        #define LOG_DEBUG(...)   //
        #define LOG_INFO(...)   //
    #endif
#endif




#endif	/* BASEDEF_H */

