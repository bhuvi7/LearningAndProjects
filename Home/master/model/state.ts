import { District } from "./district";
export class State{
    id:number;
    stateCode:string;
    stateName:string;
    isActive:string;
    districts:Array<District>;
    circleIdToShow?:number;
    districtIdToShow?:number;
    userLevel?:string;
    userGroup?:string;
    userRole?:string;
}

export class StateArray{
    id:Array<State>;
    
}