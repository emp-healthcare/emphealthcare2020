/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gateway.ReadFromSensor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

import org.springframework.stereotype.Repository;

@Repository
public class ReadFromSensor {

	List<String> dataSensor = new ArrayList<String>();
	private static Timer timer;

	public List<String> readFromSensor(String sensorName) throws IOException {

		System.out.println("Sensor : "+sensorName);
		List<String> data = new ArrayList<>();
		// Get Sensor Data :

		String SensorName = sensorName;
		String commande = "";
		String historyFile = "";

		String communicationSupport = "";

		// Bluetooth Data
		String hc05Url = "";
		OutputStream os = null;
		InputStream is = null;
		StreamConnection streamConnection = null;

		// Lire les data du sensor
		// Fichier de l'hystorique
		historyFile = "Ressources/History/history" + SensorName;

		// Ouvertur du fichier :
		BufferedReader in = new BufferedReader(new FileReader("Ressources/SensorsData/" + SensorName + ".txt"));
		String line;
		int i = 0;
		while ((line = in.readLine()) != null) {
			i++;
			switch (i) {
			case 2:
				commande = line;
				break;
			case 3:
				break;
			case 4:
				communicationSupport = line;
				break;
			case 5:
				if (communicationSupport.equals("Bluetooth")) {
					hc05Url = line;
				}
				break;

			}
		}

		// La lecture from Sensor

		if (communicationSupport.equals("Bluetooth")) { // En cas le suppurt est le bluetooth

			if (!commande.equals("")) {

				streamConnection = (StreamConnection) Connector.open(hc05Url);
				if (streamConnection != null) {
					os = streamConnection.openOutputStream();
					is = streamConnection.openInputStream();

					// Envoyer les commandes via bluetooth
					os.write(commande.getBytes()); // 49 en ASCII pour prendre les mesures de temperatures;50 pour ECG

					bluetoothListener listener = new bluetoothListener();
					data = listener.funct2(is, 5);
					 
					
					is.close();
				    os.write("0".getBytes()); //50 en ASCII
				    os.close();
				    streamConnection.close();

				} else {
					System.out.println("error connexion");
					return null;
				}

			}
		}

		return data;
	}

}
