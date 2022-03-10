package beans.web.repository;

import java.sql.Date;
import java.util.List;

import beans.web.model.Appointment;
import beans.web.model.Slots;


public interface AppointmentDAO {

	Appointment newAppointment(Appointment appointment);

	List<Appointment> getAllAppointments();

	Appointment deleteAppointment(String AppointmentId);

	Appointment updateAppointment(String AppointmentId, Appointment appointment);

	Appointment getAppointmentbyId(String AppointmentId);

	List<Appointment> getAppointmentsbyPatientId(String PatientId);
	
	List<Slots> getSlots(String patientId,String doctorId,Date appointmentDate);

}
