import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';


@Component({
    selector: 'fin-05a-list-page',
    templateUrl: 'fin-05a-list-page.component.html',
    encapsulation: ViewEncapsulation.None
})
export class Fin05aListPageComponent implements OnInit {
    ngOnInit() { }
    constructor(private router: Router, private route: ActivatedRoute) { }


}
