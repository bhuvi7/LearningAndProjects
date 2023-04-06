import { Component, OnInit } from '@angular/core';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import 'chartjs-plugin-datalabels';
import { Label, Color } from 'ng2-charts';
import { Invoice } from "../model/invoice";
import { DashboardProgressService } from "../dashboard-progress.service";
import * as dateFormat from 'dateformat';
import { Router } from "@angular/router";
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'revenue-budget',
  templateUrl: './revenue-budget.component.html',
  providers: [NgbModalConfig, NgbModal]
})
export class RevenueBudgetComponent implements OnInit {


  // public pieChartLabels = ['FIN 01', 'FIN 02', 'FIN 02A', 'FIN 02B', 'FIN 04', 'FIN 05A', 'FIN 11'];
  // // public pieChartData =   [14.42,14.48,12.00,14.95,14.20,15.79,14.17];
  // public pieChartData :  ChartDataSets[] = [
  //   {
  //     data: [14.42,14.48,12.00,14.95,14.20,15.79,14.17],  backgroundColor: ["#FF6384","#FF9F40","#F44336", "#36A2EB","#FFCD56","#1AD175","#4BC0C0"],
  //   },
  // ]; 
  // public pieChartType = 'pie';
  // public pieChartOptions: ChartOptions = {
  //   responsive: true,
  //   tooltips:{enabled:true},
  //   title: {
  //     display: true,
  //     text: 'Last Quarter Revenue Contribution',
  //     fontSize: 14
  //   }, legend: {
  //     display: true ,
  //     position: 'left',   

  //     labels: {
  //         fontSize: 14,
  //         fontColor:'black'           
  //     }
  //   },

  //   plugins: {
  //     datalabels: {    
  //       color: 'black',

  //       font: {
  //         weight: 'bold'
  //       }

  //     },
  //     labels: [{
  //         render: function(args) {

  //             return args.label;
  //         },
  //         fontSize: 9,
  //         position: 'outside'
  //     },{
  //         render: function(args) {
  //             return  args.value +"%";
  //         },
  //         fontSize: 9.5,
  //         position: 'border'
  //     }]
  // }
  // };

  dtOptions: DataTables.Settings = {
    pagingType: 'full_numbers',
    pageLength: 10
  };

  public chartDatas: Array<number>;
  public chartBackgroundColor: Array<string>;
  public chartLabels: Array<string>;
  public pieChartData: ChartDataSets[];
  public pieChartType = 'pie';
  public pieChartOptions: ChartOptions;
  public pieChartPlugin: any;
  private stateDataArray: Array<number>;
  private stateBgColorArray: Array<string>;
  private stateLabelsArray: Array<string>;
  private currentState: string = "";
  private data: any;
  public selectedValue: Boolean;
  public invoices: Array<Invoice>;
  public stateName: string = "";
  public districtName: string = "";
  public invoiceTypes: any = [];
  public invoiceByQuaters: any = [];
  public totalInvoiceAmount: number;
  public headingState: string;
  public headingAmount: number;
  public currentDate: string;
  public modalBodyContent: string;
  public invoiceTableData: Array<any>;
  public invoiceTableTotal = 0;

  constructor(public dashboardProgressService: DashboardProgressService, private router: Router, config: NgbModalConfig, private modalService: NgbModal) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  openModal(content, invoiceTypeName) {
    this.invoiceTableData = [];
    this.invoiceTableTotal = 0;
    this.modalBodyContent = invoiceTypeName
    this.dashboardProgressService.fetchAllInvoiceFilter(this.stateName,"", this.districtName, invoiceTypeName, "").subscribe(x => {
      if (invoiceTypeName == "FIN_02") {
        let y = []
        y = x
        x = []
        y.forEach(e => {
          if (e.invoiceTypeName == "FIN_02") {
            x.push(e)
          }
        })
      }
      x.forEach(element => {
        if (element.paymentStatus == "PAYMENT-RECEIVED") {
          this.invoiceTableData.push(element)
        }
      })
      this.invoiceTableData.forEach(element => {
        this.invoiceTableTotal += element.totalInvoiceValue
      });
      this.modalService.open(content, {
        size: 'lg'
      })
    })
  }

