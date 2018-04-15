import {Component, Injectable, OnInit} from '@angular/core';
import { HttpClient } from "@angular/common/http";


@Component({
  selector: 'app-compteur-demineur',
  templateUrl: './compteur-demineur.component.html',
  styleUrls: ['./compteur-demineur.component.css']
})


//Mettre l'injectable car on a importer le module HttpClient
@Injectable()
export class CompteurDemineurComponent implements OnInit {

  public listLevel =["Facile", "Moyen", "Difficile"];

  public minute = 0;
  public seconde = 0;
  public compteur: any;

  public Cases//=[];

  //Constructeur du httpClient
  constructor(private httpClient: HttpClient) {}

  //Permet de récupérer depuis le back-end les données des différents niveaux
  //Récupère le niveau facile
  getLevelEasy(){
    this.httpClient
      .get<any[]>('http://localhost:8080/demineur/niveauFacile')
      .subscribe(
        (response)=>{
          this.Cases = response;
          console.log("easy ok !");
          console.log(this.Cases);

          if(this.Cases[0]==1){
            console.log("c'est un 1");
          }
        }
      )
  }

  //Récupère le niveau moyen
  getLevelMedium(){
    this.httpClient
      .get<any[]>('http://localhost:8080/demineur/niveauMoyen')
      .subscribe(
        (response)=>{
          this.Cases = response;
          console.log("medium ok !");
          console.log(this.Cases);
        }
      )
  }

  //Récupère le niveau difficile
  getLevelHard(){
    this.httpClient
      .get<any[]>('http://localhost:8080/demineur/niveauDifficile')
      .subscribe(
        (response)=>{
          this.Cases = response;
          console.log("hard ok !");
          console.log(this.Cases);
        }
      )
  }

  start() {
    if (this.compteur === undefined) {
      this.compteur = setInterval(() => {
          this.seconde += 1;
          if (this.seconde === 60) {
            this.seconde = 0;
            this.minute += 1;
            if (this.minute === 60) {
              this.minute = 0;
            }
          }
        }
        , 1000);
      console.log("Timer Start !");
    }
  }
  stop() {
    clearInterval(this.compteur);
    this.compteur = undefined;
    console.log("Timer  Stop !");
  }

  reset(){
    clearInterval(this.compteur);
    this.seconde = 0;
    this.minute = 0;
    this.compteur = undefined;
    console.log("Timer Reset !");
    console.log("New Grid !")
  }



ngOnInit() {
  }

}
