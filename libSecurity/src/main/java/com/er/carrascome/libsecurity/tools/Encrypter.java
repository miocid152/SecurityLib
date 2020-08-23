package com.er.carrascome.libsecurity.tools;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encrypter {
    private String tipoAlgoritmoLong;
    private String utf;
    private String algoritmo;
    private String pwd;

    public Encrypter(){
        this.tipoAlgoritmoLong="SHA-256";
        this.utf="UTF-8";
        this.algoritmo="AES";
    }

    public String encriptar(Object o,boolean pwdDefault,String pwd) throws Exception{
        SecretKeySpec secretKeySpec;
        if(pwdDefault){
            secretKeySpec = generateKey("miocid");
        }
        else {
            secretKeySpec = generateKey(pwd);
        }
        Cipher cipher = Cipher.getInstance(algoritmo);
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        byte[] retornoEnBytes = cipher.doFinal(convierteObjectoEnBytes(o));
        String retornoEnString = Base64.encodeToString(retornoEnBytes,Base64.DEFAULT);
        return retornoEnString;
    }

    public Object desEncriptar(String encriptado,boolean pwdDefault,String pwd) throws Exception {
        SecretKeySpec secretKeySpec;
        if(pwdDefault){
            secretKeySpec = generateKey("miocid");
        }
        else {
            secretKeySpec = generateKey(pwd);
        }
        Cipher cipher = Cipher.getInstance(algoritmo);
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        byte[] datosDecodificados = Base64.decode(encriptado,Base64.DEFAULT);
        byte[] datosDesEncriptadosBytes = cipher.doFinal(datosDecodificados);
        Object retornoEnObjeto = convierteBytesEnObjeto(datosDesEncriptadosBytes);
        return retornoEnObjeto;
    }


    private SecretKeySpec generateKey(String pws) throws Exception{

        MessageDigest sha = MessageDigest.getInstance(tipoAlgoritmoLong);
        byte[] key = pws.getBytes(utf);
        key = sha.digest(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,algoritmo);
        return secretKeySpec;

    }

    private byte[] convierteObjectoEnBytes(Object o){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(o);
            out.flush();
            byte[] bytes = bos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bos!=null) {
                    bos.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    private Object convierteBytesEnObjeto(byte[] bytes){
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            return o;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }


}

