import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { UserIdleService } from 'angular-user-idle';
@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(private userIdle: UserIdleService,private _router: Router) {  }

  ngOnInit(){

   this.userIdle.resetTimer();
   this.userIdle.startWatching();//will logout after 10 mins of inactivity
   this.userIdle.onTimerStart().subscribe(count => {this._router.navigate(['/auth/login']);
   localStorage.removeItem('currentUser');});
   this.userIdle.onTimeout().subscribe(count => {this.userIdle.stopWatching();this.userIdle.stopTimer()});
  
  }


}
