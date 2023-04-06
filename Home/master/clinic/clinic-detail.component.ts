import { Component, OnInit } from '@angular/core';
import { MasterService } from "../master.service";
import 'rxjs/add/operator/switchMap';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { InvoiceType } from '../model/invoice-type';
import { Clinic } from '../model/clinic';


@Component({
    selector: 'clinic-detail',
    templateUrl: './clinic-detail.component.html'
})
export class ClinicDetailComponent implements OnInit {
    public clinic: Clinic;
    // public states:any[];
    public districts: any[];
    public loading: Boolean = true;
    public stateFilter: string = "";
    public districtFilter: string = "";
    public stateFilterDatas: Array<any>;
    public districtFilterDatas: Array<any>;
    public clinicTypeId: any;
    public clinics: Array<any>;
    constructor(public masterService: MasterService, public router: Router, private route: ActivatedRoute) { }

    ngOnInit() {
        this.clinic = new Clinic();
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.masterService.getClinicById(par['_id'])).subscribe(x => {
                this.clinic = x;
                
                this.stateFilter = x.stateName
                this.districtFilter = x.districtName
                // this.clinicTypeId=x.sta
                this.loading = false;
            });
        }
        this.loading = false;

        this.masterService.getAllState().subscribe((x => {
            this.stateFilterDatas = x;

        }));

        this.masterService.getAllClinic().subscribe(x => {
            this.clinics = x;
            
        });
        // this.masterService.getAllDistrict().subscribe((x => {
        //     this.districts = x;

        // }));

    }


    handleForm(event) {
        switch (event.target.id) {
            case "state":
                this.stateFilter = event.target.value;
                
                this.districtFilter = "";
                this.districtFilterDatas = this.stateFilterDatas.find(state => state.id == event.target.value).districts
                // this.filterFunction();
                break;
            case "district":
                this.districtFilter = event.target.value;
                

                // this.filterFunction();
                break;
            default:
                break;
        }
    }






    save() {
        this.clinics.forEach((x, i) => {
            if (x.clinicCode == this.clinic.clinicCode) {
                Swal.fire('', 'Clinic Code Already Exists', 'error');
            }


            if (this.clinics.length - 1 == i) {
                if (this.clinic.id != undefined) {

                    
                    this.masterService.updateClinic(this.clinic).subscribe((x) => {
                        Swal.fire('', 'Clinic updated successfully!!!', 'success');
                        this.exit();

                    });
                } else {
                    
                    this.masterService.addClinic(this.clinic).subscribe((x) => {
                        Swal.fire('', 'Clinic added successfully!!!', 'success');
                        this.exit();
                    });
                }
            }
        })

    }


    exit() {
        this.router.navigateByUrl("/master/clinic-list");
    }

}
