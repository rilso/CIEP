import { Component, OnInit } from '@angular/core';
import {DateAdapter, ErrorStateMatcher, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS, MAT_MOMENT_DATE_FORMATS, MomentDateAdapter} from '@angular/material-moment-adapter';
import {UsuarioService} from '../usuario.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {Pessoafisica} from '../../../model/pessoafisica';
import {AuthGuardService} from '../../guards/auth.guard.service';
import {UsuarioDao} from '../../../model/usuario-dao';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
@Component({
  selector: 'app-pessoafisica-detalhe',
  templateUrl: './pessoafisica-detalhe.component.html',
  styleUrls: ['./pessoafisica-detalhe.component.css'],

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

export class PessoafisicaDetalheComponent implements OnInit {

  constructor(private pessoafisicaService: UsuarioService,
              private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthGuardService
  ) {
  }

  select_sexo = [
    {sexo: 'Masculino', nome: 'Masculino'},
    {sexo: 'Feminino', nome: 'Feminino'},
    {sexo: 'Outros', nome: 'Outros'}
  ];

  pessoafisica: Pessoafisica;

  usuarioDao: UsuarioDao;

  formPessoa: FormGroup;

  matcher = new MyErrorStateMatcher();

  inscricao: Subscription;

  ngOnInit(): void {
    this.inscricao = this.route.params.subscribe(
      (params: Params) => {
        const id: number = +params.id;
        if (id) {
          this.pessoafisicaService.bucarPessoaFisicaPorId(id).subscribe(dados => {
            console.log(dados);
            this.pessoafisica = dados;
            this.formPessoa = this.fb.group({
              id: [this.pessoafisica.id],
              nome: [this.pessoafisica.nome, [Validators.required, Validators.minLength(3)]],
              cpf: [this.pessoafisica.cpf, Validators.required],
              sexo: [this.pessoafisica.sexo, Validators.required],
              idade: [this.pessoafisica.idade, Validators.required],
              descricao: [this.pessoafisica.descricao, Validators.required],
            });
          }, error => {
            console.error(error);
          });
        } else {
          this.usuarioDao = {
            email: '',
            senha: '',
          };
          this.pessoafisica = {
            id: null,
            nome: '',
            cpf: '',
            sexo: '',
            idade:  null,
            usuario: this.usuarioDao,
            descricao: ''
          };
          this.formPessoa = this.fb.group({     // {5}
            id: [this.pessoafisica.id],
            nome: [this.pessoafisica.nome, Validators.required],
            cpf: [this.pessoafisica.cpf, Validators.required],
            sexo: [this.pessoafisica.sexo, Validators.required],
            idade: [this.pessoafisica.idade, Validators.required],
            descricao: [this.pessoafisica.descricao, Validators.required],
          });
        }
      });
  }

  onSubmit(): void {
    this.pessoafisica = this.formPessoa.value;
    if (this.pessoafisica.id === null) {
      this.pessoafisicaService.cadastrarPessoaFisica(this.pessoafisica).subscribe(() => {
        this.pessoafisicaService.showMessage('Pessoa fisica salvo com sucesso', false);
        this.usuarioDao = {
          email: localStorage.getItem('email_acesso'),
          senha: localStorage.getItem('senha_acesso')
        };
        this.authService.login(this.usuarioDao, false);
      });
    } else {
      this.pessoafisicaService.editarPessoaFisica(this.pessoafisica).subscribe(() => {
        this.pessoafisicaService.showMessage('Pessoa fisica atualizada com sucesso', false);
      });
      this.router.navigate(['/usuario-pessoa']).then(() => {
        window.location.reload();
      });
    }
  }

  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    return false;
  }

  get getControl(){
    return this.formPessoa.controls;
  }
  voltar(): void {this.router.navigate(['/usuario-pessoa']);
  }
}


