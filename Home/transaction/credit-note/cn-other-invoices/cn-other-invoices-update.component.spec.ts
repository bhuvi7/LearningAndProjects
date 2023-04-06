import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CnOtherInvoicesUpdateComponent } from './cn-other-invoices-update.component';

describe('CnOtherInvoicesUpdateComponent', () => {
  let component: CnOtherInvoicesUpdateComponent;
  let fixture: ComponentFixture<CnOtherInvoicesUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CnOtherInvoicesUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CnOtherInvoicesUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
