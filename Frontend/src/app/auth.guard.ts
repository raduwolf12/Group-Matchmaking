import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const accessToken = sessionStorage.getItem('accessToken');
    const userRole = sessionStorage.getItem('userRole');

    if (accessToken) {
      console.log(userRole)
      if (userRole == 'TEACHER') {
        // User has the teacher role, allow access to teacher-form
        if (state.url.includes('teacher-form')) {
          return true;
        } else {
          // Redirect to home if user doesn't have permission for other pages
          this.router.navigate(['/teacher-form']);
          return false;
        }
      } else if (userRole == 'STUDENT') {
        // User has the student role, allow access to all routes except teacher-form
        if (state.url.includes('student-form')) {
          return true;
        } else {
          this.router.navigate(['/student-form']);
          return false;
        }
      } else if (userRole == 'PROFESSOR') {
        // User has the student role, allow access to all routes except teacher-form
        if (state.url.includes('professor-form')) {
          return true;
        } else {
          this.router.navigate(['/professor-form']);
          return false;
        }
      } else {
        // Unknown role, redirect to login page
        this.router.navigate(['/login']);
        return false;
      }
    } else {
      // User is not logged in, redirect to login page
      this.router.navigate(['/login']);
      return false;
    }
  }

}
