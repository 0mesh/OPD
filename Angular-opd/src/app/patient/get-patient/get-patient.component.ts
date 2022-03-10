import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Patient } from 'src/app/model/patient';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-get-patient',
  templateUrl: './get-patient.component.html',
  styleUrls: ['./get-patient.component.css'],
})
export class GetPatientComponent implements OnInit {
  patient: Patient;
  patientToString: string;
  patientId: string;
  patientExist: boolean = false;
  getPatientForm = this.fb.group({
    patientId: [
      '',
      [
        Validators.required,
        Validators.minLength(7),
        Validators.maxLength(7),
        Validators.pattern('^[P]{1}[0-9]{6}'),
      ],
    ],
  });
  constructor(
    private fb: FormBuilder,
    private patientService: PatientService
  ) {}

  get patientIdValidator() {
    return this.getPatientForm.get('patientId');
  }
  ngOnInit(): void {}
  getPatient(getPatientForm) {
    if (getPatientForm.dirty && getPatientForm.touched) {
      console.log('patient id entered is :' + this.patientId);
      this.patientService.getPatient(this.patientId).subscribe({
        next: (data: Patient) => {
          this.patient = data;
          console.log('patient retreived - ');
          console.log(this.patient);
          this.patientExist = true;
        },
        error: (err) => {
          alert('Patient with id: ' + this.patientId + ' does not exists');
        },
        complete: () => {
          console.log('completed');
        },
      });
    }
  }
}
