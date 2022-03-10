package beans.web.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beans.web.model.PatientMedicalHistory;
import beans.web.repository.PatientMedicalHistoryDAO;
import beans.web.repository.PatientMedicalHistoryDAOImpl;

@Service
@Transactional
public class PatientMedicalHistoryService {
	@Autowired
	private PatientMedicalHistoryDAO patientMedicalHistoryDao;

	public List<PatientMedicalHistory> getPatientMedicalHistory(String patientId) throws HibernateException {

		try {
			return patientMedicalHistoryDao.getPatientMedicalHistory(patientId);
		} catch (HibernateException e) {

			throw new HibernateException("Patient does not have any medical history associated with us");
		}
	}

	public PatientMedicalHistory addPatientMedicalHistory(PatientMedicalHistory history) {
		return patientMedicalHistoryDao.addPatientMedicalHistory(history);
	}

}
