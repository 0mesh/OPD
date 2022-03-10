package beans.web.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.time.Period;
import javax.sql.DataSource;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import beans.web.model.Appointment;
import beans.web.model.Slots;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public AppointmentDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
   
	@Autowired
	private Logger log;
	@Override
	public Appointment newAppointment(Appointment appointment) {
		try {
			String sql = "insert into  Appointment(PatientId,DoctorId,AffectedOrgan,DoctorName,AppointmentDate,StartTime,EndTime,BookingStatus) values(?,?,?,?,?,?,?,?)";
			Object[] args = { appointment.getPatientId(), appointment.getDoctorId(), appointment.getOrgan().toString(),
					appointment.getDoctorName(), appointment.getAppointmentDate(), appointment.getSlotStartTime(),
					appointment.getSlotEndTime(), true };
			jdbcTemplate.update(sql, args);
			String appId = jdbcTemplate.queryForObject(
					"select AppointmentID  from Appointment where AId=  (SELECT  IDENT_CURRENT('Appointment'))",
					String.class);
			System.out.println("Appointment fixed for patient Id " + appointment.getPatientId());
			
			log.info("Appointment Booked for Patient Id  ", appointment.getPatientId());

			return getAppointmentbyId(appId);
		} catch (DataAccessException e) {
			log.error("Error creating Appointment");
			System.out.println(e.getMessage());
			throw new DataAccessResourceFailureException("Server Error");
		}
	}

	@Override
	public List<Appointment> getAllAppointments() {
		try {
		String sql = "select AppointmentId,PatientId,DoctorId,DoctorName,AppointmentDate,StartTime,EndTime,AffectedOrgan,BookingStatus from Appointment";
		log.info("Accessed All Appointments");
		return jdbcTemplate.query(sql, new AppointmentMapper());
		
		}
		catch(DataAccessException e) {
			log.error("Accessing appointment records failed... Possible cause: No records Available");
			throw new DataAccessResourceFailureException("Server Error");
		}
	}

	@Override
	public Appointment deleteAppointment(String appointmentId) {
		try {
			Appointment appointment = getAppointmentbyId(appointmentId);
			String sql = "Delete from Appointment where AppointmentId = ?";
			jdbcTemplate.update(sql, new Object[] { appointmentId });
			System.out.println("The appointment was deleted successfully");
		   log.info("The appointment "+appointmentId+" was deleted succefully");
			return appointment;
		} catch (DataAccessException e) {
			log.error("Deletimg appointment "+appointmentId+" failed");
			throw new DataAccessResourceFailureException(
					"Appointment with Appointment ID - " + appointmentId + " does not exist");
		}

	}

	@Override
	public Appointment updateAppointment(String appointmentId, Appointment appointment) {
		Appointment app = getAppointmentbyId(appointmentId);
		LocalDate originalDate = app.getAppointmentDate().toLocalDate();
		int duration = Period.between(LocalDate.now(), originalDate).getDays();
		
		try {

			String sql = "Update Appointment SET AppointmentDate=?,StartTime=?,EndTime=? where AppointmentId = ?";
			
			try{if (duration > 2) {
				jdbcTemplate.update(sql, new Object[] { appointment.getAppointmentDate(),
						appointment.getSlotStartTime(), appointment.getSlotEndTime(), appointmentId });
				System.out.println("update query run");
				log.info("Updated appointment "+appointmentId);
				return getAppointmentbyId(appointmentId);
			}
			else return appointment;
			}catch(DataAccessException e) {
				throw new DataAccessResourceFailureException("Appointment cannot be updated...");
			}
			

		} catch (DataAccessException e) {
			log.error("Updating appointment Failed");
			throw new DataAccessResourceFailureException("Server Error... Please check logs");

		}

	}

	@Override
	public Appointment getAppointmentbyId(String AppointmentId) {
		String sql = "Select * from Appointment where AppointmentId = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { AppointmentId }, new AppointmentMapper());
	}

	@Override
	public List<Appointment> getAppointmentsbyPatientId(String PatientId) {
		List<Appointment> list = null;
		try {
			String sql = "Select * from Appointment where PatientId = ?";
			list = jdbcTemplate.query(sql, new Object[] { PatientId }, new AppointmentMapper());
			
		} catch (DataAccessException e) {
			log.error(e);
			System.out.println(e.getMessage());
		}

		return list;

	}

	@Override
	public List<Slots> getSlots(String patientId,String doctorId, Date appointmentDate) {
		List<Slots> list = null;
		try {
			String sql = "Select * from dbo.udfGetAllAvailableSlots(?,?,?)";

			list = jdbcTemplate.query(sql, new Object[] {patientId, doctorId, appointmentDate }, new SlotMapper());
			return list;
		} catch (DataAccessException e) {
			log.error("Error getting slots");
			throw new DataAccessResourceFailureException("Error getting slots");
		}
		

	}
}
