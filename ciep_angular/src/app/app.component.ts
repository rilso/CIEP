import {Component, OnInit} from '@angular/core';
import {AuthGuardService} from './guards/auth.guard.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  isLoggedIn$: Observable<boolean>;

  constructor(private authService: AuthGuardService) { }

  ngOnInit(): void {
    this.isLoggedIn$ = this.authService.isLoggedIn;
  }

  onLogout(): void {
    this.authService.logout();
  }
  isLoggedIn(): boolean {
    if (localStorage.getItem('email') === null){
      return false;
    }
    return true;
  }
}

