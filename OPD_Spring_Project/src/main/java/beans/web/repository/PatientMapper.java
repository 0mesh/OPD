package beans.web.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import beans.web.model.Gender;
import beans.web.model.Patient;

public class PatientMapper implements RowMapper<Patient> {

	@Override
	public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Patient patient = new Patient();
		patient.setPatientId(rs.getString("PatientId"));
		patient.setFirstName(rs.getString("FirstName"));
		patient.setLastName(rs.getString("LastName"));
		patient.setAddress(rs.getString("Address"));
		patient.setEmail(rs.getString("Email"));
		patient.setPhoneNumber(rs.getString("PhoneNumber"));
		patient.setCity(rs.getString("City"));
		patient.setState(rs.getString("State"));
		String gender =  rs.getString("Gender");
		System.out.println(gender);
		if(gender.equals("MALE")) {
			patient.setGender(Gender.MALE);	
		}
		else if(gender.equals("FEMALE")) {
			patient.setGender(Gender.FEMALE);	
		}
		else {
			patient.setGender(Gender.OTHER);	
			
		}
		patient.setDateOfBirth(rs.getDate("DOB"));
		patient.setSsn(rs.getString("SSN"));
		return patient;
	}

}
