import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Patient } from '../model/patient';
import { formatDate } from '@angular/common';
import { MedicalHistory } from '../model/MedicalHistory';
@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private http: HttpClient;
  private url: string = 'http://localhost:8080/patients';
  patientObservable: Observable<Patient>;
  constructor(http: HttpClient) {
    this.http = http;
  }
  public addPatient(patient: Patient): Observable<Patient> {
    console.log('patient to be added');
    console.log(patient);

    return this.http.post<Patient>(this.url, patient).pipe(
      map((x) => {
        x.dateOfBirth = new Date(x.dateOfBirth);
        x.firstName = x.firstName.trim();
        x.lastName = x.lastName.trim();
        return x;
      }),
      tap((x) => {
        console.log('getting patient details of patientid ' + x.patientId);
        console.log(x);
      })
    );
  }
  public getPatient(id: string): Observable<Patient> {
    return this.http.get<Patient>(`${this.url}/${id}`).pipe(
      map((x) => {
        x.dateOfBirth = new Date(x.dateOfBirth);
        x.firstName = x.firstName.trim();
        x.lastName = x.lastName.trim();
        return x;
      }),
      tap((x) => {
        console.log('getting patient details of patientid ' + x.patientId);
        console.log(x);
      })
    );
  }
  public getAllPatients(): Observable<Patient[]> {
    return this.http.get<Patient[]>(this.url).pipe(
      map((x) => {
        x.forEach((data) => {
          data.dateOfBirth = new Date(data.dateOfBirth);
          data.firstName = data.firstName.trim();
          data.lastName = data.lastName.trim();
        });
        return x;
      }),
      tap((x) => {
        console.log('getting patient details of patientid ' + x.length);
        console.log(x);
      })
    );
  }
  public updatePatient(patient: Patient): Observable<Patient> {
    return this.http
      .put<Patient>(`${this.url}/${patient.patientId}`, patient)
      .pipe(
        map((x) => {
          x.dateOfBirth = new Date(x.dateOfBirth);
          x.firstName = x.firstName.trim();
          x.lastName = x.lastName.trim();
          return x;
        }),
        tap((x) => {
          console.log('getting patient details of patientid ' + x.patientId);
          console.log(x);
        })
      );
  }

  public getMedicalHistory(id: string): Observable<MedicalHistory[]> {
    return this.http.get<MedicalHistory[]>(`${this.url}/history/${id}`);
  }

  public deletePatient(id: string): Observable<Patient> {
    return this.http.delete<Patient>(`${this.url}/${id}`).pipe(
      map((x) => {
        x.dateOfBirth = new Date(x.dateOfBirth);
        x.firstName = x.firstName.trim();
        x.lastName = x.lastName.trim();
        return x;
      }),
      tap((x) => {
        console.log('getting patient details of patientid ' + x.patientId);
        console.log(x);
      })
    );
  }
}
