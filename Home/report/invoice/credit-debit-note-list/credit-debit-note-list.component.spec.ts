import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditDebitNoteListComponent } from './credit-debit-note-list.component';

describe('CreditDebitNoteListComponent', () => {
  let component: CreditDebitNoteListComponent;
  let fixture: ComponentFixture<CreditDebitNoteListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreditDebitNoteListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreditDebitNoteListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
