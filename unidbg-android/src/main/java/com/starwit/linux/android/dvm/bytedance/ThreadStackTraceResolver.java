package com.starwit.linux.android.dvm.bytedance;

import com.github.unidbg.linux.android.dvm.BaseVM;
import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.StackTraceElement;
import com.github.unidbg.linux.android.dvm.VaList;
import com.github.unidbg.linux.android.dvm.array.ArrayObject;
import com.starwit.linux.android.dvm.AbstractMethodResolver;

/**
 * @ClassName ThreadStackTraceResolver
 * @Description 栈追踪处理
 * @Author iczer
 * @Date 2020/9/23 21:35
 * @Version 1.0
 */
public class ThreadStackTraceResolver extends AbstractMethodResolver {
    public ThreadStackTraceResolver() {
        super("java/lang/Thread->getStackTrace()[Ljava/lang/StackTraceElement;",
                (BaseVM vm, DvmObject dvmObject, VaList vaList) ->
                {
                    DvmObject[] objs = new DvmObject[14];
                    objs[0] = new StackTraceElement(vm, "dalvik.system.VMStack.getThreadStackTrace(Native Method)");
                    objs[1] = new StackTraceElement(vm, "java.lang.Thread.getStackTrace(Thread.java:580)");
                    objs[2] = new StackTraceElement(vm, "com.ss.sys.ces.a.leviathan(Native Method)");
                    objs[3] = new StackTraceElement(vm, "com.ss.sys.ces.gg.tt$1.a(Unknown Source)");
                    objs[4] = new StackTraceElement(vm, "com.bytedance.frameworks.baselib.network.http.e.a(SourceFile:33947656)");
                    objs[5] = new StackTraceElement(vm, "com.bytedance.ttnet.a.a.onCallToAddSecurityFactor(SourceFile:33816621)");
                    objs[6] = new StackTraceElement(vm, "android.support.v7.app.AppCompatViewInflater$DeclaredOnClickListener");
                    objs[7] = new StackTraceElement(vm, "java.lang.reflect.Method.invoke(Native Method)");
                    objs[8] = new StackTraceElement(vm, "com.ttnet.org.chromium.base.Reflect.on(SourceFile:50659347)");
                    objs[9] = new StackTraceElement(vm, "com.ttnet.org.chromium.base.Reflect.call(SourceFile:50528262)");
                    objs[10] = new StackTraceElement(vm, "org.chromium.c.a(SourceFile:33882174)");
                    objs[11] = new StackTraceElement(vm, "org.chromium.e.onCallToAddSecurityFactor(SourceFile:33685508)");
                    objs[12] = new StackTraceElement(vm, "com.ttnet.org.chromium.net.impl.CronetUrlRequestContext.onCallToAddSecurityFactor(SourceFile:33685512)");
                    objs[13] = new StackTraceElement(vm, "com.ttnet.org.chromium.net.impl.CronetUrlRequest.addSecurityFactor(SourceFile:33882142)");
                    return new ArrayObject(objs);
                });
    }
}
