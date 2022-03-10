import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Patient } from 'src/app/model/patient';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-patient-registration',
  templateUrl: './patient-registration.component.html',
  styleUrls: ['./patient-registration.component.css'],
})
export class PatientRegistrationComponent {
  patient: Patient;
  patientToString: string;
  newPatient: Patient;
  maxDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  constructor(
    private fb: FormBuilder,
    private patientService: PatientService,
    private datePipe: DatePipe,
    private activatedRoute: ActivatedRoute,
    private routerService: Router
  ) {
    this.patient = new Patient();
  }

  registrationForm = this.fb.group({
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
    dateOfBirth: ['', [Validators.required]],
    ssn: [
      '',
      [
        Validators.required,
        Validators.pattern('^[0-9]{3}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$'),
      ],
    ],
  });

  get firstNameValidator() {
    return this.registrationForm.get('firstName');
  }

  get lastNameValidator() {
    return this.registrationForm.get('lastName');
  }
  get emailValidator() {
    return this.registrationForm.get('email');
  }
  get phoneValidator() {
    return this.registrationForm.get('phone');
  }
  get addressValidator() {
    return this.registrationForm.get('address');
  }
  get cityValidator() {
    return this.registrationForm.get('city');
  }
  get stateValidator() {
    return this.registrationForm.get('state');
  }
  get genderValidator() {
    return this.registrationForm.get('gender');
  }
  get dateOfBirthValidator() {
    return this.registrationForm.get('dateOfBirth');
  }
  get ssnValidator() {
    return this.registrationForm.get('ssn');
  }
  public savePatient(registerPatientForm) {
    console.log(registerPatientForm);
    if (registerPatientForm.dirty && registerPatientForm.valid) {
      // alert(`patient : ${JSON.stringify(registerPatientForm.value)}`);
      console.log(registerPatientForm.value);
      console.log(this.patient);
      this.patientService.addPatient(this.patient).subscribe({
        next: (x: Patient) => {
          this.newPatient = x;
          console.log('data from server-');
          console.log(x);
          this.patientToString = this.newPatient.toString();
          console.log(this.newPatient);
          alert('patient added with id -' + this.newPatient.patientId);
          console.log(this.newPatient.patientId);
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