  assignChartDatas() {

    this.pieChartData.push({
      data: this.chartDatas,
      backgroundColor: this.chartBackgroundColor,
      label: 'Dataset 2',

    });
    this.pieChartOptions = {
      responsive: true,
      tooltips: { enabled: true },
      title: {
        display: true,
        // text: "Last Quarter Revenue Contribution" +" - RM "+(this.totalInvoiceAmount!=undefined?this.totalInvoiceAmount:"")+ (this.stateName!=undefined?" for "+this.stateName:"")+(this.districtName!=undefined?" / "+this.districtName:""),
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
      //   'onClick': function (c, i) {
      //   //  
      //    let e = i[0];
      //   //  
      //    var x_value = this.data.labels[e["_index"]];
      //    var y_value = this.data.datasets[0].data[e["_index"]];
      //        var clickedLabel = x_value;
      //    
      // },
      plugins: {
        datalabels: {
          color: 'black',
          font: {
            weight: 'bold'
          }
        },
      }
    };


  }

  public barChartOptions: ChartOptions = {
    responsive: true,
    title: {
      display: true,
      text: 'Revenue Breakdown',
      fontSize: 14,
      fontColor: 'black'

    },
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
    legend: {
      display: true,
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
  public barChartLabels: Label[] = ['Rental', 'Maintenance'];
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [];

  public barChartData: ChartDataSets[] = [
    { data: [300, 350], label: 'Expected' },
    { data: [150, 250], label: 'Actual' }
  ];

  public lineChartData: ChartDataSets[] = [
    {
      data: [0, 12, 32, 45, 65, 78, 95], label: 'RM-327', backgroundColor: '#FF6384',
      borderColor: '#FF6384', fill: false
    },
  ];

  public lineChartLabels: Label[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13'];
  public lineChartOptions: ChartOptions = {
    responsive: true,
    elements: {
      line: {
        tension: 0,
        borderWidth: 4
      }
    },
    title: {
      display: true,
      text: 'Budget Utilization Report',
      fontSize: 14,
      fontColor: 'black'
    },
    tooltips: {
      mode: 'index',
      intersect: false,
    },
    hover: {
      mode: 'nearest',
      intersect: true
    },
    scales: {
      xAxes: [{
        display: true,
        scaleLabel: {
          display: true,
          labelString: 'Year',
          fontColor: 'black'

        },
        ticks: {
          beginAtZero: true,
          fontColor: 'black'

        }
      }],
      yAxes: [{
        display: true,
        scaleLabel: {
          display: true,
          labelString: 'Amount',
          fontColor: 'black'

        },
        ticks: {
          beginAtZero: true,
          fontColor: 'black',
          suggestedMax: 300
        }
      }]
    },
    legend: {
      display: true,
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
  public lineChartColors: Color[] = [
    {
      borderColor: '#C2DFFF',
      backgroundColor: 'rgba(0,0,0,0)',
    },
  ];
  public lineChartLegend = true;
  public lineChartType = 'line';
  public lineChartPlugins = [];

  ngOnInit() {
    this.data = require('../../../../../assets/data/revenue.json');
    // 
    this.chartDatas = new Array<number>();
    this.chartBackgroundColor = new Array<string>();
    this.chartLabels = new Array<string>();
    this.pieChartData = new Array<ChartDataSets>();
    this.currentDate = dateFormat(new Date(), 'dd/mm/yyyy');
    // this.stateDataArray=this.data.stateData;
    this.stateBgColorArray = ['#3FC380', '#89C4F4', '#E08283', '#B388DD', '#F2C300', '#41C3AC', '#81B9C3', '#FF884D', '#FF9124', '#069BFF', '#FF6B57', '#3FC380', '#89C4F4', '#E08283', '#B388DD', '#F2C300', '#41C3AC', '#81B9C3', '#FF884D', '#FF9124', '#069BFF', '#FF6B57', '#F2C300', '#41C3AC'];
    // this.stateLabelsArray=this.data.stateLabel;
    // this.chartDatas = this.stateDataArray;
    this.chartBackgroundColor = this.stateBgColorArray;

    this.dashboardProgressService.fetchAllRevenueAnalysisLastQuater().subscribe((x => {
      this.invoices = x;
      this.invoices.forEach((x) => {
        if (x.totalInvoiceValue > 0) {
          x.totalInvoiceValue = Math.trunc(x.totalInvoiceValue / 1000);
          this.chartLabels.push(x.stateName);
          this.chartDatas.push(x.totalInvoiceValue)
        }
      });
    }));
    this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByInvoiceType("ALL", "ALL").subscribe((x) => {
      this.totalInvoiceAmount = 0;
      this.headingAmount = 0;
      x.forEach((e) => {
        if (e.totalInvoiceValue > 0) {
          this.headingAmount += e.totalInvoiceValue
          e.totalInvoiceValue = Math.trunc(e.totalInvoiceValue);
          this.invoiceTypes.push(e);
          this.totalInvoiceAmount += e.totalInvoiceValue
        }
      })
    })
    this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByQuater("ALL", "ALL").subscribe((x) => {
      x.forEach((e) => {
        e.totalInvoiceValue = Math.trunc(e.totalInvoiceValue);
        this.invoiceByQuaters.push(e);
      })


    })




    // this.chartLabels =this.stateLabelsArray;
    this.currentState = 'state';
    this.headingState = "Statewise "
    this.assignChartDatas();

  }

  openInvoiceReport(invoiceTypeName) {
    if (this.stateName.length < 2) {
      this.stateName = '""';
    }
    if (this.districtName.length < 2) {
      this.districtName = '""';
    }
    var url = 'report/invoice/invoice-list/RA/' + invoiceTypeName + "/" + this.stateName + "/" + this.districtName;
    this.router.navigateByUrl(url);
  }

  clickFn(event) {

    // 
    //   
    // 
    // 
    if (this.currentState == 'state') {
      this.stateName = this.chartLabels[event.active[0]._index]
      this.pieChartData = [];
      this.chartLabels = [];
      this.chartDatas = [];
      this.invoiceTypes = [];
      this.invoiceByQuaters = [];
      this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByStateName(this.stateName).subscribe((x => {
        this.invoices = x;
        this.invoices.forEach((x) => {
          if (x.totalInvoiceValue > 0) {
            x.totalInvoiceValue = Math.trunc(x.totalInvoiceValue / 1000);
            this.chartLabels.push(x.districtName);
            this.chartDatas.push(x.totalInvoiceValue)
          }
        });
      }));
      this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByInvoiceType("STATE", this.stateName).subscribe((x) => {
        this.totalInvoiceAmount = 0;
        this.headingAmount = 0;
        x.forEach((e) => {
          if (e.totalInvoiceValue > 0) {
            this.headingAmount += e.totalInvoiceValue
            e.totalInvoiceValue = Math.trunc(e.totalInvoiceValue);
            this.invoiceTypes.push(e);
            this.totalInvoiceAmount += e.totalInvoiceValue
          }
        })
      })
      this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByQuater("STATE", this.stateName).subscribe((x) => {
        x.forEach((e) => {
          e.totalInvoiceValue = Math.trunc(e.totalInvoiceValue);
          this.invoiceByQuaters.push(e);
        })

      })
      this.currentState = 'district';
      this.assignChartDatas();
      this.headingState = "Districtwise "
      // }else if(this.currentState=='circle'){
      //   this.pieChartData=[];
      //   this.chartDatas = this.data.districtData;
      //   this.chartLabels=this.data.districtLabel;
      //   this.chartBackgroundColor=[];
      //   this.chartBackgroundColor.push('#3FC380', '#89C4F4');
      //   this.currentState = 'district';
      //   this.assignChartDatas();
    } else if (this.currentState == 'district') {
      this.districtName = this.chartLabels[event.active[0]._index];
      this.pieChartData = [];
      this.chartLabels = [];
      this.chartDatas = [];
      this.invoiceTypes = [];
      this.invoiceByQuaters = [];
      this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByDistrictName(this.districtName).subscribe((x => {
        this.invoices = x;
        this.invoices.forEach((x) => {
          if (x.totalInvoiceValue > 0) {
            x.totalInvoiceValue = Math.trunc(x.totalInvoiceValue / 1000);
            this.chartLabels.push(x.quater);
            this.chartDatas.push(x.totalInvoiceValue)
          }
        });
      }));
      this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByInvoiceType("DISTRICT", this.districtName).subscribe((x) => {
        this.totalInvoiceAmount = 0;
        this.headingAmount = 0;
        x.forEach((e) => {
          if (e.totalInvoiceValue > 0) {
            this.headingAmount += e.totalInvoiceValue
            e.totalInvoiceValue = Math.trunc(e.totalInvoiceValue);
            this.invoiceTypes.push(e);
            this.totalInvoiceAmount += e.totalInvoiceValue
          }
        })
      })
      this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByQuater("DISTRICT", this.districtName).subscribe((x) => {
        x.forEach((e) => {
          e.totalInvoiceValue = Math.trunc(e.totalInvoiceValue);
          this.invoiceByQuaters.push(e);
        })

      })
      this.currentState = 'quater';
      this.headingState = "Quaterwise "
      this.assignChartDatas();
    }
  }

  backButton() {
    if (this.currentState == 'quater') {
      this.pieChartData = [];
      this.chartLabels = [];
      this.chartDatas = [];
      this.invoiceByQuaters = [];
      this.invoiceTypes = [];
      this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByStateName(this.stateName).subscribe((x => {
        this.invoices = x;
        this.invoices.forEach((x) => {
          if (x.totalInvoiceValue > 0) {
            x.totalInvoiceValue = Math.trunc(x.totalInvoiceValue / 1000);
            this.chartLabels.push(x.districtName);
            this.chartDatas.push(x.totalInvoiceValue)
          }
        });
      }));
      this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByInvoiceType("STATE", this.stateName).subscribe((x) => {
        this.totalInvoiceAmount = 0;
        this.headingAmount = 0;
        x.forEach((e) => {
          if (e.totalInvoiceValue > 0) {
            this.headingAmount += e.totalInvoiceValue
            e.totalInvoiceValue = Math.trunc(e.totalInvoiceValue);
            this.invoiceTypes.push(e);
            this.totalInvoiceAmount += e.totalInvoiceValue
          }
        })
      })
      this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByQuater("STATE", this.stateName).subscribe((x) => {
        x.forEach((e) => {
          e.totalInvoiceValue = Math.trunc(e.totalInvoiceValue);
          this.invoiceByQuaters.push(e);
        })

      })
      this.districtName = "";
      this.headingState = "Districtwise "
      this.currentState = 'district';
      this.assignChartDatas();
    } else if (this.currentState == 'district') {
      this.pieChartData = [];
      this.chartLabels = [];
      this.chartDatas = [];
      this.invoiceTypes = [];
      this.invoiceByQuaters = [];
      this.dashboardProgressService.fetchAllRevenueAnalysisLastQuater().subscribe((x => {
        this.invoices = x;
        this.invoices.forEach((x) => {
          if (x.totalInvoiceValue > 0) {
            x.totalInvoiceValue = Math.trunc(x.totalInvoiceValue / 1000);
            this.chartLabels.push(x.stateName);
            this.chartDatas.push(x.totalInvoiceValue)
          }
        });
      }));
      this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByInvoiceType("ALL", "ALL").subscribe((x) => {
        this.totalInvoiceAmount = 0;
        this.headingAmount = 0;
        x.forEach((e) => {
          if (e.totalInvoiceValue > 0) {
            this.headingAmount += e.totalInvoiceValue
            e.totalInvoiceValue = Math.trunc(e.totalInvoiceValue);
            this.invoiceTypes.push(e);
            this.totalInvoiceAmount += e.totalInvoiceValue
          }
        })
      })
      this.dashboardProgressService.fetchAllRevenueAnalysisLastQuaterByQuater("ALL", "ALL").subscribe((x) => {
        x.forEach((e) => {
          e.totalInvoiceValue = Math.trunc(e.totalInvoiceValue);
          this.invoiceByQuaters.push(e);
        })

      })
      this.stateName = "";
      this.districtName = "";
      this.currentState = 'state';
      this.headingState = "Statewise "
      this.assignChartDatas();
    }
    // else if(this.currentState=='circle'){
    //   this.pieChartData=[]; 
    //   this.chartDatas = this.data.stateData;
    //   this.chartLabels= this.data.stateLabel;
    //   this.chartBackgroundColor=[];      
    //   this.chartBackgroundColor=['#3FC380', '#89C4F4', '#E08283', '#B388DD', '#F2C300', '#41C3AC', '#81B9C3', '#FF884D', '#FF9124', '#069BFF', '#FF6B57'];
    //   this.currentState = 'state';
    //   this.assignChartDatas();
    // }
  }
}
