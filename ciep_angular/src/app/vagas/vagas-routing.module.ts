import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {VagasempresasComponent} from './vagas-empresas/vagasempresas.component';
import {VagasDetalheComponent} from './vagas-detalhe/vagas-detalhe.component';
import {VagasUsuarioComponent} from './vagas-usuario/vagas-usuario.component';



const vagasRoutes: Routes = [
  {path: 'vagas-emprego', component: VagasDetalheComponent},
  {path: 'vagas-emprego/:id', component: VagasDetalheComponent},
  {path: 'vagas-emprego-usuario', component: VagasUsuarioComponent},
  {path: 'vagas-emprego-empresa', component: VagasempresasComponent}
];

@NgModule({
  imports: [RouterModule.forChild(vagasRoutes)],
  exports: [RouterModule]
})
export class VagasRoutingModule { }

