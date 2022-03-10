import { DatePipe } from '@angular/common';
import { ChangeDetectorRef, AfterContentChecked } from '@angular/core';
import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { MedicalHistory } from 'src/app/model/MedicalHistory';
import { Patient } from 'src/app/model/patient';
import { PatientService } from 'src/app/services/patient.service';
import { PatientMedicalHistoryService } from '../../services/patient-medical-history.service';

@Component({
  selector: 'app-get-details',
  templateUrl: './get-details.component.html',
  styleUrls: ['./get-details.component.css'],
})
export class GetDetailsComponent implements OnInit {
  id: string;
  patient: Patient;

  dateofbirth: any;
  datePipe: any;
  historyList: MedicalHistory[];
  deletedList: MedicalHistory[];

  constructor(
    private activatedRoute: ActivatedRoute,
    private patientService: PatientService,
    private datepipe: DatePipe,
    private patientMedicalHistoryService: PatientMedicalHistoryService,
    private cdRef: ChangeDetectorRef
  ) {
    this.patient = new Patient();
  }
  ngAfterContentChecked() {
    this.cdRef.detectChanges();
  }
  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['id'];

    this.patientService.getPatient(this.id).subscribe({
      next: (data) => {
        this.patient = data;
        this.dateofbirth = this.datepipe.transform(
          this.patient.dateOfBirth,
          'dd/MM/yyyy'
        );
      },
    });
    this.patientMedicalHistoryService
      .getPatientMedicalHistory(this.id)
      .subscribe({
        next: (data) => {
          this.historyList = data;
          console.log(this.historyList);
        },
        error: (err) => {
          console.log(err);
        },
        complete: () => {
          console.log('Details Accessed');
        },
      });
  }
}
