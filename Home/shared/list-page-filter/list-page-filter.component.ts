import { Component, EventEmitter, Input, Output } from "@angular/core";
import { AppSharedService } from "./../app-shared.service";
import { State } from "../../master/model/state";
import { District } from '../../master/model/district';
import { Circle } from '../../master/model/circle';
import { User } from "../../../../../app/pages/home/user-management/model/user";
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
@Component({
    selector: 'list-page-filter',
    templateUrl: './list-page-filter.component.html'
})
export class ListPagefilter {
    @Output() selectedIds = new EventEmitter<{stateId:number, districtId:number, circleId:number, clinicId:number, clinicTypeId:string,hideFilter:boolean}>();
    @Output() hideFilter: EventEmitter<Boolean> = new EventEmitter();
    constructor(private appSharedService: AppSharedService,private http: HttpClient) {}

    public user :User;
    public stateId: number = 0;
    public districtId: number= 0;
    public circleId: number = 0;
    public clinicId: number = 0;
    public clinicTypeId: string;
    public states = new Array<State>();
    public districts: Array<District>;
    public circles = new Array<Circle>();
    public findDuplicate = new Array<number>();
    public loading: Boolean = true;
    public disableButton: Boolean = true;
    serviceApiUrl: string = environment.serviceApiUrl;
    public userLevel : string;
    // public diableFilter : boolean = false;

     ngOnInit() {
        this.loading = true;
        this.states = [];
         this.appSharedService.fetchStates().then(
            (val:Array<State>) => {this.states=val;this.loading = false;this.findDuplicateState()},
            (err) => console.error(err)
          );;
        this.user = this.appSharedService.getUser();
        this.disableButton = true;
        if(this.user.userDetails.length==1){
           
            const userDetail = this.user.userDetails[0];
        
            if(userDetail.userLevel !=='HO'){
                this.selectedIds.emit({stateId:(userDetail.stateId==null?0:userDetail.stateId),circleId:(userDetail.circleId==null?0:userDetail.circleId),districtId:(userDetail.districtId==null?0:userDetail.districtId),clinicId:(userDetail.clinicId==null?0:userDetail.clinicId),clinicTypeId:this.clinicTypeId,hideFilter:true});
            }
        }
        
    }
    
    findDuplicateState(){
        let stateId = new Array<number>();
        this.states.forEach((state,index)=>{
            if (stateId.indexOf(state.id) == -1) {
                stateId.push(state.id);
            }else{
                let state1 = this.states.find(s=>s.id==state.id);
                state.districts.forEach((x)=>{
                state1.districts.push(x);
                });
               
                this.states[this.states.findIndex(s=>s.id==state.id)] = state1;
                this.states.splice(index,1);
            }
            if(this.states.length-1==index){
                if(this.states.length==1){
                        if(this.states[0].userLevel=='STATE' ||this.states[0].userLevel=='HO'){
                            this.stateId = this.states[0].id;
                            this.districts = this.states[0].districts;
                            if(this.districts.find((dis)=>dis.id==0)==undefined){
                                let d = new District();
                                d.id = 0;
                                d.districtCode = 'ALL';
                                d.districtName = 'ALL';
                                this.districts.unshift(d);
                            }
                            this.disableButton =false;
                        }
                        if(this.states[0].userLevel=='DISTRICT' ){
                            this.stateId = this.states[0].id;
                          
                            this.districts = this.states[0].districts;
                            this.districtId = this.states[0].districts[0].id;
                       
                        }
                }
                if(this.user.userDetails.length==1){
                    const userDetail = this.user.userDetails[0];
                    if(userDetail.userLevel =='HO'){
                        let s = new State();
                        s.id = 0;
                        s.stateCode = 'ALL';
                        s.stateName = 'ALL';
                        this.states.unshift(s);
                        this.disableButton = false;
                    }
                  
                }
            }
        })
    }

    resetFilter() {
        this.stateId = null;
        this.districtId = null;
        this.circleId = null;
    }


    handleForm(event) {
        // this.disableButton =true;
        switch (event.target.id) { 
            case "state":
                this.stateId = event.target.value;
                
                this.circleId=0;
                this.districtId=0;
                this.findDuplicate = new Array<number>();
                this.circles = [];
                this.states.forEach((state, i) => {
                    if (state.id == +this.stateId) {
                        this.userLevel = state.userLevel;
                        
                      
                        this.districts = state.districts;
                        this.districts.forEach((d) => {
                            let circle = new Circle();
                            circle.id = d.circleId;
                            circle.circleCode = d.circleName;
                            circle.circleName = d.circleName;
                            if (this.findDuplicate.indexOf(d.circleId) == -1) {
                                this.findDuplicate.push(d.circleId);
                                this.circles.push(circle)
                            }
                        })
                        if(this.userLevel=='STATE' ||this.userLevel=='HO'){
                            
                            if(this.districts.find((dis)=>dis.id==0)==undefined){
                                let d = new District();
                                d.id = 0;
                                d.districtCode = 'ALL';
                                d.districtName = 'ALL';
                                this.districts.unshift(d);
                            }
                           
                            this.disableButton =false;
                        }else{
                            this.disableButton =true;
                        }
                    }
                    // this.inputDataList.forEach((data: any) => {
                    //     if (state.userLevel !== 'STATE' && state.userLevel !== 'HO' ) {
                    //         if (state.userLevel === 'CIRCLE') {
                    //             if (+state.circleIdToShow === +data.circleId) {
                    //                 this.filteredData.push(data);
                    //             }
                    //         } else {
                    //             if (+state.districtIdToShow === +data.districtId) {
                    //                 this.filteredData.push(data);
                    //             }
                    //         }
                    //     } else if (state.userLevel === 'STATE' || state.userLevel === 'HO' ) {
                    //         this.filteredData.push(data);
                    //     }
                        
                    // })
                });
        
                break;
            case "circle":
                this.districtId=0;
                this.circleId = event.target.value;
                if(this.userLevel=='CIRCLE' && this.user.userDetails.length>1){
                    this.disableButton =false;
                }
                if(this.userLevel=='HO' || this.userLevel=='STATE' ){
                  if(this.stateId>0){
                    this.districts = [];
                      let selectedState = this.states.filter((x)=>x.id==this.stateId)[0];
                      selectedState.districts.forEach((d,i)=>{                   
                                if(+d.circleId ==+this.circleId && +this.circleId>0){                                
                                    this.districts.push(d); 
                                }    
                      });
                  }
                }
                break;
            case "district":
                this.circleId=0;
                this.districtId = event.target.value;
                if(this.userLevel=='DISTRICT' && this.user.userDetails.length>1){
                    this.disableButton =false;
                }
                break;
            default:
                break;
        }
    }

    go(){
        this.selectedIds.emit({stateId:this.stateId,circleId:this.circleId,districtId:this.districtId,clinicId:(this.clinicId==null?0:this.clinicId),clinicTypeId:this.clinicTypeId,hideFilter:false});
    }

    }
   