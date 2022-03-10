package beans.web.model;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
	private String appointmentId;
	private String doctorId;
	private String patientId;
	private AffectedOrgan organ;
	private String doctorName;
	private Date appointmentDate;
	private Time slotStartTime;
	private Time slotEndTime;
	private boolean isActive;

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", doctorId=" + doctorId + ", patientId=" + patientId
				+ ", organ=" + organ + ", doctorName=" + doctorName + ", appointmentDate=" + appointmentDate
				+ ", slotStartTime=" + slotStartTime + ", slotEndTime=" + slotEndTime + ", isActive=" + isActive + "]";
	}

	public AffectedOrgan getOrgan() {
		return organ;
	}

	public void setOrgan(AffectedOrgan organ) {
		this.organ = organ;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Appointment() {
		super();
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		if (appointmentDate == null) {
			throw new NullPointerException("Appointment Date is null");
		}
		this.appointmentDate = appointmentDate;
	}

	public Time getSlotStartTime() {
		return slotStartTime;
	}

	public void setSlotStartTime(Time slotStartTime) {
		if (slotStartTime == null) {
			throw new NullPointerException("Slot timings cannot be null");
		}
		this.slotStartTime = slotStartTime;
	}

	public Time getSlotEndTime() {
		return slotEndTime;
	}

	public void setSlotEndTime(Time slotEndTime) {
		if (slotStartTime == null) {
			throw new NullPointerException("Slot timings cannot be null");
		}
		this.slotEndTime = slotEndTime;
	}

}
