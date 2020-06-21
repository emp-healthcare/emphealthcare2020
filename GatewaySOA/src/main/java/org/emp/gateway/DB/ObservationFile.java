/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gateway.DB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.emp.gateway.model.Mail;
import org.emp.gateway.model.Observation;
import org.springframework.stereotype.Repository;


@Repository("fileDAO") 
public class ObservationFile implements ObservationDAO{

	@Override
	public List<String> getSensorDataByDate(String sensorName, String date) { //ça chercher dans l'historique non envoyé à l'hopital
		// TODO Auto-generated method stub
		List<String> s =new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("Ressources/HistoryNotSent/" +sensorName+"/" + date + ".txt"));
			String line;
			while ((line = in.readLine()) != null) {
				s.add(line);
			}
			in.close();
			
			File file = new File("Ressources/HistoryNotSent/" +sensorName+"/" + date + ".txt");
			file.delete();
			
			return s;
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("file not found");
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	
	/*
	@Override
	public List<String> getNotSentSensorData(String sensorName) {
		// TODO Auto-generated method stub
		
		List<String> response=new ArrayList<String>();
		
		// Charger tout les noms de fichier dans le dossier historique :
		List<String> s1 = getFolderFilesName("History/"+sensorName);
		
		System.out.println("History :");
		for(int i=0;i<s1.size();i++)
			System.out.println("\t"+s1.get(i));
		
		// Charger tout les noms de fichier dans le dossier historique envoyé :
			
		List<String> s2 = getFolderFilesName("HistorySent/"+sensorName);
		System.out.println("Histor Senty :");
		for(int i=0;i<s2.size();i++)
			System.out.println("\t"+s2.get(i));
		
		//Pour chaque nom qui n'existe pas dans le historique Envoyé envoyé le nom du fichier
		
		for(int i=0;i<s1.size();i++) {
            if(!exist(s1.get(i),s2)) {
                //Envoyer les valeurs de ce fichier :
            	response.add(s1.get(i));
            }
		}
					
		return response;
	}
*/
   
	private boolean exist(String date, List<String> lesDatesEnvoyées) {
		for(int i=0;i<lesDatesEnvoyées.size();i++) {
			if(date.equals(lesDatesEnvoyées.get(i)))
				return false;
		}
		
		return true;
	}
	

	private List<String> getFileValues(String fileName) throws IOException{
		List<String> values = new ArrayList<String>();
        BufferedReader in = null;    
        in = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = in.readLine()) != null) {
        		values.add(line);
            }  
        in.close();
        return values;
	}
	
	private List<String> getFolderFilesName(String folderName) {
        File repertoire = new File(folderName);
        File[] listeFilePath = repertoire.listFiles();
        List<String> s = new ArrayList<String>();
        for (int i = 0, num = 0; i < listeFilePath.length; i++) {
            s.add(listeFilePath[i].getName().replaceAll(".txt", ""));
        }
        return s;
    }
    
	
	
	@Override
	public List<String> getNotSentSensorData(String sensorName){			
		List<String>  s1 = getFolderFilesName("Ressources/HistoryNotSent/"+sensorName);
		return s1;
		
	}


	@Override
	public boolean sendMail(Mail mail) {
		try {
			PrintWriter writer = new PrintWriter("Ressources/inbox/"+mail.getSubject()+".txt", "UTF-8");
			writer.println("Time :\t"+mail.getTime()); 
			writer.println("From :\t"+mail.getFrom()); 
			writer.println("Subject :\t"+mail.getSubject()); 
			writer.println("\t"+mail.getText()); 
			writer.close();
			return true;
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
}
