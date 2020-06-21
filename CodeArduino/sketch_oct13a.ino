   #include <OneWire.h>
  #include <DallasTemperature.h>
  #define ONE_WIRE_BUS 7
  OneWire oneWire(ONE_WIRE_BUS);
  DallasTemperature sensors(&oneWire);
  
  int ReceivedFromBT=0;
  int commande=48;
  int ECGPin1=10;
  int ECGPin2=11;


  void setup() { 
   Serial.begin(9600);
   sensors.begin();
   pinMode(ECGPin1,INPUT);
  pinMode(ECGPin2,INPUT);
  }
  
  void loop() {
    commande=48;
      if(commande==48){
      commande=listenBleutooth(48);
    }

    //La mesure de Température
    while(commande==49){
      mesureTemperature();
      commande=listenBleutooth(49);
      delay(500);
    }

    //La mesure de l'ECG
   while(commande==50){
      if((digitalRead(ECGPin1)==1)||(digitalRead(ECGPin2)==1)){
        Serial.print(0);
      }
      else{
        Serial.print(analogRead(A0)+100);
      }
      commande=listenBleutooth(50);
      delay(1000);
   }

   //la mesure de l'EMG
    while(commande==51){
      Serial.print(analogRead(A1));
      commande=listenBleutooth(51);
      delay(1000);
    
    }
    delay(200);
  }
  
  void mesureTemperature(){
    sensors.requestTemperatures();   
    Serial.print(sensors.getTempCByIndex(0));
  }
  
  int listenBleutooth(int current){
    if(Serial.available()>0){
      ReceivedFromBT=Serial.read();
      return ReceivedFromBT;
    }
    else
      return current;
  }
