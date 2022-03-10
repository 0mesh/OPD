import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { AddAppointmentComponent } from '../add-appointment/add-appointment.component';
import { AppointmentComponent } from '../appointment/appointment.component';
import { GetAllAppointmentsComponent } from '../get-all-appointments/get-all-appointments.component';
import { UpdateAppointmentComponent } from '../update-appointment/update-appointment.component';
import { ActivatedRouteSnapshot, RouterModule } from '@angular/router';

import { Routes } from '@angular/router';
import { HomeComponent } from 'src/app/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/app/material/material.module';
import { ConfirmationDialogComponent } from 'src/app/patient/confirmation-dialog/confirmation-dialog.component';

export const appointmentRoutes: Routes = [
  {
    path: '',
    component: AppointmentComponent,
    children: [
      { path: 'all', component: GetAllAppointmentsComponent },
      {
        path: 'update/:id',
        component: UpdateAppointmentComponent,
      },
      { path: 'new/:id', component: AddAppointmentComponent },
      { path: '', redirectTo: 'all', pathMatch: 'full' },
    ],
  },
  { path: '**', pathMatch: 'full', component: HomeComponent },
];

@NgModule({
  declarations: [
    AddAppointmentComponent,
    AppointmentComponent,
    GetAllAppointmentsComponent,
    UpdateAppointmentComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(appointmentRoutes),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialModule,
    FlexLayoutModule,
  ],
})
export class AppointmentRoutingModule {
  constructor() {
    console.log('Appointment Routing Module');
  }
}
