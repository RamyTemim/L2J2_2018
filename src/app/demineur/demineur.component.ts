import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-demineur',
  templateUrl: './demineur.component.html',
  styleUrls: ['./demineur.component.css']
})
export class DemineurComponent implements OnInit {


  ngOnInit() {
    this.getDemineur();
    this.manageButtonsEvent();
  }


  /******************************   Method to make ajax request   *******************************/


  /**
   *
   * Make request to spring boot application
   *
   *
   * @param config
   */
  ajax(config) {
    let xhttp: XMLHttpRequest = new XMLHttpRequest();
    xhttp.onload = () => {
      if (xhttp.status == 200) {
        console.log(xhttp.responseText);
        config.success(xhttp.responseText);
      }
    };

    xhttp.open(config.method, config.url, true);
    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(config.data);
  }



  /**
   *
   * Define format of request object to call function ajax()
   *
   *
   * @param method
   * @param url
   * @param data
   * @param success
   * @returns {{method: any; url: any; data: any; success: any}}
   */
  static formatRequestObject(method, url, data, success) {
    return {
      method,
      url,
      data,
      success
    };
  }




  /******************************   Method to manage buttons   *******************************/


  /**
   * Call method associate to select button : create grid ; validate grid ; display solution
   */
  manageButtonsEvent() {
    let bValidate = document.getElementById('bValide');
    let bSolution = document.getElementById('bSolution');
    let bNewGrid = document.getElementById('bNewGrid');

    let res;

    bValidate.onclick = () => {
      confirm('Valider la grille ?');
      res = DemineurComponent.validateGrid();
      if(res) alert("Félicitation vous avez réussi !!!");
      else alert("Désolé mais votre grille n'est pas bonne : retentez votre chance :) ");
    };

    bSolution.onclick = () => {
      confirm('Afficher la solution ?');
      this.getSolution();
    };

    bNewGrid.onclick = () => {
      confirm('Charger une nouvelle grille ?');
      this.getDemineur();
    };

  }



          private sizeGrid;
          static mineList = [];
          static flagList = [];


  /******************************   Method to create a grid   *******************************/


  /**
   *
   * Get back a String which contains elements of a sudoku grid which depend of selected level
   * Then call generateGird and param = String from back
   *
   *    1°)get back value of select to add on url of request
   *    2°)create object of request format to call ajax()
   *    3°)call ajax() and apply method with response of request to create a grid on frontend
   */
  getDemineur() {
    let select = document.querySelector('#levelDemineur');
    let level = select.options[select.selectedIndex].getAttribute('name');
    let method = 'GET';
    let url = 'http://localhost:8080/demineur/' + level;

    let size;

    switch(level){
      case "niveauFacile" : size = 64; break;
      case "niveauMoyen" : size = 256; break;
      case "niveauDifficile" : size = 480; break;
    }

    this.sizeGrid = size;

    let data = null;
    let success = (response) => {
      this.generateGridDemineur(response, size);
    };

    let config = DemineurComponent.formatRequestObject(method, url, data, success);
    this.ajax(config);
  }


  /**
   *
   * Method to generate a grid
   *
   * @param response
   * @param size
   */
  generateGridDemineur(response, size) {
    // If there is nothing receive from de back -> STOP
    if (response === '') return;

    let rowSize = size === 64 ? 8 : 16;


    // Get back values of grid to generate and select element in those which will contains the demineur game grid
    let array = DemineurComponent.responseToArray(response);
    let container = document.getElementById('demineurContainer');
    let fragContainer = document.createDocumentFragment();
    console.log(container);



    // Create principal cube -> the grid and associated style
    let styleCube = 'display: inline-block; position: relative;';
    let cubeContainer = DemineurComponent.createCube('cubeD', styleCube);


    // Define row and cell style
    let styleRow = 'display: block; position: relative; height:30px;';

    let styleCellD = '' +
      '  display: inline-block;' +
      '  text-align: center;' +
      '  width: 30px;' +
      '  height: 30px;' +
      '  background-color: white;' +
      '  color: white;'   +
      '  border : 1px;'+
      '  border-color : black;'+
      '  border-style : solid;'+
      '  line-height: 30px;' +
      '  box-sizing: border-box;' +
      '  -moz-box-sizing: border-box;' +
      '  overflow: hidden;' +
      '  -webkit-box-sizing: border-box;';



    // If container already contains a grid -> it's remove
    DemineurComponent.removeGrid(container);

    let row;
    let rowNumber;


    for (let i = 0; i < size; i += 1) {          ////  Loop to implement row_Id  ////

      if (i % rowSize === 0) {
        rowNumber = (i / rowSize);
        let rowId = 'row_' + rowNumber;
        row = DemineurComponent.createRow(rowId, styleRow);
        cubeContainer.appendChild(row);
      }

      let value = array[i];
      let cellIdD = i;
      let cell = DemineurComponent.createCell(cellIdD, styleCellD, value);
      cell.textContent = value;
      cell.setAttribute('column', '' + (i % rowSize));
      cell.setAttribute('row', '' + rowNumber);
      cell.classList.add('cellGrid');

      cell.oncontextmenu = (e) => {
        e.preventDefault();

        if (e.target.className.indexOf('cellFlag') < 0) DemineurComponent.addFlag(e.target.id);
        else {
          e.target.classList.remove('cellFlag');
          cell.setAttribute('style', styleCellD);
        }
      };

      if (value === "*") {
        cell.classList.add('mine');
        DemineurComponent.mineList.push(cellIdD);

        cell.onclick = () => {
          DemineurComponent.devoileMine();
        };
      } else {
        cell.onclick = (e) => {
          this.devoileGrid(e.target.id);
        };


      }

      row.appendChild(cell);
    }                                         ////  END loop  ////

    fragContainer.appendChild(cubeContainer);
    container.appendChild(fragContainer);
  }


