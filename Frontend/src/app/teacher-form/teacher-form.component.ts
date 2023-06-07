import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ProjectDialogInfoComponent } from '../dialog/project-dialog-info/project-dialog-info.component';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Project } from '../model/project-model';

@Component({
  selector: 'app-teacher-form',
  templateUrl: './teacher-form.component.html',
  styleUrls: ['./teacher-form.component.scss'],
})
export class TeacherFormComponent {
  projectForm = this.fb.group({
    projectName: ['', Validators.required],
    projectDescription: ['', Validators.required],
  });
  accessToken: string | null = null;
  sessionUserId: string

  projectList: Project[] = [];

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private dialog: MatDialog) { }

  ngOnInit() {
    this.accessToken = sessionStorage.getItem('accessToken');
    this.sessionUserId = sessionStorage.getItem('userId');

    // Check if the accessToken exists
    if (this.accessToken) {
      // Use the accessToken for authenticated API calls or other purposes
      console.log('Access Token:', this.accessToken);
    } else {
      // Handle the case when the accessToken is not found
      console.log('Access Token not found');
    }
    // Fetch projects from backend
    this.fetchProjects();
  }

  onSubmit() {
    if (this.projectForm.valid) {
      const title = this.projectForm.value.projectName;
      const description = this.projectForm.value.projectDescription;

      const projectData = {
        projectId: null,
        title: title,
        description: description,
        owner_user_id: this.sessionUserId, // Set the owner_user_id value as needed
        size: 8, // Set the size value as needed
        visibility: true, // Set the visibility value as needed
      };

      const headers = new HttpHeaders().set(
        'Authorization',
        'Bearer ' + this.accessToken // Replace this.accessToken with the actual access token
      );

      this.http.post('http://localhost:8080/projects/save/project', projectData, { headers })
        .subscribe(
          (response) => {
            console.log('Project saved successfully');
          },
          (error) => {
            console.log('Error saving project:', error);
          }
        );
    }
  }

  fetchProjects(): void {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.accessToken
    );
    this.http
      .get<Project[]>('http://localhost:8080/projects/get/projects', {
        headers,
      })
      .subscribe(
        (projects) => {
          this.projectList = projects;
        }
      );
  }

  openProjectInfo() {
    this.dialog.open(ProjectDialogInfoComponent, {
      data: this.projectList,
      autoFocus: false,
    });
  }
}
