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

notre projet est constitué de 2 environnements: un environnement du patient et un environnement hospitalier (ou prestataire de santé en général).
