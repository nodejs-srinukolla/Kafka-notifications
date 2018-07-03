import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DFDComponent } from './dfd.component';

describe('DFDComponent', () => {
  let component: DFDComponent;
  let fixture: ComponentFixture<DFDComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DFDComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DFDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
