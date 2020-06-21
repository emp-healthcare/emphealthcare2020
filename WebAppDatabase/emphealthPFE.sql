-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 21, 2020 at 02:43 PM
-- Server version: 5.7.30-0ubuntu0.16.04.1
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `emphealthPFE`
--

-- --------------------------------------------------------

--
-- Table structure for table `adressepersonne`
--

CREATE TABLE `adressepersonne` (
  `id_adresse` int(11) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `ville` varchar(50) NOT NULL,
  `region` varchar(50) NOT NULL,
  `bp` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `adressepersonne`
--

INSERT INTO `adressepersonne` (`id_adresse`, `adresse`, `ville`, `region`, `bp`) VALUES
(1, 'Centre commercial', 'Bab Ezzouar', 'Alger', '21'),
(2, 'Fouzi', 'Bordj El Bahri', 'Alger', '17'),
(3, 'Coopérative Immo Tassili', 'Dar El Beida', 'Alger', '16012'),
(4, 'Villa N°03 Rte de Bir Morad Rais Vieux Kouba', 'Alger', 'Alger', '56');

-- --------------------------------------------------------

--
-- Table structure for table `allergene`
--

CREATE TABLE `allergene` (
  `allergene` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `allergene`
--

INSERT INTO `allergene` (`allergene`) VALUES
('ACARIENS'),
('ARACHIDE'),
('BLATTES ET CAFARDS'),
('BOEUF'),
('CRUSTACES'),
('LAIT'),
('LATEX'),
('LAVANDE'),
('MOISISSURES'),
('MOUTARDE'),
('NOISETTE'),
('NOIX DE COCO'),
('OEUF'),
('POILS DE CHAT'),
('POILS DE LAPIN'),
('POISSON'),
('POLLENS'),
('SOJA, LENTILLES, POIS');

-- --------------------------------------------------------

--
-- Table structure for table `allergie`
--

CREATE TABLE `allergie` (
  `id_allergie` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `allergene` varchar(50) NOT NULL,
  `date_ajout` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `commentaire` text NOT NULL,
  `severite` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `allergie`
--

INSERT INTO `allergie` (`id_allergie`, `id_patient`, `allergene`, `date_ajout`, `commentaire`, `severite`) VALUES
(1, 2, 'CRUSTACES', '2020-03-17 11:39:30', 'Allergie cutanée après avoir consommé des crustacés.', 'MOYENNE'),
(2, 2, 'NOIX DE COCO', '2020-03-17 11:38:25', ' Nausées après avoir consommé un produit contenant de la noix de coco.', 'FAIBLE');

-- --------------------------------------------------------

--
-- Table structure for table `capteur`
--

CREATE TABLE `capteur` (
  `id_capteur` varchar(255) NOT NULL,
  `fabricant` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `modele` varchar(50) NOT NULL,
  `id_personne` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `demandeur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `capteur`
--

INSERT INTO `capteur` (`id_capteur`, `fabricant`, `nom`, `modele`, `id_personne`, `type`, `demandeur`) VALUES
('capteur.ecg.2@emp.dz', 'FAB', 'DAETR', '2015', 2, 'ELECTRO-CARDIOGRAMME', 1),
('capteur.temperature.2@emp.dz', 'Dallas', 'DS18B20', '2010', 2, 'TEMPERATURE', 1);

-- --------------------------------------------------------

--
-- Table structure for table `chirurgie`
--

CREATE TABLE `chirurgie` (
  `id_chirurgie` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `description` text NOT NULL,
  `cause` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id_praticien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chirurgie`
--

INSERT INTO `chirurgie` (`id_chirurgie`, `id_patient`, `description`, `cause`, `date`, `id_praticien`) VALUES
(1, 2, 'Ablation de l\'appendice', 'Appendicite', '2020-03-17 15:39:51', 5);

-- --------------------------------------------------------

--
-- Table structure for table `dents`
--

CREATE TABLE `dents` (
  `id_dents` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id_patient` int(11) NOT NULL,
  `nom_dentiste` text NOT NULL,
  `maladie` text NOT NULL,
  `commentaire` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dents`
--

INSERT INTO `dents` (`id_dents`, `date`, `id_patient`, `nom_dentiste`, `maladie`, `commentaire`) VALUES
(1, '2020-03-18 15:43:11', 2, 'BAHOULI Lokman', 'Aphte', 'Ulcère superficiel sur les muqueuses à l’intérieur de la bouche.');

-- --------------------------------------------------------

--
-- Table structure for table `examen`
--

CREATE TABLE `examen` (
  `id_examen` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `id_demandeur` int(11) NOT NULL,
  `id_responsable` int(11) NOT NULL,
  `nom_responsable` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` text NOT NULL,
  `statut` text NOT NULL,
  `resultat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `examen`
--

INSERT INTO `examen` (`id_examen`, `id_patient`, `id_demandeur`, `id_responsable`, `nom_responsable`, `date`, `type`, `statut`, `resultat`) VALUES
(1, 2, 1, 7, 'MBENG Jean', '2020-03-18 20:33:27', 'Prise de sang', 'FIN', 'Glycémie à jeun: 1.27 g/l');

-- --------------------------------------------------------

--
-- Table structure for table `imagerie`
--

CREATE TABLE `imagerie` (
  `id_imagerie` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `id_demandeur` int(11) NOT NULL,
  `id_responsable` int(11) NOT NULL,
  `statut` text NOT NULL,
  `type` text NOT NULL,
  `url` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nom_responsable` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `imagerie`
--

INSERT INTO `imagerie` (`id_imagerie`, `id_patient`, `id_demandeur`, `id_responsable`, `statut`, `type`, `url`, `date`, `nom_responsable`) VALUES
(1, 2, 1, 6, 'FIN', 'Radiographie des poumons', '2/x_ray.jpg', '2020-03-18 15:36:41', 'OTIS Ange');

-- --------------------------------------------------------

--
-- Table structure for table `keystore`
--

CREATE TABLE `keystore` (
  `id_keystore` int(11) NOT NULL,
  `cle` text NOT NULL,
  `date_creation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_revocation` timestamp NULL DEFAULT NULL,
  `id_capteur` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `keystore`
--

INSERT INTO `keystore` (`id_keystore`, `cle`, `date_creation`, `date_revocation`, `id_capteur`) VALUES
(1, '95047166bd9e6e09708d23dbdc24bc8005f3b0aa0dbe8ee7380b28aba9cef5ca', '2020-03-12 13:26:23', NULL, 'capteur.temperature.2@emp.dz'),
(3, '26FBAE931274B157AC2BBA857F6F9D410560FA9FF80867F520123D83DAE4F7C1', '2020-03-14 21:26:12', NULL, 'capteur.ecg.2@emp.dz');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id_message` int(11) NOT NULL,
  `emetteur` int(11) NOT NULL,
  `recepteur` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `contenu` text NOT NULL,
  `statut` text NOT NULL,
  `objet` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id_message`, `emetteur`, `recepteur`, `date`, `contenu`, `statut`, `objet`) VALUES
(1, 1, 2, '2020-03-19 08:25:24', 'Message de test de connection.', 'NON-LU', 'Test d\'envoi'),
(2, 2, 1, '2020-03-19 08:25:35', 'Connection réussie.', 'NON-LU', 'Ack'),
(3, 2, 1, '2020-03-19 08:25:52', 'Premier test de connection.', 'LU', 'Test de connection');

-- --------------------------------------------------------

--
-- Table structure for table `mesure`
--

CREATE TABLE `mesure` (
  `type` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `unite` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mesure`
--

INSERT INTO `mesure` (`type`, `description`, `unite`) VALUES
('ELECTRO-CARDIOGRAMME', 'Capture les pulsations électriques cardiaque.', ''),
('ELECTRO-ENCEPHALOGRAMME', 'Capture les ondes cérébrales.', ''),
('ELECTRO-MYOGRAMME', 'Captures les signaux électriques musculaires.', ''),
('POULS', 'Capture le flux sanguin.', 'puls/min'),
('TEMPERATURE', 'Capture de la température.', '°C');

-- --------------------------------------------------------

--
-- Table structure for table `metier`
--

CREATE TABLE `metier` (
  `metier` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `metier`
--

INSERT INTO `metier` (`metier`, `description`) VALUES
('AIDE-SOIGNANT', 'Professionel qui dispense des soins liés aux fonctions d\'entretien et de continuité de la vie.'),
('AMBULANCIER', 'Professionel médicotechnique qui transporte et accompagne, des malades et blessés dans des véhicules sanitaires.'),
('AUDIOPROTHESISTE', 'Professionnel de la rééducation et de la réadaptation qui procède à l\'appareillage des déficients de l\'ouïe.'),
('CHIRURGIEN', 'Effectue des opérations chirurgicales'),
('DENTISTE', 'Assure la prévention, le diagnostic et le traitement des maladies de la bouche et des dents.'),
('DIETETICIEN', 'Professionnel de la rééducation et de la réadaptation qui dispense des conseils nutritionnels.'),
('ERGOTHERAPEUTE', 'Professionnel de la rééducation et de la réadaptation qui exécute des actes d\'ergothérapie.'),
('INFIRMIER', 'Donne des soins infirmiers sur prescription ou conseil médical.'),
('MASSEUR-KINESITHERAPEUTE', 'Professionnel de la rééducation et de la réadaptation habilité à pratiquer le massage et la gymnastique médicale.'),
('MEDECIN GENERALISTE', 'Assure la prévention, le diagnostic et le traitement des maladies de l\'ensemble du corps humain.'),
('OPTICIEN-LUNETIER', 'Professionnel de la rééducation et de la réadaptation qui fabrique, qui vend des instruments d\'optique.'),
('ORTHOPHONISTE', 'Professionnel qui exécute des actes de rééducation constituant un traitement des anomalies de nature pathologique de la voix, de la parole...'),
('ORTHOPTISTE', 'Professionnel de la rééducation et de la réadaptation qui exécute des actes d\'orthoptie.'),
('PEDICURE-PODOLOGUE', 'Professionnel de la rééducation et de la réadaptation formé pour traiter directement les affections épidermiques.'),
('PHARMACIEN', 'Professionnel expert dans la composition, préparation et contrôle des médicaments.'),
('PHYSICIEN MEDICAL', 'Professionnel expert dans la physique des rayonnements ionisants et non-ionisant.'),
('PSYCHOMOTRICIEN', 'Professionnel de la rééducation et de la réadaptation qui exécute des actes de rééducation psychomotrice.'),
('RADIOLOGUE', 'Médecin spécialiste des techniques de l’imagerie médicale : clichés radio, échographies, scanners et IRM.'),
('SAGE-FEMME', 'Assure la pratique des actes nécessaires au diagnostic, à la surveillance de la grossesse.'),
('TECHNICIEN DE LABORATOIRE MEDICAL', 'Professionnel médicotechnique qui participe à la réalisation technique d\'examens en laboratoire.');

-- --------------------------------------------------------

--
-- Table structure for table `observation`
--

CREATE TABLE `observation` (
  `id_observation` int(11) NOT NULL,
  `date_observation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valeur` varchar(255) NOT NULL,
  `id_capteur` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `observation`
--

INSERT INTO `observation` (`id_observation`, `date_observation`, `valeur`, `id_capteur`) VALUES
(1, '2020-03-10 06:28:19', 'F581E8FE203E3BDD3317C1CD1BFDF39D', 'capteur.temperature.2@emp.dz'),
(2, '2020-03-10 11:28:36', '8B188A8C132A1D5B36014C83EDD0144D', 'capteur.temperature.2@emp.dz'),
(3, '2020-03-10 18:28:52', '7C99E102BE684CE25DFF6D13EC467640', 'capteur.temperature.2@emp.dz'),
(4, '2020-03-11 07:29:07', 'E2867FCCF459BE952F0DFC47EAB7C1FC', 'capteur.temperature.2@emp.dz'),
(5, '2020-03-11 12:29:23', '88659E3E11C3C16DE874784D77E07971', 'capteur.temperature.2@emp.dz'),
(6, '2020-03-11 17:29:39', 'AE8B597ED51AC6E051A8B5D9BCEBB23A', 'capteur.temperature.2@emp.dz'),
(7, '2020-03-12 07:40:38', '4C88C590D1620AD04302B72832075BBD', 'capteur.temperature.2@emp.dz'),
(8, '2020-03-12 11:40:54', 'D5F9E98C53861A9EB7CAA7A41638BC75', 'capteur.temperature.2@emp.dz'),
(9, '2020-03-12 18:41:12', 'E2867FCCF459BE952F0DFC47EAB7C1FC', 'capteur.temperature.2@emp.dz'),
(52, '2020-03-13 08:00:53', 'DE4812D9587B2BE430E19786E72CC044', 'capteur.temperature.2@emp.dz'),
(53, '2020-03-13 14:00:53', '5752CF8979DC61786B00018009FE4CA2', 'capteur.temperature.2@emp.dz'),
(54, '2020-03-13 18:00:53', 'DE4812D9587B2BE430E19786E72CC044', 'capteur.temperature.2@emp.dz'),
(55, '2020-03-14 08:00:53', 'DE4812D9587B2BE430E19786E72CC044', 'capteur.temperature.2@emp.dz'),
(56, '2020-03-14 14:00:53', '5752CF8979DC61786B00018009FE4CA2', 'capteur.temperature.2@emp.dz'),
(57, '2020-03-14 18:00:53', '66B83D9FB31AD4FB9261B0D4C1B84007', 'capteur.temperature.2@emp.dz'),
(70, '2020-03-18 08:00:53', 'DE4812D9587B2BE430E19786E72CC044', 'capteur.temperature.2@emp.dz'),
(71, '2020-03-18 14:00:53', '5752CF8979DC61786B00018009FE4CA2', 'capteur.temperature.2@emp.dz'),
(72, '2020-03-18 18:00:53', '58B6D05CA88F5B8443437EF12402E621', 'capteur.temperature.2@emp.dz'),
(73, '2020-03-19 08:00:53', 'DE4812D9587B2BE430E19786E72CC044', 'capteur.temperature.2@emp.dz'),
(74, '2020-03-19 14:00:53', '631E7960A52224A867AE4AC09660DA0D', 'capteur.temperature.2@emp.dz'),
(75, '2020-03-19 18:00:53', '66B83D9FB31AD4FB9261B0D4C1B84007', 'capteur.temperature.2@emp.dz');

-- --------------------------------------------------------

--
-- Table structure for table `paramsclpkc`
--

CREATE TABLE `paramsclpkc` (
  `url_params` varchar(255) NOT NULL,
  `date_creation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `id_personne` int(11) NOT NULL,
  `taille` varchar(50) NOT NULL,
  `poids` varchar(50) NOT NULL,
  `groupe_sanguin` varchar(10) NOT NULL,
  `adresse_gateway` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id_personne`, `taille`, `poids`, `groupe_sanguin`, `adresse_gateway`) VALUES
(2, '175', '75', 'O-', '192.168.1.104:8080'),
(4, '173', '80.1', 'O-', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `personne`
--

CREATE TABLE `personne` (
  `id_personne` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(128) DEFAULT NULL,
  `genre` varchar(50) NOT NULL,
  `date_naissance` date NOT NULL,
  `statut_matrimonial` varchar(50) NOT NULL,
  `numero_telephone` varchar(50) NOT NULL,
  `id_adresse` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `date_creation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `url_photo` varchar(255) DEFAULT NULL,
  `url_params` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `personne`
--

INSERT INTO `personne` (`id_personne`, `nom`, `prenom`, `genre`, `date_naissance`, `statut_matrimonial`, `numero_telephone`, `id_adresse`, `username`, `password`, `date_creation`, `url_photo`, `url_params`, `role`) VALUES
(1, 'AMBARA', 'Christian Evrard', 'MASCULIN', '2000-08-12', 'CELIBATAIRE', '+213 791776407', 2, 'ambara.evrard@emphealth.dz', '$2y$12$p/F9AS4eVLo7sGgHueDMBejKnWGSz2o1En1Vn4RvlRPOeqaAZHiDC', '2020-03-16 08:48:30', NULL, NULL, 'MEDECIN'),
(2, 'AISSAT', 'Yassine', 'MASCULIN', '2002-03-02', 'CELIBATAIRE', '+213 791486401', 1, 'yassine.aissat@emphealth.dz', '$2y$12$p/F9AS4eVLo7sGgHueDMBejKnWGSz2o1En1Vn4RvlRPOeqaAZHiDC', '2020-03-18 07:51:50', NULL, NULL, 'PATIENT'),
(3, 'HARBI', 'Munira', 'FEMININ', '1972-09-10', 'MARIEE', '+213 215058581', 3, 'harbi.munira@emphealth.dz', '$2y$12$p/F9AS4eVLo7sGgHueDMBejKnWGSz2o1En1Vn4RvlRPOeqaAZHiDC', '2020-03-16 08:48:50', NULL, NULL, 'ASSISTANT_MEDICAL'),
(4, 'MASSI', 'Said', 'MASCULIN', '1974-07-29', 'MARIEE', '+213 212987678', 4, 'said.massi@emphealth.dz', '$2y$12$p/F9AS4eVLo7sGgHueDMBejKnWGSz2o1En1Vn4RvlRPOeqaAZHiDC', '2020-03-16 08:48:58', NULL, NULL, 'PATIENT'),
(5, 'CHETIOUI', 'Ali', 'MASCULIN', '1994-10-02', 'CELIBATAIRE', '+213 79177567', 3, 'ali.chetioui@emphealth.dz', '$2y$12$p/F9AS4eVLo7sGgHueDMBejKnWGSz2o1En1Vn4RvlRPOeqaAZHiDC', '2020-03-17 15:06:40', NULL, NULL, 'MEDECIN'),
(6, 'OTIS', 'Ange', 'MASCULIN', '1999-03-08', 'CELIBATAIRE', '+213 791779607', 3, 'otis.ange@emphealth.dz', '$2y$12$p/F9AS4eVLo7sGgHueDMBejKnWGSz2o1En1Vn4RvlRPOeqaAZHiDC', '2020-03-18 20:14:31', NULL, NULL, 'RADIOLOGUE'),
(7, 'MBENG', 'Jean', 'MASCULIN', '1992-02-21', 'CELIBATAIRE', '+213 796879607', 4, 'mbeng.jean@emphealth.dz', '$2y$12$p/F9AS4eVLo7sGgHueDMBejKnWGSz2o1En1Vn4RvlRPOeqaAZHiDC', '2020-03-18 20:20:01', NULL, NULL, 'LABORANTIN');

-- --------------------------------------------------------

--
-- Table structure for table `photos`
--

CREATE TABLE `photos` (
  `url_photo` varchar(255) NOT NULL,
  `date_creation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `praticien`
--

CREATE TABLE `praticien` (
  `id_personne` int(11) NOT NULL,
  `metier` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `praticien`
--

INSERT INTO `praticien` (`id_personne`, `metier`) VALUES
(5, 'CHIRURGIEN'),
(3, 'INFIRMIER'),
(1, 'MEDECIN GENERALISTE'),
(6, 'RADIOLOGUE'),
(7, 'TECHNICIEN DE LABORATOIRE MEDICAL');

-- --------------------------------------------------------

--
-- Table structure for table `praticientraitepatient`
--

CREATE TABLE `praticientraitepatient` (
  `id_droit` int(11) NOT NULL,
  `id_praticien` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `date_debut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_fin` timestamp NULL DEFAULT NULL,
  `diagnostic_principal` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `praticientraitepatient`
--

INSERT INTO `praticientraitepatient` (`id_droit`, `id_praticien`, `id_patient`, `date_debut`, `date_fin`, `diagnostic_principal`) VALUES
(1, 1, 2, '2020-03-04 21:20:47', NULL, 'Diabète'),
(2, 1, 4, '2020-03-05 07:44:48', NULL, 'Asthme'),
(3, 5, 2, '2020-03-10 07:22:29', '2020-03-17 23:00:00', 'Appendicite');

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `id_prescription` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `id_praticien` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `med1` text NOT NULL,
  `pos1` text NOT NULL,
  `med2` text NOT NULL,
  `pos2` text NOT NULL,
  `med3` text NOT NULL,
  `pos3` text NOT NULL,
  `med4` text NOT NULL,
  `pos4` text NOT NULL,
  `med5` text NOT NULL,
  `pos5` text NOT NULL,
  `statut` text NOT NULL,
  `traitement` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`id_prescription`, `id_patient`, `id_praticien`, `date`, `med1`, `pos1`, `med2`, `pos2`, `med3`, `pos3`, `med4`, `pos4`, `med5`, `pos5`, `statut`, `traitement`) VALUES
(1, 2, 1, '2020-03-18 14:23:06', 'GLUCOPHAGE 500mg', '2 à 3 fois par jour', 'AMAREL 1mg', '1 comprimé par jour', '', '', '', '', '', '', 'EN COURS', 'Diabète');

-- --------------------------------------------------------

--
-- Table structure for table `privilege`
--

CREATE TABLE `privilege` (
  `privilege` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `rendezvous`
--

CREATE TABLE `rendezvous` (
  `id_consultation` int(11) NOT NULL,
  `id_praticien` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `motif` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `accepte` tinyint(1) DEFAULT NULL,
  `effectue` tinyint(1) NOT NULL DEFAULT '0',
  `date_demande` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rendezvous`
--

INSERT INTO `rendezvous` (`id_consultation`, `id_praticien`, `id_patient`, `motif`, `date`, `accepte`, `effectue`, `date_demande`) VALUES
(1, 1, 2, 'Examen de routine', '2020-03-26', NULL, 0, '2020-03-15 15:21:48');

-- --------------------------------------------------------

--
-- Table structure for table `roleprivilege`
--

CREATE TABLE `roleprivilege` (
  `id_roleprivilege` int(11) NOT NULL,
  `role` varchar(50) NOT NULL,
  `privilege` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role`, `description`) VALUES
('ASSISTANT_MEDICAL', 'Rôle réservé aux assistants médicaux.'),
('LABORANTIN', 'Pour le personnel de laboratoire.'),
('MEDECIN', 'Rôle réservé aux médecin et tout membre du personnel du même niveau.'),
('PATIENT', 'Rôle réservé aux patients.'),
('RADIOLOGUE', 'Rpôle réservé aux radiologues.');

-- --------------------------------------------------------

--
-- Table structure for table `vitals`
--

CREATE TABLE `vitals` (
  `id_vitals` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id_patient` int(11) NOT NULL,
  `id_infirmier` int(11) DEFAULT NULL,
  `poids` text NOT NULL,
  `taille` text NOT NULL,
  `temperature` text NOT NULL,
  `pouls` text NOT NULL,
  `respiration` text NOT NULL,
  `IMC` text NOT NULL,
  `tour_de_taille` text NOT NULL,
  `tour_de_tete` text NOT NULL,
  `commentaire` text NOT NULL,
  `statut` text NOT NULL,
  `nom_infirmier` text NOT NULL,
  `tension` text NOT NULL,
  `glycemie` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vitals`
--

INSERT INTO `vitals` (`id_vitals`, `date`, `id_patient`, `id_infirmier`, `poids`, `taille`, `temperature`, `pouls`, `respiration`, `IMC`, `tour_de_taille`, `tour_de_tete`, `commentaire`, `statut`, `nom_infirmier`, `tension`, `glycemie`) VALUES
(1, '2020-03-18 10:23:19', 2, 3, '75 kg', '175 cm', '37.3 °C', '110 bpm', '', '24.5', '', '', 'Routine', 'FIN', 'HARBI Munira', '120/80 mmHg', '1.26 g/l'),
(6, '2020-03-19 11:50:32', 2, NULL, '75kg', '175 cm', '38°C', '110 bpm', '3', '2', '', '', 'RAS', 'FIN', '', '8', '2'),
(7, '2020-03-19 11:50:36', 2, NULL, '4kg', '5 cm', '4°C', '110 bpm', '4', '4', '', '', 'RAS from Raspberry', 'FIN', '', '46', '4'),
(8, '2020-03-19 11:52:47', 2, 3, '73 kg', '176 cm', '39 °C', '110 bpm', '', '24.6', '', '', 'Routine', 'EN COURS', 'HARBI Munira', '120/80 mmHg', '1.26 g/l');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adressepersonne`
--
ALTER TABLE `adressepersonne`
  ADD PRIMARY KEY (`id_adresse`);

--
-- Indexes for table `allergene`
--
ALTER TABLE `allergene`
  ADD PRIMARY KEY (`allergene`);

--
-- Indexes for table `allergie`
--
ALTER TABLE `allergie`
  ADD PRIMARY KEY (`id_allergie`),
  ADD KEY `allergene` (`allergene`),
  ADD KEY `id_patient` (`id_patient`);

--
-- Indexes for table `capteur`
--
ALTER TABLE `capteur`
  ADD PRIMARY KEY (`id_capteur`),
  ADD KEY `id_personne` (`id_personne`),
  ADD KEY `type` (`type`),
  ADD KEY `demandeur` (`demandeur`);

--
-- Indexes for table `chirurgie`
--
ALTER TABLE `chirurgie`
  ADD PRIMARY KEY (`id_chirurgie`),
  ADD KEY `id_patient` (`id_patient`),
  ADD KEY `id_praticien` (`id_praticien`);

--
-- Indexes for table `dents`
--
ALTER TABLE `dents`
  ADD PRIMARY KEY (`id_dents`),
  ADD KEY `id_patient` (`id_patient`);

--
-- Indexes for table `examen`
--
ALTER TABLE `examen`
  ADD PRIMARY KEY (`id_examen`),
  ADD KEY `id_patient` (`id_patient`),
  ADD KEY `id_demandeur` (`id_demandeur`),
  ADD KEY `id_responsable` (`id_responsable`);

--
-- Indexes for table `imagerie`
--
ALTER TABLE `imagerie`
  ADD PRIMARY KEY (`id_imagerie`),
  ADD KEY `id_patient` (`id_patient`),
  ADD KEY `id_demandeur` (`id_demandeur`),
  ADD KEY `id_responsable` (`id_responsable`);

--
-- Indexes for table `keystore`
--
ALTER TABLE `keystore`
  ADD PRIMARY KEY (`id_keystore`),
  ADD KEY `id_capteur` (`id_capteur`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id_message`),
  ADD KEY `emetteur` (`emetteur`),
  ADD KEY `recepteur` (`recepteur`);

--
-- Indexes for table `mesure`
--
ALTER TABLE `mesure`
  ADD PRIMARY KEY (`type`);

--
-- Indexes for table `metier`
--
ALTER TABLE `metier`
  ADD PRIMARY KEY (`metier`);

--
-- Indexes for table `observation`
--
ALTER TABLE `observation`
  ADD PRIMARY KEY (`id_observation`),
  ADD KEY `id_capteur` (`id_capteur`);

--
-- Indexes for table `paramsclpkc`
--
ALTER TABLE `paramsclpkc`
  ADD PRIMARY KEY (`url_params`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id_personne`);

--
-- Indexes for table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id_personne`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `id_adresse` (`id_adresse`),
  ADD KEY `url_params` (`url_params`),
  ADD KEY `url_photo` (`url_photo`),
  ADD KEY `role` (`role`);

--
-- Indexes for table `photos`
--
ALTER TABLE `photos`
  ADD PRIMARY KEY (`url_photo`);

--
-- Indexes for table `praticien`
--
ALTER TABLE `praticien`
  ADD PRIMARY KEY (`id_personne`),
  ADD KEY `metier` (`metier`);

--
-- Indexes for table `praticientraitepatient`
--
ALTER TABLE `praticientraitepatient`
  ADD PRIMARY KEY (`id_droit`),
  ADD KEY `id_personne1` (`id_praticien`),
  ADD KEY `id_personne2` (`id_patient`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`id_prescription`),
  ADD KEY `id_patient` (`id_patient`),
  ADD KEY `id_praticien` (`id_praticien`);

--
-- Indexes for table `privilege`
--
ALTER TABLE `privilege`
  ADD PRIMARY KEY (`privilege`);

--
-- Indexes for table `rendezvous`
--
ALTER TABLE `rendezvous`
  ADD PRIMARY KEY (`id_consultation`),
  ADD KEY `id_praticien` (`id_praticien`),
  ADD KEY `id_patient` (`id_patient`);

--
-- Indexes for table `roleprivilege`
--
ALTER TABLE `roleprivilege`
  ADD PRIMARY KEY (`id_roleprivilege`),
  ADD UNIQUE KEY `ind_1` (`role`,`privilege`),
  ADD KEY `index_fk2` (`privilege`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role`);

--
-- Indexes for table `vitals`
--
ALTER TABLE `vitals`
  ADD PRIMARY KEY (`id_vitals`),
  ADD KEY `id_patient` (`id_patient`),
  ADD KEY `id_infirmier` (`id_infirmier`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adressepersonne`
--
ALTER TABLE `adressepersonne`
  MODIFY `id_adresse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `allergie`
--
ALTER TABLE `allergie`
  MODIFY `id_allergie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `chirurgie`
--
ALTER TABLE `chirurgie`
  MODIFY `id_chirurgie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `dents`
--
ALTER TABLE `dents`
  MODIFY `id_dents` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `examen`
--
ALTER TABLE `examen`
  MODIFY `id_examen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `imagerie`
--
ALTER TABLE `imagerie`
  MODIFY `id_imagerie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `keystore`
--
ALTER TABLE `keystore`
  MODIFY `id_keystore` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id_message` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `observation`
--
ALTER TABLE `observation`
  MODIFY `id_observation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;
--
-- AUTO_INCREMENT for table `personne`
--
ALTER TABLE `personne`
  MODIFY `id_personne` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `praticientraitepatient`
--
ALTER TABLE `praticientraitepatient`
  MODIFY `id_droit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `id_prescription` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `rendezvous`
--
ALTER TABLE `rendezvous`
  MODIFY `id_consultation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `roleprivilege`
--
ALTER TABLE `roleprivilege`
  MODIFY `id_roleprivilege` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `vitals`
--
ALTER TABLE `vitals`
  MODIFY `id_vitals` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `allergie`
--
ALTER TABLE `allergie`
  ADD CONSTRAINT `allergie_ibfk_1` FOREIGN KEY (`allergene`) REFERENCES `allergene` (`allergene`),
  ADD CONSTRAINT `allergie_ibfk_2` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_personne`);

--
-- Constraints for table `capteur`
--
ALTER TABLE `capteur`
  ADD CONSTRAINT `capteur_ibfk_1` FOREIGN KEY (`id_personne`) REFERENCES `patient` (`id_personne`),
  ADD CONSTRAINT `capteur_ibfk_2` FOREIGN KEY (`type`) REFERENCES `mesure` (`type`),
  ADD CONSTRAINT `capteur_ibfk_3` FOREIGN KEY (`demandeur`) REFERENCES `praticien` (`id_personne`);

--
-- Constraints for table `chirurgie`
--
ALTER TABLE `chirurgie`
  ADD CONSTRAINT `chirurgie_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_personne`),
  ADD CONSTRAINT `chirurgie_ibfk_2` FOREIGN KEY (`id_praticien`) REFERENCES `praticien` (`id_personne`);

--
-- Constraints for table `dents`
--
ALTER TABLE `dents`
  ADD CONSTRAINT `dents_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_personne`);

--
-- Constraints for table `examen`
--
ALTER TABLE `examen`
  ADD CONSTRAINT `examen_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_personne`),
  ADD CONSTRAINT `examen_ibfk_2` FOREIGN KEY (`id_demandeur`) REFERENCES `praticien` (`id_personne`),
  ADD CONSTRAINT `examen_ibfk_3` FOREIGN KEY (`id_responsable`) REFERENCES `praticien` (`id_personne`);

--
-- Constraints for table `imagerie`
--
ALTER TABLE `imagerie`
  ADD CONSTRAINT `imagerie_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_personne`),
  ADD CONSTRAINT `imagerie_ibfk_2` FOREIGN KEY (`id_demandeur`) REFERENCES `praticien` (`id_personne`),
  ADD CONSTRAINT `imagerie_ibfk_3` FOREIGN KEY (`id_demandeur`) REFERENCES `praticien` (`id_personne`),
  ADD CONSTRAINT `imagerie_ibfk_4` FOREIGN KEY (`id_responsable`) REFERENCES `praticien` (`id_personne`);

--
-- Constraints for table `keystore`
--
ALTER TABLE `keystore`
  ADD CONSTRAINT `keystore_ibfk_1` FOREIGN KEY (`id_capteur`) REFERENCES `capteur` (`id_capteur`);

--
-- Constraints for table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`emetteur`) REFERENCES `personne` (`id_personne`),
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`recepteur`) REFERENCES `personne` (`id_personne`);

--
-- Constraints for table `observation`
--
ALTER TABLE `observation`
  ADD CONSTRAINT `observation_ibfk_1` FOREIGN KEY (`id_capteur`) REFERENCES `capteur` (`id_capteur`);

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`);

--
-- Constraints for table `personne`
--
ALTER TABLE `personne`
  ADD CONSTRAINT `personne_ibfk_1` FOREIGN KEY (`id_adresse`) REFERENCES `adressepersonne` (`id_adresse`),
  ADD CONSTRAINT `personne_ibfk_2` FOREIGN KEY (`url_params`) REFERENCES `paramsclpkc` (`url_params`),
  ADD CONSTRAINT `personne_ibfk_3` FOREIGN KEY (`url_photo`) REFERENCES `photos` (`url_photo`),
  ADD CONSTRAINT `personne_ibfk_4` FOREIGN KEY (`role`) REFERENCES `roles` (`role`);

--
-- Constraints for table `praticien`
--
ALTER TABLE `praticien`
  ADD CONSTRAINT `praticien_ibfk_1` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`),
  ADD CONSTRAINT `praticien_ibfk_2` FOREIGN KEY (`metier`) REFERENCES `metier` (`metier`);

--
-- Constraints for table `praticientraitepatient`
--
ALTER TABLE `praticientraitepatient`
  ADD CONSTRAINT `praticientraitepatient_ibfk_1` FOREIGN KEY (`id_praticien`) REFERENCES `praticien` (`id_personne`),
  ADD CONSTRAINT `praticientraitepatient_ibfk_2` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_personne`);

--
-- Constraints for table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_personne`),
  ADD CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`id_praticien`) REFERENCES `praticien` (`id_personne`);

--
-- Constraints for table `rendezvous`
--
ALTER TABLE `rendezvous`
  ADD CONSTRAINT `rendezvous_ibfk_1` FOREIGN KEY (`id_praticien`) REFERENCES `praticien` (`id_personne`),
  ADD CONSTRAINT `rendezvous_ibfk_2` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_personne`);

--
-- Constraints for table `roleprivilege`
--
ALTER TABLE `roleprivilege`
  ADD CONSTRAINT `index_fk1` FOREIGN KEY (`role`) REFERENCES `roles` (`role`),
  ADD CONSTRAINT `index_fk2` FOREIGN KEY (`privilege`) REFERENCES `privilege` (`privilege`);

--
-- Constraints for table `vitals`
--
ALTER TABLE `vitals`
  ADD CONSTRAINT `vitals_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_personne`),
  ADD CONSTRAINT `vitals_ibfk_2` FOREIGN KEY (`id_infirmier`) REFERENCES `praticien` (`id_personne`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
