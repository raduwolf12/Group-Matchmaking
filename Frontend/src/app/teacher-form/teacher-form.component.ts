import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ProjectDialogInfoComponent } from '../dialog/project-dialog-info/project-dialog-info.component';

@Component({
  selector: 'app-teacher-form',
  templateUrl: './teacher-form.component.html',
  styleUrls: ['./teacher-form.component.scss'],
})
export class TeacherFormComponent {
  projectForm = this.fb.group({
    projectName: ['', Validators.required],
    projectOwners: ['', Validators.required],
    projectDescription: ['', Validators.required],
  });
  projectList: string[] = ['PROJECT1', 'PROJECT2', 'PROJECTT3', 'PROJECT4'];

  constructor(private fb: FormBuilder, private dialog: MatDialog) {}

  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.warn(this.projectForm.value);
  }

  openProjectInfo() {
    this.dialog.open(ProjectDialogInfoComponent, {
      data: this.projectList,
      autoFocus: false,
    });
  }
}
