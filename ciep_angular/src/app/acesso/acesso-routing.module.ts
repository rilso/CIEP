import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {AcessoComponent} from './acesso/acesso.component';


const acessoRouts: Routes = [
  { path: 'acesso', component: AcessoComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(acessoRouts)],
  exports: [RouterModule]
})
export class AcessoRoutingModule {}
