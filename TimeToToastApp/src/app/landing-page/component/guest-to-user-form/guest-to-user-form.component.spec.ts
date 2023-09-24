import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestToUserFormComponent } from './guest-to-user-form.component';

describe('GuestToUserFormComponent', () => {
  let component: GuestToUserFormComponent;
  let fixture: ComponentFixture<GuestToUserFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GuestToUserFormComponent]
    });
    fixture = TestBed.createComponent(GuestToUserFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
