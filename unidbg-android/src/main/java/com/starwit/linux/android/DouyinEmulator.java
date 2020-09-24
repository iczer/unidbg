package com.starwit.linux.android;

import com.github.unidbg.Module;
import com.github.unidbg.Symbol;
import com.github.unidbg.linux.android.AndroidARMEmulator;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.StringObject;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.memory.MemoryBlock;
import com.starwit.linux.android.dvm.BytedanceJni;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName DouyinEmulator
 * @Description 抖音模拟器
 * @Author iczer
 * @Date 2020/9/23 22:00
 * @Version 1.0
 */
public class DouyinEmulator {
    private static final Log log = LogFactory.getLog(DouyinEmulator.class);

    public final String APP_PACKAGE_NAME = "com.ss.android.ugc.aweme";
    private final String libraryPath;
    private final int sdk;
    private final AndroidARMEmulator emulator;
    private VM vm;
    private Module module;
    private DvmClass jniObject;


    public DouyinEmulator(String libraryPath, int sdk) {
        this.libraryPath = libraryPath;
        this.sdk = sdk;
        emulator = new AndroidARMEmulator(APP_PACKAGE_NAME);

    }

    public void start() {
        final Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(sdk));
        vm = emulator.createDalvikVM(null);
        vm.setJni(new BytedanceJni());
        vm.setVerbose(true);
        DalvikModule dm = vm.loadLibrary(new File(this.libraryPath), false);
        dm.callJNI_OnLoad(emulator);
        this.module = dm.getModule();
        jniObject = vm.resolveClass("com/ss/sys/ces/a");
        jniObject.addMethodMap("Francies", "()V");
        jniObject.addStaticMethodMap("njss", "(ILjava/lang/Object;)java/lang/Object;");
        jniObject.addStaticMethodMap("Zeoy", "()V");
        jniObject.addStaticMethodMap("Louis", "()V");
        jniObject.addStaticMethodMap("Bill", "()V");
        Symbol __system_property_get = module.findSymbolByName("__system_property_get", true);
        MemoryBlock block = memory.malloc(0x10);
        Number ret = __system_property_get.call(emulator, "ro.build.version.sdk", block.getPointer())[0];
        log.info("sdk=" + new String(block.getPointer().getByteArray(0, ret.intValue())) + ", libc=" + memory.findModule("libc.so"));
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 100, 0, new StringObject(vm, "1991"));//threadId #matter nothing
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 101, 0, new StringObject(vm, "0"));//PUSH_TYPE_NOTIFY
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 102, 0, new StringObject(vm, "1128"));//aid
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 103, 0, new StringObject(vm, "51790275446"));// deviceId
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 104, 0, new StringObject(vm, "110943176729"));// installId
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 1020, 0, new StringObject(vm, ""));
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 105, 0, new StringObject(vm, "110001"));//updateVersion
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 106, 0, new StringObject(vm, "com.ss.android.ugc.aweme"));
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 107, 0, new StringObject(vm, "/data/user/0/" + APP_PACKAGE_NAME + "/files"));
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 108, 0, new StringObject(vm, "/data/app/com.ss.android.ugc.aweme-1.apk"));
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 109, 0, new StringObject(vm, "/storage/emulated/0"));
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 110, 0, new StringObject(vm, "/data"));
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", 111, 0, new StringObject(vm, "/" + sdk));
    }

    public void setMeta(int metaType, String value) {
        jniObject.callStaticJniMethod(emulator, "meta(ILandroid/content/Context;Ljava/lang/Object;)Ljava/lang/Object;", metaType, 0, new StringObject(vm, value));
    }

    public void stop() throws IOException {
        emulator.close();
        log.info("emulator is stopped");
    }

    public byte[] encrypt(int flag, int timestamp, byte[] data) {
        ByteArray array = jniObject.callStaticJniMethodObject(emulator, "leviathan(II[B)[B", flag, timestamp, new ByteArray(vm, data));
        return array.getValue();
    }
}
