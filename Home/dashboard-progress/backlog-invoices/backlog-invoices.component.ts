import { Component, OnInit } from '@angular/core';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import { Label, Color } from 'ng2-charts';
import { InvoiceCounter } from '../../../home/transaction/fin-01-invoice/model/invoice-counter';
import { InvoiceCounterRegion } from "../../shared/model/invoice-counter-region";
import { AppSharedService } from '../../shared/app-shared.service';
import { DashboardProgressService } from "../dashboard-progress.service";
import { InvoiceGeneration } from "../model/invoice-generation";
import * as dateFormat from 'dateformat';
import 'chartjs-plugin-datalabels';
import Swal from "sweetalert2";
@Component({
  selector: 'backlog-invoices',
  templateUrl: './backlog-invoices.component.html'
})
export class BacklogInvoicesComponent implements OnInit {

  constructor(private appSharedService: AppSharedService, public dashboardProgressService: DashboardProgressService) { }

  public selectedStateId: string = "0";
  public selectedDistrictId: string = "0";
  public stateFilterDatas: Array<any> = [];
  public districtFilterDatas: Array<any>;

  public invoiceGenerationFromDb: Array<InvoiceGeneration>;
  public invoiceGenerationDisplay: Array<InvoiceGeneration>;
  public fin01InvoiceCounter: InvoiceCounter = new InvoiceCounter();
  public fin02aInvoiceCounter: InvoiceCounter = new InvoiceCounter();
  public fin02InvoiceCounter: InvoiceCounter = new InvoiceCounter();
  public fin02bInvoiceCounter: InvoiceCounter = new InvoiceCounter();
  public invoiceCounterRegion: InvoiceCounterRegion;
  public chartDatas: Array<number>;
  public chartBackgroundColor: Array<string>;
  public chartLabels: any = [];
  public pieChartData: ChartDataSets[];
  public pieChartType = 'pie';
  public pieChartOptions: ChartOptions;
  public pieChartPlugin: any;
  private stateBgColorArray: Array<string>;
  public showChart: boolean = false;
  public currentDocName: string = "";
  public heading: string;
  public fin06barChartDatas: ChartDataSets[] = new Array<ChartDataSets>();
  public fin01barChartDatas: ChartDataSets[] = new Array<ChartDataSets>();
  public FIN01InvbarChartDatas: ChartDataSets[] = new Array<ChartDataSets>();
  public fin07barChartDatas: ChartDataSets[] = new Array<ChartDataSets>();
  public fin03abarChartDatas: ChartDataSets[] = new Array<ChartDataSets>();
  public FIN02aInvbarChartDatas: ChartDataSets[] = new Array<ChartDataSets>();
  public fin08bbarChartDatas: ChartDataSets[] = new Array<ChartDataSets>();
  public fin08cbarChartDatas: ChartDataSets[] = new Array<ChartDataSets>();
  public fin08barChartDatas: ChartDataSets[] = new Array<ChartDataSets>();
  public fin03barChartDatas: ChartDataSets[] = new Array<ChartDataSets>();
  public FIN02InvbarChartDatas: ChartDataSets[] = new Array<ChartDataSets>();
  public FIN02bInvbarChartDatas: ChartDataSets[] = new Array<ChartDataSets>();
  public fin06barChartOptions: ChartOptions;
  public fin01barChartOptions: ChartOptions;
  public FIN01barChartOptions: ChartOptions;
  public fin07barChartOptions: ChartOptions;
  public fin03abarChartOptions: ChartOptions;
  public FIN02abarChartOptions: ChartOptions;

