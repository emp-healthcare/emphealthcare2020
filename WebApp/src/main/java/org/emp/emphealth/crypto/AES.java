package org.emp.emphealth.crypto;

import java.security.Security;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AES {
	private static final int keyLength = 32;
    
    private static SecretKey key;
    
    private static void printByteArr(byte[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(i == 0 ? "%d" : ",%d", (arr[i] & 0xFF));
        }
        System.out.println("]");
    }

    public static byte [] encrypt(String plaintext) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        String ivStr = "0123456789abcdef";
        byte[] iv = ivStr.getBytes("US-ASCII");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        /*System.out.println(
                Base64.getEncoder().withoutPadding()
                    .encodeToString(key.getEncoded())
        );*/
        return cipher.doFinal(plaintext.getBytes());
    }

    public static String decrypt(byte [] ciphertext) throws Exception {
    	String ivStr = "0123456789abcdef";
        //printByteArr(ciphertext);
        byte[] iv = ivStr.getBytes("US-ASCII");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        return new String(cipher.doFinal(ciphertext));
    }

    public static void generateKey(byte[] keyBytes) throws Exception {
    	Security.addProvider(new BouncyCastleProvider());        
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        key = keySpec;
    }

   
}
