import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {RouterModule} from "@angular/router";

import { AppComponent } from './app.component';
import { InputViewComponent} from "./input/inputview.component";
import { TagViewComponent} from "./tag/tag.component";
import {AppRouting} from "./common/app.routing";
import {TagService} from "./service/tag.service";

@NgModule({
  declarations: [
    AppComponent,
    InputViewComponent,
    TagViewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRouting.routes,
    RouterModule
  ],
  providers: [TagService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
