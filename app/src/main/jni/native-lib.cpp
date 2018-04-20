#include <jni.h>
#include <string>
#include <string.h>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_lh_jnitestapp_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_lh_jnitestapp_MainActivity_add(JNIEnv *env, jobject instance, jint a, jint b) {
    return a + b;

}extern "C"
JNIEXPORT jstring JNICALL
Java_com_lh_jnitestapp_MainActivity_sayHello(JNIEnv *env, jobject instance, jstring s_) {
    char *fromC = " add i am from C";
    const char *s = env->GetStringUTFChars(s_, 0);
    char *result = strcat(const_cast<char *>(s), fromC);
    return env->NewStringUTF(result);
}extern "C"
JNIEXPORT jintArray JNICALL
Java_com_lh_jnitestapp_MainActivity_increaseArrayEles(JNIEnv *env, jobject instance,
                                                      jintArray intArray_) {
    jint *intArray = env->GetIntArrayElements(intArray_, NULL);
    jint length = env->GetArrayLength(intArray_);

    int i;
    for (i = 0; i < length; ++i) {
        *(intArray + i) += 10;
    }
    return intArray_;
}extern "C"
JNIEXPORT jint JNICALL
Java_com_lh_jnitestapp_MainActivity_checkPwd(JNIEnv *env, jobject instance, jstring pwd_) {
    const char *pwd = env->GetStringUTFChars(pwd_, 0);
    char *originPwd = "123456";
    int result = strcmp(pwd, originPwd);
    if (result == 0) { return 200; }
    else { return 400; }
}extern "C"
JNIEXPORT void JNICALL
Java_com_lh_jnitestapp_CGetJavaActivity_callBackAdd(JNIEnv *env, jobject instance) {
    jclass pJclass = env->FindClass("com/lh/jnitestapp/CGetJavaActivity.java");
    //第三个参数是方法签名--->cmd运行：javap -s 字节码对应的类，显示方法签名
    jmethodID pJmethodID = env->GetMethodID(pJclass, "add", "(II)I");
    jobject pJobject = env->AllocObject(pJclass);
    env->CallIntMethod(pJobject,pJmethodID,99,35);
}extern "C"
JNIEXPORT void JNICALL
Java_com_lh_jnitestapp_CGetJavaActivity_callBackHelloFromJava(JNIEnv *env, jobject instance) {

    // TODO

}