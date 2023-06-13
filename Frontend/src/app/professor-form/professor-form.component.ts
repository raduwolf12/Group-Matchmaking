import { Component, OnInit, OnDestroy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProjectDialogFormComponent } from '../dialog/project-dialog-form/project-dialog-form.component';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StudentModel } from '../model/student-model';
import { Project } from '../model/project-model';
import { Observable, Subscription } from 'rxjs';
import { StudentServiceService } from '../services/student-service.service';
import { ProjectServiceService } from '../services/project-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SettingsServiceService } from '../services/settings-service.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-professor-form',
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.scss'],
})
export class ProfessorFormComponent implements OnInit, OnDestroy {
  projectList: Project[] = [];
  sessionUserId: string;
  studentList: StudentModel[];
  showCommandCenter: boolean = false;

  myForm: FormGroup;
  formStatus$: Observable<any>;
  formDuration: number = 0;

  finalGroupsExist: boolean = false;
  finalGroups: { students: StudentModel[]; project: Project }[] = [];

  getProjectsSub: Subscription;

  selectedFile: File | null = null;

  constructor(
    private dialog: MatDialog,
    readonly studentService: StudentServiceService,
    private projectService: ProjectServiceService,
    private settingsService: SettingsServiceService,
    private fb: FormBuilder
  ) {
    this.myForm = this.fb.group({
      pairSize: [null, Validators.required],
      groupSize: [null, Validators.required],
    });
    this.sessionUserId = sessionStorage.getItem('userId');
    this.formStatus$ = settingsService.getFormStatus();
    console.log(this.formStatus$)
    // if(this.formStatus)
  }

  ngOnDestroy(): void {
    this.getProjectsSub.unsubscribe();
  }

  ngOnInit() {
    this.fetchStudents();
    this.fetchProjects();
    this.getFinalGroups();
    this.getSettings();
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  importCSV() {
    if (this.selectedFile) {
      this.settingsService.uploadFile(this.selectedFile).subscribe();
    }
    // location.reload();
  }

  exportCSV(): void {
    const csvData: string[] = [];

    // Add CSV header
    csvData.push('Project ID,Title,Description,Owner User ID,Size,Visibility');

    // Add data for each project
    this.projectList.forEach((project) => {
      const rowData = [
        project.projectId?.toString() || '',
        project.title,
        project.description,
        project.owner_user_id?.toString() || '',
        project.size?.toString() || '',
        project.visibility ? 'true' : 'false',
      ];

      csvData.push(rowData.join(','));
    });

    const csvString = csvData.join('\n');
    const blob = new Blob([csvString], { type: 'text/csv;charset=utf-8' });
    saveAs(blob, 'projects.csv');
  }

  fetchProjects(): void {
    this.getProjectsSub = this.projectService
      .getProjects()
      .subscribe((projects) => {
        this.projectList = projects;
      });
  }

  fetchStudents() {
    this.studentService
      .getStudents()
      .subscribe((value) => (this.studentList = value));
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
    // location.reload();
  }

  deleteProject(id) {
    this.projectService.deleteProject(id).subscribe();
  }

  getSettings() {
    this.settingsService.getGroupSize().subscribe((value) =>
      this.myForm.patchValue({
        groupSize: value,
      })
    );

    this.settingsService.getPairSize().subscribe((value) =>
      this.myForm.patchValue({
        pairSize: value,
      })
    );
  }

  saveSettings() {
    this.settingsService.saveSettings(this.myForm.value).subscribe();
    // location.reload();
  }

  startForm() {
    this.settingsService
      .startForm(this.formDuration)
      .subscribe(
        () => (this.formStatus$ = this.settingsService.getFormStatus())
      );
    // location.reload();
  }
  endForm() {
    this.settingsService
      .endForm()
      .subscribe(
        () => (this.formStatus$ = this.settingsService.getFormStatus())
      );
    // location.reload();
  }
  startAlgorithm() {
    this.settingsService.startAlgorithm().subscribe();
    // location.reload();
  }

  getFinalGroups() {
    let students;
    let project;
    this.settingsService.getFinalGroups().subscribe((value) => {
      this.finalGroupsExist = value;
      console.log(this.finalGroupsExist);
      for (let item of value) {
        students = this.studentList.filter((student) =>
          item.membersId.includes(student.userId)
        );
        project = this.projectList.find(
          (value) => item.projectId == value.projectId
        );
        this.finalGroups.push({ students: students, project: project });
      }
    });
    console.log(this.finalGroups);
  }
}
