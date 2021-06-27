import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthGuardService} from '../guards/auth.guard.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formLogin: FormGroup;
  private formSubmitAttempt: boolean;
    constructor(
    private fb: FormBuilder,
    private authService: AuthGuardService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.formLogin = this.fb.group({
      email: ['', Validators.required],
      senha: ['', Validators.required]
    });
  }

  isFieldInvalid(field: string) {
    return (
      (!this.formLogin.get(field).valid && this.formLogin.get(field).touched) ||
      (this.formLogin.get(field).untouched && this.formSubmitAttempt)
    );
  }

  onSubmit(): void {
    if (this.formLogin.valid) {
      this.authService.login(this.formLogin.value, false);
    }
    this.formSubmitAttempt = true;
  }

  cadastrar(): void {
    this.router.navigate(['/cadastro']);
  }
}
