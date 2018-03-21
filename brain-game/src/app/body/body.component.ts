import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  constructor() { }



  ngOnInit() {
  }



}


//Rendre invisible l'encadrer du jeu
function fold()
{
  let a = document.getElementById("resumer");
  a.style.display = "none";


}

//Rendre visible l'encadrer du jeu
function unfold()
{
  let a = document.getElementById("resumer");
  a.style.display = "block";

}

//Rend visible ou invisible l'encadrer du jeu à chaque clique
function toggle(h)
{
  let a = document.getElementById("resumer");

  //a = h.nextElementSibling;
  let x = getComputedStyle(a).display;


  if (x == "block") {

    fold();

  }
  else {

    unfold();

  }
}
