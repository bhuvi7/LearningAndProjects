import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbModalConfig, NgbTooltip } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-equipment-billing-history-detail',
  templateUrl: './equipment-billing-history-detail.component.html',
  styleUrls: ['./equipment-billing-history-detail.component.scss'],
  providers: [NgbModalConfig, NgbModal, NgbTooltip]
})
export class EquipmentBillingHistoryDetailComponent implements OnInit {

  public loading: Boolean = false;
  public loadingMonthTable: Boolean = false;
  public selectedDate: any;
  constructor(config: NgbModalConfig, private modalService: NgbModal) { }

  ngOnInit(): void {
   
    let date = new Date();
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    let monthStr;
    let dateStr;

    if (month < 10) { monthStr = "0" + month }
    else { monthStr = month }
    (day < 10 ? dateStr = "0" + day : dateStr = day);
    this.selectedDate = dateStr + "-" + monthStr + "-" + year


  }
  openMonthContent(monthContent) {
    this.modalService.open(monthContent, {
      size: 'lg', centered: false
    })
  }

}
