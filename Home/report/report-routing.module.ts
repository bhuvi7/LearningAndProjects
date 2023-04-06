import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

const routes : Routes =[
    {path:'',
    children:[
        {path:"invoice", loadChildren: () => import('./invoice/invoice.module').then(module => module.InvoiceModule)}
    ]}
]

@NgModule({
    imports:[RouterModule.forChild(routes)],
    exports:[RouterModule]
})
export class ReportRoutingModule {}