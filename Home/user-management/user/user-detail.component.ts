import { Component, OnInit } from '@angular/core';
import { UserManagementService } from "../user-management.service";
import { PagesService } from "../../../../pages/home/pages.service";
import 'rxjs/add/operator/switchMap';
import { User, UserDetail } from "../model/user";
import { UserGroup, UserGroupMaster } from "../model/user-group";
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { State } from "./../../master/model/state";
import { District } from '../../master/model/district';
import { Circle } from "../../master/model/circle";
import { UserRole } from "../model/user-role";
import { group } from '@angular/animations';
@Component({
  selector: 'user-detail',
  templateUrl: './user-detail.component.html'
})
export class UserDetailComponent implements OnInit {
  constructor(public userManagementService: UserManagementService, public router: Router, private route: ActivatedRoute, private pagesService: PagesService) { this.selectedLevel(new UserDetail(), 0, 'load'); }
  public user: User;
  public groups: any;
  public activeUserGroups: Array<UserGroup>;
  public userGroups = new Array<UserGroup>();
  public organisations: Array<any>;
  public applications: Array<any>;
  public userLevels: Array<any>;
  public userLevelMulArr: any[][];
  public states: Array<State>;
  public stateMulArr: any[][];
  public circles: Array<Circle>;
  public circleMulArr: any[][];
  public districts: Array<District>;
  public districtMulArr: any[][];
  public clinics: Array<any>;
  public clinicMulArr: any[][];
  public disableStates = new Array<boolean>();
  public disableCircles = new Array<boolean>();
  public disableDistricts = new Array<boolean>();
  public disableClinics = new Array<boolean>();
  public stateFlag = false;
  public clinicFlag = false;
  public userGroupFlag = false;
  public stateId: number = 0;
  public detailPage: string = '';
  public userGroupMasters = new Array<UserGroupMaster>();
  public userGroupMasterMulArr: any[][];
  public actives = [{ "name": "Yes", "value": "Y" }, { "name": "No", "value": "N" }];
  public loading: boolean = false;
  public validEmail: boolean = false;
  public requiredName: boolean = false;
  public requiredDesignation: boolean = false;
  public emailIdFromDd: string = '';
  public userRoles = new Array<UserRole>();
  ngOnInit() {
    this.user = new User();
    this.user.isActive = "Y";
    // this.user.applicationId=1;
    this.stateMulArr = [];
    this.districtMulArr = [];
    this.clinicMulArr = [];
    this.circleMulArr = [];
    this.userLevelMulArr = [];
    this.userGroupMasterMulArr = [];
    this.user.userDetails = new Array<UserDetail>();
    this.organisations = new Array();
    this.applications = new Array();
    this.userLevels = new Array();
    this.states = new Array();
    this.districts = new Array();
    this.circles = new Array();
    this.clinics = new Array();
    this.activeUserGroups = new Array<UserGroup>();
    this.loading = true;


    this.getAllUserGroup();
    this.organisations.push({ "name": "QMS", "id": 1 }, { "name": "MOH", "id": 2 });
    this.applications.push({ "name": "QUBICS", "id": 1 }, { "name": "CAMMS", "id": 2 });
    this.userLevels.push({ "name": "HO", "value": "HO" }, { "name": "STATE", "value": "STATE" }, { "name": "CIRCLE", "value": "CIRCLE" }, { "name": "DISTRICT", "value": "DISTRICT" }, { "name": "CLINIC", "value": "CLINIC" });
    this.userManagementService.fetchAllState().subscribe((x => {
      this.states = x;
      this.stateFlag = true;
      if (this.stateFlag && this.userGroupFlag) {
        this.getUserById();
      }
    }));
    this.userManagementService.getAllUserGroupMasterData().subscribe((x) => {
      this.userGroupMasters = x;
    });

  }
  getAllState() {
    this.userManagementService.fetchAllState().subscribe((x => {
      this.states = x;
    }));
  }
  getAllStateOther() {
    this.userManagementService.fetchAllState().subscribe((x => {
      this.states = x;
      this.states.shift();
    }));
  }
  getClinicByDistrictId(districtId: number, index: number) {
    this.userManagementService.fetchAllClinicByDistrictId(districtId).subscribe((x => {
      this.clinicMulArr[index].pop();
      this.clinicMulArr[index].push(x);
    }));
  }

  getCircleByStateId(stateId: number, index: number) {
    this.userManagementService.fetchAllCircleByStateId(stateId).subscribe((x => {
      this.circleMulArr[index].pop();
      x.unshift({ id: 0, circleName: "ALL", circleCode: "ALL" })
      this.circleMulArr[index].push(x);

    }));
    // this.circleMulArr[index].unshift({id:0,circleName:"ALL",circleCode:"ALL"});
  }
  getAllUserGroup() {
    this.userManagementService.getAllUserGroupData().subscribe((x) => {
      this.userGroups = x;
      x.forEach((g) => {
        if (g.isActive == 'Y') {
          this.activeUserGroups.push(g);
        }
      });
      this.userGroupFlag = true;
      if (this.stateFlag && this.userGroupFlag) {
        this.getUserById();
      }

    });
  }


