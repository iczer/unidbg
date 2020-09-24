package com.starwit.linux.android.dvm.bytedance;

import com.github.unidbg.linux.android.dvm.BaseVM;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.StringObject;
import com.github.unidbg.linux.android.dvm.VaList;
import com.starwit.linux.android.dvm.AbstractMethodResolver;

/**
 * @ClassName SystemPropertyResolver
 * @Description 系统属性处理
 * @Author iczer
 * @Date 2020/9/23 20:38
 * @Version 1.0
 */
public class SystemPropertyResolver extends AbstractMethodResolver {
    public SystemPropertyResolver() {
        super("java/lang/System->getProperty(Ljava/lang/String;)Ljava/lang/String;",
                (BaseVM vm, DvmClass dvmClass, VaList vaList) ->
                {
                    StringObject dvmObj = vaList.getObject(0);
                    String key = dvmObj.getValue();
                    String value;
                    switch (key) {
                        case "http.proxyHost":
                            value = ""; break;
                        case "http.proxyPort":
                            value = "80"; break;
                        case "java.vm.version":
                            value = "1.8.0"; break;
                        default:
                            value = System.getProperty(key);
                    }
                    return new StringObject(vm, value);
                });
    }
}
