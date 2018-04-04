import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-compteur',
  templateUrl: './compteur.component.html',
  styleUrls: ['./compteur.component.css']
})
export class CompteurComponent {
  public minute = 0;
  public seconde = 0;
  public compteur: any;
  constructor() {
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
    }
  }
  stop() {
    clearInterval(this.compteur);
    this.compteur = undefined;
  }

  reset(){
    clearInterval(this.compteur);
    this.seconde = 0;
    this.minute = 0;
    this.compteur = undefined;
  }

}
