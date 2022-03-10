import { Patient } from './patient';

export class MedicalHistory {
  constructor() {}
  public id: number;
  public patientId: string;
  public height: number;
  public weight: number;
  public systolic: number;
  public diastolic: number;
  public pulseRate: number;
  public historyUpdatedOn: Date;
}
