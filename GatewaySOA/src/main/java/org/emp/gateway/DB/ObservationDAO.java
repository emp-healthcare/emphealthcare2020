/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gateway.DB;

import java.util.ArrayList;
import java.util.List;

import org.emp.gateway.model.Identifiant;
import org.emp.gateway.model.Mail;
import org.emp.gateway.model.Observation;


public interface ObservationDAO {
	public List<String> getSensorDataByDate(String sensorName, String date);

	public List<String> getNotSentSensorData(String sensorName);
	
	public boolean sendMail(Mail mail);
}
