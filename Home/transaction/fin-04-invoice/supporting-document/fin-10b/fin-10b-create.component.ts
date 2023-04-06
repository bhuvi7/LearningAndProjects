import { Component, OnInit, ViewChild, EventEmitter } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin04InvoiceService } from '../../fin-04-invoice-service';
import { Action } from 'rxjs/internal/scheduler/Action';
import { ConstructionWork } from '../../model/constructionWork';
import { Fin10b } from '../../model/fin-10b';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { State } from 'src/app/pages/home/master/model/state';
import { District } from 'src/app/pages/home/master/model/district';
import { assetType, IOption, supplierName, tenderNos } from '../../model/IOption';
import * as dateFormat from 'dateformat'
// import * as EventEmitter from 'events';


@Component({
  selector: 'fin-10b-create',
  templateUrl: './fin-10b-create.component.html',
  styleUrls: ['./fin-10b-create.component.scss'],
  providers: [NgbModalConfig, NgbModal]

})


export class Fin10bCreateComponent implements OnInit {

  public clinic: any = {
    stateId: '0', districtId: '0', clinicTypeId: '0', clinicCode: '', clinicName: '', clinicId: '0'
  };
  public date = ''
  public year = new Date().getFullYear();
  public month = new Date().getMonth();
  public invoiceDateFrom: string = "";

  public fin10b: Fin10b;
  public constructionWork: ConstructionWork;
  public assetName: string;
  public beNumber: string;
  public constructionWorks: Array<ConstructionWork>;
  public modalBodyContent;
  public states: Array<State> = [];
  public districts: Array<District> = [];
  public clinicTypes: Array<any> = [];
  public clinics: Array<any> = [];
  public sOption: IOption;
  public simpleOption: Array<IOption>;
  public ngOption: Array<IOption>
  public beNumbers: Array<any>;
  public clinicOption: Array<any>;
  public loading: Boolean = true;
  public disableCreate: Boolean = false;
  public isDisabled: Boolean = false;
  public loadings: Boolean = false;
  public curDate: any
  public selectedDate: string
  public ngBoolean: Boolean = true;

  // @ViewChild('clinicIdTemp') clinicIdTemp = [] ;


  selected = "";
  assetTypes: assetType[] = [
    {
      id: 1,
      name: 'Single Loader Computed Radiography  Systems and Dry Laser Imager'
    },
    {
      id: 2,
      name: 'Radiographic/Fluoroscopic Systems, General-Purpose'
    },
    {
      id: 3,
      name: 'Analyzers, Laboratory, Clinical Chemistry, Automated (High)'
    },
    {
      id: 4,
      name: 'Analyzers, Laboratory, Clinical Chemistry, Automated (Medium)'
    },
    {
      id: 5,
      name: 'Hoods, Isolation, Laminar Air Flow'
    },
    {
      id: 6,
      name: 'Chairs, Examination/ Treatment, Dentistry'
    },
    {
      id: 7,
      name: 'Chairs, Examination/ Treatment, Dentistry, Specialist'
    },
    {
      id: 8,
      name: 'Dental Workstation'
    },
    {
      id: 9,
      name: 'Fume Extractor'
    },
  ];

  selectedlist = "";

  supplierNames: supplierName[] = [
    {
      id: 1,
      name: 'Fujifilm (Malaysia) Sdn. Bhd'
    },
    {
      id: 2,
      name: 'Shimadzu Malaysia Sdn. Bhd'
    },
    {
      id: 3,
      name: 'Utas Maju Sdn. Bhd'
    },
    {
      id: 4,
      name: 'Metro Sihat Sdn. Bhd'
    },
    {
      id: 5,
      name: 'Medi Diastika Sdn. Bhd'
    },
    {
      id: 6,
      name: 'Skydental Malaysia Sdn. Bhd'
    },
    {
      id: 7,
      name: 'Advance  Altimas Sdn. Bhd'
    },
    {
      id: 8,
      name: 'Skydental Malaysia Sdn. Bhd'
    },
    {
      id: 9,
      name: 'Medigate Sdn. Bhd'
    },
  ];



