import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';


@Component({
    selector: 'fin-11-list-page',
    templateUrl: 'fin-11-list-page.component.html',
    encapsulation: ViewEncapsulation.None
})
export class Fin11ListPageComponent implements OnInit {
    ngOnInit() { }
    constructor(private router: Router, private route: ActivatedRoute) { }


}
