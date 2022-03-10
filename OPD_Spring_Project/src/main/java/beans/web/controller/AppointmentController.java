package beans.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.itextpdf.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import beans.web.model.Appointment;
import beans.web.model.Physician;
import beans.web.model.Slots;
import beans.web.service.AppointmentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AppointmentController {

	@Autowired
	private AppointmentService service;

	// getAppointmentForm

	@RequestMapping(value = "/appointments/new", method = RequestMethod.GET)
	public String getIdSearchBar() {

		return "enterPatientId"; // enterPatientId.jsp
	}

//	@RequestMapping(value = "/appointments/form/submitpatientid", method = RequestMethod.POST)
//	public String checkPatientId(@RequestParam("id") String id, RedirectAttributes redirectAttributes) {
//
//		try {
//			this.service.SearchPatient(id);
//
//			redirectAttributes.addFlashAttribute("patientId", id);
//			return "redirect:/appointment/form";
//		} catch (Exception e) {
//			return "idNotPresent";
//		}
//
//	}


	@RequestMapping(value = "/appointments", method = RequestMethod.POST)
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment, Model model)
			throws FileNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		
		if (appointment == null) {
			return new ResponseEntity<Appointment>(HttpStatus.BAD_REQUEST);
		}
		Appointment ap = this.service.newAppointment(appointment);
		model.addAttribute("appointment", ap);
		headers.add("Appointment Created with Appointment Id- ", appointment.getAppointmentId());

		// pdf report
		System.out.println(appointment);
		String dest = "C:\\Users\\omeshA\\Desktop\\Appointment" + ap.getAppointmentId() + ".pdf";
		File file = new File(dest);
		file.getParentFile().mkdirs();
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);
		doc.add(new Paragraph("Appointment Report"));
		Table table = new Table(UnitValue.createPercentArray(8)).useAllAvailableWidth();
		// PatientId,DoctorId,AffectedOrgan,DoctorName,AppointmentDate,StartTime,EndTime
		table.addHeaderCell("PatientId");
		table.addHeaderCell("Appointment Id");
		table.addHeaderCell("Doctor Id");
		table.addHeaderCell("Doctor Name");
		table.addHeaderCell("Appointment Date");
		table.addHeaderCell("Start Time of slot");

		table.addCell(ap.getPatientId());
		table.addCell(ap.getAppointmentId());
		table.addCell(ap.getDoctorId());
		table.addCell(ap.getDoctorName());
		table.addCell(ap.getAppointmentDate().toString());
		table.addCell(ap.getSlotStartTime().toString());

		doc.add(table);

		doc.close();

		return new ResponseEntity<Appointment>(ap, headers, HttpStatus.CREATED);

	}
	


	// Update Appointment
	@PutMapping(value = "/appointments/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") String appointmentId,
			@RequestBody Appointment appFromBody) {
		HttpHeaders headers = new HttpHeaders();
		Appointment isExist = this.service.getAppointmentbyId(appointmentId);

		if (isExist == null) {
			return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
		} else if (appFromBody == null) {
			return new ResponseEntity<Appointment>(HttpStatus.BAD_REQUEST);
		}
		this.service.updateAppointment(appointmentId, appFromBody);

		headers.add("Appointment Updated  - ", appointmentId);
		return new ResponseEntity<Appointment>(this.service.getAppointmentbyId(appointmentId), headers, HttpStatus.OK);
	}

	// delete Appointment

	@DeleteMapping(value = "/appointments/{id}")
	public ResponseEntity<Appointment> deleteAppointment(@PathVariable("id") String appointmentId) {
		HttpHeaders headers = new HttpHeaders();
		Appointment appointment = this.service.getAppointmentbyId(appointmentId);
		if (appointment == null) {
			return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
		}
		this.service.deleteAppointment(appointmentId);
		;
		headers.add("Appointment Deleted  - ", appointmentId);
		return new ResponseEntity<Appointment>(appointment, headers, HttpStatus.OK);
	}

	// get All appointments

	@RequestMapping(value = "/appointments", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Appointment>> getAppointments() {

		HttpHeaders headers = new HttpHeaders();
		List<Appointment> appointments = this.service.getAllAppointments();

		if (appointments == null) {
			return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number Of Records Found", String.valueOf(appointments.size()));
		return new ResponseEntity<List<Appointment>>(appointments, headers, HttpStatus.OK);
	}
	
	
	//Get slots
	
	@RequestMapping(value = "/slots/{patientId}/{doctorId}/{date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Slots>> getSlots(@PathVariable("patientId") String patientId,@PathVariable("doctorId") String doctorId,@PathVariable("date") Date appointmentDate) {

		HttpHeaders headers = new HttpHeaders();
		List<Slots> slots = this.service.getSlots(patientId,doctorId, appointmentDate);

		if (slots == null) {
			return new ResponseEntity<List<Slots>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number Of Records Found", String.valueOf(slots.size()));
		return new ResponseEntity<List<Slots>>(slots, headers, HttpStatus.OK);
	}
	
   //Get appointment By Id
	
	@RequestMapping(value = "/appointments/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") String appointmentId) {

		HttpHeaders headers = new HttpHeaders();
		Appointment appointment = this.service.getAppointmentbyId(appointmentId);

		if (appointment == null) {
			return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Appointment>(appointment, headers, HttpStatus.OK);
	}
	
	// get All appointments by a patient

	@GetMapping(value = "/appointments/patient/{patientId}")
	public ResponseEntity<List<Appointment>> getAppointmentsofPatient(@PathVariable("patientId") String patientId) {
		HttpHeaders headers = new HttpHeaders();
		List<Appointment> appointmentsOfPatient = this.service.getAppointmentsbyPatientId(patientId);
		if (appointmentsOfPatient == null) {
			return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number Of Records Found", String.valueOf(appointmentsOfPatient.size()));
		return new ResponseEntity<List<Appointment>>(appointmentsOfPatient, headers, HttpStatus.OK);
	}

}