  public fin08bbarChartOptions: ChartOptions;
  public fin08cbarChartOptions: ChartOptions;
  public fin08barChartOptions: ChartOptions;
  public fin03barChartOptions: ChartOptions;
  public FIN02barChartOptions: ChartOptions;
  public FIN02bbarChartOptions: ChartOptions;
  // private barChartColors: Array<string> = ['#FA4848','#FFA500','#90EE90'];
  private barChartColors: Array<string> = ['#f44336', '#ffb822', '#0abb87'];
  public fin01InvDisplay: Boolean = false;
  public fin02aInvDisplay: Boolean = false;
  public fin02InvDisplay: Boolean = false;
  public fin02bInvDisplay: Boolean = false;
  public loading: Boolean = false;
  public barChartLabels = ['Initiate', 'In Progress', 'Completed'];
  public barChartType = 'bar';
  public barChartLegend = true;
  public currentState: string = "";
  public stateName: string = "";
  public circleName: string = "";
  public districtName: string = "";
  public currentDate: string;
  public totalClinic: number;
  public totalEquipment: number;
  public totalAmount: number;
  public disableState: boolean = false;
  public selectedDate: any;
  public barChartClickEvent:any;
  public disableButton:boolean=false;
  ngOnInit() {
    let date = new Date();
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    let monthStr;
    let dateStr;

    if (month < 10) { monthStr = "0" + month }
    else { monthStr = month }
    (day < 10 ? dateStr = "0" + day : dateStr = day);
    this.selectedDate = dateStr + "-" + monthStr + "-" + year

    this.invoiceGenerationFromDb = [];
    this.invoiceGenerationDisplay = [];
    this.appSharedService.fetchAllStates().subscribe(x => {
      this.stateFilterDatas = x;
    })
    this.currentDate = dateFormat(new Date(), 'dd/mm/yyyy');
    this.invoiceCounterRegion = new InvoiceCounterRegion();
    let region: any[][];
    this.loading = true;
    // this.appSharedService.getRegionBasedOnUser().then(
    //     (val:any[][]) => {;region=val;            
    //         this.invoiceCounterRegion.states= (region[0].length==0?"0":region[0].toString());
    //         this.invoiceCounterRegion.circles= (region[1].length==0?"0":region[1].toString());
    //         this.invoiceCounterRegion.districts= (region[2].length==0?"0":region[2].toString());
    //         this.invoiceCounterRegion.clinics= (region[3].length==0?"0":region[3].toString());
    this.dashboardProgressService.getFin01InvoiceCounter().subscribe(x => {
      this.fin01InvoiceCounter = x;
      this.assignBarChart("fin01");

    });
    this.dashboardProgressService.getFin02aInvoiceCounter().subscribe(x => {
      this.fin02aInvoiceCounter = x;
      this.assignBarChart("fin02a");
    });

    this.dashboardProgressService.getFin02InvoiceCounter().subscribe(x => {
      this.fin02InvoiceCounter = x;
      this.assignBarChart("fin02");
    });
    this.dashboardProgressService.getFin02bInvoiceCounter().subscribe(x => {
      this.fin02bInvoiceCounter = x;
      this.assignBarChart("fin02b");
    });
    this.loading = false;
    //   },
    //   (err) => console.error(err)
    // );

    this.showChart = false;
    this.chartDatas = new Array<number>();
    this.chartLabels = new Array();
    this.chartBackgroundColor = new Array<string>();
    this.chartLabels = new Array<string>();
    this.pieChartData = new Array<ChartDataSets>();

    this.stateBgColorArray = ['#3FC380', '#89C4F4', '#E08283', '#B388DD', '#F2C300', '#41C3AC', '#81B9C3', '#FF884D', '#FF9124', '#069BFF', '#FF6B57', '#3FC380', '#89C4F4', '#E08283', '#B388DD', '#F2C300', '#41C3AC', '#81B9C3', '#FF884D', '#FF9124', '#069BFF', '#FF6B57', '#F2C300', '#41C3AC'];
    this.chartBackgroundColor = this.stateBgColorArray;
  }

