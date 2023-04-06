import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin05aInvoiceService } from '../fin-05a-invoice-service';
import { Action } from 'rxjs/internal/scheduler/Action';
import { Fin05adata } from '../model/fin05adata';
import { Fin05a } from '../model/fin-05a';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import * as dateFormat from 'dateformat'



@Component({
  selector: 'fin-05a-create',
  templateUrl: './fin-05a-create.component.html',
  styleUrls: ['./fin-05a-create.component.scss'],
  providers: [NgbModalConfig, NgbModal]

})


export class Fin05aCreateComponent implements OnInit {

  public clinic: any;
  public date = ''
  public year = new Date().getFullYear();
  public fin05a: Fin05a;
  public fin05adata: Fin05adata;
  public assetName: string;
  public clinicTypeId: number = 0;
  public stateId: number;
  public districtId: number;
  public fin05aArray: Array<object>;
  public beNumber: string;
  public fin05adatas: Array<Fin05adata>;
  public modalBodyContent;
  public stateAndClinicFilterDatas: Array<any>;
  public siteConformityExpenses: number;
  public reference: string;
  public typeOfExpenses: string;
  public loading: Boolean = true;
  public disableCreate: Boolean = false;
  public filterDatas: any;
  public stateFilter: string = "";
  public districtFilter: string = "";
  public clinicTypeFilter;
  public clinicFilter: string = "";
  public stateFilterDatas: Array<any>;
  public districtFilterDatas: Array<any>;
  public clinicTypeFilterDatas: Array<any>;
  public clinicFilterDatas: Array<any>;
  Fin05adata: any;

  constructor(private router: Router, private route: ActivatedRoute, private fin05aService: Fin05aInvoiceService,
    config: NgbModalConfig, private modalService: NgbModal) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {
    this.fin05adata = new Fin05adata();
    this.fin05a = new Fin05a();

    this.fin05adatas = [];
    this.fin05aArray = [{}];

    this.fin05aService.fetchStateDetails().subscribe(x => {
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
        this.stateId = this.stateFilterDatas.find(state => state.stateName == event.target.value).id;
        // 
        break;
      case "district":
        this.districtFilter = event.target.value;
        this.districtId = this.districtFilterDatas.find(district => district.districtName == event.target.value).id
        // 
        // this.clinicTypeFilter = "";
        // this.clinicTypeFilterDatas = this.stateFilterDatas.find(district => district.districtName == event.target.value).clinicTypes
        // this.filterFunction();
        break;
      case "clinicTypeId":
        this.clinicTypeFilter = event.target.value;
        if (this.clinicTypeFilter === "1") {
          this.clinicTypeId = 1;
        } else if (this.clinicTypeFilter === "2") {
          this.clinicTypeId = 2;
        }

      //  
      //   this.clinicTypeFilter=event.target.value;
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
  //       // if (element.stateName == this.stateFilter) {
  //       //   this.filterDatas.push(element)
  //       // }
  //     });
  //   } else if (this.stateFilter !== "" && this.districtFilter !== "" && this.clinicTypeFilter == "" && this.clinicFilter == "") {
  //     this.clinic.forEach(element => {
  //       // if (element.stateName == this.stateFilter && element.districtName == this.districtFilter) {
  //       //   this.filterDatas.push(element)
  //       // }            
  //     })
  //   } else if (this.stateFilter !== "" && this.districtFilter !== "" && this.clinicTypeFilter !== "" && this.clinicFilter == "") {
  //       this.clinic.forEach(element => {
  //         // if (element.stateName == this.stateFilter && element.districtName == this.districtFilter && 
  //         //   element.clinicTypeFilter == this.clinicTypeFilter) {
  //         //   this.filterDatas.push(element)
  //         // }            
  //       })
  //     // } else if (this.stateFilter !== "" && this.districtFilter !== "" && this.clinicTypeFilter !== "" && this.clinicFilter !== "") {
  //     //   this.clinic.forEach(element => {
  //     //     if (element.stateName == this.stateFilter && element.districtName == this.districtFilter && 
  //     //       element.clinicTypeFilter == this.clinicTypeFilter && element.clinicFilter == this.clinicFilter) {
  //     //       this.filterDatas.push(element)
  //     //     }            
  //     //   })
  //     }
  // }

  openModal(content, modalContent) {
    this.modalBodyContent = modalContent
    this.modalService.open(content);
  }

  closeModal() {
    if (this.modalBodyContent == 'Create') { this.createFin05a(); }
  }



  addfin05adata() {
    this.fin05adata.typeOfExpenses = this.typeOfExpenses;
    this.fin05adata.reference = this.reference;
    this.fin05adata.siteConformityExpenses = this.siteConformityExpenses;
    this.fin05adatas.push(this.fin05adata);
    // this.fin05adata = new this.Fin05adata();
  }

  removeFin05adata(index) {
    this.fin05adatas.splice(index, 1)
  }

  createFin05a() {
    // this.fin05a.stateName = this.stateFilter;
    // this.fin05a.DistrictName = this.districtFilter;
    // this.fin05a.clinicType = this.clinicTypeFilter;
    // this.fin05a.clinic = this.clinicFilter; 
    this.fin05a.stateId = this.stateId;
    this.fin05a.districtId = this.districtId;

    this.fin05a.clinicTypeId = this.clinicTypeId;
    this.fin05a.typeOfExpenses = this.typeOfExpenses;
    this.fin05a.reference = this.reference;
    this.fin05a.siteConformityExpenses = this.siteConformityExpenses;
    //
    // this.fin05aArray.push({ "01": this.fin05a.stateId, "02": this.fin05a.districtId, "03": this.fin05a.clinicTypeId, 
    // "04": this.fin05a.typeOfExpenses,"05": this.fin05a.reference, "06": this.fin05a.siteConformityExpenses });
    // this.fin05aArray.push(this.fin05a);
    // delete this.fin05aArray[0];
    this.fin05aArray[0] = this.fin05a;
    this.disableCreate = true

    // this.fin05aArray.map.prototype.forEach(()=>{[this.fin05a]})
    // this.fin05a.forEach(element => {
    //             this.fin05aArray.push(element);

    //             
    // });


    //
    this.fin05aService.createFin05a(this.fin05aArray).subscribe(x => {
      this.disableCreate = false
      Swal.fire('', 'FIN 05A created and submitted successfully!!!', 'success');
      history.back()
    })
  }

  navToList() {
    history.back();
  }
}
