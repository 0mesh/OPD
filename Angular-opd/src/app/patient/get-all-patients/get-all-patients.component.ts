import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Patient } from 'src/app/model/patient';
import { PatientMedicalHistoryService } from 'src/app/services/patient-medical-history.service';
import { PatientService } from 'src/app/services/patient.service';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-get-all-patients',
  templateUrl: './get-all-patients.component.html',
  styleUrls: ['./get-all-patients.component.css'],
})
export class GetAllPatientsComponent implements OnInit, AfterViewInit {
  patientList: Patient[];
  dataSource = new MatTableDataSource();
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  displayedColumns = [
    'patientId',
    'firstName',
    'email',
    'phoneNumber',
    'MedicalHistory',
    'details',
    'Update',
    'Delete',
    'NewAppointment',
  ];

  constructor(
    private patientService: PatientService,
    private dialog: MatDialog
  ) {}
  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
  }

  ngOnInit(): void {
    this.patientService.getAllPatients().subscribe({
      next: (data) => {
        this.patientList = data;
        this.dataSource = new MatTableDataSource(this.patientList);
        console.log('no of records accessed is: ' + this.patientList.length);
        console.log(this.patientList);
        this.dataSource = new MatTableDataSource(this.patientList);
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
  updatePatient(patient) {
    console.log(patient);
  }
  deletePatient(patient) {
    console.log(patient);
    this.opendialog(patient);
  }
  doFilter(value) {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }
  opendialog(patient) {
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
        console.log(patient);

        this.patientService.deletePatient(patient.patientId).subscribe({
          next: (data) => {
            console.log('deleted patient with id ' + data.patientId);
            console.log(data);
            this.patientList = this.patientList.filter((ele) => {
              if (ele.patientId !== data.patientId) {
                return true;
              }
            });
            this.dataSource.data = this.patientList;
            this.dataSource.sort = this.sort;
          },
          error: (err) => {
            console.log(err);
          },
          complete: () => {
            console.log('completed');
            this.dataSource = new MatTableDataSource(this.patientList);
            this.dataSource.sort = this.sort;
          },
        });
      }
    });
  }
}
