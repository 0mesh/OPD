package beans.web.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import beans.web.model.AffectedOrgan;
import beans.web.model.Appointment;

public class AppointmentMapper implements RowMapper<Appointment>{

	@Override
	public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Appointment appointment = new Appointment();
		
		appointment.setAppointmentId(rs.getString("AppointmentId"));
		appointment.setPatientId(rs.getString("PatientId"));
		appointment.setDoctorId(rs.getString("DoctorId"));
		appointment.setDoctorName(rs.getString("DoctorName"));
		appointment.setAppointmentDate(rs.getDate("AppointmentDate"));
		appointment.setSlotStartTime(rs.getTime("StartTime"));
		appointment.setSlotEndTime(rs.getTime("EndTime"));
		appointment.setActive(rs.getBoolean("BookingStatus"));
		String organ = rs.getString("AffectedOrgan");
		if(organ.equals("ENT")) {
			appointment.setOrgan(AffectedOrgan.ENT);
		}
		else if (organ.equals("OPTHAMOLOGIST")) {
			appointment.setOrgan(AffectedOrgan.OPTHAMOLOGIST);
		}
		else if (organ.equals("G_PHYSICIAN")) {
			appointment.setOrgan(AffectedOrgan.G_PHYSICIAN);
		}else if (organ.equals("ORTHOPAEDIC")) {
			appointment.setOrgan(AffectedOrgan.ORTHOPAEDIC);}
			else if (organ.equals("NEUROLOGIST")) {
				appointment.setOrgan(AffectedOrgan.NEUROLOGIST);
		}else if (organ.equals("CARDIOLOGIST")) {
			appointment.setOrgan(AffectedOrgan.CARDIOLOGIST);
		}else if (organ.equals("PULMONOLOGIST")) {
			appointment.setOrgan(AffectedOrgan.PULMONOLOGIST);
		}else if (organ.equals("GYNAECOLOGIST")) {
			appointment.setOrgan(AffectedOrgan.GYNAECOLOGIST);
		}else if (organ.equals("OTHERS")) {
			appointment.setOrgan(AffectedOrgan.OTHERS);
		}
		
	
		return appointment;
	}

}
