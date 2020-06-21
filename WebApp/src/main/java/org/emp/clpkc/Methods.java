package org.emp.clpkc;


import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Methods {
    public Element H(String ID, Pairing pairing) throws NoSuchAlgorithmException {
        byte[] btID = ID.getBytes();

        MessageDigest hasher = MessageDigest.getInstance("SHA-512");
        byte[] hashBytes = hasher.digest(btID);
        Element e = pairing.getG1().newRandomElement();

        return e.setFromHash(hashBytes,0,hashBytes.length);
    }
    
    public Element H2(String ID, Element r, Pairing pairing) throws NoSuchAlgorithmException {
        byte[] btID = ID.getBytes();
        byte[] btr = r.toBytes();
        byte[] combined = new byte[btID.length+btr.length];
        System.arraycopy(btID, 0, combined, 0, btID.length);
        System.arraycopy(btr, 0, combined, btID.length, btr.length);
        MessageDigest hasher = MessageDigest.getInstance("SHA-512");
        byte[] hashBytes = hasher.digest(combined);
        Element e = pairing.getZr().newRandomElement();
        return e.setFromHash(hashBytes,0,hashBytes.length);
    }
    
    public String H3(String ID) throws NoSuchAlgorithmException {
        byte[] btID = ID.getBytes();

        MessageDigest hasher = MessageDigest.getInstance("SHA-512");
        byte[] hashBytes = hasher.digest(btID);

        return DatatypeConverter.printHexBinary(hashBytes);
    }
    
    public String H4(String ID) throws NoSuchAlgorithmException {
        byte[] btID = ID.getBytes();

        MessageDigest hasher = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = hasher.digest(btID);

        return DatatypeConverter.printHexBinary(hashBytes);
    }
    
    
}