  handleForm(event) {
    switch (event.target.id) {
      case "state":
        this.selectedStateId = event.target.value;
        this.selectedDistrictId = "0";
        this.districtFilterDatas = this.stateFilterDatas.find(state => state.id == event.target.value).districts;
        //this.filterFunction();
        break;
      case "district":
        this.selectedDistrictId = event.target.value;
        break;
      default:
        break;
    }
  }
  clickFn(event) {
    if (event.active[0] != undefined) {
      this.forwardFlow(event);
    }
  }
  forwardFlow(event) {

    if (this.currentState == 'state') {
      this.heading = this.currentDocName + " - Circlewise Clinic Splitup "
      this.loading = true;
      this.stateName = (event != undefined ? this.chartLabels[event.active[0]._index] : this.stateName);
      this.resetArray();
      this.circleWisePieChart();
      this.currentState = 'circle';
      this.assignChartDatas();
    } else if (this.currentState == 'circle') {
      this.heading = this.currentDocName + " - Districtwise Clinic Splitup "
      this.loading = true;
      this.circleName = (event != undefined ? this.chartLabels[event.active[0]._index] : this.circleName);
      this.resetArray();
      this.invoiceGenerationFromDb.forEach((y => {
        if (y.circleName == this.circleName) {
          let invGen = new InvoiceGeneration();
          this.totalClinic = y.clinicCount + this.totalClinic;
          y.amount = Math.trunc(y.amount);
          this.totalEquipment = y.equipmentCount + this.totalEquipment;
          this.totalAmount = y.amount + this.totalAmount;;
          let uniqueInvGen = this.invoiceGenerationDisplay.filter((inv => inv.districtId === y.districtId))[0];
          if (uniqueInvGen != undefined && uniqueInvGen != null) {
            invGen.stateId = uniqueInvGen.stateId;
            invGen.stateName = uniqueInvGen.stateName;
            invGen.circleName = undefined;
            invGen.districtId = uniqueInvGen.districtId;
            invGen.districtName = uniqueInvGen.districtName;
            invGen.amount = y.amount + uniqueInvGen.amount;
            invGen.equipmentCount = y.equipmentCount + uniqueInvGen.equipmentCount;
            invGen.clinicCount = y.clinicCount + uniqueInvGen.clinicCount;
            var index = this.invoiceGenerationDisplay.findIndex(item => item.districtId == y.districtId);
            this.invoiceGenerationDisplay.splice(index, 1, invGen)
          } else {
            this.invoiceGenerationDisplay.push(y);
          }
        }

      }));
      this.invoiceGenerationDisplay.forEach((x) => {
        this.chartDatas.push(x.clinicCount);
        this.chartLabels.push(x.districtName);
      })

      this.currentState = 'district';
      this.assignChartDatas();
    }
  }

  backButton() {
    this.resetArray();
    if (this.currentState == 'district') {
      this.heading = this.currentDocName + " - Circlewise Clinic Splitup ";
      this.circleWisePieChart();
      this.districtName = "";
      this.circleName = "";
      this.currentState = 'circle';
      this.assignChartDatas();
    } else if (this.currentState == 'circle') {
      this.heading = this.currentDocName + " - Statewise Clinic Splitup"
      this.stateWisePieChart();
      this.stateName = "";
      this.currentState = 'state';
      this.assignChartDatas();
    }
    else if (this.currentState == 'state') {
      this.disableState = false;
      this.showChart = false;
      this.disableButton = false;
    }
  }

