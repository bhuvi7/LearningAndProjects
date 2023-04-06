import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subject } from 'rxjs';
import Swal from 'sweetalert2';
import { MasterService } from '../master.service';
import { District } from '../model/district';

@Component({
    selector: "district-detail",
    templateUrl: "district-detail.component.html"
})
export class DistrictDetailComponent implements OnInit {
    public district: District;
    public states: any[];
    public loading: Boolean = true;
    public officePkdAddress: string;
    public officePpdAddress: string;
    public officePkdName: string;
    public officePpdName: string;
    public newDistrict: Boolean = false;

    constructor(public masterService: MasterService, public router: Router, private route: ActivatedRoute) { }

    ngOnInit() {
        this.district = new District();

        if (this.route.params['_value']['_id'] != "undefined") {
            this.newDistrict = false
            this.route.params.switchMap((par: Params) => this.masterService.getDistrictById(par['_id'])).subscribe(x => {
                this.district = x;

                this.officePkdAddress = x.officePkdAddress;
                this.officePpdAddress = x.officePpdAddress
                this.officePkdName = x.officePkdName
                this.officePpdName = x.officePpdName

                
                this.loading = false;
            });
        } else {
            this.newDistrict = true
        }
        this.loading = false;

        this.masterService.getAllState().subscribe((x => {
            this.states = x;
            

        }));

    }



    save() {
        if (this.district.id != undefined) {
            
            this.masterService.updateDistrict(this.district).subscribe((x) => {
                Swal.fire('', 'District updated successfully!!!', 'success');
                this.exit();
            });
        } else {
            
            this.masterService.addDistrict(this.district).subscribe((x) => {
                Swal.fire('', 'District added successfully!!!', 'success');
                this.exit();
            });
        }

    }


    exit() {
        this.router.navigateByUrl("/master/district-list");
    }

}
