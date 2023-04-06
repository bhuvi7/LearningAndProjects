import { Component,OnInit } from "@angular/core";
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subject } from 'rxjs';
import Swal from 'sweetalert2';
import { MasterService } from '../master.service';
import { Circle } from '../model/circle';
import { District } from '../model/district';

@Component({
    selector:"circle-detail",
    templateUrl:"circle-detail.component.html"
})
export class CircleDetailComponent implements OnInit{
    public circle: Circle;
    public states:any[];
    public loading: Boolean = true;
    constructor(public masterService: MasterService, public router: Router, private route: ActivatedRoute) { }

    ngOnInit() {
        this.circle = new Circle();
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.masterService.getCircleById(par['_id'])).subscribe(x => {
                this.circle = x;
                
                this.loading = false;
            });
        }
        this.loading = false;

        this.masterService.getAllState().subscribe((x => {
            this.states = x;
             
        }));

    }



    save() {
        if (this.circle.id != undefined) {
            this.masterService.updateCircle(this.circle).subscribe((x) => {
                Swal.fire('', 'Circle updated successfully!!!', 'success');
                this.exit();
            });
        } else {
            this.masterService.addCircle(this.circle).subscribe((x) => {
                Swal.fire('', 'Circle added successfully!!!', 'success');
                this.exit();
            });
        }

    }


    exit() {
        this.router.navigateByUrl("/master/circle-list");
    }

}