  generateChart(invoiceGenerationFromDb : Array<InvoiceGeneration>){
    if(invoiceGenerationFromDb.length>0){
      
      this.invoiceGenerationFromDb = invoiceGenerationFromDb;
      this.stateWisePieChart();
      this.currentState = 'state';
      this.assignChartDatas();
    }else{
      this.disableState = false;
      this.showChart = false;
      this.disableButton = false;
      this.loading = false;
      Swal.fire('', 'No data available', 'info');
    }
  
  }
  go(){
    

    if(this.barChartClickEvent!=undefined){
      this.disableState = true;
      this.disableButton = true;
      var d = new Date();
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 06 DOC' && this.barChartClickEvent.active[0]._index == 0) {
        this.currentDocName = "Fin 06 DOC - Initiate";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin06Intitate(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 06 DOC' && this.barChartClickEvent.active[0]._index == 1) {
        this.currentDocName = "Fin 06 DOC - Inprogress";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin06Inprogress(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 06 DOC' && this.barChartClickEvent.active[0]._index == 2) {
        this.currentDocName = "Fin 06 DOC - Completed";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin06Approved(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 01 DOC' && this.barChartClickEvent.active[0]._index == 0) {
        this.currentDocName = "Fin 01 DOC - Initiate";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin01Intitate(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 01 DOC' && this.barChartClickEvent.active[0]._index == 1) {
        this.currentDocName = "Fin 01 DOC - Inprogress";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin01Inprogress(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 01 DOC' && this.barChartClickEvent.active[0]._index == 2) {
        this.currentDocName = "Fin 01 DOC - Completed";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin01Approved(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
  
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'FIN 01 INVOICE' && this.barChartClickEvent.active[0]._index == 0) {
        this.currentDocName = "FIN 01 Invoice - Initiate";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin01InvIntitate(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'FIN 01 INVOICE' && this.barChartClickEvent.active[0]._index == 1) {
        this.currentDocName = "FIN 01 Invoice - Inprogress";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin01InvInprogress(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'FIN 01 INVOICE' && this.barChartClickEvent.active[0]._index == 2) {
        this.currentDocName = "FIN 01 Invoice - Completed";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin01InvApproved(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
  
  
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 07 DOC' && this.barChartClickEvent.active[0]._index == 0) {
        this.currentDocName = "Fin 07 DOC - Initiate";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin07IntitateOlder(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 07 DOC' && this.barChartClickEvent.active[0]._index == 1) {
        this.currentDocName = "Fin 07 DOC - Inprogress";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin07InprogressOlder(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 07 DOC' && this.barChartClickEvent.active[0]._index == 2) {
        this.currentDocName = "Fin 07 DOC - Completed";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin07ApprovedOlder(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 03a DOC' && this.barChartClickEvent.active[0]._index == 0) {
        this.currentDocName = "Fin 03a DOC - Initiate";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin03aIntitateOlder(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 03a DOC' && this.barChartClickEvent.active[0]._index == 1) {
        this.currentDocName = "Fin 03a DOC - Inprogress";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin03aInprogressOlder(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 03a DOC' && this.barChartClickEvent.active[0]._index == 2) {
        this.currentDocName = "Fin 03a DOC - Completed";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin03aApprovedOlder(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
  
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'FIN 02a INVOICE' && this.barChartClickEvent.active[0]._index == 0) {
        this.currentDocName = "FIN 02a Invoice - Initiate";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin02aInvIntitateOlder(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'FIN 02a INVOICE' && this.barChartClickEvent.active[0]._index == 1) {
        this.currentDocName = "FIN 02a Invoice - Inprogress";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin02aInvInprogressOlder(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'FIN 02a INVOICE' && this.barChartClickEvent.active[0]._index == 2) {
        this.currentDocName = "FIN 02a Invoice - Completed";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin02aInvApprovedOlder(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
  
  
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 08b DOC' && this.barChartClickEvent.active[0]._index == 0) {
        this.currentDocName = "Fin 08b DOC - Initiate";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin08bIntitate(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 08b DOC' && this.barChartClickEvent.active[0]._index == 1) {
        this.currentDocName = "Fin 08b DOC - Inprogress";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin08bInprogress(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 08b DOC' && this.barChartClickEvent.active[0]._index == 2) {
        this.currentDocName = "Fin 08b DOC - Completed";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin08bApproved(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
  
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 08c DOC' && this.barChartClickEvent.active[0]._index == 0) {
        this.currentDocName = "Fin 08c DOC - Initiate";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin08cIntitate(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 08c DOC' && this.barChartClickEvent.active[0]._index == 1) {
        this.currentDocName = "Fin 08c DOC - Inprogress";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin08cInprogress(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 08c DOC' && this.barChartClickEvent.active[0]._index == 2) {
        this.currentDocName = "Fin 08c DOC - Completed";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin08cApproved(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
  
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 08 DOC' && this.barChartClickEvent.active[0]._index == 0) {
        this.currentDocName = "Fin 08 DOC - Initiate";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin08Intitate(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 08 DOC' && this.barChartClickEvent.active[0]._index == 1) {
        this.currentDocName = "Fin 08 DOC - Inprogress";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin08Inprogress(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 08 DOC' && this.barChartClickEvent.active[0]._index == 2) {
        this.currentDocName = "Fin 08 DOC - Completed";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin08Approved(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
  
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 03 DOC' && this.barChartClickEvent.active[0]._index == 0) {
        this.currentDocName = "Fin 03 DOC - Initiate";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin03Intitate(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 03 DOC' && this.barChartClickEvent.active[0]._index == 1) {
        this.currentDocName = "Fin 03 DOC - Inprogress";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin03Inprogress(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 03 DOC' && this.barChartClickEvent.active[0]._index == 2) {
        this.currentDocName = "Fin 03 DOC - Completed";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin08bApproved(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
  
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 02 DOC' && this.barChartClickEvent.active[0]._index == 0) {
        this.currentDocName = "Fin 02 DOC - Initiate";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin02Intitate(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 02 DOC' && this.barChartClickEvent.active[0]._index == 1) {
        this.currentDocName = "Fin 02 DOC - Inprogress";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin02Inprogress(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'Fin 02 DOC' && this.barChartClickEvent.active[0]._index == 2) {
        this.currentDocName = "Fin 02 DOC - Completed";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin02Approved(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
  
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'FIN 02b INVOICE' && this.barChartClickEvent.active[0]._index == 0) {
        this.currentDocName = "FIN 02B Invoice - Initiate";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin02bInvIntitate(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
  
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'FIN 02b INVOICE' && this.barChartClickEvent.active[0]._index == 1) {
        this.currentDocName = "FIN 02B Invoice - Inprogress";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin02bInvInprogress(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
           this.generateChart(x);
        })
      }
  
      if (this.barChartClickEvent.active[0]._chart.config.options.title.text == 'FIN 02b INVOICE' && this.barChartClickEvent.active[0]._index == 2) {
        this.currentDocName = "FIN 02B Invoice - Completed";
        this.loading = true;
        this.resetArray();
        this.dashboardProgressService.getBacklogFin02bInvApproved(d.getMonth(), d.getFullYear(), this.selectedStateId, this.selectedDistrictId).subscribe((x) => {
          this.invoiceGenerationFromDb = x;
          this.stateWisePieChart();
          this.currentState = 'state';
          this.assignChartDatas();
        })
      }
  
      this.heading = this.currentDocName + " - Statewise Clinic Splitup"
    }else{
      Swal.fire('', 'Please select an particular backlog invoice in bar chart and then click Go button', 'info');
    }
  }
  clickBarChart(event) {
    this.barChartClickEvent = event;
  }

  stateWisePieChart() {
    this.invoiceGenerationFromDb.forEach((y => {
      let invGen = new InvoiceGeneration();
      this.totalClinic = y.clinicCount + this.totalClinic;
      this.totalEquipment = y.equipmentCount + this.totalEquipment;
      y.amount = Math.trunc(y.amount);
      this.totalAmount = y.amount + this.totalAmount;
      let uniqueInvGen = this.invoiceGenerationDisplay.filter((inv => inv.stateId === y.stateId))[0];
      if (uniqueInvGen != undefined && uniqueInvGen != null) {
        invGen.stateId = uniqueInvGen.stateId;
        invGen.stateName = uniqueInvGen.stateName;
        invGen.amount = y.amount + uniqueInvGen.amount;
        invGen.equipmentCount = y.equipmentCount + uniqueInvGen.equipmentCount;
        invGen.clinicCount = y.clinicCount + uniqueInvGen.clinicCount;
        var index = this.invoiceGenerationDisplay.findIndex(item => item.stateId == y.stateId);
        this.invoiceGenerationDisplay.splice(index, 1, invGen)
      } else {
        this.invoiceGenerationDisplay.push(y);
      }
    }));
    this.invoiceGenerationDisplay.forEach((x) => {
      this.chartDatas.push(x.clinicCount);
      this.chartLabels.push(x.stateName);
    })
  }
  circleWisePieChart() {
    this.invoiceGenerationFromDb.forEach((y => {
      if (y.stateName == this.stateName) {
        this.totalClinic = y.clinicCount + this.totalClinic;
        y.amount = Math.trunc(y.amount);
        this.totalEquipment = y.equipmentCount + this.totalEquipment;
        this.totalAmount = y.amount + this.totalAmount;
        let invGen = new InvoiceGeneration();
        let uniqueInvGen = this.invoiceGenerationDisplay.filter((inv => inv.circleId === y.circleId))[0];
        if (uniqueInvGen != undefined && uniqueInvGen != null) {
          invGen.stateId = uniqueInvGen.stateId;
          invGen.stateName = uniqueInvGen.stateName;
          invGen.circleId = uniqueInvGen.circleId;
          invGen.circleName = uniqueInvGen.circleName;
          invGen.districtName = undefined;
          invGen.amount = y.amount + uniqueInvGen.amount;
          invGen.equipmentCount = y.equipmentCount + uniqueInvGen.equipmentCount;
          invGen.clinicCount = y.clinicCount + uniqueInvGen.clinicCount;
          var index = this.invoiceGenerationDisplay.findIndex(item => item.circleId == y.circleId);
          this.invoiceGenerationDisplay.splice(index, 1, invGen)
        } else {
          this.invoiceGenerationDisplay.push(y);
        }
      }

    }));

    this.invoiceGenerationDisplay.forEach((x) => {
      this.chartDatas.push(x.clinicCount);
      this.chartLabels.push(x.circleName);
    });
  }
  assignChartDatas() {
    this.pieChartData.push({
      data: this.chartDatas,
      backgroundColor: this.chartBackgroundColor,
      label: 'Dataset 1',

    });
    this.pieChartOptions = {
      responsive: true,
      tooltips: { enabled: true },
      title: {
        display: true,
        fontSize: 14,
        fontColor: 'black'
      },
      legend: {
        display: true,
        position: 'left',

        labels: {
          fontSize: 14,
          fontColor: 'black'
        }
      },
      plugins: {
        datalabels: {
          color: 'black',


          font: {
            weight: 'bold'
          }

        },


      }
    };
    this.showChart = true;
    this.loading = false;

  }

  assignBarChart(invoice) {

    if (invoice == 'fin01') {

      this.fin06barChartDatas.push(
        { data: [this.fin01InvoiceCounter.oldMonSd1NotCreated, this.fin01InvoiceCounter.oldMonSd1InProgress, this.fin01InvoiceCounter.oldMonSd1Approved], backgroundColor: this.barChartColors, }

      );
      this.fin01barChartDatas.push(
        { data: [this.fin01InvoiceCounter.oldMonSd2NotCreated, this.fin01InvoiceCounter.oldMonSd2InProgress, this.fin01InvoiceCounter.oldMonSd2Approved], backgroundColor: this.barChartColors, }
      );
      this.FIN01InvbarChartDatas.push(
        { data: [this.fin01InvoiceCounter.oldMonInvNotCreated, this.fin01InvoiceCounter.oldMonInvInProgress, this.fin01InvoiceCounter.oldMonInvApproved], backgroundColor: this.barChartColors, }
      );

      this.fin06barChartOptions = {
        responsive: true,
        title: { display: true, text: 'Fin 06 DOC', fontSize: 14, fontColor: 'black', },
        legend: { display: false, },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }]
        },


      };

      this.fin01barChartOptions = {
        responsive: true,
        title: { display: true, text: 'Fin 01 DOC', fontSize: 14, fontColor: 'black', },
        legend: { display: false, },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }]
        },

      };
      this.FIN01barChartOptions = {
        responsive: true,
        title: { display: true, text: 'FIN 01 INVOICE', fontSize: 14, fontColor: 'black', },
        legend: { display: false, },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }]
        },
      }
      this.fin01InvDisplay = true;
    }
    if (invoice == 'fin02a') {

      this.fin07barChartDatas.push(
        { data: [this.fin02aInvoiceCounter.oldMonSd1NotCreated, this.fin02aInvoiceCounter.oldMonSd1InProgress, this.fin02aInvoiceCounter.oldMonSd1Approved], backgroundColor: this.barChartColors, }

      );
      this.fin03abarChartDatas.push(
        { data: [this.fin02aInvoiceCounter.oldMonSd2NotCreated, this.fin02aInvoiceCounter.oldMonSd2InProgress, this.fin02aInvoiceCounter.oldMonSd2Approved], backgroundColor: this.barChartColors, }
      );
      this.FIN02aInvbarChartDatas.push(
        { data: [this.fin02aInvoiceCounter.oldMonInvNotCreated, this.fin02aInvoiceCounter.oldMonInvInProgress, this.fin02aInvoiceCounter.oldMonInvApproved], backgroundColor: this.barChartColors, }
      );

      this.fin07barChartOptions = {
        responsive: true,
        title: { display: true, text: 'Fin 07 DOC', fontSize: 14, fontColor: 'black', },
        legend: { display: false, },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }]
        },


      };

      this.fin03abarChartOptions = {
        responsive: true,
        title: { display: true, text: 'Fin 03a DOC', fontSize: 14, fontColor: 'black', },
        legend: { display: false, },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }]
        },

      };
      this.FIN02abarChartOptions = {
        responsive: true,
        title: { display: true, text: 'FIN 02a INVOICE', fontSize: 14, fontColor: 'black', },
        legend: { display: false, },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }]
        },
      }
      this.fin02aInvDisplay = true;
    }
    if (invoice == 'fin02') {

      this.fin08bbarChartDatas.push(
        { data: [this.fin02InvoiceCounter.oldMonSd1NotCreated, this.fin02InvoiceCounter.oldMonSd1InProgress, this.fin02InvoiceCounter.oldMonSd1Approved], backgroundColor: this.barChartColors, }

      );
      this.fin08cbarChartDatas.push(
        { data: [this.fin02InvoiceCounter.oldMonSd2NotCreated, this.fin02InvoiceCounter.oldMonSd2InProgress, this.fin02InvoiceCounter.oldMonSd2Approved], backgroundColor: this.barChartColors, }
      );
      this.fin08barChartDatas.push(
        { data: [this.fin02InvoiceCounter.oldMonSd3NotCreated, this.fin02InvoiceCounter.oldMonSd3InProgress, this.fin02InvoiceCounter.oldMonSd3Approved], backgroundColor: this.barChartColors, }
      );
      this.fin03barChartDatas.push(
        { data: [this.fin02InvoiceCounter.oldMonSd4NotCreated, this.fin02InvoiceCounter.oldMonSd4InProgress, this.fin02InvoiceCounter.oldMonSd4Approved], backgroundColor: this.barChartColors, }
      );
      this.FIN02InvbarChartDatas.push(
        { data: [this.fin02InvoiceCounter.oldMonInvNotCreated, this.fin02InvoiceCounter.oldMonInvInProgress, this.fin02InvoiceCounter.oldMonInvApproved], backgroundColor: this.barChartColors, }
      );

      this.fin08bbarChartOptions = {
        responsive: true,
        title: { display: true, text: 'Fin 08b DOC', fontSize: 14, fontColor: 'black', },
        legend: { display: false, },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }]
        },


      };

      this.fin08cbarChartOptions = {
        responsive: true,
        title: { display: true, text: 'Fin 08c DOC', fontSize: 14, fontColor: 'black', },
        legend: { display: false, },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }]
        },

      };

      this.fin08barChartOptions = {
        responsive: true,
        title: { display: true, text: 'Fin 08 DOC', fontSize: 14, fontColor: 'black', },
        legend: { display: false, },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }]
        },

      };

      this.fin03barChartOptions = {
        responsive: true,
        title: { display: true, text: 'Fin 03 DOC', fontSize: 14, fontColor: 'black', },
        legend: { display: false, },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }]
        },

      };
      this.FIN02barChartOptions = {
        responsive: true,
        title: { display: true, text: 'FIN 02 INVOICE', fontSize: 14, fontColor: 'black', },
        legend: { display: false, },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }]
        },
      }
      this.fin02InvDisplay = true;
    }
    if (invoice == 'fin02b') {
      this.FIN02bInvbarChartDatas.push(
        { data: [this.fin02bInvoiceCounter.oldMonInvNotCreated, this.fin02bInvoiceCounter.oldMonInvInProgress, this.fin02bInvoiceCounter.oldMonInvApproved], backgroundColor: this.barChartColors, }
      );

      this.FIN02bbarChartOptions = {
        responsive: true,
        title: { display: true, text: 'FIN 02b INVOICE', fontSize: 14, fontColor: 'black', },
        legend: { display: false, },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: 'black'

            }
          }]
        },
      }
      this.fin02bInvDisplay = true;
    }
  }

  resetArray() {
    this.totalClinic = 0;
    this.totalEquipment = 0;
    this.totalAmount = 0;
    this.chartLabels = [];
    this.chartDatas = [];
    this.pieChartData = [];
    this.invoiceGenerationDisplay = [];
  }


}
