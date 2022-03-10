package beans.web.repository;

import java.util.List;

import org.hibernate.HibernateException;

import beans.web.model.PatientMedicalHistory;

public interface PatientMedicalHistoryDAO {
	public List<PatientMedicalHistory> getPatientMedicalHistory(String patientId) throws HibernateException ;
	public PatientMedicalHistory addPatientMedicalHistory(PatientMedicalHistory history) ;
}
