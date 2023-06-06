import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-project-dialog-form',
  templateUrl: './project-dialog-form.component.html',
  styleUrls: ['./project-dialog-form.component.scss'],
})
export class ProjectDialogFormComponent {
  projectForm = this.fb.group({
    projectName: ['', Validators.required],
    projectOwners: ['', Validators.required],
    projectDescription: ['', Validators.required],
  });

  constructor(private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) public data) {
    this.projectForm.patchValue({
      projectName: data.projectName,
      projectOwners: data.projectOwners,
      projectDescription: data.projectDescription,
    });
  }

  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.warn(this.projectForm.value);
  }
}
