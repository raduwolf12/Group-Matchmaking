import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProjectDialogFormComponent } from '../dialog/project-dialog-form/project-dialog-form.component';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StudentModel } from '../model/student-model';
import { Project } from '../model/project-model';

@Component({
  selector: 'app-professor-form',
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.scss'],
})
export class ProfessorFormComponent {
  projectList: Project[] = [];
  accessToken: string | null = null;
  sessionUserId: string;
  studentList: StudentModel[] = [];

  constructor(
    private http: HttpClient,
    private dialog: MatDialog) { }

  ngOnInit() {
    this.accessToken = sessionStorage.getItem('accessToken');
    this.sessionUserId = sessionStorage.getItem('userId');

    // Fetch projects from backend
    this.fetchProjects();

    this.fetchUsers();

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

  fetchUsers(): void {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.accessToken
    );
    this.http
      .get<StudentModel[]>('http://localhost:8080/users/get/students', {
        headers,
      })
      .subscribe((users) => {
        this.studentList = users;

      });
  }

  editProject() {
    this.dialog.open(ProjectDialogFormComponent, {
      data: this.projectList,
      autoFocus: false,
    });
  }
}
