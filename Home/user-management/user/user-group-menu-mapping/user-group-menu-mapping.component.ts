import { Component, OnInit, ViewChild } from "@angular/core";
import { Subject } from 'rxjs';
import { UserGroup, UserGroupMaster } from "../../model/user-group";
import { Menu } from "../../model/menu";
import { UserGroupMenuMapping } from "../../model/user-group-menu-mapping";
import { UserManagementService } from "../../user-management.service";
import { DataTableDirective } from 'angular-datatables';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
@Component({
    selector: "user-group-menu-mapping",
    templateUrl: "user-group-menu-mapping.component.html"
})
export class UserGroupMenuMappingComponent implements OnInit {
    dtOptions1: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 16
    };
    dtTrigger1: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement1: DataTableDirective;

    constructor(private userManagementService: UserManagementService) { }

    public userGroupMasters: Array<UserGroupMaster>;
    public userGroups: Array<UserGroup>;
    public activeUserGroups: Array<UserGroup>;
    public userGroupMenuMappingList: Array<UserGroupMenuMapping>;
    public userGroupMenuMapping: UserGroupMenuMapping;
    public filteredGroupMasterId: number;

    public menus: Array<Menu>;

    ngOnInit() {
        this.userGroupMenuMapping = new UserGroupMenuMapping();
        this.activeUserGroups = new Array<UserGroup>();
        this.menus = new Array<Menu>();
        this.getAllUserGroup();
        this.getAllUserMenu();
        this.userManagementService.getAllUserGroupMasterData().subscribe((x) => {
            this.userGroupMasters = x;
        });

    }


    getAllUserGroup() {
        this.userManagementService.getAllUserGroupData().subscribe((x) => {
            this.userGroups = x;
        });
    }

    getAllUserMenu() {
        this.userManagementService.getAllMenuData().subscribe((x) => {
            this.dtElement1.dtInstance.then((dtInstance1: DataTables.Api) => {
                dtInstance1.destroy();
                this.dtTrigger1.next();
                this.menus = x;
            });
        });

    }
    selectMenuItem(event, menu: Menu) {
        if (menu.menuType == 'MENU') {
            this.menus.forEach((x) => {
                if (x.menuId == menu.id) {
                    x.checked = event.target.checked;
                }
            });
        } else {
            menu.checked = event.target.checked;
        }
    }

    handleForm() {
        this.activeUserGroups = [];
        this.userGroups.forEach((g) => {
            if (g.isActive == 'Y' && +g.userGroupMasterId == +this.filteredGroupMasterId) {
                this.activeUserGroups.push(g);
            }
        });
    }
    resetFilter() {
        this.filteredGroupMasterId = undefined;
        this.userGroupMenuMappingList = [];
        this.activeUserGroups = [];
    }
    editUserRoleMapping(userGroup: UserGroup) {
        this.userGroupMenuMapping.userGroupName = userGroup.groupName;
        this.userGroupMenuMapping.userGroupId = userGroup.id;
        this.menus.forEach((y) => { y.checked = false; y.groupMenuMappingId = null });
        // 
        this.userManagementService.getAllUserGroupMenuMappingByGroupId(userGroup.id).subscribe((datas) => {
            datas.forEach((data) => {
                if (userGroup.id == data.userGroupId) {
                    this.menus.forEach((menu) => {
                        if(menu.menuType=='MENU'){ menu.checked = false;}
                        if (data.menuId == menu.id && menu.menuType=='SUB_MENU') {
                            menu.checked = true;
                            menu.groupMenuMappingId = data.id;
                        }
                    });
                }
            });
        })
    }

    saveUserGroupMenuMapping() {

        let deleteFlag = false;
        let userGroupMenuMappingSaveList = new Array<UserGroupMenuMapping>();
        let userGroupMenuMappingDeleteList = new Array<UserGroupMenuMapping>();
        this.menus.forEach((menu) => {
            if (menu.checked) {
                if (menu.menuType == 'SUB_MENU') {
                    let userGroupMenuMapping = new UserGroupMenuMapping();
                    userGroupMenuMapping.menuId = menu.id;
                    userGroupMenuMapping.userGroupId = this.userGroupMenuMapping.userGroupId;
                    userGroupMenuMapping.id = menu.groupMenuMappingId;
                    userGroupMenuMappingSaveList.push(userGroupMenuMapping);
                }

            } else if (menu.checked == false && menu.groupMenuMappingId != null) {
                let userGroupMenuMapping = new UserGroupMenuMapping();
                userGroupMenuMapping.menuId = menu.id;
                userGroupMenuMapping.userGroupId = this.userGroupMenuMapping.userGroupId;
                userGroupMenuMapping.id = menu.groupMenuMappingId;
                userGroupMenuMappingDeleteList.push(userGroupMenuMapping);
                deleteFlag = true;
            }
        });
        this.userManagementService.addUserGroupMenuMapping(userGroupMenuMappingSaveList).subscribe((x) => { Swal.fire('', 'Mapping updated successfully!!!', 'success') });
        if (deleteFlag) {
            this.userManagementService.deleteUserGroupMenuMapping(userGroupMenuMappingDeleteList).subscribe((x) => { });
        }
    }


    ngAfterViewInit(): void {
        this.dtTrigger1.next();
    }

    ngOnDestroy(): void {
        this.dtTrigger1.unsubscribe();
    }
}