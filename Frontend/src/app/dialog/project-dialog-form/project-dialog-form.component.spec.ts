import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectDialogFormComponent } from './project-dialog-form.component';

describe('ProjectDialogFormComponent', () => {
  let component: ProjectDialogFormComponent;
  let fixture: ComponentFixture<ProjectDialogFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectDialogFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectDialogFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
