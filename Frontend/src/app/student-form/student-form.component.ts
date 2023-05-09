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

  states: string[] = [
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

  selectedStates = this.states;
  selectedProjects = this.projectList;

  selected;
  selectedProject;

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(
      this.selectedProject,
      event.previousIndex,
      event.currentIndex
    );
  }

  onKey(event) {
    console.log(event.target.value);
    this.selectedStates = this.search(event.target.value);
  }

  onKeyProject(event) {
    console.log(event.target.value);
    this.selectedProjects = this.searchProject(event.target.value);
  }
  addUser() {
    console.log(this.selected);
    this.selected = null;
  }
  addProjects() {
    console.log(this.selectedProject);
    this.selectedProject = null;
  }
  search(value: string) {
    let filter = value.toLowerCase();
    return this.states.filter((option) =>
      option.toLowerCase().includes(filter)
    );
  }
  searchProject(value: string) {
    let filter = value.toLowerCase();
    return this.projectList.filter((option) =>
      option.toLowerCase().includes(filter)
    );
  }
}
