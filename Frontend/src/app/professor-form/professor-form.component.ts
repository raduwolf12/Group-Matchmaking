import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProjectDialogFormComponent } from '../dialog/project-dialog-form/project-dialog-form.component';

@Component({
  selector: 'app-professor-form',
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.scss'],
})
export class ProfessorFormComponent {
  projectList: string[] = [
    'PROJECT1',
    'PROJECT2',
    'PROJECTT3',
    'PROJECT4',
    'PROJECT2',
    'PROJECTT3',
    'PROJECT4',
  ];

  constructor(private dialog: MatDialog) {}

  editProject() {
    this.dialog.open(ProjectDialogFormComponent, {
      data: this.projectList,
      autoFocus: false,
    });
  }
}
