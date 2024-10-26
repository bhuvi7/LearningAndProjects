import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { 
    path: '',
    children: [ 
      {
        path: 'user',
        loadChildren:() => import('./user/user.module').then(module => module.UserModule)
      } ,    
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserManagementRoutingModule { }
