import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  constructor() { }

  public classOfResumer="resumer-none";


  ngOnInit() {
  }


//Rend visible ou invisible l'encadrer du jeu à chaque clique

  public toggle() {


    if (this.classOfResumer='resumer-none')

      this.classOfResumer='resumer-block';

    else

      this.classOfResumer='resumer-none';


  }




}