  getUserById() {
    if (this.route.params['_value']['_id'] != "undefined") {
      this.detailPage = 'edit';
      if (this.stateFlag) {
        this.route.params.switchMap((par: Params) => this.userManagementService.getUserById(par['_id'])).subscribe(x => {
          this.user = x;
          this.emailIdFromDd = this.user.email;

          if (this.user.userDetails.length > 0) {
            this.setPriority();
            this.user.userDetails.forEach((userDetail, i) => {
              // 
              this.setArrayDefaultValue(i);
              this.selectedLevel(userDetail, i, 'load');
              this.handleState(userDetail.stateId, i);
              this.handleGroup(userDetail, i);
              if (this.user.userDetails.length - 1 == i) {
                // 
                this.loading = false;
              }
            });

          } else {
            this.addUserDetail();
            this.loading = false;
          }

        });
      }
    } else {
      this.detailPage = 'add';
      if (this.user.userDetails.length == 0) {
        // this.user.userDetails.push(new UserDetail());
        this.addUserDetail();
        this.setArrayDefaultValue(0);
      }
      this.loading = false;

    }
  }


  addUserDetail() {
    let userDetail = new UserDetail();
    let userRole = new UserRole();
    let userGroup = new UserGroup();
    userGroup.userRoles = [];
    userGroup.userRoles.push(userRole);
    userDetail.userGroups = [];
    userDetail.userGroups.push(userGroup)
    // 
    this.setArrayDefaultValue(this.user.userDetails.length);
    this.user.userDetails.push(userDetail);
  }

  deleteUserDetail(i: number, userDetail: UserDetail) {

    Swal.fire({
      title: 'Are you sure?',
      text: 'Once deleted, you will not be able to recover!',
      type: 'warning',
      showCloseButton: true,
      showCancelButton: true
    }).then((willDelete) => {
      if (willDelete.dismiss) {
        //   Swal.fire('', 'Your imaginary file is safe!', 'error');
      } else {
        this.user.userDetails.splice(i, 1);
        this.userManagementService.deleteUserDetail(userDetail).subscribe();
        // Swal.fire('', 'User has been deleted successfully !!!', 'error');
      }
    });


  }

  validate() {
    this.requiredName = false;
    this.requiredName = false
    this.requiredName = false
    if (this.user.name == "") {
      Swal.fire('', 'Name is required !!!', 'error');
      this.requiredName = false
    } else {
      this.requiredName = true;
    }
    if (this.user.designation == "") {
      Swal.fire('', 'Designation is required !!!', 'error');
      this.requiredDesignation = false;
    } else {
      this.requiredDesignation = true;
    }

    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    this.validEmail = re.test(String(this.user.email).toLowerCase());
    if (!this.validEmail) {
      Swal.fire('', 'Please enter valid Email Id', 'error');
    }
  }

  setArrayDefaultValue(index: number) {
    this.userLevelMulArr[index] = new Array<any>();
    this.userLevelMulArr[index].push(this.userLevels);
    this.districtMulArr[index] = new Array<District>();
    this.circleMulArr[index] = new Array<any>();
    this.clinicMulArr[index] = new Array<any>();
    this.stateMulArr[index] = new Array<State>();
    this.userGroupMasterMulArr[index] = new Array<UserGroupMaster>();
    this.districtMulArr[index].push([]);
    this.circleMulArr[index].push([]);
    this.clinicMulArr[index].push([]);
    this.userGroupMasterMulArr[index].push([]);
    this.stateMulArr[index].push(this.states);
  }


  handleState(stateId, index) {
    if (stateId > 0) {
      this.states.forEach((x) => {
        if (x.id == +stateId) {
          if (!this.disableCircles[index] || !this.disableDistricts[index]) {
            this.districtMulArr[index].pop();
            let district = new District();
            district.districtCode = "ALL";
            district.districtName = "ALL";
            district.id = 0;
            x.districts.unshift(district)
            this.districtMulArr[index].push(x.districts);
            this.getCircleByStateId(stateId, index);
          }
        }
      })
    }
  }

