import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';
import { PatientComponent } from '../patient/patient.component';
import { PatientRegistrationComponent } from '../patient-registration/patient-registration.component';
import { UpdatePatientComponent } from '../update-patient/update-patient.component';
import { GetAllPatientsComponent } from '../get-all-patients/get-all-patients.component';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { MedicalHistoryComponent } from '../medical-history/medical-history.component';

import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MaterialModule } from 'src/app/material/material.module';
import { HomeComponent } from 'src/app/home/home.component';
import { GetDetailsComponent } from '../get-details/get-details.component';

export const patientRoutes: Routes = [
  {
    path: '',
    component: PatientComponent,
    children: [
      { path: 'new', component: PatientRegistrationComponent },
      { path: 'update/:id', component: UpdatePatientComponent },
      { path: 'details/:id', component: GetDetailsComponent },
      { path: 'history/:id', component: MedicalHistoryComponent },
      { path: 'all', component: GetAllPatientsComponent },
      { path: '', redirectTo: 'all', pathMatch: 'full' },
    ],
  },
  { path: '**', pathMatch: 'full', component: HomeComponent },
];
@NgModule({
  declarations: [
    PatientComponent,
    PatientRegistrationComponent,
    UpdatePatientComponent,
    GetAllPatientsComponent,
    MedicalHistoryComponent,
    GetDetailsComponent,
    MedicalHistoryComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(patientRoutes),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialModule,
    FlexLayoutModule,
  ],
})
export class PatientRoutingModule {}
