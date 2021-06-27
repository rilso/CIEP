import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroupDirective, NgForm, Validators, FormGroup, FormBuilder} from '@angular/forms';
import {DateAdapter, ErrorStateMatcher, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS, MAT_MOMENT_DATE_FORMATS, MomentDateAdapter} from '@angular/material-moment-adapter';
import {UsuarioService} from '../usuario.service';
import {Empresa} from '../../../model/empresa';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {AuthGuardService} from '../../guards/auth.guard.service';
import {UsuarioDao} from '../../../model/usuario-dao';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
@Component({
  selector: 'app-empresa-detalhe',
  templateUrl: './empresa-detalhe.component.html',
  styleUrls: ['./empresa-detalhe.component.css'],

  providers: [
    // The locale would typically be provided on the root module of your application. We do it at
    // the component level here, due to limitations of our example generation script.
    {provide: MAT_DATE_LOCALE, useValue: 'pt-BR'},

    // `MomentDateAdapter` and `MAT_MOMENT_DATE_FORMATS` can be automatically provided by importing
    // `MatMomentDateModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
  ],
})
export class EmpresaDetalheComponent implements OnInit, ErrorStateMatcher {

  constructor(private empresaService: UsuarioService,
              private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthGuardService
  ) {
  }

  empresa: Empresa;

  usuarioDao: UsuarioDao;

  formEmpresa: FormGroup;

  matcher = new MyErrorStateMatcher();

  inscricao: Subscription;

  ngOnInit(): void {
    this.inscricao = this.route.params.subscribe(
      (params: Params) => {
        const id: number = +params.id;
        if (id) {
          this.empresaService.bucarEmpresaPorId(id).subscribe(dados => {
            this.empresa = dados;
            this.formEmpresa = this.fb.group({
              id: [this.empresa.id],
              razaoSocial: [this.empresa.razaoSocial, [Validators.required, Validators.minLength(3)]],
              nomeFant: [this.empresa.nomeFant, [Validators.required]],
              cnpj: [this.empresa.cnpj, Validators.required],
            });
          }, error => {
            console.error(error);
          });
        } else {
          this.usuarioDao = {
            email: '',
            senha: '',
          };
          this.empresa = {
            id: null,
            razaoSocial: '',
            nomeFant: '',
            cnpj: '',
            usuario: this.usuarioDao
          };
          this.formEmpresa = this.fb.group({     // {5}
            id: [this.empresa.id],
            razaoSocial: [this.empresa.razaoSocial, Validators.required],
            cnpj: [this.empresa.cnpj, Validators.required],
            nomeFant: [this.empresa.nomeFant, [Validators.required]],
          });
        }
      });
  }

  onSubmit(): void {
    this.empresa = this.formEmpresa.value;
    if (this.empresa.id === null) {
      this.empresaService.cadastrarEmpresa(this.empresa).subscribe(() => {
        this.empresaService.showMessage('Empresa salvo com sucesso', false);
        this.usuarioDao = {
          email: localStorage.getItem('email_acesso'),
          senha: localStorage.getItem('senha_acesso')
        };
        this.authService.login(this.usuarioDao, false);
      });
    } else {
      this.empresaService.editarEmpresa(this.empresa).subscribe(() => {
        this.empresaService.showMessage('Empresa atualizado com sucesso', false);
      });
      this.router.navigate(['/usuario-empresa']).then(() => {
        window.location.reload();
      });
    }
  }

  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    return false;
  }

  get getControl() {
    return this.formEmpresa.controls;
  }

  voltar(): void {
    this.router.navigate(['/usuario-empresa']);
  }
}
