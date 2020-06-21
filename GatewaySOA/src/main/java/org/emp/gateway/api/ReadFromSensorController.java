package org.emp.gateway.api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.emp.emphealth.crypto.AES;
import org.emp.gateway.model.Identifiant;
import org.emp.gateway.model.Mail;
import org.emp.gateway.service.SensorObservationReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/gateway")
@RestController
public class ReadFromSensorController {

	private final SensorObservationReadingService sobrs;
	private String keyStringHex = null;

	@Autowired
	public ReadFromSensorController(SensorObservationReadingService sobrs) {
		this.sobrs = sobrs;
	}

	// Get Data From a sensor (RealTime)
	@GetMapping("/SensorData/{sensorName}")
	public List<String> readFromSensor(@PathVariable(name = "sensorName") String sensorName) {
		List<String> s = sobrs.readFromSensor(sensorName);
		return s;
	}

/*	// Get Data From a sensor (RealTime) avec l'authentification
	@PostMapping("/SensorData/{sensorName}")
	public List<byte[]> readFromSensor(@PathVariable(name = "sensorName") String sensorName,
									   @RequestBody Identifiant id) {
		
		List<String> s = new ArrayList<String>();
		List<byte[]> response = new ArrayList<byte[]>();
		String keyStringHex = null;

		// Chiffrement :
		// Chercher la clé :

		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader("SymetricKeys/" + sensorName + "Key.txt"));
			String line;
			while ((line = in.readLine()) != null) {
				keyStringHex = line;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (auth(id)) {
			s = sobrs.readFromSensor(sensorName);
			byte[] keyBytes = DatatypeConverter.parseHexBinary(keyStringHex);
			try {
				AES.generateKey(keyBytes);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < s.size(); i++) {
				byte[] cipher;
				try {
					cipher = AES.encrypt(s.get(i));
					response.add(cipher);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// System.out.println(AES.decrypt(cipher));

		} else
			response = null;

		return response;
	}
*/
	// Get Data By Date From a sensor avec l'authentification
	@PostMapping("/SensorDataByDate/{sensorName}/{date}")
	public List<String> getSensorDataByDate(@PathVariable(name = "sensorName") String sensorName,
			@PathVariable(name = "date") String date, @RequestBody Identifiant id) {

		List<String> s = new ArrayList<String>();
		List<String> response = new ArrayList<String>();

		if (auth(id)) {
			s = sobrs.getSensorDataByDate(sensorName, date);
			// Chiffrement :
			// Chercher la clé :
			try {
				BufferedReader in = new BufferedReader(new FileReader("SymetricKeys/" + sensorName + "Key.txt"));
				String line;
				while ((line = in.readLine()) != null) {
					keyStringHex = line;
				}

				byte[] keyBytes = DatatypeConverter.parseHexBinary(keyStringHex);
				AES.generateKey(keyBytes);
				for (int i = 0; i < s.size(); i++) {
					byte[] cipher = AES.encrypt(s.get(i));
					response.add(DatatypeConverter.printHexBinary(cipher));
				}

				// System.out.println(AES.decrypt(cipher));

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Not found");
				// e.printStackTrace();
				response = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("Not found");
				response = null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response = null;
			}
		} else
			response = null;
		;

		return response;
	}

	
	
	
	
//Read in realTime from the Sensor :
	@PostMapping("ReadFromSensor")
	public List<String> readFromSensor(@RequestBody Identifiant id) {
		String sensorName=id.getIdsensor();
		List<String> response = new ArrayList<String>();
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader("Ressources/SymetricKeys/" + id.getIdsensor().toString() + "Key.txt"));
			
