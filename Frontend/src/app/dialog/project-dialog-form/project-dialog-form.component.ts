import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { Project } from 'src/app/model/project-model';
import { ProjectServiceService } from 'src/app/services/project-service.service';

@Component({
  selector: 'app-project-dialog-form',
  templateUrl: './project-dialog-form.component.html',
  styleUrls: ['./project-dialog-form.component.scss'],
})
export class ProjectDialogFormComponent implements OnInit, OnDestroy {
  projectForm: FormGroup;
  projectSub: Subscription;
  sessionUserId;

  constructor(
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: Project,
    private projectService: ProjectServiceService
  ) {
    this.sessionUserId = sessionStorage.getItem('userId');

    this.projectForm = this.fb.group({
      projectName: ['', Validators.required],
      projectDescription: ['', Validators.required],
    });
    if (data) {
      this.projectForm.patchValue({
        projectName: data.title,
        projectDescription: data.description,
      });
    }
  }

  ngOnInit(): void {}

  ngOnDestroy(): void {
    if (this.projectSub) {
      this.projectSub.unsubscribe();
    }
  }

  onSubmit() {
    if (this.data) {
      this.data.title = this.projectForm.value.projectName;
      this.data.description = this.projectForm.value.projectDescription;
      this.projectSub = this.projectService.saveProject(this.data).subscribe();
    } else {
      const project: Project = {
        title: this.projectForm.value.projectName,
        owner_user_id: this.sessionUserId,
        description: this.projectForm.value.projectDescription,
      };
      this.projectSub = this.projectService.saveProject(project).subscribe();
    }
  }
}
