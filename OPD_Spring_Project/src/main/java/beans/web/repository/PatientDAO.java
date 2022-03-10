package beans.web.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import beans.web.model.Patient;
import beans.web.model.PatientMedicalHistory;


public interface PatientDAO {
String InsertPatient(Patient patient);
Patient UpdatePatient(String patientId,Patient patient);
Patient DeletePatient(String patientId);
Patient SearchPatient(String patientId);
List<Patient> GetAllPatients();

}
