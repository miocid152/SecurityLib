package com.er.carrascome.libsecurity.expose;

import com.er.carrascome.libsecurity.tools.Encrypter;

//generator https://www.browserling.com/tools/random-hex
// https://gist.github.com/easternHong/6ca75a7fdce15c6a23d3
//pruebas

public class SecurityExpose {

    public static String codifica(Object o) {
        try {
            Encrypter encrypter = new Encrypter();
            return encrypter.encriptar(o, true, null);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String codifica(Object o,String pwd){
        try {
            Encrypter encrypter = new Encrypter();
            return encrypter.encriptar(o, false, pwd);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Object deCodifica(String o) {
        try {
            Encrypter encrypter = new Encrypter();
            return encrypter.desEncriptar(o, true, null);
        }
        catch (Exception e){
            return null;
        }
    }

    public static Object deCodifica(String o,String pwd){
        try {
            Encrypter encrypter = new Encrypter();
            return encrypter.desEncriptar(o, false, pwd);
        } catch (Exception e) {
            return null;

        }
    }

    public static Object decodificaExtras(Object decodifica){
        return SecurityExpose.deCodifica((String) decodifica);
    }

    public static Object decodificaExtras(Object decodifica, String pwd){
        return SecurityExpose.deCodifica((String) decodifica,pwd);
    }


    public static byte[] HexStringToByteArray(String s) throws IllegalArgumentException {
        int len = s.length();
        if (len % 2 == 1) {
            throw new IllegalArgumentException("Hex string must have even number of characters");
        }
        byte[] data = new byte[len / 2]; // Allocate 1 byte per 2 hex characters
        for (int i = 0; i < len; i += 2) {
            // Convert each character into a integer (base-16), then bit-shift into place
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String ByteArrayToHexString(byte[] bytes) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] hexChars = new char[bytes.length * 2]; // Each byte has two hex characters (nibbles)
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF; // Cast bytes[j] to int, treating as unsigned value
            hexChars[j * 2] = hexArray[v >>> 4]; // Select hex character from upper nibble
            hexChars[j * 2 + 1] = hexArray[v & 0x0F]; // Select hex character from lower nibble
        }
        return new String(hexChars);
    }
}
