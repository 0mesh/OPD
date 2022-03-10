package beans.web.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.web.model.PatientMedicalHistory;

@Repository
public class PatientMedicalHistoryDAOImpl implements PatientMedicalHistoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private Logger log;

	public List<PatientMedicalHistory> getPatientMedicalHistory(String patientId) throws HibernateException {
		try {
		String hql = "FROM PatientMedicalHistory where PatientId=:patientId";
		TypedQuery<PatientMedicalHistory> query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("patientId", patientId);
		List<PatientMedicalHistory> history = query.getResultList();
		log.info("Accessed Patient Medical history of patient id: "+patientId);
		log.info("Retrieved "+history.size() +" number of records");
		return history;
		}catch(Exception e) {
			log.error(e);
			throw new HibernateException("Patient does not have any medical history associated with us");
		}
		
	}
	public PatientMedicalHistory addPatientMedicalHistory(PatientMedicalHistory history) {
	Integer id=	(Integer)	sessionFactory.getCurrentSession().save(history);
	PatientMedicalHistory inserted = sessionFactory.getCurrentSession().get(PatientMedicalHistory.class, id);
	log.info("Added to PatientMedicalHistory table with id "+id);
	System.out.println(inserted);
	return inserted;
	
	}
	


}