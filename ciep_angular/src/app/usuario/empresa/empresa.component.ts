import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import {Router} from '@angular/router';
import {UsuarioService} from '../usuario.service';
import {Empresa} from '../../../model/empresa';
import {AuthGuardService} from '../../guards/auth.guard.service';

@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.css']
})
export class EmpresaComponent implements OnInit {

  constructor(
    private empresaService: UsuarioService,
    private auth: AuthGuardService,
    private location: Location,
    private router: Router
  ) {
  }

  displayedColumns: string[] = ['id', 'razaoSocial', 'nomeFant', 'cnpj', 'acoes'];

  empresa: Empresa[];
  dataSource;

  ngOnInit(): void {
    this.empresaService.listarEmpresasPorUsuario().subscribe(dados => {
      this.empresa = [dados];
      this.dataSource = this.empresa;
    });
  }

  editarEmpresa(empresa: Empresa): void {
    this.router.navigate(['/empresa-detalhe', empresa.id]);
  }

  deletarEmpresa(empresa: Empresa): void {
    this.empresaService.deletarEmpresa(empresa.id).subscribe(dados => {
      this.auth.logout();
    });
  }
}



