package com.starwit.linux.android.dvm;

import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.linux.android.dvm.api.ApplicationInfo;
import com.starwit.linux.android.dvm.bytedance.SystemPropertyResolver;
import com.starwit.linux.android.dvm.bytedance.ThreadStackTraceResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BytedanceJni
 * @Description Bytedance jni 处理
 * @Author iczer
 * @Date 2020/9/23 20:13
 * @Version 1.0
 */
public class BytedanceJni extends AbstractJni {

    private Map<String, StaticMethodResolver> staticMethodMap;
    private Map<String, MethodResolver> methodMap;

    public BytedanceJni() {
        this.methodMap = new HashMap<>();
        this.staticMethodMap = new HashMap<>();
        this.setStaticMethodResolver();
        this.setMethodResolver();
    }

    private void setStaticMethodResolver() {
        this.addStaticMethodResolver(new SystemPropertyResolver());
        this.staticMethodMap.put("java/lang/Thread->currentThread()Ljava/lang/Thread;",
                (BaseVM vm, DvmClass dvmClass, VaList vaList) -> {
                    return vm.resolveClass("java/lang/Thread").newObject(Thread.currentThread());
                });
        this.staticMethodMap.put("android/app/Application->getApplicationInfo()Landroid/content/pm/ApplicationInfo;",
                (BaseVM vm, DvmClass dvmClass, VaList vaList) -> {
                    return new ApplicationInfo(vm);
                });
    }

    private void setMethodResolver() {
        this.addMethodResolver(new ThreadStackTraceResolver());
        this.methodMap.put("java/lang/StackTraceElement->getClassName()Ljava/lang/String;",
                (BaseVM vm, DvmObject dvmObject, VaList vaList) ->
                {
                    return new StringObject(vm, (String) dvmObject.getValue());
                });
        this.methodMap.put("android/app/Application->getApplicationInfo()Landroid/content/pm/ApplicationInfo;",
                (BaseVM vm, DvmObject dvmObject, VaList vaList) -> {
                    return new ApplicationInfo(vm);
                });
    }

    public void addStaticMethodResolver(AbstractMethodResolver resolver) {
        this.staticMethodMap.put(resolver.getSignature(), resolver.getStaticMethodResolver());
    }

    public void addMethodResolver(AbstractMethodResolver resolver) {
        this.methodMap.put(resolver.getSignature(), resolver.getMethodResolver());
    }

    @Override
    public DvmObject callStaticObjectMethodV(BaseVM vm, DvmClass dvmClass, String signature, VaList vaList) {
        StaticMethodResolver resolver = this.staticMethodMap.get(signature);
        if (resolver != null) {
            return resolver.resolve(vm, dvmClass, vaList);
        }
        return super.callStaticObjectMethodV(vm, dvmClass, signature, vaList);
    }

    @Override
    public DvmObject callObjectMethodV(BaseVM vm, DvmObject dvmObject, String signature, VaList vaList) {
        MethodResolver resolver = this.methodMap.get(signature);
        if (resolver != null) {
            return resolver.resolve(vm, dvmObject, vaList);
        }
        return super.callObjectMethodV(vm, dvmObject, signature, vaList);
    }

    @Override
    public DvmObject<?> getObjectField(BaseVM vm, DvmObject<?> dvmObject, String signature) {
        if ("android/app/ApplicationInfo->sourceDir:Ljava/lang/String;".equals(signature)) {
            return new StringObject(vm, "/data/data/");
        }
        if ("android/content/pm/ApplicationInfo->sourceDir:Ljava/lang/String;".equals(signature)) {
            return new StringObject(vm, "/data/data/");
        }
        return super.getObjectField(vm, dvmObject, signature);
    }

    @Override
    public boolean callStaticBooleanMethodV(BaseVM vm, DvmClass dvmClass, String signature, VaList vaList) {
        StaticMethodResolver resolver = this.staticMethodMap.get(signature);
        if (signature.equals("android/os/Debug->isDebuggerConnected()Z"))
            return true;
        return false;
    }

}
