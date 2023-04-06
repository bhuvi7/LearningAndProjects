import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CnOtherInvoicesComponent } from './cn-other-invoices.component';

describe('CnOtherInvoicesComponent', () => {
  let component: CnOtherInvoicesComponent;
  let fixture: ComponentFixture<CnOtherInvoicesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CnOtherInvoicesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CnOtherInvoicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
