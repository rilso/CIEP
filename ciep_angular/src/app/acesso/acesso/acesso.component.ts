import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-acesso',
  templateUrl: './acesso.component.html',
  styleUrls: ['./acesso.component.css']
})
export class AcessoComponent implements OnInit {

  constructor(
    private router: Router
  ) {}

  ngOnInit(): void {
  }
  cadastrarEmpresa(): void {
    this.router.navigate(['/empresa-detalhe']);
  }
  cadastrarPessoa(): void {
    this.router.navigate(['/pessoafisica-detalhe']);
  }
}
