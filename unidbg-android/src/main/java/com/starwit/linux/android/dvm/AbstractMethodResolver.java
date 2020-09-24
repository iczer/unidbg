package com.starwit.linux.android.dvm;

import com.github.unidbg.linux.android.dvm.BaseVM;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.VaList;

/**
 * @ClassName AbstractMethodResolver
 * @Description TODO
 * @Author iczer
 * @Date 2020/9/23 21:22
 * @Version 1.0
 */
public abstract class AbstractMethodResolver implements StaticMethodResolver, MethodResolver  {
    private String signature;
    private MethodResolver methodResolver;
    private StaticMethodResolver staticMethodResolver;

    public AbstractMethodResolver(String signature, MethodResolver resolver) {
        this.signature = signature;
        this.methodResolver = resolver;
    }

    public AbstractMethodResolver(String signature, StaticMethodResolver staticResolver) {
        this.signature = signature;
        this.staticMethodResolver = staticResolver;
    }

    public AbstractMethodResolver(String signature, MethodResolver resolver, StaticMethodResolver staticResolver) {
        this.signature = signature;
        this.methodResolver = resolver;
        this.staticMethodResolver = staticResolver;
    }

    @Override
    public DvmObject resolve(BaseVM vm, DvmClass dvmClass, VaList vaList) {
        return this.staticMethodResolver.resolve(vm, dvmClass, vaList);
    }

    @Override
    public DvmObject resolve(BaseVM vm, DvmObject dvmObject, VaList vaList) {
        return this.methodResolver.resolve(vm, dvmObject, vaList);
    }

    public String getSignature() {
        return signature;
    }

    public MethodResolver getMethodResolver() {
        return methodResolver;
    }

    public StaticMethodResolver getStaticMethodResolver() {
        return staticMethodResolver;
    }
}
