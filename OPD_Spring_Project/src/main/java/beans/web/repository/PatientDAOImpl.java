package beans.web.repository;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import beans.web.config.DatabaseConfiguration;
import beans.web.model.Patient;
import beans.web.model.PatientMedicalHistory;
import beans.web.service.PatientService;
@Repository
public class PatientDAOImpl implements PatientDAO {

	@Autowired
	 private SessionFactory sessionFactory;
@Autowired
private Logger log ;
@Override
public String InsertPatient(Patient patient) {
	// TODO Auto-generated method stub
	String id= (String)	sessionFactory.getCurrentSession().save(patient);
	System.out.println(id);
		return id;
}
@Override
public Patient UpdatePatient(String patientId, Patient patient) {
	// TODO Auto-generated method stub
	sessionFactory.getCurrentSession().update(patient);
	Patient updated = SearchPatient(patientId);
	return updated;
}
@Override
public Patient DeletePatient(String patientId) {
	// TODO Auto-generated method stub
	Patient searchPatient = SearchPatient(patientId);
	sessionFactory.getCurrentSession().delete(searchPatient);
	return searchPatient;
}
@Override
public Patient SearchPatient(String patientId) {
	// TODO Auto-generated method stub
	Patient p =sessionFactory.getCurrentSession().get(Patient.class, patientId);
	return p;
}
@Override
public List<Patient> GetAllPatients() {
	// TODO Auto-generated method stub
	 TypedQuery<Patient> query=sessionFactory.getCurrentSession().createQuery("from Patient");
     return query.getResultList();
}


}
