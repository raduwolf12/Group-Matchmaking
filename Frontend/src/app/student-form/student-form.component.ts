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

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.scss'],
})
export class StudentFormComponent implements OnInit {
  @ViewChild('optionRef') optionRef: MatOption;

  myForm: FormGroup;
  options: string[] = ['One', 'Two', 'Three'];
  filteredOptions: Observable<StudentModel[]>;
  projectList: string[] = [
    'PROJECT1PROJECT1PROJECT1PROJECT1PROJECT1PROJECT1PROJECT1PROJECT1PROJECT1',
    'PROJECT2',
    'PROJECTT3',
    'PROJECT4',
  ];
  selectedStudentList: StudentModel[] = [];
  isUserGrouped: boolean = false;
  studentList: StudentModel[] = [
    {
      id: 1,
      studentName: 'Alabama',
    },
    {
      id: 2,
      studentName: 'Colorado',
    },
    {
      id: 3,
      studentName: 'Florida',
    },
    {
      id: 4,
      studentName: 'Georgia',
    },
  ];

  constructor(
    private fb: FormBuilder,
    private _snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {
    this.myForm = this.fb.group({
      student: [''],
    });
  }

  ngOnInit() {
    this.filteredOptions = this.myForm.get('student').valueChanges.pipe(
      startWith(''),
      map((value) => this._filter(value || ''))
    );
  }

  _filter(value: string): StudentModel[] {
    const filterValue = value.toLowerCase();
    return this.studentList.filter((option) =>
      option.studentName.toLowerCase().includes(filterValue)
    );
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.projectList, event.previousIndex, event.currentIndex);
  }

  addStudent() {
    const student = this.studentList.find(
      (value) => value.studentName === this.myForm.get('student').value
    );
    if (!student) {
      this.openSnackBar('No student selected!');
    } else if (!this.selectedStudentList.includes(student)) {
      this.selectedStudentList.push(student);
      this.myForm.get('student').reset();
    } else {
      this.openSnackBar('Student already in your group!');
      this.myForm.get('student').setValue('');
    }
    this.optionRef.deselect();
  }

  removeStudent(id: number) {
    const index = this.selectedStudentList.findIndex(
      (student) => student.id === id
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
}