  /**
   *
   * Create cube in an element div
   *
   * @param id
   * @param style
   * @returns {HTMLDivElement}
   */
  static createCube(id, style) {
    let cube = document.createElement('div');
    cube.id = id;
    cube.setAttribute('style', style);

    return cube;
  }


  /**
   *
   *  Create a row in an element div
   *
   * @param id
   * @param style
   * @returns {HTMLDivElement}
   */
  static createRow(id, style) {
    let row = document.createElement('div');
    row.id = id;
    row.setAttribute('style', style);

    return row;
  }




  /**
   *
   * Create a new cell which contains a value : number or ? -> if cell is hide
   *
   * @param id
   * @param style
   * @param value
   * @returns {HTMLDivElement}
   *
   */
  static createCell(id, style, value): HTMLDivElement {
    let div = document.createElement('div');
    div.id = id;
    div.setAttribute('style', style);
    div.textContent = value;

    return div;
  }


  /**
   *
   * Define syntax -> if element container isn't empty, syntax is replace by ''
   *
   * @param container
   */
  static removeGrid(container) {
    container.innerHTML = '';
  }



  /******************************   Method to play on the grid   *******************************/



  /**
   * Method to dispay solution
   */
  getSolution() {
    let i;
    for(i = 0; i < this.sizeGrid; i++){
      let styleSolution = '' +
        '  display: inline-block;' +
        '  text-align: center;' +
        '  width: 30px;' +
        '  height: 30px;' +
        '  background-color: DarkGrey;' +
        '  color: black;'   +
        '  border : 1px;'+
        '  border-color : black;'+
        '  border-style : solid;'+
        '  line-height: 30px;' +
        '  box-sizing: border-box;' +
        '  -moz-box-sizing: border-box;' +
        '  overflow: hidden;' +
        '  -webkit-box-sizing: border-box;';

      let styleMineSol = '' +
        '  display: inline-block;' +
        '  text-align: center;' +
        '  width: 30px;' +
        '  height: 30px;' +
        '  background-color: DarkGrey;' +
        '  color: red;'   +
        '  border : 1px;'+
        '  border-color : black;'+
        '  border-style : solid;'+
        '  line-height: 30px;' +
        '  box-sizing: border-box;' +
        '  -moz-box-sizing: border-box;' +
        '  overflow: hidden;' +
        '  -webkit-box-sizing: border-box;';

      let styleNumSol = '' +
        '  display: inline-block;' +
        '  text-align: center;' +
        '  width: 30px;' +
        '  height: 30px;' +
        '  background-color: DarkGrey;' +
        '  color: blue;'   +
        '  border : 1px;'+
        '  border-color : black;'+
        '  border-style : solid;'+
        '  line-height: 30px;' +
        '  box-sizing: border-box;' +
        '  -moz-box-sizing: border-box;' +
        '  overflow: hidden;' +
        '  -webkit-box-sizing: border-box;';

      let cell = document.getElementById(i.toString());
      if (cell.textContent === "*") cell.setAttribute('style', styleMineSol);
      else if (cell.textContent === "-") cell.setAttribute('style', styleSolution);
      else cell.setAttribute('style', styleNumSol);

    }
  }


