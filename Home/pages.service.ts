import { Injectable } from "@angular/core";
import { User } from "../../../app/pages/home/user-management/model/user";
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { State } from "../../../app/pages/home/master/model/state";
import { District } from "../../../app/pages/home/master/model/district";
import { Circle } from "../../../app/pages/home/master/model/circle";
import { Router } from "@angular/router";
import Swal from 'sweetalert2';
@Injectable({providedIn:'root'})
export class PagesService{
    public user:User;
    public states = new Array<State>();
    public districts = new Array<District>();
    public circles = new Array<Circle>();
    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: HttpClient,private _router: Router) {

    }
    public getUser(){
        this.user = JSON.parse(localStorage.getItem('currentUser'));
        return this.user.userDetails;
    }
    public getUserId(){
        this.user = JSON.parse(localStorage.getItem('currentUser'));
        return this.user.id;
    }
    public getMenuMappingPriority(userDetail?:any){
        //
        let user =(userDetail==undefined?this.getUser():userDetail);
        let minMasterUserGroupPriority =user.sort(function (a,b) {
          return a.userGroupMasterPriority - b.userGroupMasterPriority || a.userGroupPriority - b.userGroupPriority
        })
    
        return minMasterUserGroupPriority;
    }
    public getMenuMappingPriorityGroupId(){
        // 
        let minMasterUserGroupPriority = this.getMenuMappingPriority();
        let userGroupId = undefined;
        if(minMasterUserGroupPriority.length>0){
            if(minMasterUserGroupPriority[0] !=undefined){
                if(minMasterUserGroupPriority[0].userGroupId !=undefined && minMasterUserGroupPriority[0].userGroupId>0 ){
                    userGroupId = minMasterUserGroupPriority[0].userGroupId;
                }else{
                    this.redirectToLogin();
                }
            }else{
                this.redirectToLogin();
            }
        }else{
            this.redirectToLogin();
        }
        return minMasterUserGroupPriority[0].userGroupId;
    }
    public redirectToLogin(){
        this._router.navigate(['/auth/login']);
        localStorage.removeItem('currentUser');
        Swal.fire('', 'User Group not assigned! Please contact administrator!!!', 'error')
    }
    public fetchAllCircleByStateId(stateId: number) {
        return this.http.get<Array<Circle>>(this.serviceApiUrl + 'circle/state-id/'+stateId)
    }

    public fetchStates():Array<State> {
        this.getUser();
        let statesToReturn = new Array<State>();
        this.http.get<Array<State>>(this.serviceApiUrl + 'state/all').subscribe((x)=>{
            this.states = x;
            this.user.userDetails.forEach((userDetail,i)=>{
                this.states.forEach((state,j)=>{
                    if(userDetail.stateId==+state.id){
                        let districts = new Array<District>();
                        state.districts.forEach((d,i)=>{
                            if(userDetail.userLevel=='circle' || userDetail.userLevel=='district' || userDetail.userLevel=='clinic' ){
                                if(userDetail.userLevel=='circle' ){
                                    if(d.circleId ==userDetail.circleId){
                                        state.circleIdToShow = userDetail.circleId;
                                        // state.userGroup = userDetail.
                                        districts.push(d); 
                                    }
                                }else{
                                    if(d.id ==userDetail.districtId){
                                        
                                        state.districtIdToShow = d.id;
                                        districts.push(d); 
                                    }
                                }                  
                              } 
                              if(state.districts.length -1 == i ){
                                districts.length > 0 ? state.districts = districts:'';
                              }
                          });
                          state.userLevel = userDetail.userLevel;
                          statesToReturn.push(state);
                        // 
                    }
                    if((this.user.userDetails.length-1 ==i) && (this.states.length -1 ==j)){
                        if(statesToReturn.length==0){
                           statesToReturn =  this.states; 
                        }                        
                    }        
                })
            });
        });
       
       return statesToReturn;
    }
    public fetchDistricts(districts:Array<District>):Array<District> {
        this.getUser();
        let districtToReturn = new Array<District>();
        
            this.user.userDetails.forEach((userDetail,i)=>{
                if(userDetail.userLevel=='district' || userDetail.userLevel=='clinic' ){
                    districts.forEach((district,j)=>{
                        if(userDetail.districtId==+district.id){
                            districtToReturn.push(district);
                        }      
                    });
                }else if(userDetail.userLevel=='circle'){
                    districts.forEach((district,j)=>{
                        if(userDetail.circleId==+district.circleId){
                            districtToReturn.push(district);
                        }      
                    });
                }
                else if(userDetail.userLevel=='state'){
                    districtToReturn = districts;
                }
             
            });
    
       return districtToReturn;
    }




}