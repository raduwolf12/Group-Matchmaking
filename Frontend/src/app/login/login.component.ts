import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  email: string;
  password: string;
  errorMessage: string;

  constructor(private http: HttpClient, private router: Router) { }
  login(): void {
    const loginData = {
      email: this.email,
      password: this.password
    };

    const apiUrl = 'http://localhost:8080/auth/login';

    this.http.post(apiUrl, loginData).subscribe(
      (response: any) => {
        if (response.accessToken) {
          // Store the access token in the session
          sessionStorage.setItem('accessToken', response.accessToken);
          sessionStorage.setItem('userId', response.id);
          // Make a call to get the user role
          this.getUserRole(response.id);
        }
      },
      (error: any) => {
        this.errorMessage = error.error.message || 'Login failed. Please try again.';
      }
    );
  }

  getUserRole(userId: string): void {
    const apiUrl = `http://localhost:8080/users/get/user/${userId}`;
    const accessToken = sessionStorage.getItem('accessToken');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${accessToken}`);

    this.http.get(apiUrl, { headers }).subscribe(
      (response: any) => {
        if (response.role) {
          // Redirect based on the user role
          sessionStorage.setItem('userRole', response.role);
          switch (response.role) {
            case 'STUDENT':
              this.router.navigate(['/student-form']);
              break;
            case 'TEACHER':
              this.router.navigate(['/teacher-form']);
              break;
            case 'PROFESSOR':
              this.router.navigate(['/professor-form']);
              break;
            default:
              this.errorMessage = 'Invalid user role.';
              break;
          }
        } else {
          this.errorMessage = 'User role not found.';
        }
      },
      (error: any) => {
        this.errorMessage = error.error.message || 'Failed to get user role.';
      }
    );
  }

}
