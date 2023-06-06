import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectDialogInfoComponent } from './project-dialog-info.component';

describe('ProjectDialogInfoComponent', () => {
  let component: ProjectDialogInfoComponent;
  let fixture: ComponentFixture<ProjectDialogInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectDialogInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectDialogInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
