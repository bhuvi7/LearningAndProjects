import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin11InvoiceService } from '../fin-11-invoice-service';
import { Action } from 'rxjs/internal/scheduler/Action';
import { Fin11data } from '../model/fin11data';
import { Fin11 } from '../model/fin-11';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import * as dateFormat from 'dateformat'



@Component({
  selector: 'fin-11-create',
  templateUrl: './fin-11-create.component.html',
  styleUrls: ['./fin-11-create.component.scss'],
  providers: [NgbModalConfig, NgbModal]

})


export class Fin11CreateComponent implements OnInit {

  public clinic: any;
  public date = ''
  public year = new Date().getFullYear();
  public fin11: Fin11;
  public fin11data: Fin11data;
  public fin11Array;
  public clinicTypeId: number = 0;
  public fin11datas: Array<Fin11data>;
  public modalBodyContent;
  public stateAndClinicFilterDatas: Array<any>;
  public concessionElements: number;
  public reference;
  public typeOfExpenses;
  public loading: Boolean = true;
  public disableCreate: Boolean = false;
  public filterDatas: any;
  public stateFilter: string = "";
  public districtFilter: string = "";
  public clinicTypeFilter: string = "";
  public clinicFilter: string = "";
  public stateFilterDatas: Array<any>;
  public districtFilterDatas: Array<any>;
  public clinicTypeFilterDatas: Array<any>;
  public clinicFilterDatas: Array<any>;
  Fin11data: any;

  constructor(private router: Router, private route: ActivatedRoute, private fin11Service: Fin11InvoiceService,
    config: NgbModalConfig, private modalService: NgbModal) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {
    this.fin11data = new Fin11data();
    this.fin11 = new Fin11();

    this.fin11datas = []
    this.fin11Array = [{}];

    this.fin11Service.fetchStateDetails().subscribe(x => {
      this.stateFilterDatas = x;
      //  
    })
    this.date = dateFormat(new Date(), 'dd-mm-yyyy');
    this.loading = false;
  }

  handleForm(event) {
    switch (event.target.id) {
      case "state":
        this.stateFilter = event.target.value;
        this.districtFilter = "";
        this.districtFilterDatas = this.stateFilterDatas.find(state => state.stateName == event.target.value).districts
        this.fin11.stateId = this.stateFilterDatas.find(state => state.stateName == event.target.value).id
        //  
        break;
      case "district":
        this.districtFilter = event.target.value;
        // this.clinicTypeFilter = "";
        // this.clinicTypeFilterDatas = this.clinic.find(district => district.districtName == event.target.value).clinicTypeId
        this.fin11.districtId = this.districtFilterDatas.find(district => district.districtName == event.target.value).id
        // 

        break;
      case "clinicTypeId":
        this.clinicTypeFilter = event.target.value;
        if (this.clinicTypeFilter === "1") {
          this.clinicTypeId = 1;
        } else if (this.clinicTypeFilter === "2") {
          this.clinicTypeId = 2;
        }

      // 
      //   this.clinicFilter = "";
      // this.clinicFilterDatas = this.clinicFilterDatas.find(clinicType => clinicType.clinicTypeName == event.target.value).clinics
      // this.filterFunction();
      // case "clinic":
      //   this.clinicFilter = event.target.value;
      //   this.filterFunction();
      //   break;
      default:
        break;
    }
  }

  // filterFunction() {
  //   this.filterDatas = []
  //   if (this.stateFilter !== "" && this.districtFilter == "" && this.clinicTypeFilter == "" && this.clinicFilter == "") {
  //     this.clinic.forEach(element => {
  //       if (element.stateName == this.stateFilter) {
  //         this.filterDatas.push(element)
  //       }
  //     });
  //   } else if (this.stateFilter !== "" && this.districtFilter !== "" && this.clinicTypeFilter == "" && this.clinicFilter == "") {
  //     this.clinic.forEach(element => {
  //       if (element.stateName == this.stateFilter && element.districtName == this.districtFilter) {
  //         this.filterDatas.push(element)
  //       }
  //     })
  // } else if (this.stateFilter !== "" && this.districtFilter !== "" && this.clinicTypeFilter !== "" && this.clinicFilter == "") {
  //     this.clinic.forEach(element => {
  //       if (element.stateName == this.stateFilter && element.districtName == this.districtFilter &&
  //         element.clinicTypeFilter == this.clinicTypeFilter) {
  //         this.filterDatas.push(element)
  //       }
  //     })
  // } else if (this.stateFilter !== "" && this.districtFilter !== "" && this.clinicTypeFilter !== "" && this.clinicFilter !== "") {
  //   this.clinic.forEach(element => {
  //     if (element.stateName == this.stateFilter && element.districtName == this.districtFilter &&
  //       element.clinicTypeFilter == this.clinicTypeFilter && element.clinicFilter == this.clinicFilter) {
  //       this.filterDatas.push(element)
  //     }
  //   })
  //     }
  // }

  openModal(content, modalContent) {
    this.modalBodyContent = modalContent
    this.modalService.open(content);
  }

  closeModal() {
    if (this.modalBodyContent == 'Create') { this.createFin11(); }
  }



  addfin11data() {
    this.fin11data.typeOfExpenses = this.typeOfExpenses;
    this.fin11data.reference = this.reference;
    this.fin11data.concessionElements = this.concessionElements;
    this.fin11datas.push(this.fin11data);
    // this.fin11data = new this.Fin11data();
  }

  removeFin11data(index) {
    this.fin11datas.splice(index, 1)
  }

  createFin11() {
    // this.fin11.stateName = this.stateFilter;
    // this.fin11.DistrictName = this.districtFilter;
    // this.fin11.clinicType = this.clinicTypeFilter;
    // this.fin11.clinic = this.clinicFilter;
    this.disableCreate = true
    this.fin11.clinicTypeId = this.clinicTypeId;
    this.fin11.typeOfExpenses = this.typeOfExpenses;
    this.fin11.reference = this.reference;
    this.fin11.concessionElements = this.concessionElements;
    this.fin11Array[0] = this.fin11;
    //
    this.fin11Service.createFin11(this.fin11Array).subscribe(x => {
      this.disableCreate = false
      Swal.fire('', 'FIN 11 created and submitted successfully!!!', 'success');
      history.back()
    })
  }

  navToList() {
    history.back();
  }
}
