package org.emp.emphealth;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.emp.emphealth.core.resources.Capteur;
import org.emp.emphealth.core.resources.Chirurgie;
import org.emp.emphealth.core.resources.Examen;
import org.emp.emphealth.core.resources.Imagerie;
import org.emp.emphealth.core.resources.KeyStore;
import org.emp.emphealth.core.resources.Message;
import org.emp.emphealth.core.resources.Observation;
import org.emp.emphealth.core.resources.Patient;
import org.emp.emphealth.core.resources.Personne;
import org.emp.emphealth.core.resources.Praticien;
import org.emp.emphealth.core.resources.PraticienTraitePatient;
import org.emp.emphealth.core.resources.Prescription;
import org.emp.emphealth.core.resources.Rendezvous;
import org.emp.emphealth.core.resources.Vitals;
import org.emp.emphealth.crypto.AES;
import org.emp.emphealth.services.resources.CapteurService;
import org.emp.emphealth.services.resources.ExamenService;
import org.emp.emphealth.services.resources.MessageService;
import org.emp.emphealth.services.resources.PatientService;
import org.emp.emphealth.services.resources.PersonneService;
import org.emp.emphealth.services.resources.PraticienService;
import org.emp.emphealth.services.resources.PraticienTraitePatientService;
import org.emp.emphealth.services.resources.PrescriptionService;
import org.emp.emphealth.services.resources.VitalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	MyUserDetails user;

	@Autowired
	PraticienService praticienService;

	@Autowired
	PersonneService personneService;
	
	@Autowired
	VitalsService vitalsService;

	@Autowired
	PatientService patientService;

	@Autowired
	PraticienTraitePatientService praticienTraitePatientService;

	@Autowired
	CapteurService capteurService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	ExamenService examenService;
	
	@Autowired
	PrescriptionService prescriptionService;
	/*
	 * @RequestMapping("/") public String welcomePage(Model model) {
	 * 
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * user = (MyUserDetails) auth.getPrincipal(); String vraiID =
	 * user.getIdorigine(); if(user.getRole().equals("ROLE_PATIENT")) {
	 * 
	 * }else if(user.getRole().equals("ROLE_PRATICIEN")) { PraticienRepository
	 * praticien = praticienService.getPraticien(user.getIdorigine()).get();
	 * System.out.println(praticien.toString()); List<PatientRepository> patients =
	 * new ArrayList<>(); for(Traite t : praticien.getTraitements()) {
	 * patients.add(patientService.getPatient(t.getPatient().getIdpatient()).get());
	 * }
	 * 
	 * 
	 * model.addAttribute("userVrai", praticien); model.addAttribute("patients",
	 * patients); if(!user.getPhoto().isEmpty()) model.addAttribute("userPhoto",
	 * user.getPhoto()); }
	 * 
	 * 
	 * return "accueil"; }
	 */

	@RequestMapping("/")
	public String welcomePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		// List<PraticienTraitePatient> patients =
		// praticienTraitePatientService.getAllPraticienTraitePatientByPraticien(praticien);
		if (user.getPhoto() != null)
			model.addAttribute("userPhoto", user.getPhoto());
		System.out.println(Arrays.asList(praticien.getPatients().toArray()));
		model.addAttribute("nombrePatient", praticien.getPatients().size());
		model.addAttribute("nombreCapteurs", praticien.getCapteurs().size());
		model.addAttribute("userVrai", user);
		model.addAttribute("praticien", praticien);

		// Notifications

		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addAttribute("demandesDeRendezvous", demandesDeRendezvous);
		model.addAttribute("total", total);

		return "dashboard";
	}

	@RequestMapping("/stats")
	public String statsPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		// List<PraticienTraitePatient> patients =
		// praticienTraitePatientService.getAllPraticienTraitePatientByPraticien(praticien);
		if (user.getPhoto() != null)
			model.addAttribute("userPhoto", user.getPhoto());
		System.out.println(Arrays.asList(praticien.getPatients().toArray()));
		model.addAttribute("nombrePatient", praticien.getPatients().size());
		model.addAttribute("nombreCapteurs", praticien.getCapteurs().size());
		model.addAttribute("userVrai", user);
		model.addAttribute("praticien", praticien);

		// Notifications

		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addAttribute("demandesDeRendezvous", demandesDeRendezvous);
		model.addAttribute("total", total);

		return "stats";
	}

	@RequestMapping("/ajout")
	public String ajout(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		// List<PraticienTraitePatient> patients =
		// praticienTraitePatientService.getAllPraticienTraitePatientByPraticien(praticien);
		if (user.getPhoto() != null)
			model.addAttribute("userPhoto", user.getPhoto());
		System.out.println(Arrays.asList(praticien.getPatients().toArray()));
		model.addAttribute("nombrePatient", praticien.getPatients().size());
		model.addAttribute("nombreCapteurs", praticien.getCapteurs().size());
		model.addAttribute("userVrai", user);
		model.addAttribute("praticien", praticien);

		// Notifications

		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addAttribute("demandesDeRendezvous", demandesDeRendezvous);
		model.addAttribute("total", total);

		return "ajout_patient";
	}

	@RequestMapping("/teleconsultation")
	public String teleconsultationModel(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		// List<PraticienTraitePatient> patients =
		// praticienTraitePatientService.getAllPraticienTraitePatientByPraticien(praticien);
		if (user.getPhoto() != null)
			model.addAttribute("userPhoto", user.getPhoto());
		System.out.println(Arrays.asList(praticien.getPatients().toArray()));
		model.addAttribute("nombrePatient", praticien.getPatients().size());
		model.addAttribute("nombreCapteurs", praticien.getCapteurs().size());
		model.addAttribute("userVrai", user);
		model.addAttribute("praticien", praticien);

		// Notifications

		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addAttribute("demandesDeRendezvous", demandesDeRendezvous);
		model.addAttribute("total", total);

		return "teleconsultation";
	}

	@RequestMapping("/inbox")
	public String inboxPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Personne praticienPersonne = personneService.getPersonne(user.getIDPersonne()).get();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		// List<PraticienTraitePatient> patients =
		// praticienTraitePatientService.getAllPraticienTraitePatientByPraticien(praticien);
		if (user.getPhoto() != null)
			model.addAttribute("userPhoto", user.getPhoto());
		System.out.println(Arrays.asList(praticien.getPatients().toArray()));
		model.addAttribute("nombrePatient", praticien.getPatients().size());
		model.addAttribute("nombreCapteurs", praticien.getCapteurs().size());
		model.addAttribute("userVrai", user);
		model.addAttribute("praticien", praticien);

		// Notifications

		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addAttribute("demandesDeRendezvous", demandesDeRendezvous);
		model.addAttribute("total", total);
		model.addAttribute("praticienPersonne", praticienPersonne);

		return "inbox";
	}

	@RequestMapping("/compose")
	public String composePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		// List<PraticienTraitePatient> patients =
		// praticienTraitePatientService.getAllPraticienTraitePatientByPraticien(praticien);
		if (user.getPhoto() != null)
			model.addAttribute("userPhoto", user.getPhoto());
		System.out.println(Arrays.asList(praticien.getPatients().toArray()));
		model.addAttribute("nombrePatient", praticien.getPatients().size());
		model.addAttribute("nombreCapteurs", praticien.getCapteurs().size());
		model.addAttribute("userVrai", user);
		model.addAttribute("praticien", praticien);

		// Notifications

		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addAttribute("demandesDeRendezvous", demandesDeRendezvous);
		model.addAttribute("total", total);

		return "compose";
	}

	@RequestMapping("/rendezvous")
	public String rdvPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		HashMap<Patient, Personne> h = new HashMap<>();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		// List<PraticienTraitePatient> patients =
		// praticienTraitePatientService.getAllPraticienTraitePatientByPraticien(praticien);
		if (user.getPhoto() != null)
			model.addAttribute("userPhoto", user.getPhoto());
		System.out.println(Arrays.asList(praticien.getPatients().toArray()));
		model.addAttribute("nombrePatient", praticien.getPatients().size());
		model.addAttribute("nombreCapteurs", praticien.getCapteurs().size());
		model.addAttribute("userVrai", user);
		model.addAttribute("praticien", praticien);

		List<Rendezvous> demandes = new ArrayList<>();
		List<Rendezvous> refus = new ArrayList<>();
		List<Rendezvous> avenir = new ArrayList<>();
		List<Rendezvous> termine = new ArrayList<>();

		for (Rendezvous rdv : praticien.getRdv()) {
			if (rdv.getAccepte() == null) {
				demandes.add(rdv);
				h.put(rdv.getPatient(), personneService.getPersonne(rdv.getPatient().getIDPersonne()).get());
			} else if (rdv.getAccepte().equals("0")) {
				refus.add(rdv);
				h.put(rdv.getPatient(), personneService.getPersonne(rdv.getPatient().getIDPersonne()).get());
			} else if (rdv.getEffectue().equals("0")) {
				avenir.add(rdv);
				h.put(rdv.getPatient(), personneService.getPersonne(rdv.getPatient().getIDPersonne()).get());
			} else if (rdv.getEffectue().equals("1")) {
				termine.add(rdv);
				h.put(rdv.getPatient(), personneService.getPersonne(rdv.getPatient().getIDPersonne()).get());
			}
		}
		// Notifications

		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addAttribute("demandesDeRendezvous", demandesDeRendezvous);
		model.addAttribute("total", total);
		model.addAttribute("demandes", demandes);
		model.addAttribute("refus", refus);
		model.addAttribute("avenir", avenir);
		model.addAttribute("termine", termine);
		model.addAttribute("h", h);

		return "rendezvous";
	}

	@RequestMapping("/profil")
	public String profilPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		if (user.getPhoto() != null)
			model.addAttribute("userPhoto", user.getPhoto());
		// System.out.println(Arrays.asList(praticien.getPatients().toArray()));
		model.addAttribute("nombrePatient", praticien.getPatients().size());
		model.addAttribute("nombreCapteurs", praticien.getCapteurs().size());
		model.addAttribute("userVrai", user);
		model.addAttribute("praticien", praticien);

		// Notifications
		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addAttribute("demandesDeRendezvous", demandesDeRendezvous);
		model.addAttribute("total", total);

		return "profil";
	}

	@RequestMapping("/patients")
	public String patientsPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		// List<PraticienTraitePatient> patients =
		// praticienTraitePatientService.getAllPraticienTraitePatientByPraticien(praticien);
		if (user.getPhoto() != null)
			model.addAttribute("userPhoto", user.getPhoto());
		System.out.println(Arrays.asList(praticien.getPatients().toArray()));
		model.addAttribute("nombrePatient", praticien.getPatients().size());
		model.addAttribute("nombreCapteurs", praticien.getCapteurs().size());
		model.addAttribute("userVrai", user);
		model.addAttribute("praticien", praticien);
		LocalDate current = LocalDate.now();
		HashMap<Personne, Integer> h = new HashMap<>();
		HashMap<Personne, PraticienTraitePatient> h2 = new HashMap<>();
		HashMap<Personne, Boolean> h3 = new HashMap<>();
		List<Personne> patientsPersonne = new ArrayList<>();
		for (PraticienTraitePatient ptp : praticien.getPatients()) {

			Personne per = personneService.getPersonne("" + ptp.getPatient().getIDPersonne()).get();
			for (Capteur c : praticien.getCapteurs()) {
				if (c.getPatient() == ptp.getPatient())
					h3.put(per, true);
			}
			patientsPersonne.add(per);
			int age = Period.between(new java.util.Date(per.getDateNaissance().getTime()).toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate(), current).getYears();
			h.put(personneService.getPersonne("" + ptp.getPatient().getIDPersonne()).get(), Integer.valueOf(age));
			h2.put(per, ptp);
		}

		model.addAttribute("h", h);
		model.addAttribute("h2", h2);
		model.addAttribute("h3", h3);
		model.addAttribute("patientsPersonne", patientsPersonne);

		// Notifications
		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addAttribute("demandesDeRendezvous", demandesDeRendezvous);
		model.addAttribute("total", total);

		return "patients";
	}

	@RequestMapping("/capteurs")
	public String capteursPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		// List<PraticienTraitePatient> patients =
		// praticienTraitePatientService.getAllPraticienTraitePatientByPraticien(praticien);
		if (user.getPhoto() != null)
			model.addAttribute("userPhoto", user.getPhoto());
		System.out.println(Arrays.asList(praticien.getCapteurs().toArray()));

		HashMap<Capteur, Personne> patientsPersonne = new HashMap<Capteur, Personne>();
		for (Capteur c : praticien.getCapteurs()) {
			patientsPersonne.put(c, personneService.getPersonne("" + c.getPatient().getIDPersonne()).get());
		}
		model.addAttribute("nombrePatient", praticien.getPatients().size());
		model.addAttribute("nombreCapteurs", praticien.getCapteurs().size());
		model.addAttribute("userVrai", user);
		model.addAttribute("praticien", praticien);
		model.addAttribute("patientsPersonne", patientsPersonne);
		model.addAttribute("capteurs", praticien.getCapteurs());

		// Notifications
		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addAttribute("demandesDeRendezvous", demandesDeRendezvous);
		model.addAttribute("total", total);

		return "capteurs";
	}

	@RequestMapping("/403")
	public String deniedPage(Model model) {

		return "403";
	}

	@RequestMapping("/login/form")
	public String loginPage(Model model) {

		return "login";
	}

	@RequestMapping("/patients/{id}")
	public ModelAndView consulterPatient(@PathVariable(name = "id") String id) {

		// Vérifier que le médecin traite le patient
		LocalDate current = LocalDate.now();
		ModelAndView model = new ModelAndView("consulter_patient");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		if (user.getPhoto() != null)
			model.addObject("userPhoto", user.getPhoto());

		Patient patient = patientService.getPatient(id).get();
		Personne personne = personneService.getPersonne(id).get();
		if (personne.getPhoto() != null)
			model.addObject("personnePhoto", personne.getPhoto());
		boolean h3 = false;
		for (Capteur c : praticien.getCapteurs()) {
			if (c.getPatient() == patient)
				h3 = true;
		}
		List<Long> xTemp = new ArrayList<>();
		List<Double> yTemp = new ArrayList<>();
		List<Long> xECG = new ArrayList<>();
		List<Double> yECG = new ArrayList<>();

		for (int i = 0; i < patient.getCapteurs().size(); i++) {
			// Récupérer les observations de la base
			Capteur capteur = capteurService.getCapteur(patient.getCapteurs().get(i).getIDCapteur()).get();

			// Récupérer la clé

			KeyStore tmp2 = new KeyStore();
			Collections.sort(capteur.getCles());

			tmp2 = capteur.getCles().get(capteur.getCles().size() - 1);
			byte[] cleS = DatatypeConverter.parseHexBinary(tmp2.getCle());
			// Déchiffrer les observations

			for (Observation o : capteur.getObservations()) {
				try {
					o.setValeur(AES.decrypt(DatatypeConverter.parseHexBinary(o.getValeur())));
					o.setDateObservation(o.getDateObservation());
					// System.out.println(o.getValeur());
					// System.out.println(o.getDateObservation());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// Populer le graphe

			for (int j = 0; j < capteur.getObservations().size(); j++) {

				if (capteur.getMesure().getType().equals("TEMPERATURE")) {
					xTemp.add(capteur.getObservations().get(j).getDateObservation().getTime());
					yTemp.add(Double.parseDouble(capteur.getObservations().get(j).getValeur()));
					model.addObject("capteurTemp", capteur);
					model.addObject("idTemp", capteur.getIDCapteur());
					model.addObject("uniteTemp", capteur.getMesure().getUnite());
				} else if (capteur.getMesure().getType().equals("ELECTRO-CARDIOGRAMME")) {
					xECG.add(capteur.getObservations().get(j).getDateObservation().getTime());
					yECG.add(Double.parseDouble(capteur.getObservations().get(j).getValeur()));
					model.addObject("capteurECG", capteur);
					model.addObject("idECG", capteur.getIDCapteur());
					model.addObject("uniteECG", capteur.getMesure().getUnite());
				}

			}
		}
		
		HashMap<Praticien, Personne> h = new HashMap<Praticien, Personne>();
		
		for(PraticienTraitePatient ptp : patient.getPraticiens()) {
			h.put(ptp.getPraticien(), personneService.getPersonne(ptp.getPraticien().getIDPersonne()).get());
		}
		for(Chirurgie chir : patient.getChirurgie()) {
			h.put(chir.getPraticien(), personneService.getPersonne(chir.getPraticien().getIDPersonne()).get());
		}
		
		List<Prescription> patientPrescriptions = new ArrayList<>();
		
		for(Prescription p : praticien.getPrescriptions()) {
			if(p.getPatient().getIDPersonne().equals(patient.getIDPersonne()))
				patientPrescriptions.add(p);
		}
		
		
		List<Imagerie> patientImagerie= new ArrayList<>();
		
		for(Imagerie p : praticien.getImages()) {
			if(p.getPatient().getIDPersonne().equals(patient.getIDPersonne()))
				patientImagerie.add(p);
		}
		
		List<Examen> patientExamen= new ArrayList<>();

		for(Examen p : praticien.getExamens()) {
			if(p.getPatient().getIDPersonne().equals(patient.getIDPersonne()))
				patientExamen.add(p);
		}
		
		
		int age = Period.between(new java.util.Date(personne.getDateNaissance().getTime()).toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate(), current).getYears();
		
		model.addObject("xTemp", xTemp);
		model.addObject("yTemp", yTemp);
		model.addObject("xECG", xECG);
		model.addObject("yECG", yECG);

		model.addObject("h3", h3);
		model.addObject("nombrePatient", praticien.getPatients().size());
		model.addObject("nombreCapteurs", praticien.getCapteurs().size());
		model.addObject("userVrai", user);
		model.addObject("praticien", praticien);
		model.addObject("patientPersonne", personne);
		model.addObject("patientVrai", patient);
		model.addObject("allergies", patient.getAllergies());
		model.addObject("patientPrescriptions", patientPrescriptions);
		
		model.addObject("patientImagerie", patientImagerie);
		
		model.addObject("patientExamen", patientExamen);
		
		model.addObject("age", age);
		
		// Notifications
		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addObject("demandesDeRendezvous", demandesDeRendezvous);
		model.addObject("total", total);
		model.addObject("h", h);

		return model;
	}

	/*
	 * @RequestMapping("/accueil") public String accueilPage(Model model) {
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * user = (MyUserDetails) auth.getPrincipal(); String vraiID =
	 * user.getIdorigine(); if(user.getRole().equals("ROLE_PATIENT")) {
	 * 
	 * }else if(user.getRole().equals("ROLE_PRATICIEN")) { Praticien praticien =
	 * praticienService.getPraticien(user.getIdorigine()).get();
	 * System.out.println(praticien.toString()); model.addAttribute("userVrai",
	 * praticien); if(!user.getPhoto().isEmpty()) model.addAttribute("userPhoto",
	 * user.getPhoto()); }
	 * 
	 * 
	 * return "accueil"; }
	 */

	@RequestMapping("/capteurs/{id}")
	public ModelAndView consulterCapteur(@PathVariable(name = "id") String id) {

		// Vérifier que le médecin a demandé le capteur

		// praticienService = new PraticienService();

		ModelAndView model = new ModelAndView("consulter_capteur");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		if (user.getPhoto() != null)
			model.addObject("userPhoto", user.getPhoto());

		// Récupérer les observations de la base
		Capteur capteur = capteurService.getCapteur(id).get();

		// Récupérer la clé

		KeyStore tmp2 = new KeyStore();
		Collections.sort(capteur.getCles());

		tmp2 = capteur.getCles().get(capteur.getCles().size() - 1);
		byte[] cleS = DatatypeConverter.parseHexBinary(tmp2.getCle());
		// Déchiffrer les observations

		for (Observation o : capteur.getObservations()) {
			try {
				o.setValeur(AES.decrypt(DatatypeConverter.parseHexBinary(o.getValeur())));
				o.setDateObservation(o.getDateObservation());
				// System.out.println(o.getValeur());
				// System.out.println(o.getDateObservation());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Populer le graphe
		List<Long> x = new ArrayList<>();
		List<Double> y = new ArrayList<>();

		for (int i = 0; i < capteur.getObservations().size(); i++) {
			x.add(capteur.getObservations().get(i).getDateObservation().getTime());
			y.add(Double.parseDouble(capteur.getObservations().get(i).getValeur()));
		}

		model.addObject("x", x);
		model.addObject("y", y);
		model.addObject("capteur", capteur);
		model.addObject("unite", capteur.getMesure().getUnite());
		model.addObject("nombrePatient", praticien.getPatients().size());
		model.addObject("nombreCapteurs", praticien.getCapteurs().size());
		model.addObject("userVrai", user);
		model.addObject("praticien", praticien);

		// Notifications
		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addObject("demandesDeRendezvous", demandesDeRendezvous);
		model.addObject("total", total);

		return model;
	}
	
	@RequestMapping("/vital/{id}")
	public ModelAndView consulterVital(@PathVariable(name = "id") String id) {

		// Vérifier que le médecin a demandé le capteur

		// praticienService = new PraticienService();

		ModelAndView model = new ModelAndView("consulter_vitals");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		if (user.getPhoto() != null)
			model.addObject("userPhoto", user.getPhoto());

		
		Vitals vitals = vitalsService.getVitals(id).get();
		
		model.addObject("nombrePatient", praticien.getPatients().size());
		model.addObject("nombreCapteurs", praticien.getCapteurs().size());
		model.addObject("userVrai", user);
		model.addObject("praticien", praticien);

		// Notifications
		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addObject("demandesDeRendezvous", demandesDeRendezvous);
		model.addObject("total", total);
		model.addObject("vitals", vitals);

		return model;
	}

	
	@RequestMapping("/message/{id}")
	public ModelAndView consulterMessage(@PathVariable(name = "id") String id) {

		// Vérifier que le médecin a demandé le capteur

		// praticienService = new PraticienService();

		ModelAndView model = new ModelAndView("consulter_message");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		if (user.getPhoto() != null)
			model.addObject("userPhoto", user.getPhoto());

		
		Message message = messageService.getMessage(id).get();
		
		model.addObject("nombrePatient", praticien.getPatients().size());
		model.addObject("nombreCapteurs", praticien.getCapteurs().size());
		model.addObject("userVrai", user);
		model.addObject("praticien", praticien);

		// Notifications
		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addObject("demandesDeRendezvous", demandesDeRendezvous);
		model.addObject("total", total);
		model.addObject("message", message);

		return model;
	}
	
	
	@RequestMapping("/examen/{id}")
	public ModelAndView consulterExamen(@PathVariable(name = "id") String id) {

		// Vérifier que le médecin a demandé le capteur

		// praticienService = new PraticienService();

		ModelAndView model = new ModelAndView("consulter_examen");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		if (user.getPhoto() != null)
			model.addObject("userPhoto", user.getPhoto());

		
		Examen examen = examenService.getExamen(id).get();
		
		model.addObject("nombrePatient", praticien.getPatients().size());
		model.addObject("nombreCapteurs", praticien.getCapteurs().size());
		model.addObject("userVrai", user);
		model.addObject("praticien", praticien);

		// Notifications
		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addObject("demandesDeRendezvous", demandesDeRendezvous);
		model.addObject("total", total);
		model.addObject("examen", examen);

		return model;
	}
	
	
	@RequestMapping("/prescription/{id}")
	public ModelAndView consulterPrescription(@PathVariable(name = "id") String id) {

		// Vérifier que le médecin a demandé le capteur

		// praticienService = new PraticienService();

		ModelAndView model = new ModelAndView("consulter_prescription");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MyUserDetails) auth.getPrincipal();
		Praticien praticien = praticienService.getPraticien("" + user.getIDPersonne()).get();
		if (user.getPhoto() != null)
			model.addObject("userPhoto", user.getPhoto());

		
		Prescription prescription = prescriptionService.getPrescription(id).get();
		
		model.addObject("nombrePatient", praticien.getPatients().size());
		model.addObject("nombreCapteurs", praticien.getCapteurs().size());
		model.addObject("userVrai", user);
		model.addObject("praticien", praticien);

		// Notifications
		int demandesDeRendezvous = 0;
		for (Rendezvous r : praticien.getRdv()) {
			if (r.getAccepte() == null)
				demandesDeRendezvous++;
		}

		int total = demandesDeRendezvous;
		model.addObject("demandesDeRendezvous", demandesDeRendezvous);
		model.addObject("total", total);
		model.addObject("prescription", prescription);

		return model;
	}

	@RequestMapping("/register")
	public String registerPage(Model model) {

		return "register";
	}

}
