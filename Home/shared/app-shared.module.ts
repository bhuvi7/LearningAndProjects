import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../theme/shared/shared.module';
import { ListPagefilter } from './list-page-filter/list-page-filter.component';

@NgModule({
    imports:[CommonModule,SharedModule],
    declarations:[ListPagefilter],
    exports:[ListPagefilter]
})
export class AppSharedModule{}