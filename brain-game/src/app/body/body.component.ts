import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  constructor() { }

  public classOfResumer;


  ngOnInit() {
  }

  //Rendre invisible l'encadrer du jeu
  public fold() {
    debugger;
    //let a = document.getElementsByClassName("resumer");
    this.classOfResumer = "resumer-none";


  }

//Rendre visible l'encadrer du jeu
  public unfold() {
    let a = document.getElementsByClassName("resumer");
    this.classOfResumer = "resumer-block";

  }

//Rend visible ou invisible l'encadrer du jeu à chaque clique

  public toggle() {
    debugger;
    /*console.log(1111111);
     let a = document.getElementsByClassName("resumer");
     let x = getComputedStyle(a).display;


     if (x == "block") {

       this.fold();

     }
     else {

       this.unfold();

     }
     */
  }



}




