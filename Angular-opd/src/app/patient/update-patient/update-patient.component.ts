import { DatePipe } from '@angular/common';
import { ChangeDetectorRef, AfterContentChecked } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from 'src/app/model/patient';
import { PatientService } from 'src/app/services/patient.service';
@Component({
  selector: 'app-update-patient',
  templateUrl: './update-patient.component.html',
  styleUrls: ['./update-patient.component.css'],
})
export class UpdatePatientComponent implements OnInit {
  id: string;
  patient: Patient;
  dateofbirth: string;
  updatedPatient: Patient;
  constructor(
    private activatedRoute: ActivatedRoute,
    private routerService: Router,
    private patientService: PatientService,
    private fb: FormBuilder,
    private datePipe: DatePipe,
    private cdRef: ChangeDetectorRef
  ) {
    this.patient = new Patient();
  }
  updatePatientForm = this.fb.group({
    patientId: [],
    firstName: [
      '',
      [
        Validators.required,
        Validators.minLength(5),
        Validators.pattern('^[a-zA-Z]+$'),
      ],
    ],
    lastName: [
      '',
      [
        Validators.required,
        Validators.minLength(5),
        Validators.pattern('^[a-zA-Z]+$'),
      ],
    ],
    email: [
      '',
      [
        Validators.required,
        Validators.pattern('[A-Za-z0-9._%-]+@[A-Za-z0-9._%-]+\\.[a-z]{2,3}'),
      ],
    ],
    phone: [
      '',
      [
        Validators.required,
        Validators.pattern(
          '^[0-9]{10}$|^[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{4}$'
        ),
      ],
    ],
    address: ['', [Validators.required]],
    city: [
      '',
      [
        Validators.required,
        Validators.minLength(3),
        Validators.pattern('^[a-zA-Z]+$'),
      ],
    ],
    state: [
      '',
      [
        Validators.required,
        Validators.minLength(3),
        Validators.pattern('^[a-zA-Z]+$'),
      ],
    ],
    gender: ['', [Validators.required]],
    ssn: [
      '',
      [
        Validators.required,
        Validators.pattern('^[0-9]{3}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$'),
      ],
    ],
  });
  get firstNameValidator() {
    return this.updatePatientForm.get('firstName');
  }
  get lastNameValidator() {
    return this.updatePatientForm.get('lastName');
  }
  get emailValidator() {
    return this.updatePatientForm.get('email');
  }
  get phoneValidator() {
    return this.updatePatientForm.get('phone');
  }
  get addressValidator() {
    return this.updatePatientForm.get('address');
  }
  get cityValidator() {
    return this.updatePatientForm.get('city');
  }
  get stateValidator() {
    return this.updatePatientForm.get('state');
  }
  get genderValidator() {
    return this.updatePatientForm.get('gender');
  }
  get ssnValidator() {
    return this.updatePatientForm.get('ssn');
  }
  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['id'];
    console.log('patient id:' + this.id);
    this.patientService.getPatient(this.id).subscribe({
      next: (data) => {
        this.patient = data;
        this.dateofbirth = this.datePipe.transform(
          this.patient.dateOfBirth,
          'yyyy-MM-dd'
        );
        console.log(this.patient);
      },
    });
    console.log(this.dateofbirth);
  }
  ngAfterContentChecked() {
    this.cdRef.detectChanges();
  }
  updatePatient(updatePatientForm) {
    if (updatePatientForm.dirty && updatePatientForm.valid) {
      console.log(this.patient);
      this.patientService.updatePatient(this.patient).subscribe({
        next: (x: Patient) => {
          this.updatedPatient = x;
          console.log('data from server-');
          console.log(x);
          // this.patientToString = this.newPatient.toString();
          console.log(this.updatedPatient);
          alert('patient updated');
          console.log(this.updatedPatient);
        },
        error: (err) => {
          console.log('error occured' + err);
        },
        complete: () => {
          console.log('completed');
          this.routerService.navigate(['/patients']);
        },
      });
    }
  }
}
