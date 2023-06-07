import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, OnInit, ViewChild } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatOption } from '@angular/material/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, map, startWith } from 'rxjs';
import { ProjectDialogInfoComponent } from '../dialog/project-dialog-info/project-dialog-info.component';
import { StudentModel } from '../model/student-model';
import { ProjectPreference } from '../model/project-preference-model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { GroupPreference } from '../model/group-preference-model';
import { Project } from '../model/project-model';


@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.scss'],
})
export class StudentFormComponent implements OnInit {

  projectList: Project[] = [];
  selectedProjects: Project[] = [];

  pairSize: number | null = null;
  accessToken: string | null = null;
  selectedProjectsLimit: number = 0;
  sessionUserId: string




  @ViewChild('optionRef') optionRef: MatOption;

  myForm: FormGroup;
  options: string[] = ['One', 'Two', 'Three'];
  filteredOptions: Observable<StudentModel[]>;
  selectedStudentList: StudentModel[] = [];
  isUserGrouped: boolean = false;
  studentList: StudentModel[] = [];

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private _snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {
    this.myForm = this.fb.group({
      student: [''],
    });
  }

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

    // Fetch users from backend
    this.fetchUsers();

    // Fetch pair size from backend
    this.fetchPairSize();

    this.filteredOptions = this.myForm.get('student').valueChanges.pipe(
      startWith(''),
      map((value) => this._filter(value || ''))
    );
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
        this.myForm.get('student').reset();

        // console.log(this.studentList)
      });
  }

  fetchPairSize(): void {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.accessToken
    );
    this.http
      .get<number>('http://localhost:8080/admin/settings/pairSize', {
        headers,
      })
      .subscribe((pairSize: number) => {
        this.selectedProjectsLimit = pairSize - 1;
      });
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
      }
      else {
        this.openSnackBar('You reached the group limit');
        this.myForm.get('student').setValue('');
      }

    } else {
      this.openSnackBar('Student already in your group!');
      this.myForm.get('student').setValue('');
    }
    this.optionRef.deselect();
    console.log(this.selectedStudentList)

  }


  removeStudent(id: number) {
    const index = this.selectedStudentList.findIndex(
      (student) => student.userId === id
    );

    if (index !== -1) {
      this.selectedStudentList.splice(index, 1);
    }
  }

  submitSelections() {
    console.log(this.myForm.value);
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

  // onKey(event) {
  //   console.log(event.target.value);
  //   this.filteredStudents = this.searchStudent(event.target.value);
  // }

  // onKeyProject(event) {
  //   console.log(event.target.value);
  //   this.selectedProjectss = this.searchProject(event.target.value);
  // }
  // searchStudent(value: string) {
  //   let filter = value.toLowerCase();
  //   return this.students.filter((option) =>
  //     option.toLowerCase().includes(filter)
  //   );
  // }
  // searchProject(value: string) {
  //   let filter = value.toLowerCase();
  //   return this.projectList.filter((option) =>
  //     option.toLowerCase().includes(filter)
  //   );
  // }
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
        ...this.selectedStudentList.map((student) => student.userId) // Add IDs from selectedStudentList
      ],
    };

    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.accessToken
    );

    this.http
      .post(
        'http://localhost:8080/preferences/save/project/preference',
        projectPreferences,
        {
          headers,
        }
      )
      .subscribe(() => {
        // Project preferences saved
      });

    this.http
      .post(
        'http://localhost:8080/preferences/save/group/preference',
        groupPreference,
        {
          headers,
        }
      )
      .subscribe(() => {
        // Group preference saved
      });
  }
}
