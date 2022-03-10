package beans.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "PatientMedicalHistory")
public class PatientMedicalHistory {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;
	
	@Column(name="Height",nullable=false)
	private Integer height;
	
	@Column(name="Weight",nullable=false)
	private Integer weight;
	
	@Column(name="Diastolic",nullable=false)
	private Integer diastolic ;
	
	@Column(name="Systolic",nullable=false)
	private Integer systolic;
	
	@Column(name="PulseRate",nullable=false)
	private int pulseRate;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="HistoryUpdatedOn")
	private Date historyUpdatedOn;
	
	@Column(name="PatientId")
	private String patientId;
	
	public PatientMedicalHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
//	public String getPatientId() {
//		return patientId;
//	}
//
//	public void setPatientId(String patientId) {
//		this.patientId = patientId;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	

	

	public Integer getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(Integer diastolic) {
		this.diastolic = diastolic;
	}

	public Integer getSystolic() {
		return systolic;
	}

	public void setSystolic(Integer systolic) {
		this.systolic = systolic;
	}

	public int getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(int pulseRate) {
		this.pulseRate = pulseRate;
	}

	public Date getHistoryUpdatedOn() {
		return historyUpdatedOn;
	}

	public void setHistoryUpdatedOn(Date historyUpdatedOn) {
		this.historyUpdatedOn = historyUpdatedOn;
	}

	

	@Override
	public String toString() {
		return "PatientMedicalHistory [id=" + id + ", height=" + height + ", weight=" + weight + ", diastolic="
				+ diastolic + ", systolic=" + systolic + ", pulseRate=" + pulseRate + ", historyUpdatedOn="
				+ historyUpdatedOn +  "]";
	}
	
	
}
