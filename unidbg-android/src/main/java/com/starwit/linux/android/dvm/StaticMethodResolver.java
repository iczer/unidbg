package com.starwit.linux.android.dvm;

import com.github.unidbg.linux.android.dvm.BaseVM;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.VaList;

/**
 * @ClassName StaticMethodResolver
 * @Description dvm 静态函数处理接口
 * @Author iczer
 * @Date 2020/9/23 20:34
 * @Version 1.0
 */
public interface StaticMethodResolver {
    DvmObject resolve(BaseVM vm, DvmClass dvmClass, VaList vaList);
}
