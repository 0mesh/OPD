import { TestBed } from '@angular/core/testing';

import { PatientMedicalHistoryService } from './patient-medical-history.service';

describe('PatientMedicalHistoryService', () => {
  let service: PatientMedicalHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientMedicalHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
