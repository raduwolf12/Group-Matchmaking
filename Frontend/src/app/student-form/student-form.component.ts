import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatOption } from '@angular/material/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, Subscription, map, startWith } from 'rxjs';
import { ProjectDialogInfoComponent } from '../dialog/project-dialog-info/project-dialog-info.component';
import { StudentModel } from '../model/student-model';
import { ProjectPreference } from '../model/project-preference-model';

import { GroupPreference } from '../model/group-preference-model';
import { Project } from '../model/project-model';
import { StudentServiceService } from '../services/student-service.service';
import { ProjectServiceService } from '../services/project-service.service';
import { SettingsServiceService } from '../services/settings-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.scss'],
})
export class StudentFormComponent implements OnInit, OnDestroy {
  projectList: Project[] = [];
  selectedProjects: Project[] = [];

  pairSize: number | null = null;
  selectedProjectsLimit: number = 0;
  sessionUserId: string;

  @ViewChild('optionRef') optionRef: MatOption;

  myForm: FormGroup;
  filteredOptions: Observable<StudentModel[]>;
  selectedStudentList: StudentModel[] = [];
  isUserPaired: boolean = false;
  studentList: StudentModel[] = [];
  groupedStudents: StudentModel[] = [];
  projectPrefList: Project[] = [];
  private studentSub: Subscription;
  private projectSub: Subscription;
  private projecPrefSub: Subscription;
  private groupPrefSub: Subscription;
  private pairSizeSub: Subscription;

  constructor(
    private fb: FormBuilder,
    private _snackBar: MatSnackBar,
    private dialog: MatDialog,
    private studentService: StudentServiceService,
    private projectService: ProjectServiceService,
    private settingsService: SettingsServiceService,
    private router: Router
  ) {
    this.myForm = this.fb.group({
      student: [''],
    });
    this.sessionUserId = sessionStorage.getItem('userId');
    const state = this.router.getCurrentNavigation()?.extras?.state;
    console.log(state);

    if (state && state['data']) {
      const userData: StudentModel = state['data'];
      this.isUserPaired = userData.isUserPaired ? userData.isUserPaired : false;
    }
  }

  ngOnInit() {
    this.fetchStudents();
    this.fetchProjects();
    this.getPairSize();
    if (this.isUserPaired) {
      this.getPreferences();
    }
  }

  ngOnDestroy() {
    this.studentSub.unsubscribe();
    this.projectSub.unsubscribe();
    this.pairSizeSub.unsubscribe();
    if (this.groupPrefSub) {
      this.groupPrefSub.unsubscribe();
    }
    if (this.projecPrefSub) {
      this.projecPrefSub.unsubscribe();
    }
  }

  _filter(value: string): StudentModel[] {
    const filterValue = value.toLowerCase();
    return this.studentList.filter((option) =>
      option.name.toLowerCase().includes(filterValue)
    );
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.projectList, event.previousIndex, event.currentIndex);
  }

  addStudent() {
    const student = this.studentList.find(
      (value) => value.name === this.myForm.get('student').value
    );
    if (!student) {
      this.openSnackBar('No student selected!');
    } else if (!this.selectedStudentList.includes(student)) {
      if (this.selectedStudentList.length < this.selectedProjectsLimit) {
        this.selectedStudentList.push(student);
        this.myForm.get('student').reset();
      } else {
        this.openSnackBar('You reached the group limit');
        this.myForm.get('student').setValue('');
      }
    } else {
      this.openSnackBar('Student already in your group!');
      this.myForm.get('student').setValue('');
    }
    this.optionRef.deselect();
  }

  removeStudent(id: number) {
    const index = this.selectedStudentList.findIndex(
      (student) => student.userId === id
    );

    if (index !== -1) {
      this.selectedStudentList.splice(index, 1);
    }
  }

  openSnackBar(text: string) {
    this._snackBar.open(text, 'OK', {
      verticalPosition: 'top',
      duration: 3000,
    });
  }

  openProjectInfo() {
    this.dialog.open(ProjectDialogInfoComponent, {
      data: this.projectList,
      autoFocus: false,
    });
  }

  fetchStudents() {
    this.studentSub = this.studentService
      .getStudents()
      .subscribe((students: StudentModel[]) => {
        this.studentList = students;
        this.filteredOptions = this.myForm.get('student').valueChanges.pipe(
          startWith(''),
          map((value) => this._filter(value || ''))
        );
      });
  }

  fetchProjects() {
    this.projectSub = this.projectService
      .getProjects()
      .subscribe((projects: Project[]) => {
        this.projectList = projects;
      });
  }

  getPairSize() {
    this.pairSizeSub = this.settingsService
      .getPairSize()
      .subscribe((pairSize: number) => {
        this.selectedProjectsLimit = pairSize - 1;
      });
  }

  getPreferences() {
    console.log(this.sessionUserId);
    this.studentService
      .getGroupPreference(this.sessionUserId)
      .subscribe((value) => {
        for (let userId of value['mates']) {
          let mate = this.studentList.find(
            (student) => student.userId === userId
          );
          this.groupedStudents.push(mate);
        }
      });

    this.studentService
      .getProjectPreference(this.sessionUserId)
      .subscribe((value) => {
        for (let projectPref of value) {
          let project = this.projectList.find(
            (p) => p.projectId == projectPref.projectId
          );
          this.projectPrefList.push(project);
        }
      });
  }

  leaveParty() {
    this.studentService
      .leaveParty(this.sessionUserId)
      .subscribe(() => (this.isUserPaired = false));
  }

  savePreferences(): void {
    const projectPreferences: ProjectPreference[] = this.projectList.map(
      (project, index) => ({
        projectPreferenceId: null,
        userId: parseInt(this.sessionUserId),
        rank: index + 1,
        projectId: project.projectId,
      })
    );

    const groupPreference: GroupPreference = {
      groupOwner: parseInt(this.sessionUserId),
      mates: [
        parseInt(this.sessionUserId),
        ...this.selectedStudentList.map((student) => student.userId), // Add IDs from selectedStudentList
      ],
    };

    this.projecPrefSub = this.studentService
      .saveProjectPreference(projectPreferences)
      .subscribe();
    this.groupPrefSub = this.studentService
      .saveGroupPreference(groupPreference)
      .subscribe(() => this.router.navigate(['login']));
  }
}