  selectedlis = "";
  tenderNo: tenderNos[] = [
    {
      id: 1,
      name: '10(01) - Single Loader Computed Radiography  Systems and Dry Laser Imager'
    },
    {
      id: 2,
      name: '10(02) - Radiographic/Fluoroscopic Systems, General-Purpose'
    },
    {
      id: 3,
      name: '10(03) - Analyzers, Laboratory, Clinical Chemistry, Automated (High)'
    },
    {
      id: 4,
      name: '10(04) - Analyzers, Laboratory, Clinical Chemistry, Automated (Medium)'
    },
    {
      id: 5,
      name: '10(05) - Hoods, Isolation, Laminar Air Flow'
    },
    {
      id: 6,
      name: '10(06) - Chairs, Examination/ Treatment, Dentistry'
    },
    {
      id: 7,
      name: '10(06)A - Chairs, Examination/Treatment, Dentistry'
    },
    {
      id: 8,
      name: '10(07) - Chairs, Examination/ Treatment, Dentistry, Specialist'
    },
    {
      id: 9,
      name: '10(08) - Dental Workstation'
    },
    {
      id: 10,
      name: '10(09) - Fume Extractor'
    },
  ];


  constructor(private router: Router, private route: ActivatedRoute, private fin04Service: Fin04InvoiceService,
    config: NgbModalConfig, private modalService: NgbModal) {
    config.backdrop = 'static';
    config.keyboard = false;


  }



  ngOnInit() {

    this.constructionWork = new ConstructionWork();
    this.fin10b = new Fin10b();
    this.assetName = ""
    this.beNumber = ""
    this.constructionWorks = []
    this.clinicOption = [
      { value: '0', label: 'Select' }
    ]
    this.simpleOption = this.fin04Service.getCharacters(this.clinicOption);
    this.sOption = new IOption();
    // if (this.route.params['_value']['_clinicId'] != "undefined") {
    //     this.route.params.switchMap((par: Params) => this.fin04Service.fetchForFin10bCreate(par['_clinicId'])).subscribe(x => {
    //         this.clinic = x;
    //         this.fin10b.stateId = x.stateId;
    //         this.fin10b.districtId = x.districtId;
    //         this.fin10b.clinicId = x.id;
    //         this.fin10b.clinicTypeId = x.clinicTypeId;
    //         this.fin10b.clinicCode = x.clinicCode;
    //         this.loading = false;
    //     })
    // }

    this.fin04Service.fetchStateDetails().subscribe(x => {
      this.states = x;
      this.fin04Service.fetchClinicTypeDetails().subscribe(y => {
        this.clinicTypes = y;
      })
      this.date = dateFormat(new Date(), 'dd-mm-yyyy');
      this.loading = false;
    })

  }

  // current Date fetcher
  curDatePicker() {

    let date_ob = new Date();
    date_ob.setDate(0);
    let date = ("0" + date_ob.getDate()).slice(-2)
    let month = ("0" + [(date_ob.getMonth() + 1)]).slice(-2);
    let year = date_ob.getFullYear();

    this.curDate = year + "-" + month + "-" + date
  }

  //
  datePicker() {
    if (this.selectedDate != '') {
      //this.filterFunction();
    }
    else {
      let date = new Date();
      let day = date.getDate();
      let month = date.getMonth();

      let year = date.getFullYear();

      let monthStr;
      let dateStr;

      if (month < 10) {
        monthStr = "0" + month
     

      }
      else {
        monthStr = month
        }
      (day < 10 ? dateStr = "0" + day : dateStr = day);
      
      this.selectedDate = year + "-" + monthStr + "-" + dateStr;


    }
  }

