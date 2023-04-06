import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardProgress } from "../dashboard-progress/dashboard-progress.component";
const routes: Routes = [
  {
    path: '',component:DashboardProgress  
  }
]; 

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardProgressRoutingModule { }
  