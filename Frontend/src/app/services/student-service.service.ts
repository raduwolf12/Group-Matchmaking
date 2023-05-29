import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class StudentServiceService {
  constructor(private httpClient: HttpClient) {}

  getProjects() {
    return this.httpClient.get('url');
  }
}
