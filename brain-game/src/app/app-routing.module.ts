import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import {DemineurComponent} from "./demineur/demineur.component";
import {SudokuComponent} from "./sudoku/sudoku.component";
import {BodyComponent} from "./body/body.component";
import {TeamComponent} from "./team/team.component";


const routes: Routes = [

  { path: '', redirectTo:'/Accueil', pathMatch:'full'},
  { path: 'Accueil' , component: BodyComponent },
  { path: 'Demineur' , component: DemineurComponent },
  { path: 'Sudoku' , component: SudokuComponent },
  { path: 'Team' , component: TeamComponent},





]

@NgModule({
  imports: [ RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
