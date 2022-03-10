import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllAppointmentsComponent } from './get-all-appointments.component';

describe('GetAllAppointmentsComponent', () => {
  let component: GetAllAppointmentsComponent;
  let fixture: ComponentFixture<GetAllAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetAllAppointmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAllAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
