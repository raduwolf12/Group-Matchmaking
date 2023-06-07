import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentFormComponent } from './student-form/student-form.component';
import { TeacherFormComponent } from './teacher-form/teacher-form.component';
import { ProfessorFormComponent } from './professor-form/professor-form.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  // { path: 'home', component: StudentFormComponent },
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  // { path: 'teacher', component: TeacherFormComponent },
  // { path: 'professor', component: ProfessorFormComponent },

  { path: 'login', component: LoginComponent },
  { path: 'student-form', component: StudentFormComponent, canActivate: [AuthGuard] },
  { path: 'teacher-form', component: TeacherFormComponent, canActivate: [AuthGuard] },
  { path: 'professor-form', component: ProfessorFormComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: 'student-form' } // Redirect any other route to home

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
