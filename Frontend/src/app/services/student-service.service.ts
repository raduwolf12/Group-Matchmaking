import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StudentModel } from '../model/student-model';
import { ProjectPreference } from '../model/project-preference-model';
import { GroupPreference } from '../model/group-preference-model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StudentServiceService {
  accessToken: string | null = null;
  sessionUserId: string;
  headers: HttpHeaders;

  constructor(private httpClient: HttpClient) {
    this.accessToken = sessionStorage.getItem('accessToken');
    this.sessionUserId = sessionStorage.getItem('userId');
    this.headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.accessToken
    );
  }

  // Fetch users from backend
  getStudents(): Observable<StudentModel[]> {
    return this.httpClient.get<StudentModel[]>(
      'http://localhost:8080/users/get/students',
      {
        headers: this.headers,
      }
    );
  }

  saveProjectPreference(projectPreferences: ProjectPreference[]) {
    return this.httpClient.post(
      'http://localhost:8080/preferences/save/project/preference',
      projectPreferences,
      {
        headers: this.headers,
      }
    );
  }

  saveGroupPreference(groupPreference: GroupPreference) {
    return this.httpClient.post(
      'http://localhost:8080/preferences/save/group/preference',
      groupPreference,
      {
        headers: this.headers,
      }
    );
  }
}