  /**
   * Method to display only mines
   */
  static devoileMine() {
    let styleCellMine = '  display: inline-block;' +
      '  width: 30px;' +
      '  height: 30px;' +
      '  background-color: DarkGrey;' +
      '  color: red;' +
      '  border : 1px;'+
      '  border-color : black;'+
      '  text-align: center;' +
      '  border-style : solid;'+
      '  line-height: 30px;' +
      '  box-sizing: border-box;' +
      '  overflow: hidden;' +
      '  -moz-box-sizing: border-box;' +
      '  -webkit-box-sizing: border-box;';

    const listMines = document.querySelectorAll('.mine');
    for(let i = 0 ; i < listMines.length ; i++) {
      listMines[i].setAttribute('style', '' + styleCellMine);
    }

    const listCellGrid = document.querySelectorAll('.cellGrid');
    for (let i = 0; i < listCellGrid.length; i += 1) {

    }


    alert("Vous avez perdu... :(");
  }




  /**
   *Method which reveal cell around the selected cell
   * @param cell
   */
   devoileGrid(cell) {
    let method = 'GET';
    let url = 'http://localhost:8080/demineur/devoileGrid?id=' + cell;
    let success = (response) => {
      DemineurComponent.devoileCase(response);
    };


    let data = DemineurComponent.getJSon(cell);
    let config = DemineurComponent.formatRequestObject(method, url, JSON.stringify(data), success);
    this.ajax(config);
  }


  /**
   * Method who apply a new style to cell which have to be reveal
   * @param list
   */
   static devoileCase(list) {

      const arrayList = DemineurComponent.responseToArray(list);

      let styleCellEmpty = '  display: inline-block;' +
        '  width: 30px;' +
        '  height: 30px;' +
        '  background-color: DarkGrey;' +
        '  color: DarkGrey;' +
        '  border : 1px;'+
        '  border-color : black;'+
        '  border-style : solid;'+
        '  line-height: 30px;' +
        '  box-sizing: border-box;' +
        '  -moz-box-sizing: border-box;' +
        '  -webkit-box-sizing: border-box;';

      let styleCellNumber = '  display: inline-block;' +
        '  width: 30px;' +
        '  height: 30px;' +
        '  background-color: DarkGrey;' +
        '  color: blue;' +
        '  border : 1px;'+
        '  border-color : black;'+
        '  text-align: center;' +
        '  border-style : solid;'+
        '  overflow: hidden;' +
        '  line-height: 30px;' +
        '  box-sizing: border-box;' +
        '  -moz-box-sizing: border-box;' +
        '  -webkit-box-sizing: border-box;';


      for (let i = 0; i < arrayList.length; i += 1) {
        let cell = document.getElementById(arrayList[i]);
        const content = cell.textContent;
        if (content === "*") break;
        else if (content === "1"
          || content === "2"
          || content === "3"
          || content === "4"
          || content === "5") cell.setAttribute('style', styleCellNumber);

        else cell.setAttribute('style', styleCellEmpty);
      }
  }



  /**
   * Method to add a "flag" on a cell choose by player
   * @param id
   */
   static addFlag(id){
    let styleFlag = '' +
      '  display: inline-block;' +
      '  text-align: center;' +
      '  width: 30px;' +
      '  height: 30px;' +
      '  background-color: blue;' +
      '  color: blue;' +
      '  border : 1px;'+
      '  border-color : black;'+
      '  border-style : solid;'+
      '  line-height: 30px;' +
      '  box-sizing: border-box;' +
      '  -moz-box-sizing: border-box;' +
      '  overflow: hidden;' +
      '  -webkit-box-sizing: border-box;';

    let cell = document.getElementById(id);
    cell.classList.add('cellFlag');

    cell.setAttribute('style', styleFlag);
    DemineurComponent.flagList.push(id);

  }




  /******************************   Method to convert   *******************************/


  /**
   *
   * Convert response in an ArrayList
   *
   * @param response
   * @returns {any[]}
   */
  static responseToArray(response) {
    response = response.replace("[", "");
    response = response.replace("]", "");
    response = response.replace(/ /g,'');
    response = response.split(',');

    let array = [];
    for (let i = 0 ; i < response.length; i += 1) {
      array.push(response[i]);
    }

    return array;
  }


  /**
   *
   * Convert ArrayList to JSon format
   *    -> use to return a grid completed by a user
   *      -> in validateGrid()
   *
   *
   * @returns {{arrayList: any[]}}
   */
  static getJSon(cell) {
    let json = {
      arrayList: []
    };

    let crtValue = cell.textContent;
    json.arrayList.push(crtValue);
    console.log(cell);


    return json;
  }


  private static validateGrid() {
    let sizeFL = DemineurComponent.flagList.length;
    let sizeML = DemineurComponent.mineList.length;
    let i;
    let res = true;

    if(sizeFL === sizeML){
      for (i = 0 ; i<sizeML;i++){
        if(DemineurComponent.flagList[i] !== DemineurComponent.mineList[i]) res=false;
      }
    }
    else res=false;

    return res;
  }


}