  handleForm(event) {

    this.clinic.clinicCode = ""
    this.clinic.clinicName = ""
    switch (event.target.id) {
      case "state":
        this.constructionWorks = []
        this.assetName = ''
        this.beNumber = ''
        this.clinic.stateId = event.target.value;
        this.clinic.districtId = '0';
        this.clinic.clinicTypeId = '0';
        this.clinic.clinicId = '0';
        this.clinicOption = [
          { value: '0', label: 'Select' }
        ]
        this.simpleOption = this.fin04Service.getCharacters(this.clinicOption);
        this.districts = this.states.find(state => state.id == event.target.value).districts
        break;
      case "district":
        this.constructionWorks = []
        this.assetName = ''
        this.beNumber = ''
        this.clinic.districtId = event.target.value;
        this.clinic.clinicTypeId = '0';
        this.clinic.clinicId = '0';
        this.clinicOption = [
          { value: '0', label: 'Select' }
        ]
        this.simpleOption = this.fin04Service.getCharacters(this.clinicOption);
        break;
      case "clinicType":
        this.constructionWorks = []
        this.assetName = ''
        this.beNumber = ''
        this.clinic.clinicTypeId = event.target.value;
        this.clinic.clinicId = '0';
        this.clinicOption = [
          { value: '0', label: 'Select' }
        ]
        this.simpleOption = this.fin04Service.getCharacters(this.clinicOption);
        this.fetchClinics(this.clinic.districtId, this.clinic.clinicTypeId);

        break;
      case "":
        this.clinicBenumber();
        // this.ngBoolean=false;
        // this.constructionWorks = []
        // this.assetName = ''
        // this.beNumber = ''
        // this.clinic.clinicId=event.target.value;
        // this.clinic.clinicTypeId = '0';
        // this.clinic.stateId = '0';
        // this.clinic.districtId = '0';
        // this.clinicOption = [
        //   { value: '0', label: 'Select' }
        // ]

        break;

      default:
        break;
    }


  }
  clinicBenumber() {
    this.fin04Service.fetchDataForFin08bCreate(this.clinic.clinicId, this.month, this.year).subscribe(x => {

      this.beNumbers = []
      x.forEach(z => {
        this.sOption.value = z.beNumber;
        this.sOption.label = z.beNumber
        this.beNumbers.push(this.sOption)
        this.sOption = new IOption();

      })
      this.ngOption = this.fin04Service.getCharacters(this.beNumbers)

    })
  }


  fetchClinics(districtId, clinicTypeId) {
    this.fin04Service.fetchClinicsDetails(districtId, clinicTypeId).subscribe(x => {
      this.clinics = x;
      this.clinics.forEach(clinic => {
        this.sOption.value = clinic.id.toString();
        this.sOption.label = clinic.clinicName;
        this.clinicOption.push(this.sOption);

        this.sOption = new IOption();

      })
      this.simpleOption = this.fin04Service.getCharacters(this.clinicOption);
    })
  }

  openModal(content, modalContent) {
    this.modalBodyContent = modalContent
    this.modalService.open(content);
  }

  closeModal() {
    if (this.modalBodyContent == 'Create') { this.createFin10b(); }
  }

  addConstructionWork() {
    this.ngBoolean = false;
    this.constructionWork.assetName = this.assetName;
    this.constructionWork.beNumber = this.beNumber;
    this.constructionWork.clinicId = this.clinic.clinicId;
    this.constructionWorks.push(this.constructionWork);
    this.constructionWork = new ConstructionWork();
  }

  removeConstructionWork(index) {
    this.constructionWorks.splice(index, 1)
  }

  createFin10b() {
    this.fin04Service.fetchDataForFin10b().subscribe(x => {

    })
    this.disableCreate = true
    this.fin10b.stateId = this.clinic.stateId;
    this.fin10b.districtId = this.clinic.districtId;
    this.fin10b.clinicId = this.clinic.clinicId;
    this.fin10b.clinicTypeId = this.clinic.clinicTypeId;
    this.fin10b.assetName = this.assetName;
    this.fin10b.beNumber = this.beNumber;
    this.fin10b.fin10bConstructionWorks = this.constructionWorks
    this.fin10b.date = this.selectedDate
    this.fin04Service.createFin10b(this.fin10b).subscribe(x => {

      this.disableCreate = false
      Swal.fire('', 'Fin 10b created and submitted successfully!!!', 'success');
      this.router.navigate(['/transaction/fin-04-invoice/fin-10b-list/inprogress'])


    })


  }

  navToList() {
    this.router.navigate(['/transaction/fin-04-invoice/fin-04-list-page'])
  }
}
