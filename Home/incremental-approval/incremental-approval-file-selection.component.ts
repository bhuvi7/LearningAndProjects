import { Component, OnInit,OnDestroy,ViewChild } from '@angular/core';
import { Router } from "@angular/router";
import { DataTableDirective } from 'angular-datatables';
import { Observable } from 'rxjs/Rx';
import { Subject } from 'rxjs';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { IncrementalService } from "./incremental.service";
import { browser } from 'protractor';
import { saveAs } from 'file-saver';
import { HttpClient } from '@angular/common/http';
import * as FileSaver from 'file-saver';


@Component({
  selector: 'incremental-approval-file-selection',
  templateUrl: './incremental-approval-file-selection.component.html'
})
export class IncrementalApprovalFileSelectionComponent implements OnInit {
  dtOptions: DataTables.Settings = {
    pagingType: 'full_numbers',
    pageLength: 10
  };
  dtTrigger: Subject<any> = new Subject();
  @ViewChild(DataTableDirective, {static: false})
  dtElement: DataTableDirective;
  public loading :boolean=false;
    approvalReason: any;
    rejectionReason: any;
    flag: string;
  constructor( private http: HttpClient, private router: Router, private incrementalService: IncrementalService) { }

  public invoiceDatas: any;
  public buttonEnable: any;
  public createData: any;
  public invoiceType: string;
  public cardTitle: string;
  public districtId;
  public clinicTypeId;
  public filterDatas: any;
  public stateFilter: string = "";
  public districtFilter: string = "";
  public stateFilterDatas: Array<any>;
  public districtFilterDatas: Array<any>;
  limit=5;

  ngOnInit() {
      this.filterDatas = [];
      this.buttonEnable = [];
    //   this.fin01Service.fetchDataForFin02aCreateList().subscribe(x => {
        //   this.invoiceDatas = x;
        //   this.invoiceDatas.forEach(element => {
        //       this.filterDatas.push(element);
        //   });
        this.filterDatas = [{id:'1',month:"7",year:"2020",
        stateName:"JOHOR", districtName:"BATU",fileName:"FILE-1",invoiceType:"FIN02", 
        fileLink:"https://file-examples-com.github.io/uploads/2017/02/file_example_XLS_50.xls",  
        fileLink3:"https://www.contextures.com/SampleData.zip",
        fileLink2:"https://www.esa.int/var/esa/storage/images/esa_multimedia/images/2017/07/vesuvius_on_fire/17064023-1-eng-GB/Vesuvius_on_fire_focuson.gif"
         },
        { id:'2', month:"8",year:"2020",
        stateName:"PULAU", districtName:"SPS",fileName:"FILE-2",invoiceType:"FIN02", 
        fileLink:"https://file-examples-com.github.io/uploads/2017/02/file_example_XLS_100.xls" }]
    //   })
      this.incrementalService.fetchStateDetails().subscribe(x => {
          this.stateFilterDatas = x;   
          this.loading = false; 
          this.dtTrigger.next();
      })
      for(let i=0; i<this.filterDatas.length; i++ ){
      this.buttonEnable[i] = true;}
    //   document.getElementById("data.comment").onchange = this.enableButton();
  }
//   document.getElementById("data.comment").onchange = function() {myFunction()};
//  function myFunction()
//   {
download(fileLink,fileName)
{
  // browser.downloads.download("fileLink")
  
  
 FileSaver.saveAs(fileLink,fileName + '_export_incremental_data_' + new  Date().getTime() );
}
enableButton(comment,i)
{
//    len = this.comment.lenght;


// let len = comment.lenght;
// let lim = 5;
//    if( len  >= lim)
//    {  
//            
//    }
    if( comment){
       
       this.buttonEnable[i] = false;
   }
}
//   }
//   enableButton(comment)
//   { 
//       if( comment.lenght > 3 )
//       {
//           this.buttonEnable = true;
//       }
//   }
  toApprove(comment)
  {
      
      Swal.fire("","File Approved Successfully!!!","success")
      this.approvalReason = comment
      this.flag = "approved";
      this.incrementalService.putCommentAndFlag(this.approvalReason,this.flag);
  }
  toReject(comment)
  {
    
    Swal.fire("","File Rejected Sucessfully!!!","warning")
    this.rejectionReason = comment;
    this.flag = "rejected"
    this.incrementalService.putCommentAndFlag(this.rejectionReason,this.flag);
  }
}