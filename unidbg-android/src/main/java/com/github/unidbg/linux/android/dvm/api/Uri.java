package com.github.unidbg.linux.android.dvm.api;

import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.VM;

/**
 * @ClassName Uri
 * @Description TODO
 * @Author iczer
 * @Date 2020/11/29 22:12
 * @Version 1.0
 */
public class Uri extends DvmObject<String> {
    public Uri(VM vm, String uriStr) {
        super(vm.resolveClass("android/net/Uri"), uriStr);
    }
}
