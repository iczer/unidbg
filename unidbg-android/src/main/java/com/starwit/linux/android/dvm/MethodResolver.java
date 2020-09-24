package com.starwit.linux.android.dvm;

import com.github.unidbg.linux.android.dvm.BaseVM;
import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.VaList;

/**
 * @ClassName MethodResolver
 * @Description dvm 实例函数处理接口
 * @Author iczer
 * @Date 2020/9/23 20:29
 * @Version 1.0
 */
public interface MethodResolver {
    DvmObject resolve(BaseVM vm, DvmObject dvmObject, VaList vaList);
}
