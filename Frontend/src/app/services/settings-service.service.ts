import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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
  }

  getFormStatus() {
    return this.httpClient.get('http://localhost:8080/form/status', {
      headers: this.headers,
      responseType: 'text',
    });
  }

  startForm(formDuration): Observable<any> {
    const formData = new FormData();
    formData.append('duration', formDuration.toString());
    return this.httpClient.post('http://localhost:8080/form/open', formData, {
      headers: this.headers,
      responseType: 'text',
    });
  }

  endForm(): Observable<any> {
    return this.httpClient.get('http://localhost:8080/form/close', {
      headers: this.headers,
      responseType: 'text',
    });
  }

  getGroupSize(): Observable<number> {
    return this.httpClient.get<number>(
      'http://localhost:8080/admin/settings/groupSize',
      {
        headers: this.headers,
      }
    );
  }

  saveSettings(params): any {
    return this.httpClient.post<number>(
      `http://localhost:8080/admin/settings?pairSize=${params.pairSize}&groupSize=${params.groupSize}`,
      {
        headers: this.headers,
      }
    );
  }

  uploadFile(selectedFile) {
    const formData = new FormData();
    formData.append('csvFile', selectedFile);
    return this.httpClient.post(
      'http://localhost:8080/api/csv/upload',
      formData
    );
  }

  startAlgorithm(): Observable<any> {
    return this.httpClient.post(`http://localhost:8080/admin/start`, {
      headers: this.headers,
    });
  }

  getFinalGroups(): Observable<any> {
    return this.httpClient.get('http://localhost:8080/admin/get/final-groups', {
      headers: this.headers,
    });
  }
}
