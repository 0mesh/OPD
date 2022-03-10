import { ChangeDetectorRef } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MedicalHistory } from 'src/app/model/MedicalHistory';
import { Patient } from 'src/app/model/patient';
import { PatientMedicalHistoryService } from 'src/app/services/patient-medical-history.service';
import { PatientService } from 'src/app/services/patient.service';
@Component({
  selector: 'app-medical-history',
  templateUrl: './medical-history.component.html',
  styleUrls: ['./medical-history.component.css'],
})
export class MedicalHistoryComponent implements OnInit {
  id: string;
  patient: Patient;
  medicalHistory: MedicalHistory;
  dateofbirth: string;
  updatedHistory: MedicalHistory;
  constructor(
    private activatedRoute: ActivatedRoute,
    private routerService: Router,
    private patientMedicalHistoryService: PatientMedicalHistoryService,
    private fb: FormBuilder,
    private cdRef: ChangeDetectorRef
  ) {
    this.medicalHistory = new MedicalHistory();
  }
  updateHistoryForm = this.fb.group({
    patientId: [],
    height: [
      '',
      [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(3),
        Validators.pattern('^[0-9]*$'),
        Validators.max(280),
        Validators.min(40),
      ],
    ],
    weight: [
      '',
      [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(3),
        Validators.pattern('^[0-9]*$'),
        Validators.max(600),
        Validators.min(2),
      ],
    ],
    systolic: [
      '',
      [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(3),
        Validators.pattern('^[0-9]*$'),
      ],
    ],
    diastolic: [
      '',
      [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(3),
        Validators.pattern('^[0-9]*$'),
      ],
    ],
    pulseRate: [
      '',
      [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(3),
        Validators.pattern('^[0-9]*$'),
      ],
    ],
  });
  get heightValidator() {
    return this.updateHistoryForm.get('height');
  }
  get weightValidator() {
    return this.updateHistoryForm.get('weight');
  }
  get systolicValidator() {
    return this.updateHistoryForm.get('systolic');
  }
  get diastolicValidator() {
    return this.updateHistoryForm.get('diastolic');
  }
  get pulseRateValidator() {
    return this.updateHistoryForm.get('pulseRate');
  }
  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.medicalHistory.patientId = this.id;
    console.log('ID- ' + this.id);
    console.log('MedId - ' + this.medicalHistory.patientId);
    this.cdRef.detectChanges();
  }
  addHistory(updateHistoryForm) {
    if (updateHistoryForm.dirty && updateHistoryForm.valid) {
      console.log(this.medicalHistory);
      console.log(this.medicalHistory.patientId);
      this.patientMedicalHistoryService
        .addPatientMedicalHistory(this.medicalHistory)
        .subscribe({
          next: (x: MedicalHistory) => {
            this.updatedHistory = x;
            console.log('data from server-');
            console.log(x);
            // this.patientToString = this.newPatient.toString();
            console.log(this.updatedHistory);
          },
          error: (err) => {
            console.log('error occured' + err);
          },
          complete: () => {
            console.log('completed');
            alert('Medical details Added');
            this.routerService.navigate(['/patients/details/' + this.id]);
          },
        });
    }
  }
}
