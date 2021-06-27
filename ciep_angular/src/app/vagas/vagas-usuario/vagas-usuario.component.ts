import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Vagas} from '../../../model/vagas';
import {VagasService} from '../vagas.service';
import {UsuarioService} from '../../usuario/usuario.service';

@Component({
  selector: 'app-vagasusuario',
  templateUrl: './vagas-usuario.component.html',
  styleUrls: ['./vagas-usuario.component.css']
})
export class VagasUsuarioComponent implements OnInit {

  constructor(
    private vagasService: VagasService,
    private usuarioService: UsuarioService,
    private router: Router
  ) { }

  displayedColumns: string[] = ['id', 'nomeEmpresa', 'escolaridade', 'descricao', 'salario', 'acoes'];

  vagas: Vagas[];

  dataSource;

  ngOnInit(): void {
    this.usuarioService.listarEmpresasPorUsuario().subscribe(obj => {
      if (obj !== null){
        this.router.navigate(['/vagas-emprego-empresa']);
      }
      this.vagasService.listarVagasPessoaFisica().subscribe(dados => {
        this.vagas = dados;
        this.dataSource = this.vagas;
      });
    });
  }
}
