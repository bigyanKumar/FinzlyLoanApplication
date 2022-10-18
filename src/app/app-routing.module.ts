import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent  } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { LoanComponent } from './loan/loan.component';

const routes: Routes = [
  {path: 'login', component : LoginComponent},
  {path: 'dashboard', component : DashboardComponent},
  {path: 'signup', component : SignupComponent},
  {path: 'loan', component : LoanComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
