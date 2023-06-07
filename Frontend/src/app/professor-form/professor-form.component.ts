import { Component, OnInit, OnDestroy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProjectDialogFormComponent } from '../dialog/project-dialog-form/project-dialog-form.component';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StudentModel } from '../model/student-model';
import { Project } from '../model/project-model';
import { Observable, Subscription } from 'rxjs';
import { StudentServiceService } from '../services/student-service.service';
import { ProjectServiceService } from '../services/project-service.service';

@Component({
  selector: 'app-professor-form',
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.scss'],
})
export class ProfessorFormComponent implements OnInit, OnDestroy {
  projectList: Project[] = [];
  sessionUserId: string;
  studentList$: Observable<StudentModel[]>;

  getProjectsSub: Subscription;

  constructor(
    private dialog: MatDialog,
    readonly studentService: StudentServiceService,
    private projectService: ProjectServiceService
  ) {
    this.sessionUserId = sessionStorage.getItem('userId');
    this.studentList$ = studentService.getStudents();
  }

  ngOnDestroy(): void {
    this.getProjectsSub.unsubscribe();
  }

  ngOnInit() {
    this.fetchProjects();
  }

  fetchProjects(): void {
    this.getProjectsSub = this.projectService
      .getProjects()
      .subscribe((projects) => {
        this.projectList = projects;
      });
  }

  addProject() {
    this.dialog.open(ProjectDialogFormComponent, {
      autoFocus: false,
    });
  }

  editProject(project: Project) {
    this.dialog.open(ProjectDialogFormComponent, {
      data: project,
      autoFocus: false,
    });
  }
}
