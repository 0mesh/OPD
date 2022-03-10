import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MedicalHistory } from '../model/MedicalHistory';

@Injectable({
  providedIn: 'root',
})
export class PatientMedicalHistoryService {
  private http: HttpClient;
  private url: string = 'http://localhost:8080/patients/history';

  constructor(http: HttpClient) {
    this.http = http;
  }

  public addPatientMedicalHistory(
    history: MedicalHistory
  ): Observable<MedicalHistory> {
    return this.http.post<MedicalHistory>(this.url, history, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      // observe: 'response'
    });
  }

  public getPatientMedicalHistory(id: string): Observable<MedicalHistory[]> {
    return this.http.get<MedicalHistory[]>(`${this.url}/${id}`);
  }
}
