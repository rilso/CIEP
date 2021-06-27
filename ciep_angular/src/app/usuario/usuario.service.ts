import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatSnackBar} from '@angular/material/snack-bar';
import {EMPTY, Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {catchError, map} from 'rxjs/operators';
import {Empresa} from '../../model/empresa';
import {Pessoafisica} from '../../model/pessoafisica';



@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(
    private httpCliente: HttpClient,
    private snackbar: MatSnackBar
  ) {
  }

  empresa: Empresa[];

  listarEmpresasPorUsuario(): Observable<Empresa> {
    const url = `${environment.config.URL_API}/empresa/listar/`;
    const email = localStorage.getItem('email');
    return this.httpCliente.get<Empresa>(url + email).pipe(
      map((empresa) => empresa)
    );
  }

  cadastrarEmpresa(empresa: Empresa): Observable<Empresa> {
    const url = `${environment.config.URL_API}/empresa/add`;
    empresa.usuario = {
      email: localStorage.getItem('email_acesso'),
      senha: localStorage.getItem('senha_acesso')
    };
    return this.httpCliente.post<Empresa>(url, empresa).pipe(
      map(obj => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  editarEmpresa(empresa: Empresa): Observable<Empresa> {
    const url = `${environment.config.URL_API}/empresa/edit/`;
    const email = localStorage.getItem('email');
    return this.httpCliente.put<Empresa>(url + email, empresa).pipe(
      map(obj => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage('Ocorreu um erro!', true);
    return EMPTY;
  }

  showMessage(msg: string, isError: boolean = false): void {
    this.snackbar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      panelClass: isError ? ['msg-error'] : ['msg-success'],
    });
  }

  bucarEmpresaPorId(idEmp: number): Observable<Empresa> {
    const url = `${environment.config.URL_API}/empresa/`;
    return this.httpCliente.get<Empresa>(url + idEmp).pipe(
      map((empresa) => empresa),
      catchError((e) => this.errorHandler(e))
    );
  }

  listarPessoaPorUsuario(): Observable<Pessoafisica> {
    const url = `${environment.config.URL_API}/pessoaFisica/listar/`;
    const email = localStorage.getItem('email');
    return this.httpCliente.get<Pessoafisica>(url + email).pipe(
      map((pessoafisica) => pessoafisica)
    );
  }

  cadastrarPessoaFisica(pessoafisica: Pessoafisica): Observable<Pessoafisica> {
    const url = `${environment.config.URL_API}/pessoaFisica/add`;
    pessoafisica.usuario = {
      email: localStorage.getItem('email_acesso'),
      senha: localStorage.getItem('senha_acesso')
    };
    return this.httpCliente.post<Pessoafisica>(url, pessoafisica).pipe(
      map(obj => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  editarPessoaFisica(pessoafisica: Pessoafisica): Observable<Pessoafisica> {
    const url = `${environment.config.URL_API}/pessoaFisica/edit/`;
    const email = localStorage.getItem('email');
    return this.httpCliente.put<Pessoafisica>(url + email, pessoafisica).pipe(
      map(obj => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  bucarPessoaFisicaPorId(idPess: number): Observable<Pessoafisica> {
    const url = `${environment.config.URL_API}/pessoaFisica/`;
    return this.httpCliente.get<Pessoafisica>(url + idPess).pipe(
      map((pessoafisica) => pessoafisica),
      catchError((e) => this.errorHandler(e))
    );
  }

  deletarEmpresa(idEmp: number): Observable<any> {
    const url = `${environment.config.URL_API}/empresa/delete/`;
    return this.httpCliente.delete<any>(url + idEmp).pipe(
      map((obj) => obj),
      catchError( (e) => this.errorHandler(e))
    );
  }

  deletarPessoaFisica(idPess: number): Observable<any> {
    const url = `${environment.config.URL_API}/pessoaFisica/delete/`;
    return this.httpCliente.delete<any>(url + idPess).pipe(
      map((obj) => obj),
      catchError( (e) => this.errorHandler(e))
    );
  }
}

