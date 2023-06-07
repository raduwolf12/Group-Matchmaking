import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private router: Router, private snackBar: MatSnackBar) { }


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      tap(
        () => { },
        (error) => {
          if (error.status === 401) {
            // Unauthorized error, token expired or invalid
            sessionStorage.removeItem('accessToken');
            sessionStorage.removeItem('userRole');
            sessionStorage.removeItem('userId');
            this.router.navigate(['/login']);
            this.snackBar.open('Your session has expired. Please log in again.', 'OK', {
              duration: 5000,
            });
          }
        }
      )
    );
  }
}
