import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Project } from '../model/project-model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProjectServiceService {
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

  // Fetch projects from backend
  getProjects(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(
      'http://localhost:8080/projects/get/projects',
      {
        headers: this.headers,
      }
    );
  }

  saveProject(projectData: Project) {
    return this.httpClient.post(
      'http://localhost:8080/projects/save/project',
      projectData,
      { headers: this.headers }
    );
  }
}
