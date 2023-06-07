import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ProjectDialogInfoComponent } from '../dialog/project-dialog-info/project-dialog-info.component';
import { Project } from '../model/project-model';
import { ProjectServiceService } from '../services/project-service.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-teacher-form',
  templateUrl: './teacher-form.component.html',
  styleUrls: ['./teacher-form.component.scss'],
})
export class TeacherFormComponent implements OnInit, OnDestroy {
  projectForm: FormGroup;
  accessToken: string | null = null;
  sessionUserId: string;

  saveProjectSub: Subscription;
  getProjectSub: Subscription;

  projectList: Project[] = [];

  constructor(
    private fb: FormBuilder,
    private dialog: MatDialog,
    private projectService: ProjectServiceService
  ) {
    this.sessionUserId = sessionStorage.getItem('userId');
    this.projectForm = this.fb.group({
      projectName: ['', Validators.required],
      projectDescription: ['', Validators.required],
    });
  }

  ngOnDestroy(): void {
    this.getProjectSub.unsubscribe();
    if (this.saveProjectSub) {
      this.saveProjectSub.unsubscribe();
    }
  }

  ngOnInit() {
    this.fetchProjects();
  }

  onSubmit() {
    if (this.projectForm.valid) {
      const title = this.projectForm.value.projectName;
      const description = this.projectForm.value.projectDescription;
      const projectData: Project = {
        projectId: null,
        title: title,
        description: description,
        owner_user_id: parseInt(this.sessionUserId), // Set the owner_user_id value as needed
        size: 8, // Set the size value as needed
        visibility: true, // Set the visibility value as needed
      };
      this.saveProject(projectData);
    }
  }

  saveProject(projectData: Project) {
    this.saveProjectSub = this.projectService
      .saveProject(projectData)
      .subscribe();
  }

  fetchProjects() {
    this.getProjectSub = this.projectService
      .getProjects()
      .subscribe((projects) => {
        this.projectList = projects;
      });
  }

  openProjectInfo() {
    this.dialog.open(ProjectDialogInfoComponent, {
      data: this.projectList,
      autoFocus: false,
    });
  }
}
