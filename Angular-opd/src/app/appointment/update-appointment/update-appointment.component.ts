import { DatePipe } from '@angular/common';
import { Component, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Appointment } from 'src/app/model/appointment';
import { Slots } from 'src/app/model/Slots';
import { AppointmentService } from 'src/app/services/appointment.service';
import { doctorMapFinal, Doctor } from '../doctorArray';
import { ChangeDetectorRef, AfterContentChecked } from '@angular/core';

@Component({
  selector: 'app-update-appointment',
  templateUrl: './update-appointment.component.html',
  styleUrls: ['./update-appointment.component.css'],
})
export class UpdateAppointmentComponent implements OnInit {
  appointment: Appointment;
  appointmentToString: string;
  slots: Slots[] = [];
  id: string;
  appId: string;
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

  ngOnInit() {
    this.appId = this.activatedRoute.snapshot.params['id'];
    console.log('patient id:' + this.id);
    this.appointmentService.getAppointmentById(this.appId).subscribe({
      next: (data) => {
        this.appointment = data;
        console.log(this.appointment);
      },
    });
  }
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
  updateForm = this.fb.group({
    appointmentId: [''],
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
    return this.updateForm.get('patientId');
  }
  get affectedOrganValidator() {
    return this.updateForm.get('organ');
  }
  get doctorNameValidator() {
    return this.updateForm.get('doctorName');
  }
  get doctorIdValidator() {
    return this.updateForm.get('doctorId');
  }
  get appointmentDateValidator() {
    return this.updateForm.get('appointmentDate');
  }
  get dateValidator() {
    return this.updateForm.get('appointmentDate');
  }
  get startTimeValidator() {
    return this.updateForm.get('slotStartTime');
  }
  get endTimeValidator() {
    return this.updateForm.get('slotEndTime');
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
  ngAfterContentChecked() {
    this.cdRef.detectChanges();
  }
  onSelectedSpeciality() {
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
    console.log('Date- ' + tempDate);
    this.appointmentService
      .getSlots(this.id, this.appointment.doctorId, tempDate)
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
  //onSubmit
  public updateAppointment(appointmentForm) {
    if (appointmentForm.dirty && appointmentForm.valid) {
      console.log('appointment interface values ' + this.appointmentToString);
      this.appointmentService.updateAppointment(this.appointment).subscribe({
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
            'Appointment updated for Patient Id ' + this.appointment.patientId
          );
          this.routerService.navigate(['/appointments/all']);
        },
      });
    }
  }
}
