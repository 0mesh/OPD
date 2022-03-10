import { DatePipe, TitleCasePipe } from '@angular/common';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Appointment } from 'src/app/model/appointment';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import { AppointmentService } from 'src/app/services/appointment.service';
import { ConfirmationDialogComponent } from '../../patient/confirmation-dialog/confirmation-dialog.component';
import { PatientService } from 'src/app/services/patient.service';
import { Patient } from 'src/app/model/patient';
@Component({
  selector: 'app-get-all-appointments',
  templateUrl: './get-all-appointments.component.html',
  styleUrls: ['./get-all-appointments.component.css'],
})
export class GetAllAppointmentsComponent implements OnInit {
  appointmentList: Appointment[];
  dataSource = new MatTableDataSource();
  currentDate = new Date().getTime();
  patientList: Patient[];
  patientNameList: string[];
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  displayedColumns = [
    'appointmentId',
    'patientId',
    'doctorName',
    'appointmentDate',
    'startTime',
    'endTime',
    'Download',
    'Update',
    'Delete',
  ];
  constructor(
    private appointmentService: AppointmentService,
    private dialog: MatDialog,
    private datePipe: DatePipe,
    private patientService: PatientService
  ) {}
  ngAfterViewInit(): void {
    this.dataSource.sortingDataAccessor = this.sortByDate;
    this.dataSource.sort = this.sort;
  }
  ngOnInit(): void {
    this.appointmentService.getAllAppointments().subscribe({
      next: (data) => {
        this.appointmentList = data;
        this.dataSource = new MatTableDataSource(this.appointmentList);
        console.log(
          'no of records accessed is: ' + this.appointmentList.length
        );
        console.log(this.appointmentList);
        this.dataSource = new MatTableDataSource(this.appointmentList);
        this.dataSource.sortingDataAccessor = this.sortByDate;
        this.dataSource.sort = this.sort;
      },
      error: (err) => {
        console.log(err);
      },
      complete: () => {
        console.log('Completed');
      },
    });
  }
  deleteAppointment(appointment) {
    console.log(appointment);
    this.opendialog(appointment);
  }
  doFilter(value) {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }
  opendialog(appointment) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        message: 'Are you sure want to delete?',
        buttonText: {
          ok: 'Delete',
          cancel: 'Cancel',
        },
      },
    });
    dialogRef.afterClosed().subscribe((confirmed: boolean) => {
      if (confirmed) {
        console.log('delete confirmed');
        console.log(appointment);
        this.appointmentService
          .deleteAppointment(appointment.appointmentId)
          .subscribe({
            next: (data) => {
              console.log('deleted appointment with id ' + data.appointmentId);
              console.log(data);
              this.appointmentList = this.appointmentList.filter((ele) => {
                if (ele.appointmentId !== data.appointmentId) {
                  return true;
                }
              });
              this.dataSource.data = this.appointmentList;
              this.dataSource.sortingDataAccessor = this.sortByDate;
              this.dataSource.sort = this.sort;
            },
            error: (err) => {
              console.log(err);
            },
            complete: () => {
              console.log('completed');
              this.dataSource = new MatTableDataSource(this.appointmentList);
              this.dataSource.sortingDataAccessor = this.sortByDate;
              this.dataSource.sort = this.sort;
            },
          });
      }
    });
  }
  downloadAppointment(appointment) {
    var patient: Patient;
    this.patientService.getPatient(appointment.patientId).subscribe({
      next: (data) => {
        patient = data;
      },
      complete: () => {
        this.savePdf(patient, appointment);
      },
    });
  }
  private sortByDate(appoinment: Appointment, prop) {
    switch (prop) {
      case 'appointmentDate':
        return new Date(appoinment.appointmentDate);

      default:
        return appoinment[prop];
    }
  }
  private savePdf(patient: Patient, appointment: Appointment) {
    let printDate = this.datePipe.transform(
      new Date(appointment.appointmentDate),
      'dd/MM/yyyy'
    );
    const timeDiff = Math.abs(Date.now() - patient.dateOfBirth.getTime());
    let age = Math.floor(timeDiff / (1000 * 3600 * 24) / 365);
    let doc = new jsPDF('p', 'pt', 'a4');
    doc.setTextColor(194, 24, 91);
    doc
      .setFont('arial', 'bold')
      .setFontSize(30)
      .text('Appointment Report', 180, 40);
    doc.setFontSize(10).setTextColor(0, 0, 0);
    doc.line(0, 60, 700, 60);
    doc.text(`Appointment Date`, 470, 100);
    doc.text(`${printDate} `, 470, 120);
    doc.text(
      ` Patient ID - ${appointment.patientId} \n Patient Name - ${patient.firstName} ${patient.lastName} \n Age - ${age}`,
      50,
      150
    );
    autoTable(doc, {
      startY: 250,
      theme: 'grid',
      styles: { halign: 'center' },
      headStyles: { fillColor: 'rgb(66,66,66)' },
      head: [['AppointmentId', 'Doctor Name', 'Speciality', 'Slot']],
      body: [
        [
          `${appointment.appointmentId}`,
          `${appointment.doctorName}`,
          `${appointment.organ}`,
          `${appointment.slotStartTime} - ${appointment.slotEndTime} `,
        ],
      ],
    });
    doc.setFont('arial', 'bold').setFontSize(10).setTextColor(0, 0, 0);
    doc.line(0, 600, 900, 600);
    doc.text('Receptionist ', 470, 450);
    doc.save(`${appointment.appointmentId}.pdf`);
  }
}
