import { Injectable } from "@angular/core";
import { User } from "../../../../app/pages/home/user-management/model/user";
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { State } from "../../../../app/pages/home/master/model/state";
import { District } from "../../../../app/pages/home/master/model/district";
import { Circle } from "../../../../app/pages/home/master/model/circle";
import { UserGroupRoleMapping } from "../user-management/model/user-group-role-mapping";

import { Observable } from "rxjs/Observable"
@Injectable({providedIn:'root'})
export class AppSharedService{

    public user:User;
    public states = new Array<State>();
    public districts = new Array<District>();
    public circles = new Array<Circle>();
    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: HttpClient) {}

    public getUser(){
        this.user = JSON.parse(localStorage.getItem('currentUser'));
        return this.user;
    }

    public getUserId(){
        this.user = JSON.parse(localStorage.getItem('currentUser'));
        return this.user.id;
    }

    public fetchAllCircleByStateId(stateId: number) {
        return this.http.get<Array<Circle>>(this.serviceApiUrl + 'circle/state-id/'+stateId)
    }

    public fetchStates() {
        var that = this;
        var promise = new Promise(function(resolve, reject) {
        that.getUser();
        let statesToReturn = new Array<State>();
        that.states = new Array<State>();
        that.http.get<Array<State>>(that.serviceApiUrl + 'state/all').subscribe((x)=>{
            x.forEach((x => {
                if (x.id != 0) {  that.states.push(x) }
              }));    
            that.user.userDetails.forEach((userDetail,i)=>{
                let filteredStated ;
                that.states.forEach((state,j)=>{
                    filteredStated = JSON.parse(JSON.stringify(state));
                    if(userDetail.stateId > 0 && userDetail.stateId==+state.id){
                        let districts = new Array<District>();
                        
                        state.districts.forEach((d,i)=>{
                            if(userDetail.userLevel=='CIRCLE' || userDetail.userLevel=='DISTRICT' || userDetail.userLevel=='CLINIC' ){
                                if(userDetail.userLevel=='CIRCLE' ){
                                    if(+d.circleId ==+userDetail.circleId && +userDetail.circleId>0){
                                        state.circleIdToShow = userDetail.circleId;
                                        districts.push(d); 
                                    }else if(userDetail.circleId ==0){
                                        state.circleIdToShow = userDetail.circleId;
                                        districts.push(d); 
                                    }
                                }else{ //
                                    if((+d.id ==+userDetail.districtId) && (userDetail.districtId >0)){
                                        // 
                                        state.districtIdToShow = d.id;
                                        districts.push(d); 
                                    }else if(userDetail.districtId ==0){
                                        
                                        state.districtIdToShow = d.id;
                                        districts.push(d); 
                                    }
                                }                  
                              } 
                              if(state.districts.length -1 == i ){
                                    if(districts.length > 0){                                       
                                        filteredStated.districts = districts
                                    }
                              }
                          });
                          filteredStated.userLevel = userDetail.userLevel;
                          statesToReturn.push(filteredStated);
                    }
                    if((userDetail.userLevel=='HO' || userDetail.userLevel=='STATE') && userDetail.stateId==0){
                        state.userLevel = userDetail.userLevel;
                        statesToReturn.push(state);
                    } 
                })
            });
            resolve(statesToReturn);
        });
      
    })
       
        return promise;
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

    public buttonRoleApproval(stateId,circleId,districtId,clinicId,buttonName,level){
        
        var that = this;
        var promise = new Promise(function(resolve, reject) {
            let buttonHasApproval =false;
            let user =that.getUser();
            if(user.userDetails.length>0){
                
                user.userDetails.forEach((detail)=>{
                        if(level=='CLINIC' && detail.userLevel=='CLINIC' ){
    
                            if(detail.stateId==stateId && detail.districtId==districtId && detail.clinicId==clinicId){
                                // 
                                // 
                                that.getUserGroupRoleMappingByGroupId(detail.userGroupId).subscribe(x=>{
                                    // 
                                   x.forEach((userGroupRoleMapping)=>{
                                    //    
                                    if(userGroupRoleMapping.userRoleName === buttonName ){
                                        buttonHasApproval =true
                                        // 
                                        resolve(true);
                                       return buttonHasApproval;
                                    }
                                   })
                                    
                                    
                             })
                            }
                        
    
                        }
                        if(level=='DISTRICT' && detail.userLevel=='DISTRICT'){
                            
                            if(detail.stateId==stateId && detail.districtId==districtId){
                                // 
                                // 
                                that.getUserGroupRoleMappingByGroupId(detail.userGroupId).subscribe(x=>{
                                    // 
                                   x.forEach((userGroupRoleMapping)=>{
                                    //    
                                    if(userGroupRoleMapping.userRoleName === buttonName ){
                                        buttonHasApproval =true
                                        // 
                                        resolve(true);
                                       return buttonHasApproval;
                                    }
                                   })
                                    
                                    
                             })
                            }
                       
                            // 
                        }
                   
                })
                
            }
        })
        return promise;

    }
    getUserGroupRoleMappingByGroupId(id){
        return this.http.get<Array<UserGroupRoleMapping>>(this.serviceApiUrl+"user-group-role-mapping/group-id/"+id);
    }
    public fetchAllStates() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }
}