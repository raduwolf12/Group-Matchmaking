import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentFormComponent } from './student-form/student-form.component';
import { TeacherFormComponent } from './teacher-form/teacher-form.component';

const routes: Routes = [
  { path: 'home', component: StudentFormComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'teacher', component: TeacherFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
