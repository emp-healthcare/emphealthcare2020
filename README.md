# EMPHealthcare2020
Ce projet consiste en la réalisation d'une plateforme sécurisée de télésurveillance médicale.

## Motivation
La propagation du virus SARS-CoV-2, virus provoquant la maladie baptisée Covid-19, se présente comme l'un des évènements les plus marquants de ce début de millénaire. Pour réussir à vaincre ce virus de la famille des coronavirus, deux approches ont été présentées. La première consiste à laisser le virus se propager au maximum dans la population en espérant atteindre une immunité de groupe. La seconde consiste à freiner au maximum la propagation du virus en maintenant le taux R<sub>0</sub> qui représente le nombre moyen de personnes qu’une personne contagieuse peut infecter proche de 0. La seconde méthode est celle qui a été adoptée par la majorité des pays du monde, et pour la mettre en place, ces derniers ont appliqué une politique massive  de confinement.

La politique de confinement, qu'il s'agisse d'un confinement total ou partiel, a pour objectif la réduction maximale des interactions physiques entre les populations. Elle est généralement mise en exécution par la fermeture des transports, des établissements scolaires et autres lieux de rassemblement. Pour assurer la continuité des services essentiels comme l'éducation ou la santé, le recours aux solutions virtuelles n'est plus une option mais une nécessité. La télémédecine, qui existe depuis de nombreuses années et qui a bien évolué depuis, se présente ainsi comme quasi-indispensable aujourd'hui. La possibilité de pouvoir réaliser des consultation à distance dans le cadre de la téléconsultation ou encore celle de pouvoir suivre l'évolution de la maladie d'un patient dans le cadre de la télésurveillance médicale est un avantage énorme dans une situation de confinement.

## Fonctionnalités
<ul>
   <li> La capacité d'établir une connexion sans fil entre le l'application sur le gateway et les capteurs; </li>
    <li>  La possibilité de transmettre les données mesurées du gateway au centre de santé; </li>
    <li>  La possibilité pour le centre de santé et pour le patient d'émettre des requêtes; </li>
    <li>  La possibilité pour le patient d'interagir avec un médecin au niveau de l'hôpital à l'aide de messages; </li>
    <li>  La possibilité de traiter et d'analyser les données mesurées à travers des graphes, et alerter le médecin si il y'a un risque lié à l'état de santé d'un patient; </li>
    <li>  La possibilité d'intégrer les mesures d'un patient à son dossier médical électronique; </li>
    <li>  La possibilité pour le patient de réaliser une vidéoconférence avec un médecin au niveau du centre de santé; </li>
    <li>  La possibilité pour le centre de santé de se connecter au gateway et de récupérer les mesures prélevées sur le patient. </li>
</ul>
  
## Architecture globale de la plateforme

Notre projet est constitué de 2 environnements: un environnement du patient et un environnement hospitalier (ou prestataire de santé en général).

![Architecture globale de notre plateforme](/Misc/images/archglo.png "Architecture globale de notre plateforme")

## Composants du système
L'architecture globale de la plateforme présentée précédemment nous laisse envisager une division de notre système en 3 parties: la partie concernant l'ensemble des technologies utilisées pour assurer les différents services, l'environnement du patient et l'environnement hospitalier.

### Couche des technologies
Cette partie, bien qu'invisible aux utilisateurs, c'est celle qui assure le fonctionnement et la sécurité de la plateforme. Elle représente l'ensemble des technologies sur lesquelles s'appuie la plateforme pour fonctionner. Nous avons principalement les technologies de communication et les techniques de sécurité.

![Architecture en couches](/Misc/images/archcouches.png "Architecture en couches")


La liaison entre l'environnement du patient et l'environnement hospitalier se fait par liaison Internet grâce à des services web. Nous avons également développé les APIs qui permettent aux applications clients de communiquer avec la plateforme. En plus de cela nous avons développé les librairies pour assurer la sécurité, librairies qui sont le résultat de la mise en pratique des protocoles que nous avons mis en place. Nous détaillerons tout cela dans le prochain chapitre.

### Environnement du patient
L'environnement du patient est présenté dans la figure \ref{ban}. Il peut être divisé en 3 parties: le dispositif de capture, le gateway et une application mobile.

![Environnement du patient](/Misc/images/Disp.png "Environnement du patient")


#### Le dispositif de capture
Le dispositif de capture se compose d'un ensemble de capteurs physiologiques placés sur le corps du patient. Ces capteurs transmettent les données collectées au Gateway chargé sur de les transférer à l'hôpital via Internet après certains traitements. Il s'agit donc d'un réseau corporel ou BAN ayant pour élément central le gateway.
 
#### Le gateway
Il s'agit de l'élément central et essentiel de l'environnement du patient. Il communique de manière sécurisée avec  à la fois le dispositif de capture et l'environnement hospitalier. Il transfère les mesures provenant des capteurs à l'hôpital. Il dispose d'une application qui lui permet aussi de manipuler les différents capteurs pour démarrer ou arrêter des prises de mesure ou encore visualiser les mesures sous forme de graphe.

### Environnement hospitalier
Au niveau du l'environnement de l'hôpital il existe trois composants principaux (figure \ref{hos}): l'EHRManager, l'application web et la base de données.

![Environnement hospitalier](/Misc/images/hospital.png "Environnement hospitalier")


#### L'EHRManager
L'EHRManager est le serveur web chargé de gérer tout l'environnement de l'hôpital. C'est lui qui communique avec le gateway et l'application mobile du patient. Il est donc un des acteurs de tous les protocoles de communication sécurisés mis en place. C'est également lui qui héberge l'application web du côté de l'hôpital. Il gère également la communication avec la base de données. C'est lui en plus qui s'occupe du contrôle d'accès et de la gestion des clés de chiffrements. On peut le réaliser grâce à un ou plusieurs serveurs physiques pour éviter qu'il y'ait un goulot d'étranglement dans notre système.

#### L'application web
L'application web est celle qui est utilisée au niveau de l'hôpital par les membres du personnel de santé. C'est cette application qui permet au médecin d'analyser les données provenant des capteurs à l'aide de graphes. Elle communique avec le gateway et l'application mobile du patient par l'intermédiaire de l'EHRManager. Elle peut fournir d'autres fonctionnalités comme la téléconsultation par vidéoconférence, un service de messagerie ou encore un service de prise de rendez-vous.

![Application web de la plateforme](/Misc/images/appli.png "Application web de la plateforme")


#### La base de données
La base de données peut être divisée en 3 parties. La première partie est celle rassemblant les informations du dossier médical électronique. La deuxième est celle concernant les capteurs et les mesures effectuées par les capteurs. La troisième partie concerne les clés de chiffrement des données. Chaque partie possède ses règles et droits d'accès. Elle est gérée par l'EHRManager.

![Base de données globale de la plateforme](/Misc/images/database.png "Base de données globale de la plateforme")

## Captures d'écran

### Gateway Raspberry Pi
![ECG](/Misc/images/histECG.png "ECG")

![Symptômes](/Misc/images/Symp.png "Symptômes")

### Application web

![Accueil](/Misc/images/accueil.png "Accueil")

![Télésurveillance médicale](/Misc/images/Patient.png "Télésurveillance médicale")

![Dossier médical électronique](/Misc/images/patient2.png "Dossier médical électronique")

