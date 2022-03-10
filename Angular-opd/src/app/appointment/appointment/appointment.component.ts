import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css'],
})
export class AppointmentComponent implements OnInit {
  newApp = false;
  reschedule = false;
  cancel = false;
  opened=true;

  constructor() {}

  ngOnInit(): void {}

  public onNew() {
    this.newApp = true;
    this.reschedule = false;
    this.cancel = false;
  }

  public onReschedule() {
    this.newApp = false;
    this.reschedule = true;
    this.cancel = false;
  }
  public onCancel() {
    this.newApp = false;
    this.reschedule = false;
    this.cancel = true;
  }
}
