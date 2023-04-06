import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subject } from 'rxjs';
import Swal from 'sweetalert2';
import { MasterService } from '../master.service';
import { District } from '../model/district';
import { State } from '../model/state';

@Component({
    selector: "state-detail",
    templateUrl: "state-detail.component.html"
})
export class StateDetailComponent implements OnInit {
    public state: State;
    public states: Array<any>;
    public loading: Boolean = true;


    constructor(public masterService: MasterService, public router: Router, private route: ActivatedRoute) { }

    ngOnInit() {

        this.masterService.getAllState().subscribe(x => {
            this.states = x;
            
        });

        this.state = new State();
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.masterService.getStateById(par['_id'])).subscribe(x => {
                this.state = x;
                this.loading = false;
            });
        }
        this.loading = false;
    }



    save() {
        this.states.forEach((x, i) => {
            if (x.stateCode == this.state.stateCode) {
                Swal.fire('', 'State Code Already Exists', 'error');
            }


            if (this.states.length - 1 == i) {
                if (this.state.id != undefined) {
                    
                    this.masterService.updateState(this.state).subscribe((x) => {
                        Swal.fire('', 'State updated successfully!!!', 'success');
                        this.exit();
                    });
                } else {
                    
                    this.masterService.addState(this.state).subscribe((x) => {
                        Swal.fire('', 'State added successfully!!!', 'success');
                        this.exit();
                    });
                }
            }
        })
    }


    exit() {
        this.router.navigateByUrl("/master/state-list");
    }

}
