package beans.web.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import beans.web.model.Appointment;
import beans.web.model.Patient;
import beans.web.model.PatientMedicalHistory;
import beans.web.service.PatientMedicalHistoryService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientMedicalHistoryController {
    @Autowired
    private Logger log;
// private static Logger log = LogManager.getLogger(PatientController.class);
    @Autowired
    private PatientMedicalHistoryService patientMedicalHistoryService;
    @GetMapping(value = "/patients/history/{patientId}")
    public ResponseEntity<List<PatientMedicalHistory>> getPatientMedicalHistory(
            @PathVariable("patientId") String patientId) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<PatientMedicalHistory> list = patientMedicalHistoryService.getPatientMedicalHistory(patientId);
            headers.add("Number Of Records Found", String.valueOf(list.size()));
            log.info("successfully accessed all medical history records of patient " + patientId);
            return new ResponseEntity<List<PatientMedicalHistory>>(list, headers, HttpStatus.OK);
        } catch (HibernateException e) {
            log.error("cannot access records from database");
            return new ResponseEntity<List<PatientMedicalHistory>>(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/patients/history", method = RequestMethod.POST)
    public ResponseEntity<PatientMedicalHistory> createPatientMedicalHistory(@RequestBody PatientMedicalHistory patientMedicalHistory)
             {
        HttpHeaders headers = new HttpHeaders();
        
        try {
        PatientMedicalHistory hist = this.patientMedicalHistoryService.addPatientMedicalHistory(patientMedicalHistory);
        log.info("Patient Medical History Saved for patient   - "+ hist.getPatientId());
        headers.add("Patient Medical History Saved - ", hist.getPatientId());
        return new ResponseEntity<PatientMedicalHistory>(hist, headers, HttpStatus.CREATED);
        }catch (HibernateException e) {
            System.err.println(e);
            return new ResponseEntity<PatientMedicalHistory>(HttpStatus.BAD_REQUEST);
        }
    }
    
    
  
}