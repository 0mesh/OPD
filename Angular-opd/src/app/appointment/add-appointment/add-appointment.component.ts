import { Component, SimpleChanges } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Doctor, doctorMapFinal } from '../doctorArray';
import { Appointment } from 'src/app/model/appointment';
import { AppointmentService } from 'src/app/services/appointment.service';
import { DatePipe } from '@angular/common';
import { Slots } from 'src/app/model/Slots';
import { stringify } from '@angular/compiler/src/util';
import { ChangeDetectorRef, AfterContentChecked } from '@angular/core';

@Component({
  selector: 'app-add-appointment',
  templateUrl: './add-appointment.component.html',
  styleUrls: ['./add-appointment.component.css'],
})
export class AddAppointmentComponent {
  appointment: Appointment;
  appointmentToString: string;
  slots: Slots[] = [];
  id: string;
  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
  }
  currentDate = new Date();
  date = new Date();
  minDate = this.datePipe.transform(
    this.currentDate.setDate(this.currentDate.getDate() + 1),
    'yyyy-MM-dd'
  );

  maxDate = this.datePipe.transform(
    this.date.setDate(this.date.getDate() + 30),
    'yyyy-MM-dd'
  );

  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private routerService: Router,
    private appointmentService: AppointmentService,
    private datePipe: DatePipe,
    private cdRef: ChangeDetectorRef
  ) {
    this.appointment = new Appointment();
  }

  ngAfterContentChecked() {
    this.cdRef.detectChanges();
  }

  registrationForm = this.fb.group({
    patientId: [
      '',
      [
        Validators.required,
        Validators.minLength(7),
        Validators.maxLength(7),
        Validators.pattern('^[P]{1}[0-9]{6}'),
      ],
    ],
    organ: ['', [Validators.required]],
    doctorName: ['', Validators.required],
    doctorId: [''],
    appointmentDate: ['', [Validators.required]],
    slotStartTime: ['', Validators.required],
    slotEndTime: ['', Validators.required],
  });

  get patientIdValidator() {
    return this.registrationForm.get('patientId');
  }

  get affectedOrganValidator() {
    return this.registrationForm.get('organ');
  }
  get doctorNameValidator() {
    return this.registrationForm.get('doctorName');
  }
  get doctorIdValidator() {
    return this.registrationForm.get('doctorId');
  }
  get appointmentDateValidator() {
    return this.registrationForm.get('appointmentDate');
  }

  get dateValidator() {
    return this.registrationForm.get('appointmentDate');
  }
  get startTimeValidator() {
    return this.registrationForm.get('slotStartTime');
  }

  get endTimeValidator() {
    return this.registrationForm.get('slotEndTime');
  }

  DoctorMap = doctorMapFinal;
  specialityArray = Array.from(doctorMapFinal.keys());
  speciality: string;
  doctorArrayMap = [];
  doctorName: string;
  tempdoctorName: string;
  doctorId: string = '';
  doctorObject: Doctor;

  ngOnChanges(changes: SimpleChanges): void {
    for (const propName in changes) {
      const chng = changes[propName];

      const cur = JSON.stringify(chng.currentValue);
      const prev = JSON.stringify(chng.previousValue);
    }
  }

  onSelectedSpeciality() {
    this.appointment.doctorName = null;
    this.appointment.appointmentDate = null;
    this.tempdoctorName = null;
    console.log(this.speciality);
    this.doctorArrayMap = doctorMapFinal.get(this.speciality);
    this.doctorName = '';
    this.doctorId = '';
  }
  onSelectedDoctor() {
    this.doctorId = this.tempdoctorName.substring(0, 7);
  }

  public getSlots() {
    let tempDate: string = this.datePipe.transform(
      this.appointment.appointmentDate,
      'yyyy-MM-dd'
    );
    this.appointment.slotStartTime = null;
    this.appointment.slotEndTime = null;
    console.log('Date- ' + tempDate);
    this.appointmentService
      .getSlots(this.id, this.doctorId, tempDate)
      .subscribe({
        next: (data) => {
          this.slots = data;
          console.log(this.slots);
        },
        error: (error) => {
          console.log(error);
        },
        complete: () => {
          console.log('Completed successfully');
        },
      });
  }

  //endTime

  public getEndTime() {
    let temp = this.slots.filter((slot) => {
      if (slot.startTime === this.appointment.slotStartTime) {
        return true;
      }
    });

    this.appointment.slotEndTime = temp[0].endTime;
  }

  // public onChange(event) {
  //   let i =  event.target;
  //   console.log(i);
  //   this.doctorId = this.doctorArrayMap[i]['doctorId'];

  //   console.log(this.doctorArrayMap[i]);
  // }

  //onSubmit

  public saveAppointment(appointmentForm) {
    console.log(this.tempdoctorName);
    let docNameIDcopy: string = this.tempdoctorName;
    console.log(docNameIDcopy);
    // this.registrationForm.patchValue({
    //   doctorName: docNameIDcopy.split("-")[1],
    //   doctorId: this.doctorId,
    // });

    console.log(this.doctorName);
    this.appointment.patientId = this.id;
    this.appointment.doctorId = this.doctorId;
    this.appointment.doctorName = docNameIDcopy.split('-')[1];
    this.appointment.organ = this.speciality;
    console.log('double shot -' + this.doctorName);
    console.log('appointment object - ');
    console.log(this.appointment);
    if (appointmentForm.dirty && appointmentForm.valid) {
      console.log('appointment interface values ' + this.appointmentToString);
      this.appointmentService.newAppointment(this.appointment).subscribe({
        next: (x) => {
          this.appointment = x;
          this.appointmentToString = this.appointment.toString();
          console.log('From appointmentService ' + this.appointment);
        },
        error: (err) => {
          console.log('error occured' + err);
        },
        complete: () => {
          console.log('completed');
          alert(
            'Appointment fixed for Patient Id ' + this.appointment.patientId
          );
          this.routerService.navigate(['/appointments/all']);
        },
      });
    }
  }
}
