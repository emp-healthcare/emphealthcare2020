package org.emp.emphealth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.emp.clpkc.CLPKC;
import org.emp.clpkc.Methods;
import org.emp.clpkc.ParamsEchange;
import org.emp.emphealth.core.resources.Capteur;
import org.emp.emphealth.core.resources.KeyStore;
import org.emp.emphealth.core.resources.Observation;
import org.emp.emphealth.crypto.AES;
import org.emp.emphealth.services.resources.CapteurService;
import org.emp.emphealth.services.resources.KeyStoreService;
import org.emp.emphealth.services.resources.ObservationService;
import org.emp.emphealth.services.resources.PersonneService;
import org.emp.emphealth.util.Identifiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.util.io.Base64;

@RestController
public class EHRManagerController {
	
	@Autowired
	PersonneService personneService;
	
	@Autowired
	ObservationService observationService;
	
	@Autowired
	CapteurService capteurService;
	
	@Autowired
	KeyStoreService keyStoreService;
	
	@PostMapping("/KeyExchange")
	public List<String> echange(@RequestBody ParamsEchange params) {
		
		Capteur capteur = capteurService.getCapteur(params.id).get();
		String Idproprio = capteur.getPatient().getIDPersonne();
		List<String> liste = new ArrayList<>();
		 CLPKC noeud2 = new CLPKC();
		try {
			java.io.InputStream initialStream = new ClassPathResource("paramsclpkc/" + Idproprio + ".txt").getInputStream();
			byte[] buffer = new byte[initialStream.available()];
		    initialStream.read(buffer);
		 
		    File targetFile = new File("tmp");
		    FileOutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
			
			//String url = KgcApplication.class.getClassLoader().getResource("params").getPath();
		    String url = targetFile.getPath();
            BufferedReader in = new BufferedReader(new FileReader(url.toString()));
            
           
            String line;
            int i=0;
            
            while ((line = in.readLine()) != null)
            {
                i++;
                switch(i){
                    case 1: //ID
                        noeud2.init(line);
                        noeud2.setID(line);
                        System.out.println("ID: " + noeud2.getID());
                        break;
                    case 2: //PKGC
                        Element PKGC= noeud2.pairing.getG1().newRandomElement();
                        PKGC.setFromBytes(Base64.decode(line));
                        noeud2.PKGC=PKGC.duplicate();
                        System.out.println("PKGC: "+ Base64.encodeBytes(noeud2.PKGC.toBytes()));
                        break;
                    case 3: //PPubKGC
                        Element PPubKGC=noeud2.pairing.getG1().newRandomElement();
                        PPubKGC.setFromBytes(Base64.decode(line));
                        noeud2.PPubKGC=PPubKGC.duplicate();
                        System.out.println("PPubKGC: "+ Base64.encodeBytes(noeud2.PPubKGC.toBytes()));
                        break;
                        
                    case 4: 
                        Element Q=noeud2.pairing.getG1().newRandomElement();
                        Q.setFromBytes(Base64.decode(line));
                        
                        noeud2.Q=Q.duplicate();
                        System.out.println("Q: "+ Base64.encodeBytes(noeud2.Q.toBytes()));
                        break; 
                    case 5:
                        Element D=noeud2.pairing.getG1().newRandomElement();
                        D.setFromBytes(Base64.decode(line));
                        noeud2.setD(D.duplicate());
                        System.out.println("D: "+ Base64.encodeBytes(noeud2.getD().toBytes()));
                        break;
                    case 6:
                        Element X=noeud2.pairing.getZr().newRandomElement();
                        X.setFromBytes(Base64.decode(line));
                        noeud2.setX(X.duplicate());
                        System.out.println("X: "+ Base64.encodeBytes(noeud2.getX().toBytes()));
                        break;
                    case 7:
                        Element P=noeud2.pairing.getG1().newRandomElement();
                        P.setFromBytes(Base64.decode(line));
                        noeud2.P = P.duplicate();
                        System.out.println("P: "+ Base64.encodeBytes(noeud2.P.toBytes()));
                        break;
                    case 10:
                        Element SPrime=noeud2.pairing.getG1().newRandomElement();
                        SPrime.setFromBytes(Base64.decode(line));
                        
                        noeud2.setSPrime(SPrime.duplicate());
                        System.out.println("SPrime: "+ Base64.encodeBytes(SPrime.toBytes()));
                        System.out.println(Base64.encodeBytes((noeud2.getD().duplicate().add(noeud2.Q.duplicate().powZn(noeud2.getX().duplicate()))).toBytes()));
                        break;
                }
            }
            
            System.err.println("sucess EMR");
        } catch (FileNotFoundException ex) {
        	System.out.println("Paramètres non-trouvé");
        	liste.add("Paramètres non-trouvé");
        	return liste;
        } catch (Exception ex) {
            Logger.getLogger(EHRManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		noeud2.setT(noeud2.pairing);
		
		try {
			Element _Q = noeud2.pairing.getG1().newRandomElement();
			_Q.setFromBytes(Base64.decode(params.Q.getBytes()));
			Element _P = noeud2.pairing.getG1().newRandomElement();
			_P.setFromBytes(Base64.decode(params.P.getBytes()));
			Element _T = noeud2.pairing.getG1().newRandomElement();
			_T.setFromBytes(Base64.decode(params.T.getBytes()));
			noeud2.setK(noeud2.pairing, _Q, _P, _T);
		} catch (IOException e) {
			e.printStackTrace();
		}
		liste.add(Base64.encodeBytes(noeud2.Q.toBytes()));
		liste.add(Base64.encodeBytes(noeud2.P.toBytes()));
		liste.add(Base64.encodeBytes(noeud2.T.toBytes()));
		System.out.println(params.id);
		
		List<KeyStore> tmp = new ArrayList<>();
		tmp = keyStoreService.getListkeys(capteur);
		
		System.out.println(Arrays.toString(tmp.toArray()));
		if(tmp.size()!=0) {
		KeyStore tmp2 = new KeyStore();
		
		Collections.sort(tmp);
		
		
		tmp2 = tmp.get(tmp.size()-1);
		tmp2.setDateRevocation(new Timestamp(new Date().getTime()));
		keyStoreService.addKeyStore(tmp2);
		tmp.clear();
		}
		KeyStore keyStore = new KeyStore();
		
		
		String skey = DatatypeConverter.printHexBinary(noeud2.K.toBytes());
		System.out.println(skey);
		try {
			keyStore.setCle(new Methods().H4(skey));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(skey);
		keyStore.setDateCreation(new Timestamp(new Date().getTime()));
		keyStore.setCapteur(capteur);
		
		
		keyStoreService.addKeyStore(keyStore);
		
		
		return liste;
	}
	
	
	@PostMapping("/Refresh")
	public ModelAndView refresh(@ModelAttribute("capteur") Capteur ca) {
		
		Capteur c = capteurService.getCapteur(ca.getIDCapteur()).get();
		
		//Construire l'objet id
		Identifiant identifiant = new Identifiant();
		
		identifiant.setIdsensor(c.getIDCapteur());
		KeyStore tmp2 = new KeyStore();
		Collections.sort(c.getCles());
		
		tmp2 = c.getCles().get(c.getCles().size()-1);
		System.out.println("cle "  + tmp2.getCle());
		byte[] cleS = DatatypeConverter.parseHexBinary(tmp2.getCle());
		
		try {
			AES.generateKey(cleS);
			identifiant.setIdChiffre(DatatypeConverter.printHexBinary(AES.encrypt("Hopital@emp.dz")));
			String timestamp = new Timestamp(new Date().getTime()).toString();
			String timestampChiffre = DatatypeConverter.printHexBinary(AES.encrypt(timestamp));
			identifiant.setTimestamp(timestampChiffre);
			String hashc = new Methods().H3("Hopital@emp.dz+"+timestamp);
			identifiant.setHash(hashc);
			
			System.out.println(identifiant.toString());
			
			Client client = ClientBuilder.newClient();
	        Response r = client.target("http://"+c.getPatient().getAdresseGateway()+"/api/gateway/Refresh").request(MediaType.APPLICATION_JSON).post(Entity.entity(identifiant, MediaType.APPLICATION_JSON));
	        
			
	        List<String> l = r.readEntity(new GenericType<List<String>>() {});
	        String timestampRetour = l.get(1);
            
	        for(int i = 3; i < l.size();i=i+2) {
	        	String s = AES.decrypt(DatatypeConverter.parseHexBinary(l.get(i)));
	        	System.out.println("s "+s);
	        	//System.out.println(new Timestamp(new Date().getTime()));
	        	observationService.addObservation(new Observation("",Timestamp.valueOf(s),l.get(i+1), c));
	        }
            
            
			//System.out.println(AES.decrypt(AES.encrypt("34")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Envoyer la requête
		//Faire la redirection
		//AppController app = new AppController();
		//System.out.println(c.getIDCapteur());
		//app.consulterCapteur(c.getIDCapteur());
		//return "redirect:/capteurs/"+c.getIDCapteur();
		return new ModelAndView("redirect:/capteurs/"+c.getIDCapteur());
	}
	
	
}
