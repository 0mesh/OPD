package beans.web.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.web.model.Appointment;
import beans.web.model.Patient;

import beans.web.model.Slots;
import beans.web.repository.AppointmentDAO;
import beans.web.repository.PatientDAO;


@Service
public class AppointmentService {

	@Autowired
	private AppointmentDAO appointmentRepo;

	
	
	public AppointmentService() {
		System.out.println("Inside AppointmentService class");
	}

	public Appointment newAppointment(Appointment appointment) {
		return this.appointmentRepo.newAppointment(appointment);

	}

	public List<Appointment> getAllAppointments() {
		return this.appointmentRepo.getAllAppointments();
	}

	public Appointment deleteAppointment(String AppointmentId) {
		return this.appointmentRepo.deleteAppointment(AppointmentId);

	}

	public Appointment updateAppointment(String AppointmentId, Appointment appointment) {
		return this.appointmentRepo.updateAppointment(AppointmentId, appointment);

	}

	public Appointment getAppointmentbyId(String AppointmentId) {
		return this.appointmentRepo.getAppointmentbyId(AppointmentId);
	}

	public List<Appointment> getAppointmentsbyPatientId(String PatientId) {
		return this.appointmentRepo.getAppointmentsbyPatientId(PatientId);
	}
	

	
	

	
	public List<Slots> getSlots(String patientId,String DoctorId, Date appointmentDate) {
		return this.appointmentRepo.getSlots(patientId,DoctorId, appointmentDate);
	}

}
