package com.starwit.linux.android.dvm;

import com.github.unidbg.linux.android.dvm.BaseVM;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.VaList;

/**
 * @ClassName AbstractMethodResolver
 * @Description TODO
 * @Author iczer
 * @Date 2020/9/23 20:40
 * @Version 1.0
 */
public abstract class AbstractStaticMethodResolver implements StaticMethodResolver {
    private String signature;
    private StaticMethodResolver staticMethodResolver;

    public AbstractStaticMethodResolver(String signature, StaticMethodResolver resolver) {
        this.signature = signature;
        this.staticMethodResolver = resolver;
    }

    @Override
    public DvmObject resolve(BaseVM vm, DvmClass dvmClass, VaList vaList) {
        return this.staticMethodResolver.resolve(vm, dvmClass, vaList);
    }

    public String getSignature() {
        return signature;
    }

    public StaticMethodResolver getStaticMethodResolver() {
        return staticMethodResolver;
    }
}
