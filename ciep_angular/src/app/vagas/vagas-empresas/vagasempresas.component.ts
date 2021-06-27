import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {VagasService} from '../vagas.service';
import {FormBuilder} from '@angular/forms';
import {Vagas} from '../../../model/vagas';


@Component({
  selector: 'app-vagas',
  templateUrl: './vagasempresas.component.html',
  styleUrls: ['./vagasempresas.component.css']
})
export class VagasempresasComponent implements OnInit {

  constructor(private vagasempregoService: VagasService,
              private fb: FormBuilder,
              private router: Router,
  ) {
  }

  displayedColumns: string[] = ['id', 'nomeEmpresa', 'escolaridade', 'descricao', 'salario', 'acoes'];
  vagas: Vagas[];
  dataSource;

  ngOnInit(): void {
    this.vagasempregoService.listarVagasEmpresas().subscribe( dados => {
      this.vagas = dados;
      this.dataSource = this.vagas;
    });
  }
  editarVagasEmprego(vaga: Vagas): void {
    this.router.navigate(['/vagas-emprego', vaga.id]);
  }

  cadastrarVagasEmprego(): void {
    this.router.navigate(['/vagas-emprego']);
  }

  deletarVagaEmprego(vaga: Vagas): void {
    this.vagasempregoService.deletarVagaEmprego(vaga.id).subscribe(dados => {
      this.router.navigate(['/vagas-emprego-empresa'])
        .then(() => {
          window.location.reload();
        });
    });
  }
}
