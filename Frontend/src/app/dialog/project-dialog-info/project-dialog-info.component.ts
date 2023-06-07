import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-project-dialog-info',
  templateUrl: './project-dialog-info.component.html',
  styleUrls: ['./project-dialog-info.component.scss'],
})
export class ProjectDialogInfoComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data) {}
}
