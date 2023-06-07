import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentFormComponent } from './student-form/student-form.component';

import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { MatIconModule } from '@angular/material/icon';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatExpansionModule } from '@angular/material/expansion';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TeacherFormComponent } from './teacher-form/teacher-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProjectDialogInfoComponent } from './dialog/project-dialog-info/project-dialog-info.component';
import { ProfessorFormComponent } from './professor-form/professor-form.component';
import { ProjectDialogFormComponent } from './dialog/project-dialog-form/project-dialog-form.component';
import { LoginComponent } from './login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './auth.interceptor';
import { ProjectServiceService } from './services/project-service.service';
import { StudentServiceService } from './services/student-service.service';
import { SettingsServiceService } from './services/settings-service.service';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'student-form', component: StudentFormComponent },
  { path: 'teacher-form', component: TeacherFormComponent },
  { path: 'professor-form', component: ProfessorFormComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];

@NgModule({
  declarations: [
    AppComponent,
    StudentFormComponent,
    TeacherFormComponent,
    ProjectDialogInfoComponent,
    ProfessorFormComponent,
    ProjectDialogFormComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCardModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    DragDropModule,
    FormsModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatSnackBarModule,
    MatDialogModule,
    MatTooltipModule,
    MatExpansionModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    ProjectServiceService,
    StudentServiceService,
    SettingsServiceService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
