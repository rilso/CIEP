import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatSnackBar} from '@angular/material/snack-bar';
import {EMPTY, Observable} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {environment} from '../../environments/environment';
import {Usuario} from '../../model/usuario';

@Injectable({
  providedIn: 'root'
})
export class CadastroService {

  constructor(
    private httpCliente: HttpClient,
    private snackbar: MatSnackBar
  ) { }

  cadastrar(usuario: Usuario): Observable<any>{
    const url = `${environment.config.URL_API}/usuario/add`;
    return this.httpCliente.post<Usuario>(url, usuario).pipe(
      map(obj => obj),
      catchError( (e) => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any>{
    this.showMessage('Ocorreu um erro!', true );
    return EMPTY;
  }

  showMessage(msg: string, isError: boolean = false): void{
    this.snackbar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      panelClass: isError ? ['msg-error'] : ['msg-success'],
    });
  }
}
