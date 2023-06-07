import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SettingsServiceService {
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

  // Fetch pair size from backend
  getPairSize(): Observable<number> {
    return this.httpClient.get<number>(
      'http://localhost:8080/admin/settings/pairSize',
      {
        headers: this.headers,
      }
    );
    // .subscribe((pairSize: number) => {
    //   this.selectedProjectsLimit = pairSize - 1;
    // });
  }
}
