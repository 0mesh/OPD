import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
// import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { FlexLayoutModule } from '@angular/flex-layout';
import { DatePipe } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';

// import { UpdatePatientComponent } from './patient/update-patient/update-patient.component';

// import { GetAllPatientsComponent } from './patient/get-all-patients/get-all-patients.component';
import { ConfirmationDialogComponent } from './patient/confirmation-dialog/confirmation-dialog.component';
// import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';
// import { AppointmentComponent } from './appointment/appointment/appointment.component';
// import { CancelAppointmentComponent } from './appointment/cancel-appointment/cancel-appointment.component';
// import { GetAllAppointmentsComponent } from './appointment/get-all-appointments/get-all-appointments.component';

// import { AddAppointmentComponent } from './appointment/add-appointment/add-appointment.component';
// import { UpdateAppointmentComponent } from './appointment/update-appointment/update-appointment.component';
// import { PatientRegistrationComponent } from './patient/patient-registration/patient-registration.component';
// import { PatientComponent } from './patient/patient/patient.component';
// import { GetDetailsComponent } from './patient/get-details/get-details.component';
// import { MedicalHistoryComponent } from './patient/medical-history/medical-history.component';
// import { AppointmentRoutingModule } from './appointment/appointment-routing/appointment-routing.module';
// import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    // PatientComponent,
    // PatientRegistrationComponent,
    // UpdatePatientComponent,

    // GetAllPatientsComponent,
    ConfirmationDialogComponent,
    // ErrorComponent,
    HomeComponent,
    // AppointmentComponent,
    // CancelAppointmentComponent,
    // GetAllAppointmentsComponent,
    // AddAppointmentComponent,
    // UpdateAppointmentComponent,
    // GetDetailsComponent,
    // MedicalHistoryComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    // FormsModule,
    HttpClientModule,
    // ReactiveFormsModule,
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent],
})
export class AppModule {}
