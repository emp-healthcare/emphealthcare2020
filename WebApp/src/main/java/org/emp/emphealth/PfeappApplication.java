package org.emp.emphealth;

import javax.xml.bind.DatatypeConverter;

import org.emp.emphealth.crypto.AES;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PfeappApplication {

	public static void main(String[] args) {
		
		byte[] cleS = DatatypeConverter.parseHexBinary("95047166bd9e6e09708d23dbdc24bc8005f3b0aa0dbe8ee7380b28aba9cef5ca");
		try {
			AES.generateKey(cleS);
			System.out.println(DatatypeConverter.printHexBinary(AES.encrypt("35.3")));
			//System.out.println(AES.decrypt(AES.encrypt("34")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(PfeappApplication.class, args);
	}

}
