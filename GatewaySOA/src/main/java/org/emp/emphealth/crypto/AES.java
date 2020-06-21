/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.emphealth.crypto;

import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author blunt
 */
public class AES {
    private static final int keyLength = 32;
    private static final SecureRandom random = new SecureRandom();
    
    private static SecretKey key;
    private static byte[] iv;
    
    private static void printByteArr(byte[] arr) {

    }

    public static byte [] encrypt(String plaintext) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        String ivStr = "0123456789abcdef";
        iv = ivStr.getBytes("US-ASCII");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        
        printByteArr(iv);
        return cipher.doFinal(plaintext.getBytes());
    }

    public static String decrypt(byte [] ciphertext) throws Exception {
        printByteArr(ciphertext);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        String ivStr = "0123456789abcdef";
        iv = ivStr.getBytes("US-ASCII");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        return new String(cipher.doFinal(ciphertext));
    }

    public static void generateKey(byte[] keyBytes) throws Exception {
    	Security.addProvider(new BouncyCastleProvider());
        //byte[] keyBytes = new byte[keyLength];
        //random.nextBytes(keyBytes);
        //keyBytes = cle.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        key = keySpec;
    }

    private static IvParameterSpec generateIV(Cipher cipher) throws Exception {
        byte [] ivBytes = new byte[cipher.getBlockSize()];
        random.nextBytes(ivBytes);
        return new IvParameterSpec(ivBytes);
    }
}
