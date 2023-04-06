import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate } from '@angular/router';
import { AdminComponent } from '../../theme/layout/admin/admin.component';
import { AuthGuardService } from "../../theme/layout/auth/auth-guard.service";

const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    canActivate: [AuthGuardService],
    children: [
      {
        path: 'dashboard',
        loadChildren: () => import('./dashboard/dashboard.module').then(module => module.DashboardModule)
      },
      {
        path: 'incremental-approval',
        loadChildren: () => import('./incremental-approval/incremental.module').then(module => module.IncrementalModule)
      },
      {
        path: 'master',
        loadChildren: () => import('./master/master.module').then(module => module.MasterModule)
      },
      {
        path: 'transaction',
        loadChildren: () => import('./transaction/transaction.module').then(module => module.TransactionModule)
      },
      {
        path: 'dashboard-progress',
        loadChildren: () => import('./dashboard-progress/dashboard-progress.module').then(module => module.DashboardProgressModule)
      },
      {
        path: 'user-management',
        loadChildren: () => import('./user-management/user-management.module').then(module => module.UserManagementModule)
      },
      {
        path: 'report',
        loadChildren: () => import('./report/report.module').then(module => module.ReportModule)
      },
      {
        path: 'special-approval',
        loadChildren: () => import('./special-approval/specialapproval-routing.module').then(module => module.SpecialRoutingModule)
      },

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
