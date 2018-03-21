import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DemineurComponent } from './demineur.component';

describe('DemineurComponent', () => {
  let component: DemineurComponent;
  let fixture: ComponentFixture<DemineurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DemineurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DemineurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