  openRoles(userDetail: UserDetail) {
    // 
    if (userDetail.userGroups.length > 0) {

      this.userRoles = [];
      this.userRoles = userDetail.userGroups[0].userRoles;
    }

  }
  userGroupChange(groups, userDetail) {
    // 
    // 
    // 
    let group = groups.find(group => +group.id == +userDetail.userGroupId);
    this.user.userDetails.forEach((detail) => {
      if (detail.id == userDetail.id) {
        detail.userGroups = [group];
      }
    })
  }
  setPriority() {

    let minMasterUserGroupPriority = this.pagesService.getMenuMappingPriority(this.user.userDetails);
    this.user.menuMappingGroupMasterPriority = minMasterUserGroupPriority[0].userGroupMasterPriority;
    this.user.menuMappingGroupPriority = minMasterUserGroupPriority[0].userGroupPriority;
    this.user.menuMappingGroupName = minMasterUserGroupPriority[0].userGroupName;
  }

  handleGroup(userDetail: UserDetail, index: number) {
    let userGroupMasters = this.userGroups.filter((g) => g.userGroupMasterName === userDetail.userLevel);
    this.userGroupMasterMulArr[index].pop();
    this.userGroupMasterMulArr[index].push(userGroupMasters);
  }


  handleDistrict(userDetail, districtId, index: number) {
    if (userDetail == 'CLINIC') {
      this.getClinicByDistrictId(districtId, index);
    }

  }


  selectedLevel(userDetail: UserDetail, index: number, from: string) {


    if (from != 'load') {
      this.user.userDetails[index].stateId = null;
      this.user.userDetails[index].circleId = null;
      this.user.userDetails[index].districtId = null;
      this.user.userDetails[index].clinicId = null;

    }
    if (userDetail.userLevel == 'STATE') {
      // if(this.loading==false){
      //   this.getAllState();
      // }

      this.disableStates[index] = false;
      this.disableCircles[index] = true;
      this.disableDistricts[index] = true;
      this.disableClinics[index] = true;

    } else if (userDetail.userLevel == 'CIRCLE') {
      //   if(this.loading==false){
      //   this.getAllStateOther(); 
      // }    
      this.disableStates[index] = false;
      this.disableCircles[index] = false;
      this.disableDistricts[index] = true;
      this.disableClinics[index] = true;
      this.handleState(this.stateId, index);
    } else if (userDetail.userLevel == 'DISTRICT') {
      //   if(this.loading==false){
      //   this.getAllStateOther();  
      // }   
      this.disableStates[index] = false;
      this.disableCircles[index] = true;
      this.disableDistricts[index] = false;
      this.disableClinics[index] = true;
      this.handleState(this.stateId, index);
    } else if (userDetail.userLevel == 'CLINIC') {
      //   if(this.loading==false){
      //   this.getAllStateOther();   
      // }  
      this.disableStates[index] = false;
      this.disableCircles[index] = false;
      this.disableDistricts[index] = false;
      this.disableClinics[index] = false;
      if (userDetail.districtId != null) {
        this.getClinicByDistrictId(userDetail.districtId, index)
      }
    } else {
      this.disableStates[index] = true;
      this.disableCircles[index] = true;
      this.disableDistricts[index] = true;
      this.disableClinics[index] = true;
    }
  }


  save() {

    this.validate();
    if (this.validEmail && this.requiredName && this.requiredDesignation) {
      this.user.userName = this.user.email;
      this.user.userDetails.forEach((detail) => {
        // let userRole = new UserRole();
        // let userGroup =new UserGroup();
        // userGroup.userRoles = [];
        // userGroup.userRoles.push(userRole);
        detail.userGroups = [];
        // detail.userGroups.push(userGroup) 

      })
      // 
      // 
      if (this.user.id != undefined) {
        if (this.user.email != this.emailIdFromDd) {
          this.userManagementService.getUserByEmailId(this.user.email).subscribe((x) => {
            if (x.length > 0) {
              Swal.fire('', 'Email Id already exist!!!', 'error');
            } else if (x.length == 0) {
              // this.setPriority();
              this.userManagementService.updateUser(this.user).subscribe((x) => {
                Swal.fire('', 'User updated successfully!!!', 'success');
                this.exit();
              });
            }
          });
        } else {
          // this.setPriority();
          this.userManagementService.updateUser(this.user).subscribe((x) => {
            Swal.fire('', 'User updated successfully!!!', 'success');
            this.exit();
          });
        }

      } else {
        this.userManagementService.getUserByEmailId(this.user.email).subscribe((x) => {
          if (x.length > 0) {
            Swal.fire('', 'Email Id already exist!!!', 'error');
          } else if (x.length == 0) {
            this.user.password = 'qubics';
            // this.setPriority();
            this.userManagementService.addUser(this.user).subscribe((x) => {
              Swal.fire('', 'User added successfully!!! <br/>Your User Name is ' + this.user.userName, 'success');
              this.exit();
            });
          }
        })
      }
    }




  }


  exit() {
    this.router.navigateByUrl("/user-management/user");
  }

}
