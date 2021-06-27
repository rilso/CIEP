import {CadastroComponent} from './cadastro.component';
import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';


const cadastroRouts: Routes = [
  { path: 'cadastro', component: CadastroComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(cadastroRouts)],
  exports: [RouterModule]
})
export class CadastroRoutingModule { }
