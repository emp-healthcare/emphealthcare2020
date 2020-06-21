/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.clpkc ;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.util.io.Base64;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




/**
 *
 * @author ambara
 */
public class CLPKC {

    private String ADRESSE_SERVEUR = "192.168.43.45:8081";
    //Init
    private String ID;
    
    public Pairing pairing;

    public String getADRESSE_SERVEUR() {
        return ADRESSE_SERVEUR;
    }

    public void setADRESSE_SERVEUR(String ADRESSE_SERVEUR) {
        this.ADRESSE_SERVEUR = ADRESSE_SERVEUR;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setD(Element D) {
        this.D = D;
    }

    public void setX(Element x) {
        this.x = x;
    }

    public void setSPrime(Element SPrime) {
        this.SPrime = SPrime;
    }

    public void setS(Element[] S) {
        this.S = S;
    }

    
    
    public Element getD() {
        return D;
    }

    public Element getX() {
        return x;
    }

    public Element getSA() {
        return SA;
    }

    public void setSA(Element SA) {
        this.SA = SA;
    }

    public Element getSPrime() {
        return SPrime;
    }

    public Element[] getS() {
        return S;
    }
    private Element D, x, SPrime, SA;
    private Element[] S = new Element[2];
    public Element P, Q, PKGC, PPubKGC;
    public Element[] P2003 = new Element[2];
    
    private String ADRESSE_IP = "http://localhost:8080/";

    public String getADRESSE_IP() {
        return ADRESSE_IP;
    }

    public void setADRESSE_IP(String ADRESSE_IP) {
        this.ADRESSE_IP = ADRESSE_IP;
    }
    
    public void setID(String _ID, Pairing pairing) throws IOException{
        
        Client client = ClientBuilder.newClient();
        
        Response r = client.target(ADRESSE_IP + "getGenerator").request().get();
        Response r2 = client.target(ADRESSE_IP + "getPublicKey").request().get();
        
        PKGC = pairing.getG1().newRandomElement();
        String s = r.readEntity(String.class);
        System.out.println(s);
        PKGC.setFromBytes(Base64.decode(s.getBytes()));
        
        PPubKGC = pairing.getG1().newRandomElement();
        String s2 = r2.readEntity(String.class);
        System.out.println(s2);
        PPubKGC.setFromBytes(Base64.decode(s2.getBytes()));
        
        
        
        this.ID = _ID;
        System.out.println(ID);
    }
    
    public String getID(){
        return this.ID;
    }
    
    
    
    public void SetPartialKey(Pairing pairing) throws NoSuchAlgorithmException, IOException{
        Client client = ClientBuilder.newClient();
        Response r = client.target(ADRESSE_IP + "extractQ").request(MediaType.TEXT_PLAIN).post(Entity.text(ID));
        Response r2 = client.target(ADRESSE_IP + "extractPartialKey").request(MediaType.TEXT_PLAIN).post(Entity.text(ID));
        Q = pairing.getG1().newRandomElement();
        String s = r.readEntity(String.class);
        Q.setFromBytes(Base64.decode(s.getBytes()));
        D = pairing.getG1().newRandomElement();
        String s2 = r2.readEntity(String.class);
        D.setFromBytes(Base64.decode(s2.getBytes()));
        System.out.println("Q = " + Q.toString());
        System.out.println("Clé partielle D = " + D.toString());
        
    }
    
   public void SetPrivateKey(Pairing pairing){
        S[0] = D.duplicate();
        x = pairing.getZr().newRandomElement();
        S[1] = x.duplicate();
        SA = D.duplicate().powZn(x.duplicate());

    }
    
     public void SetPublicKey(Pairing pairing){
        P = pairing.getG1().newRandomElement();
        P = PKGC.duplicate().powZn(x.duplicate());
        P2003[0] = P.duplicate();
        P2003[1] = PPubKGC.duplicate().powZn(x.duplicate());
        System.out.println("Clé publique P = " + P.toString());
    }
    
   public void SetSecretKey(Pairing pairing){
        SPrime = D.duplicate().add(Q.duplicate().powZn(x.duplicate()));
        System.out.println("Clé secrète S' = " + SPrime.toString());
    }
    
    //Echange
    public Element T, ab, K;
    
    public void setT(Pairing pairing){
        T = pairing.getG1().newRandomElement();
        ab = pairing.getZr().newRandomElement();
        T = PKGC.duplicate().powZn(ab.duplicate());
        System.out.println("Clé publique temporaire T = " + T.toString()+"\n");
    }
    
    public void setK(Pairing pairing, Element _Q, Element _P, Element _T){
        Element K1 = pairing.getG1().newRandomElement();
        K1 = pairing.pairing(_Q.duplicate(), PPubKGC.duplicate().add(_P.duplicate())).mulZn(ab);
        Element K2 = pairing.pairing(SPrime.duplicate(), _T.duplicate());
        K = K1.mul(K2);
        System.out.println("K1 = " + K1.toString());
        System.out.println("K2 = " + K2.toString());
        System.out.println("Clé symétrique K = " + K.toString()+"\n");
    }
    
    
    
    
    public void init(String noeudId) throws IOException, NoSuchAlgorithmException{       
        pairing = PairingFactory.getPairing("a.properties");      
        /*this.setID(noeudId,pairing);
        this.SetPartialKey(pairing);
        this.SetPrivateKey(pairing);
        this.SetPublicKey(pairing);
        this.SetSecretKey(pairing);*/
    }
    
    
    public void generationCle() throws IOException, NoSuchAlgorithmException{       
        this.setT(pairing);
        Client client = ClientBuilder.newClient();
        Response r = client.target("http://"+ADRESSE_SERVEUR+"/KeyExchange").request(MediaType.APPLICATION_JSON).post(Entity.entity(new ParamsEchange(this.getID(),Base64.encodeBytes(this.Q.toBytes()), Base64.encodeBytes(this.P.toBytes()), Base64.encodeBytes(this.T.toBytes())), MediaType.APPLICATION_JSON));
        Element _Q = pairing.getG1().newRandomElement();
        Element _P = pairing.getG1().newRandomElement();
        Element _T = pairing.getG1().newRandomElement();

        List<String> l = r.readEntity(new GenericType<List<String>>() {});
            _Q.setFromBytes(Base64.decode(l.get(0)));
            _P.setFromBytes(Base64.decode(l.get(1)));
            _T.setFromBytes(Base64.decode(l.get(2)));         
            this.setK(pairing, _Q, _P, _T);
    }
    
    public Element[] sign(Pairing pairing) throws NoSuchAlgorithmException{
       Element a = pairing.getZr().newRandomElement();
       Element r = pairing.getG2().newRandomElement();
       r = pairing.pairing(PKGC.duplicate().powZn(a.duplicate()), PKGC.duplicate());
       Element v = pairing.getZr().newRandomElement();
       Methods methods = new Methods();
       v = methods.H2("Message", r, pairing).duplicate();
       Element U = pairing.getG1().newRandomElement();
       U = SA.duplicate().powZn(v).add(PKGC.duplicate().powZn(a.duplicate()));
       
       
       Element[] signature = new Element[2];
       signature[0] = pairing.getG1().newRandomElement();
       signature[0] = U.duplicate();
       signature[1] = pairing.getZr().newRandomElement();
       signature[1] = v.duplicate();
       System.out.println(U + " " + v);
       return signature;
    }
    
    public boolean verifySignature(Pairing pairing, Element[] _P2003, Element _Q, Element[] signature) throws NoSuchAlgorithmException{
       Element YAneg = pairing.getG1().newRandomElement();
       YAneg = _P2003[1].duplicate().negate();
       Element e2 = pairing.getGT().newRandomElement();
       e2 = pairing.pairing(_Q.duplicate(), YAneg.duplicate());
       e2 = e2.duplicate().mulZn(signature[1]);
       Element e1 = pairing.getGT().newRandomElement();
       e1 = pairing.pairing(signature[0].duplicate(), PKGC.duplicate());
       Element r2 = pairing.getG2().newRandomElement();
       r2 = e1.duplicate().mul(e2.duplicate());
       Methods methods = new Methods();
       return signature[1].equals(methods.H2("Message", r2, pairing));
    }
}
