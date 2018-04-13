import {Component, OnInit} from '@angular/core';
//import { NgForOf } from '@angular/common';
@Component({
  selector: 'app-sudoku',
  templateUrl: './sudoku.component.html',
  styleUrls: ['./sudoku.component.css']
})


export class SudokuComponent implements OnInit {
  constructor() {}

  ngOnInit() {
    this.getSudoku();
    this.manageButtonsEvent();
  }




  /**
   *
   *
   * Call method associate to select button : create grid ; validate grid ; display solution
   *
   */
  manageButtonsEvent() {
    let btnValidate = document.getElementById('btnValide');
    let btnSolution = document.getElementById('btnSolution');
    let btnNewGrid = document.getElementById('btnNewGrid');

    btnValidate.onclick = () => {
      confirm('Valider la grille ?');
      this.validateGrid();
    };

    btnSolution.onclick = () => {
      confirm('Afficher la solution ?');
      this.getSolution();
    };

    btnNewGrid.onclick = () => {
      confirm('Charger une nouvelle grille ?');
      this.getSudoku();
    };
  }




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
   *
   * Get back a String which contains elements of a sudoku grid which depend of selected level
   * Then call generateGird and param = String from back
   *
   *    1°)get back value of select to add on url of request
   *    2°)create object of request format to call ajax()
   *    3°)call ajax() and apply method with response of request to create a grid on frontend
   */
  getSudoku() {
    let select = document.querySelector('#levelSudoku');
    let level = select.options[select.selectedIndex].getAttribute('name'); /* -> pq indice pour tableau ?? */
    let method = 'GET';
    let url = 'http://localhost:8080/sudoku/' + level;

    let data = null;
    let success = (response) => {
      this.generateGrid(response);
    };

    let config = SudokuComponent.formatRequestObject(method, url, data, success);
    this.ajax(config);
  }



  /**
   *
   * Get back a String which contains solution of latest created sudoku grid
   * Call ajax to display it on frontend
   */
  getSolution() {
    let method = 'GET';
    let url = 'http://localhost:8080/sudoku/SolutionGrid';
    let data = null;
    let success = (response) => {
      this.generateGrid(response);
    };

    let config = SudokuComponent.formatRequestObject(method, url, data, success);
    this.ajax(config);

  }






  /********************************************************************************************/



















  /*********************************   Validation of grid   ***********************************/







  /**
   * Check if grid is complete
   *
   * @returns {boolean}
   */
  static checkGridValues() {
    let listCells = document.querySelectorAll('div.cellGrid');
    for (let i = 0; i < listCells.length; i += 1) {
      let crtValue = listCells[i].textContent;
      if (crtValue === '-' || crtValue === '0' || crtValue === '') return false;
    }

    return true;
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
  static getJSonGrid() {
    let json = {
      arrayList: []
    };

    let listCells = document.querySelectorAll('div.cellGrid');
    for (let i = 0; i < listCells.length; i += 1) {
      let crtValue = listCells[i].textContent;
      json.arrayList.push(crtValue);
    }

    return json;
  }




  /**
   *
   * Return play grid to spring boot and get back response to validate it
   *
   *
   * Check if grid is correct :
   *      - check if complete                     call method in :                   FRONT
   *      - check if equals to the solution       get back boolean value from :      BACK
   *
   */
  validateGrid() {
    let method = 'POST';
    let url = 'http://localhost:8080/sudoku/validateGrid';
    let success = (response) => {
      if (response === 'true') alert('Bien joué, vous avez réussi la grille');
      else alert('La grille est incorrecte');
    };

    let valideGrid = SudokuComponent.checkGridValues();
    if (!valideGrid) {
      alert("La grille n'est pas complète");
      return;
    }

    let data = SudokuComponent.getJSonGrid();
    let config = SudokuComponent.formatRequestObject(method, url, JSON.stringify(data), success);
    this.ajax(config);
  }







  /***************************************************************************************/


















  /*********************************   Creation of grid   *********************************/








  /**
   *
   *
   *
   *
   * Create a grid on web page -> cell contains : value or - (if cell is hide)
   *
   *
   * @param response -> from spring boot application
   *
   *
   *
   */
  generateGrid(response) {


    // If there is nothing receive from de back -> STOP
    if (response === '') return;



    // Get back values of grid to generate and select element in those which will contains the sudoku game grid
    let array = SudokuComponent.responseToArray(response);
    let container = document.getElementById('sudokuContainer');
    let fragContainer = document.createDocumentFragment();



    // Create principal cube -> the grid and associated style
    let styleCube = 'display: inline-block; position: relative;';
    let cubeContainer = SudokuComponent.createCube('cube', styleCube);
    this.cubeEvent(cubeContainer);



    // Define row and cell style
    let styleRow = 'display: block; position: relative;';

    let styleCell = '' +
      '  display: inline-block;' +
      '  width: 40px;' +
      '  height: 40px;' +
      '  background-color: white;' +
      '  line-height: 40px;' +
      '  box-sizing: border-box;' +
      '  -moz-box-sizing: border-box;' +
      '  -webkit-box-sizing: border-box;';



    // If container already contains a grid -> it's remove
    //    -> Use in function getSolution(); else there is 2 grid diplay on screen
    SudokuComponent.removeGrid(container);



    //Create 81 cell with an Id and a style ; if (Id % 9 === 0) -> it's a new line -> create a new row_Id
    let row;
    let rowNumber;



    for (let i = 0; i < 81; i += 1) {          ////  Loop to implement row_Id  ////
      if (i%9 === 0) {
        rowNumber = (i / 9);
        let rowId = 'row_' + rowNumber;
        row = SudokuComponent.createRow(rowId, styleRow);
        cubeContainer.appendChild(row);
      }

      let crt = array[i];
      let cellId = 'grid_' + i;
      let value = crt > 0 ? crt : '-';
      let cell = SudokuComponent.createCell(cellId, styleCell, value);
      cell.setAttribute('column',  '' + (i%9));
      cell.setAttribute('row',  '' + rowNumber);
      cell.classList.add('cellGrid');
      SudokuComponent.generateBorder(cell, i);

      if (crt === 0) this.setSelectableCell(cell, cubeContainer);

      row.appendChild(cell);

    }                                         ////  END loop  ////



    fragContainer.appendChild(cubeContainer);
    container.appendChild(fragContainer);

    SudokuComponent.addCrossBorder(cubeContainer);

  }







  /*****************************************************************************************/



















  /*****************************   Methods to create a grid   ******************************/







  /**
   *
   *
   * Generate border for the grid
   *
   *
   * @param cell
   * @param index
   */
  static generateBorder(cell, index) {
    let smallBorderStyle = '0.5px solid #D1D1D1';
    let bigBorderStyle = '1px solid black';

    if (index % 9 === 0) {
      cell.style.borderLeft = bigBorderStyle;
      cell.style.borderRight = smallBorderStyle;
    }

    if (index % 9 === 8) {
      cell.style.borderRight = bigBorderStyle;
    }

    if (index % 9 > 0 && index % 9 < 8) {
      cell.style.borderRight = smallBorderStyle;
    }

    if (index < 9) {
      cell.style.borderTop = bigBorderStyle;
      cell.style.borderBottom = smallBorderStyle;
    }

    if (index <= 71) {
      cell.style.borderBottom = smallBorderStyle;
    }

    if (index > 71) {
      cell.style.borderBottom = bigBorderStyle;
    }
  }




  /**
   *
   * ????????????????????????????????
   *
   *
   *
   * @param type
   * @param x
   * @param y
   * @param dimensions
   * @returns {HTMLDivElement}
   */
  static createLine(type, x, y, dimensions) {
    let line = document.createElement('div');
    line.style.backgroundColor = 'black';
    line.style.position = 'absolute';
    line.style.left = x + 'px';
    line.style.top = y + 'px';

    if (type === 'horizontal') {
      line.style.width = dimensions.width + 'px';
      line.style.height = '2px';
    }

    if (type === 'vertical') {
      line.style.height = dimensions.height + 'px';
      line.style.width = '2px';
    }

    return line;
  }




  /**
   *
   * ????????????????????????????????
   *
   * @param container
   */
  static addCrossBorder(container) {
    let dimensions = container.getBoundingClientRect();
    let gapH = dimensions.width / 3;
    let gapV = dimensions.height / 3;
    let nbcross = 2;

    for (let i = 0; i < nbcross; i += 1) {
      let lineH = this.createLine('horizontal', 0, gapH * (i + 1), dimensions);
      let lineV = this.createLine('vertical', gapV * (i + 1), 0, dimensions);

      container.appendChild(lineH);
      container.appendChild(lineV);
    }
  }




  /**
   *
   * ????????????????????????????????
   *
   * @param cell
   * @param cubeContainer
   */
  setSelectableCell(cell, cubeContainer) {
    cell.style.backgroundColor = '#E7E7E7';
    cell.classList.add('selectable');
    this.cellEvent(cell, cubeContainer);
  }




  /**
   *
   * Convert JSon response in an ArrayList
   *
   * @param response
   * @returns {any[]}
   */
  static responseToArray(response) {
    response = response.substring(1);
    response = response.split(']')[0];
    response = response.split(',');

    let array = [];
    for (let i = 0 ; i < response.length; i += 1) {
      array.push(parseInt(response[i]));
    }

    return array;
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
  static createCell(id, style, value) {
    let div = document.createElement('div');
    div.id = id;
    div.setAttribute('style', style);
    div.textContent = value;

    return div;
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
   * Define syntax -> if element container isn't empty, syntax is replace by ''
   *
   * @param container
   */
  static removeGrid(container) {
    container.innerHTML = '';
  }







  /*************************************************************************************/










  /**
   *
   * Verify validity of input number
   *
   * @param number -> put by user
   * @returns {boolean} if number is correct
   */
  static validNumber(number) {
    return !(number < 1 || number > 9);
  }



  /**
   *
   * If number is conform -> change value of cell
   *
   * @param cell
   * @param number
   */
  static updateCellValue(cell, number) {
    if (SudokuComponent.validNumber(number)) {
      if (number.indexOf('0') !== -1) cell.textContent = number.split('0')[1];
      else cell.textContent = number;
    } else {
      cell.textContent = 0;
    }
  }


  /**
   *
   * Return value and index of cell
   *
   * @returns {any}
   */
  static getInputValues() {
    let input = document.getElementById('inputSudoku');
    if (input === null) return {
      element: null,
      cell: null,
      value: null
    };

    let cell = document.getElementById(input.getAttribute('refCell'));

    return {
      element: input,
      cell: cell,
      value: input.value
    };
  }





















  /*********************** Method to change value of a cell *************************/










  /**
   *
   *  ????????????????????????????????
   *
   */
  static removeInput() {
    let input = SudokuComponent.getInputValues();
    if (input.element !== null) {
      input.element.parentNode.removeChild(input.element);
    }
  }




  /**
   *
   * ????????????????????????????????
   *
   *
   * @param cell
   * @param cubeContainer
   */
  createInput(cell, cubeContainer) {
    SudokuComponent.removeInput();

    let value = parseInt(cell.textContent);
    let left = cell.getAttribute('column') * 40;
    let top = cell.getAttribute('row') * 40;
    let input = document.createElement('input');
    input.id = 'inputSudoku';
    input.classList.add('selectable');
    input.setAttribute('refCell', cell.id);
    input.setAttribute('style', '' +
      'width: 34px;' +
      'height: 33px;' +
      'line-height: 33px;' +
      'position: absolute;' +
      'left: ' + left + 'px;' +
      'top: ' + top + 'px;');
    input.type = 'number';
    input.min = '1';
    input.max = '9';
    input.value =  value.toString();

    cubeContainer.appendChild(input);
    input.focus();
    this.inputEvent(input);
  }




  cellEvent(cell, cubeContainer) {
    cell.onclick = (e) => {
      if (e.target === cell) this.createInput(cell, cubeContainer);
    }
  }






  /**
   *
   * When there is an action on selectable cell -> remove input
   *
   * @param input
   */
  inputEvent(input) {
    input.onkeyup = (e) => {
      let input = SudokuComponent.getInputValues();
      if (input.element !== null) SudokuComponent.updateCellValue(input.cell, input.value);
    };

    input.onmouseup = (e) => {
      let input = SudokuComponent.getInputValues();
      if (input.element !== null) SudokuComponent.updateCellValue(input.cell, input.value);
    };
  }






  /**
   *
   *  When action on cube -> remove input if cell is selectable
   *
   * @param cube
   */
  cubeEvent(cube) {
    cube.onclick = (e) => {
      if (!e.target.classList.contains('selectable')) SudokuComponent.removeInput();
    }
  }







  /*****************************************************************************************/









}
