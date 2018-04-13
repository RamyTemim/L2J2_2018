import {Component, Injectable, OnInit} from '@angular/core';
import { HttpClient } from "@angular/common/http";
import 'rxjs/add/operator/map';

//import {Case} from "../case";

@Component({
  selector: 'app-demineur',
  templateUrl: './demineur.component.html',
  styleUrls: ['./demineur.component.css']
})

//Mettre l'injectable car on a importer le module HttpClient
@Injectable()
export class DemineurComponent implements OnInit {

  public Cases=[];

  //Crée la grille du demineur
  //grille

  data: any = {};





  //Constructeur du httpClient
  constructor(private httpClient: HttpClient) {}

  //Permet de récupérer depuis le back-end les données des différents niveaux
  getLevelEasy(){
    this.httpClient
      .get<any[]>('http://localhost:8080/demineur/niveauFacile')
      .subscribe(
        (response)=>{
          console.log("easy ok !");
          this.Cases = response;
        }
      )
  }

  getLevelMedium(){
    this.httpClient
      .get<any[]>('http://localhost:8080/demineur/niveauMoyen')
      .subscribe(
        (response)=>{
          console.log("medium ok !");
          this.Cases = response;
        }
      )
  }

  getLevelHard(){
    this.httpClient
      .get<any[]>('http://localhost:8080/demineur/niveauDifficile')
      .subscribe(
        (response)=>{
          console.log("hard ok !");
          this.Cases = response;
        }
      )
  }




/*  case Case: {
    id: 11;
    valeur: 0;
  }


 /* export class Case {
  private value: number;
  private id: number;

  constructor (value: number, id: number) {

    this.value=value;
    this.id=id;
  }

}

export class Ligne extends Case {

  private Case: Array[]<Case>;

  constructor(value: number, id: number) {
    super(value, id);
  }

}

export class Grille extends Ligne {

  private Ligne: Array[]<Ligne>;

  constructor(value: number, id: number){
    super(value, id);
  }

}

class Ligne {
  private name :string;

  constructor(theName :string){
    this.name=theName;

  }
}
 */

 /* constructor() { }*/

  ngOnInit() {
  }

}



