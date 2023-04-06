import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'fin-01-invoice',
        loadChildren: () => import('./fin-01-invoice/fin-01-invoice.module').then(module => module.Fin01InvoiceModule)
      },
      {
        path: 'fin-02-invoice',
        loadChildren: () => import('./fin-02-invoice/fin-02-invoice.module').then(module => module.Fin02InvoiceModule)
      },
      {
        path: 'fin-02a-invoice',
        loadChildren: () => import('./fin-02a-invoice/fin-02a-invoice.module').then(module => module.Fin02aInvoiceModule)
      },
      {
        path: 'fin-02b-invoice',
        loadChildren: () => import('./fin-02b-invoice/fin-02b-invoice.module').then(module => module.Fin02bInvoiceModule)
      },
      {
        path: 'fin-04-invoice',
        loadChildren: () => import('./fin-04-invoice/fin-04-invoice.module').then(module => module.Fin04InvoiceModule)
      },
      {
        path: 'fin-05a-invoice',
        loadChildren: () => import('./fin-05a-invoice/fin-05a-invoice.module').then(module => module.Fin05aInvoiceModule)
      },
      {
        path: 'fin-09-invoice',
        loadChildren: () => import('./fin-09-invoice/fin-09-invoice.module').then(module => module.Fin09InvoiceModule)
      },
      {
        path: 'fin-11-invoice',
        loadChildren: () => import('./fin-11-invoice/fin-11-invoice.module').then(module => module.Fin11InvoiceModule)
      },
      {
        path: 'credit-note',
        loadChildren: () => import('./credit-note/credit-note.module').then(module => module.CreditNoteModule)
      },
      {
        path: 'debit-note',
        loadChildren: () => import('./debit-note/debit-note.module').then(module => module.DebitNoteModule)
      },
      {
        path: 'pai',
        loadChildren: () => import('./payment-against-invoice/payment-against-invoice.module').then(module => module.PaymentAgainstInvoiceModule)
      },
      {
        path: 'sa',
        loadChildren: () => import('./sundry-advances/sundry-advances.module').then(module => module.SAModule)
      },
      {
        path: 'pami',
        loadChildren: () => import('./payment-against-multiple-invoice/pami.modulte').then(module => module.PAMIModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransactionRoutingModule { }
