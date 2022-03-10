package beans.web.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import beans.web.model.Patient;
import beans.web.model.PatientMedicalHistory;
import beans.web.service.PatientService;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {
//	 private static Log log = LogFactory.getLog(PatientController.class);
		@Autowired
		private Logger log;
//	 private static Logger log = LogManager.getLogger(PatientController.class);
		
		 @Autowired
	private PatientService patientService;

	
//	public PatientController(PatientService patientService) {
//		this.patientService = patientService;
//	}
//	
//	@RequestMapping(value = "/add/patient", method = RequestMethod.GET)
//	public String getAddPatientForm() {
//		return "addPatient";
//	}
//	
//	@RequestMapping(value = "/submit/addpatient", method = RequestMethod.POST)
//	public ModelAndView submitAddPatientForm(@ModelAttribute("patient") Patient patient) {
//
//		ModelAndView model = new ModelAndView();	
//		
//		String id =patientService.addPatient(patient);
//		patient.setPatientId(id);
//		model.addObject("patientData",patient);
//		model.setViewName("registrationSuccess");
//		System.out.println(patient.toString());
//		return model;
//	}
//	@RequestMapping(value = "/update/patient", method = RequestMethod.GET)
//	public String getUpdatePatientForm() {
//		return "getPatientID";
//	}
//	@RequestMapping(value="/submit/patientID",method = RequestMethod.POST)
//	public ModelAndView submitUpdatedPatientID(@RequestParam("id") String id) {
//		System.out.println(id);
//		ModelAndView model = new ModelAndView();
//		Patient patient =null;
//		try {
//			
//			patient = patientService.searchPatient(id);
//			System.out.println(patient.toString());
//			model.addObject("patient", patient);
//			model.setViewName("updatePatient");
//		}catch(DataAccessException e) {
//			model.setViewName("idNotPresent");
//		}
//		
//		return model;
//		
//	}
//	
//	@RequestMapping(value = "/submit/updatepatient", method = RequestMethod.PUT)
//	public ModelAndView submitUpdatePatientForm(@RequestParam("patientId")String id,@ModelAttribute("patient") Patient patient) {
//
//		ModelAndView model = new ModelAndView();	
//		System.out.println("Before update "+patient.toString());
//		System.out.println("update pateint id "+id);
//	Patient updatedPatient =patientService.updatePatient(patient.getPatientId(),patient);
//		System.out.println("After update"+updatedPatient.toString());
//		model.addObject("patientData",updatedPatient);
//		model.setViewName("updateSuccess");
////		System.out.println(patient.toString());
//		return model;
//	}

	@GetMapping(value = "/patients")
	public ResponseEntity<List<Patient>> getAllPatients() {
		HttpHeaders headers = new HttpHeaders();
		try {
			List<Patient> list = patientService.getAllPatients();
			headers.add("Number Of Records Found", String.valueOf(list.size()));
			log.info("successfully accessed all records");
			return new ResponseEntity<List<Patient>>(list, headers, HttpStatus.OK);
		} catch (DataAccessException e) {
			log.error("cannot access records from database");
			return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("id") String patientId) {
		HttpHeaders headers = new HttpHeaders();
		try {
			Patient patient = patientService.getPatient(patientId);
			headers.add("Number Of Records Found", String.valueOf("1"));
			log.info("successfully accessed record of patient with id "+patientId);
			return new ResponseEntity<Patient>(patient, headers, HttpStatus.OK);
		} catch (DataAccessException e) {
			log.error("cannot access records from database");
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/patients", produces = "application/json")
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
		System.out.println("Patient from angular"+patient.toString());
		HttpHeaders headers = new HttpHeaders();
//		if (patient == null) {
//			log.error("cannot access record ");
//			return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
//		}

		try {
			String patientId = patientService.addPatient(patient);

			patient.setPatientId(patientId);
			System.out.println(patient);
			log.info("Patient Created with ID  - "+ patientId);
			headers.add("Patient Created with ID  - ", patientId);
			try {
				log.info("successfully added patient  to database records");
				return new ResponseEntity<Patient>(patientService.getPatient(patientId), headers,
						HttpStatus.CREATED);
			} catch (DataAccessException e) {
				return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (DataAccessException e) {
			System.err.println(e);
			return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/patients/{id}", produces = "application/json")
	public ResponseEntity<Patient> createPatient(@PathVariable("id") String patientId, @RequestBody Patient patient) {
		HttpHeaders headers = new HttpHeaders();
		if (patient == null) {
			return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
		}

		try {
			Patient patientDoesExists = patientService.getPatient(patientId);
		}catch(DataAccessException e) {
			log.error(e);
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
			
		}	try {
			
			Patient updatedPatient = patientService.updatePatient(patientId, patient);
			headers.add("Patient updated with Id:", patientId);
			try {
				return new ResponseEntity<Patient>(patientService.getPatient(patientId), headers,
						HttpStatus.CREATED);
			} catch (DataAccessException e) {
				log.error(e);
				return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (DataAccessException e) {
			log.error(e);
			return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping(value ="patients/{id}")
	public ResponseEntity<Patient> deletePatient(@PathVariable("id")String patientId){
		
		HttpHeaders headers = new HttpHeaders();
		try {
			Patient patientDoesExists = patientService.getPatient(patientId);
			try {
			Patient patient = patientService.deletePatient(patientId);
			log.info("Patient  deleted with Id "+patientId);
			headers.add("Patient  deleted with Id",patientId);
			return new ResponseEntity<Patient>(patient,headers,HttpStatus.OK);
			}catch(DataAccessException  e) {
				log.info("Database Error");
				return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch(DataAccessException e) {
			log.error("Patient with id: "+patientId+" does not exists");
			log.error(e);
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
			
		}
	}
	
	

}
