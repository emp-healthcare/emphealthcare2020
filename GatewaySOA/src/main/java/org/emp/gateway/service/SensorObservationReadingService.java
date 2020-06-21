package org.emp.gateway.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.emp.gateway.DB.ObservationDAO;
import org.emp.gateway.ReadFromSensor.ReadFromSensor;
import org.emp.gateway.model.Identifiant;
import org.emp.gateway.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SensorObservationReadingService {

	private final ReadFromSensor RFS;
	private final ObservationDAO observationDao;

	@Autowired
	public SensorObservationReadingService(ReadFromSensor RFS,@Qualifier("fileDAO") ObservationDAO observationDao) {
		this.RFS=RFS;
		this.observationDao=observationDao;
	}
	
	 
	 public List<String> readFromSensor(String sensorName){
		 try {
			return RFS.readFromSensor(sensorName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	 }
	 
	 
	 public List<String> getSensorDataByDate(String sensorName,String date){
		 return observationDao.getSensorDataByDate(sensorName, date);		 
	 }

	 
	 
	 public List<String> getNotSentSensorData(String sensorName){
		 List<String> datesNonEnvoyesFilesNames = observationDao.getNotSentSensorData(sensorName);
		 List<String> response=new ArrayList<String>();
		 for(int i=0;i<datesNonEnvoyesFilesNames.size();i++) {		 
			 //response.add(datesNonEnvoyesFilesNames.get(i));		 
			 List<String> h = getSensorDataByDate(sensorName,datesNonEnvoyesFilesNames.get(i));
				 response.addAll(h);
		 }
			 
		 return response;
	 }
	 
	 
	 public boolean sendMail(Mail mail) {		
		return observationDao.sendMail(mail);
	 }
	
	
}
