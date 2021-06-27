import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from './guards/auth.guard';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home/home.component';


const appRouts: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(appRouts)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
