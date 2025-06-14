import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientPlaceComponent } from './client-place.component';

describe('ClientPlaceComponent', () => {
  let component: ClientPlaceComponent;
  let fixture: ComponentFixture<ClientPlaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientPlaceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientPlaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
