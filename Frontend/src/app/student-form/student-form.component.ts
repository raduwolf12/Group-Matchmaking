import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component } from '@angular/core';

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.scss'],
})
export class StudentFormComponent {
  userList = [
    {
      name: 'User1',
    },
    {
      name: 'User2',
    },
  ];

  projectList: string[] = ['PROJECT1', 'PROJECT2', 'PROJECTT3', 'PROJECT4'];

  students: string[] = [
    'Alabama',
    'Alaska',
    'Arizona',
    'Arkansas',
    'California',
    'Colorado',
    'Connecticut',
    'Delaware',
    'Florida',
    'Georgia',
    'Hawaii',
    'Idaho',
    'Illinois',
    'Indiana',
    'Iowa',
    'Kansas',
    'Kentucky',
    'Louisiana',
    'Maine',
    'Maryland',
    'Massachusetts',
    'Michigan',
    'Minnesota',
    'Mississippi',
    'Missouri',
    'Montana',
    'Nebraska',
    'Nevada',
    'New Hampshire',
    'New Jersey',
    'New Mexico',
    'New York',
    'North Carolina',
    'North Dakota',
    'Ohio',
    'Oklahoma',
    'Oregon',
    'Pennsylvania',
    'Rhode Island',
    'South Carolina',
    'South Dakota',
    'Tennessee',
    'Texas',
    'Utah',
    'Vermont',
    'Virginia',
    'Washington',
    'West Virginia',
    'Wisconsin',
    'Wyoming',
  ];

  filteredStudents = this.students;
  // selectedProjects = this.projectList;

  selectedStudent;
  selectedStudentList = [];
  selectedProjects;

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(
      this.selectedProjects,
      event.previousIndex,
      event.currentIndex
    );
  }

  onKey(event) {
    console.log(event.target.value);
    this.filteredStudents = this.searchStudent(event.target.value);
  }

  // onKeyProject(event) {
  //   console.log(event.target.value);
  //   this.selectedProjectss = this.searchProject(event.target.value);
  // }
  addStudent() {
    console.log(this.selectedStudent);
    this.selectedStudentList.push(this.selectedStudent);
    this.selectedStudent = null;
  }
  addProjects() {
    console.log(this.selectedProjects);
    // this.selectedProjects = null;
  }
  searchStudent(value: string) {
    let filter = value.toLowerCase();
    return this.students.filter((option) =>
      option.toLowerCase().includes(filter)
    );
  }
  // searchProject(value: string) {
  //   let filter = value.toLowerCase();
  //   return this.projectList.filter((option) =>
  //     option.toLowerCase().includes(filter)
  //   );
  // }
}
