import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecialApprovalComponent } from './special-approval.component';

describe('SpecialApprovalComponent', () => {
  let component: SpecialApprovalComponent;
  let fixture: ComponentFixture<SpecialApprovalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpecialApprovalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpecialApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
