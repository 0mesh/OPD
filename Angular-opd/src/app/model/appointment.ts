export class Appointment {
  constructor() {}

  public appointmentId?: string;
  public patientId: string;

  public organ: string;

  public doctorName: string;

  public doctorId: string;

  public appointmentDate: Date;

  public slotStartTime;

  public slotEndTime;
}
