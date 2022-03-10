package beans.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beans.web.model.Patient;
import beans.web.model.PatientMedicalHistory;
import beans.web.repository.PatientDAO;
import beans.web.repository.PatientDAOImpl;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
@Service

public class PatientService {

	@Autowired
	private PatientDAO patientDao;
	
	@Transactional
	public String addPatient(Patient patient) {
		return patientDao.InsertPatient(patient);
	}
	
	@Transactional
	 public List<Patient> getAllPatients(){
		 return patientDao.GetAllPatients();
	 }
	 
	@Transactional
	public Patient updatePatient(String patientId,Patient patient) {
		return patientDao.UpdatePatient(patientId, patient);
	}

	@Transactional
	public Patient getPatient(String patientId) {
		return patientDao.SearchPatient(patientId);
	}
	
	@Transactional
	public Patient deletePatient(String patientId) {
		return patientDao.DeletePatient(patientId);
	}

}
