import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from "@angular/common/http";


import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SideComponent } from './side/side.component';
import { BodyComponent } from './body/body.component';
import { SudokuComponent } from './sudoku/sudoku.component';
import { DemineurComponent} from './demineur/demineur.component';
import { TeamComponent } from './team/team.component';
import { CompteurComponent } from './compteur-sudoku/compteur-sudoku.component';
import {CompteurDemineurComponent} from "./compteur-demineur/compteur-demineur.component";
import {MailComponent} from './mail/mail.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SideComponent,
    BodyComponent,
    SudokuComponent,
    DemineurComponent,
    TeamComponent,
    MailComponent,
    CompteurComponent,
    CompteurDemineurComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
