import { Component, OnInit } from '@angular/core';
import {UsuarioService} from '../usuario.service';
import {Location} from '@angular/common';
import {Router} from '@angular/router';
import {Pessoafisica} from '../../../model/pessoafisica';
import {AuthGuardService} from '../../guards/auth.guard.service';

@Component({
  selector: 'app-pessoafisica',
  templateUrl: './pessoafisica.component.html',
  styleUrls: ['./pessoafisica.component.css']
})
export class PessoafisicaComponent implements OnInit {

  constructor(
    private pessoafisicaService: UsuarioService,
    private location: Location,
    private router: Router,
    private auth: AuthGuardService
  ) {
  }

  displayedColumns: string[] = ['id', 'nome', 'cpf', 'sexo', 'idade', 'descricao', 'acoes'];

  pessoafisica: Pessoafisica[];

  dataSource;

  ngOnInit(): void {
    this.pessoafisicaService.listarPessoaPorUsuario().subscribe(dados => {
      this.pessoafisica = [dados];
      this.dataSource = this.pessoafisica;
      if (dados === null){
        this.router.navigate(['/usuario-empresa']);
      }
    });
  }

  editarPessoaFisica(pessoafisica: Pessoafisica): void {
    this.router.navigate(['/pessoafisica-detalhe', pessoafisica.id]);
  }

  deletarPessoaFisica(pessoaFisica: Pessoafisica): void {
    this.pessoafisicaService.deletarPessoaFisica(pessoaFisica.id).subscribe(dados => {
      this.auth.logout();
    });
  }
}
