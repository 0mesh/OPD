import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Appointment } from '../model/appointment';
import { Slots } from '../model/Slots';

@Injectable({
  providedIn: 'root',
})
export class AppointmentService {
  private http: HttpClient;
  private url: string = 'http://localhost:8080/appointments';
  private slotUrl: string = 'http://localhost:8080/slots';
  private appointmentUrl = 'http://localhost:8080/appointments/patient';
  appointmentObservable: Observable<Appointment>;
  constructor(http: HttpClient) {
    this.http = http;
  }

  public newAppointment(appointment: Appointment): Observable<Appointment> {
 
    return this.http
      .post<Appointment>(this.url, appointment, {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
        // observe: 'response'
      })
      .pipe(
        map((x) => {
          x.appointmentDate = new Date(x.appointmentDate);
          return x;
        }),
        tap((x) => {
          console.log(x);
        })
      );
  }

  public getAllAppointments(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(this.url);
  }

  public getSlots(
    patientId: string,
    doctorId: string,
    appointmentDate: string
  ): Observable<Slots[]> {
    return this.http.get<Slots[]>(
      `${this.slotUrl}/${patientId}/${doctorId}/${appointmentDate}`
    );
  }

  public deleteAppointment(id: string): Observable<Appointment> {
    return this.http.delete<Appointment>(`${this.url}/${id}`);
  }

  public updateAppointment(appointment: Appointment): Observable<Appointment> {
    return this.http.put<Appointment>(
      `${this.url}/${appointment.appointmentId}`,
      appointment
    );
  }

  public getAppointmentById(id: string): Observable<Appointment> {
    return this.http.get<Appointment>(`${this.url}/${id}`);
  }

  public getAllAppointmentsOfPatient(id: string): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${this.appointmentUrl}/${id}`);
  }
}
