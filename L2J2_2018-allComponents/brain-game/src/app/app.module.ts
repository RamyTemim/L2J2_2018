import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SideComponent } from './side/side.component';
import { BodyComponent } from './body/body.component';
import { SudokuComponent } from './sudoku/sudoku.component';
import { DemineurComponent} from './demineur/demineur.component';
import { TeamComponent } from './team/team.component';
import { CompteurComponent } from './compteur/compteur.component';


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
    CompteurComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
