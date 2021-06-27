import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EmpresaComponent} from './empresa/empresa.component';
import {PessoafisicaComponent} from './pessoafisica/pessoafisica.component';
import {EmpresaDetalheComponent} from './empresa-detalhe/empresa-detalhe.component';
import {PessoafisicaDetalheComponent} from './pessoafisica-detalhe/pessoafisica-detalhe.component';




const clienteRoutes: Routes = [
  {path: 'usuario-empresa', component: EmpresaComponent},
  {path: 'usuario-pessoa', component: PessoafisicaComponent},
  {path: 'empresa-detalhe', component: EmpresaDetalheComponent},
  {path: 'pessoafisica-detalhe', component: PessoafisicaDetalheComponent},
  {path: 'empresa-detalhe/:id', component: EmpresaDetalheComponent},
  {path: 'pessoafisica-detalhe/:id', component: PessoafisicaDetalheComponent},
  ];

@NgModule({
  imports: [RouterModule.forChild(clienteRoutes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
