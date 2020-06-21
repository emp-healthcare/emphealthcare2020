/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.clpkc;

import it.unisa.dia.gas.jpbc.Element;

/**
 *
 * @author ambara
 */
public class SignatureEchange {
    public String P2003_0;
    public String P2003_1;
    public String Q;
    public String signature_0;
    public String signature_1;

    public SignatureEchange(String P2003_0, String P2003_1, String Q, String signature_0, String signature_1) {
        this.P2003_0 = P2003_0;
        this.P2003_1 = P2003_1;
        this.Q = Q;
        this.signature_0 = signature_0;
        this.signature_1 = signature_1;
    }

    public SignatureEchange() {
    }
    
    
}
