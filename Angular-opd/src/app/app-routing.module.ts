import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';

const routes: Routes = [

  
  {
    path: 'patients',
    loadChildren: () =>
      import('../app/patient/patient-routing/patient-routing.module').then(
        (m) => m.PatientRoutingModule
      ),
  },

  //appointments sub routes

  {
    path: 'appointments',
    loadChildren: () =>
      import(
        '../app/appointment/appointment-routing/appointment-routing.module'
      ).then((m) => m.AppointmentRoutingModule),
  },

  { path: '**', pathMatch: 'full', component: HomeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
