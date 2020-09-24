package com.starwit.linux.android;

import java.io.IOException;

public class DouyinEmulatorTest {

    static String libraryPath = "unidbg-android/src/test/resources/example_binaries/armeabi-v7a/libcms.so";

    public static void main(String[] args) throws IOException {
        DouyinEmulator emulator = new DouyinEmulator(libraryPath,23);
        emulator.start();
        for (int i = 0; i < 300; i++) {
            long timestamp = System.currentTimeMillis();
            byte[] data = "75e3ba5532ebc30fba36674de5b044bb00000000000000000000000000000000d4845ba4d4587a60e2b73030ecff23aee5afe8c143fcee165f7ab2c19a7562b6".getBytes();
            byte[] result = emulator.encrypt(-1, (int) (timestamp / 1000), data);
            System.out.println(byte2Hex(result));
            System.out.println("cost:" + (System.currentTimeMillis() - timestamp));
        }
        emulator.stop();
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}