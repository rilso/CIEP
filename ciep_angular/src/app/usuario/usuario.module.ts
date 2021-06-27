import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmpresaComponent } from './empresa/empresa.component';
import { PessoafisicaComponent } from './pessoafisica/pessoafisica.component';
import {MatTableModule} from '@angular/material/table';
import { UsuarioRoutingModule } from './usuario-routing.module';
import { EmpresaDetalheComponent } from './empresa-detalhe/empresa-detalhe.component';
import { PessoafisicaDetalheComponent } from './pessoafisica-detalhe/pessoafisica-detalhe.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';


@NgModule({
  declarations: [
    EmpresaComponent,
    PessoafisicaComponent,
    EmpresaDetalheComponent,
    PessoafisicaDetalheComponent,
  ],
  imports: [
    CommonModule,
    MatTableModule,
    UsuarioRoutingModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatSelectModule,
  ]
})
export class UsuarioModule { }
