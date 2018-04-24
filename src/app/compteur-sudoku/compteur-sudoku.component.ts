import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-compteur',
  templateUrl: './compteur-sudoku.component.html',
  styleUrls: ['./compteur-sudoku.component.css']
})
export class CompteurComponent implements OnInit {


  public minute = 0;
  public seconde = 0;
  public compteur: any;

  constructor() { }

  start() {
    console.log(this.minute + " : " + this.seconde);
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

  restart(){
    this.reset();
    this.start();
  }



  ngOnInit() {
  }

}