			String line;
			while ((line = in.readLine()) != null) {
				keyStringHex = line;
			}
			if(auth( id)) {
				byte[] keyBytes = DatatypeConverter.parseHexBinary(keyStringHex);				
				AES.generateKey(keyBytes);
				
				//Ajouter l'ID du patient chiffré par la clé symetrique du capteur demandé				
				in = new BufferedReader(new FileReader("Ressources/Patient.txt"));
				String line2;
				String patient = "de";
								
				while ((line2 = in.readLine()) != null) {
					patient = line2;
				}				
				
				response.add(DatatypeConverter.printHexBinary(AES.encrypt(patient)));
	
				//Ajouter TimeStamp chiffré par la clé symetrique du capteur demandé
				response.add(DatatypeConverter.printHexBinary(AES.encrypt(id.getTimestamp())));
			
				String somme=patient+"+"+(id.getTimestamp());
				String hash=H(somme);
				response.add(hash);
				
				List<String> s = sobrs.readFromSensor(sensorName);				
				byte[] cipher;
				cipher = AES.encrypt( s.get( s.size()-1));				
				String valueString=DatatypeConverter.printHexBinary(cipher);
				response.add(valueString);		
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return response;
	}	
	
//getAllNotSentDATA from the gateway :
	@PostMapping("Refresh")
	public List<String> getNotSentSensorData(@RequestBody Identifiant id) {
		
		System.out.println("Identifiant :");
		System.out.println("\t idSensor: "+id.getIdsensor());
		System.out.println("\t IdChiffre: "+id.getIdChiffre());
		System.out.println("\t TimeStamp: "+id.getTimestamp());
		System.out.println("\t Hash: "+id.getHash());
		
		BufferedReader in;
		List<String> responseDAO=null ;
		List<String> response = new ArrayList<String>();
		
		try {
			in = new BufferedReader(new FileReader("Ressources/SymetricKeys/" + id.getIdsensor().toString() + "Key.txt"));
			String line;
			while ((line = in.readLine()) != null) {
				keyStringHex = line;
			}
			
			System.out.println("Symetric Key: "+keyStringHex);
			
			if(auth( id)) {
				responseDAO= sobrs.getNotSentSensorData(id.getIdsensor().toString());	
				
				byte[] keyBytes = DatatypeConverter.parseHexBinary(keyStringHex);
				
				AES.generateKey(keyBytes);
				
				
				//Ajouter l'ID du patient chiffré par la clé symetrique du capteur demandé				
				in = new BufferedReader(new FileReader("Ressources/Patient.txt"));
				String line2;
				String patient = "";
				
				
				while ((line2 = in.readLine()) != null) {
					patient = line2;
				}				
				
				response.add(DatatypeConverter.printHexBinary(AES.encrypt(patient)));
	
				//Ajouter TimeStamp chiffré par la clé symetrique du capteur demandé
				response.add(DatatypeConverter.printHexBinary(AES.encrypt(id.getTimestamp())));
			
				String somme=patient+"+"+(id.getTimestamp());
				String hash=H(somme);
				response.add(hash);
				
				
				// Chiffrer les observations valeur par valeur
				for(int i=0;i<responseDAO.size();i++) {
					byte[] cipher;
					cipher = AES.encrypt(responseDAO.get(i));
					
					String valueString=DatatypeConverter.printHexBinary(cipher);
					response.add(valueString);
				}
				
				return response;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	
	@PostMapping("SendMail")
	
	public boolean sendMail(@RequestBody Mail mail) {		
		return sobrs.sendMail(mail);	
	}

	private boolean auth(Identifiant id) {
		
		byte[] keyBytes = DatatypeConverter.parseHexBinary(keyStringHex);
		
		try {/*
				AES.generateKey(keyBytes);
			
			 	String idClair = AES.decrypt(DatatypeConverter.parseHexBinary(id.getIdChiffre())) ;
				String timestampClair = AES.decrypt(DatatypeConverter.parseHexBinary(id.getTimestamp())) ;
				
				String s=idClair+"+"+timestampClair;
				
				String h=H(s);
				System.out.println(h);
				System.out.println(id.getHash());
				if(h.equals(id.getHash()))*/
					return true;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return false;
	}

	private static String H(String ID) throws NoSuchAlgorithmException {
		byte[] btID = ID.getBytes();

		MessageDigest hasher = MessageDigest.getInstance("SHA-512");
		byte[] hashBytes = hasher.digest(btID);
		return DatatypeConverter.printHexBinary(hashBytes);
	}
}
