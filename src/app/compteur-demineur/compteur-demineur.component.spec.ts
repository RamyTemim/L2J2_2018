import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompteurDemineurComponent } from './compteur-demineur.component';

describe('CompteurDemineurComponent', () => {
  let component: CompteurDemineurComponent;
  let fixture: ComponentFixture<CompteurDemineurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompteurDemineurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompteurDemineurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
