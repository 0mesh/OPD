import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css'],
})
export class PatientComponent implements OnInit {
  opened = true;
  registerPatient = false;
  updatePatient = false;

  allPatients = true;
  constructor() {}

  ngOnInit(): void {}
  public onAllPatients() {
    this.opened = true;
    this.registerPatient = false;

    this.allPatients = true;
  }
  public onnewPatientRegistration() {
    this.registerPatient = true;

    this.allPatients = false;
  }
}
