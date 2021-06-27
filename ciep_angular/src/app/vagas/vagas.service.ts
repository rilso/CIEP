import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Vagas} from '../../model/vagas';
import {EMPTY, Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {catchError, map} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class VagasService {

  constructor(
    private httpCliente: HttpClient,
    private snackbar: MatSnackBar
  ) {
  }

  listarVagasEmpresas(): Observable<Vagas[]> {
    const url = `${environment.config.URL_API}/vagasEmprego/listar/`;
    const email = localStorage.getItem('email');
    return this.httpCliente.get<Vagas[]>(url + email).pipe(
      map((vagas) => vagas)
    );
  }

  bucarVagaEmpregoPorId(id: number): Observable<Vagas> {
    const url = `${environment.config.URL_API}/vagasEmprego/` ;
    return this.httpCliente.get<Vagas>(url + id).pipe(
      map((dados) => dados),
      catchError( (e) => this.errorHandler(e))
    );
  }

  cadastrarVagasEmprego(vagas: Vagas): Observable<Vagas> {
    const url = `${environment.config.URL_API}/vagasEmprego/add/`;
    const email = localStorage.getItem('email');
    return this.httpCliente.post<Vagas>(url + email, vagas).pipe(
      map(obj => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  editarVagasEmprego(vagas: Vagas): Observable<Vagas> {
    const url = `${environment.config.URL_API}/vagasEmprego/edit/`;
    const email = localStorage.getItem('email');
    return this.httpCliente.put<Vagas>(url + email, vagas).pipe(
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

  deletarVagaEmprego(idVaga: number): Observable<Vagas> {
    const url = `${environment.config.URL_API}/vagasEmprego/delete/`;
    return this.httpCliente.delete<Vagas>(url + idVaga).pipe(
      map((obj) => obj),
      catchError( (e) => this.errorHandler(e))
    );
  }

  listarVagasPessoaFisica(): Observable<Vagas[]> {
    const url = `${environment.config.URL_API}/vagasEmprego`;
    return this.httpCliente.get<Vagas[]>(url).pipe(
      map((vagas) => vagas)
    );
  }
}
