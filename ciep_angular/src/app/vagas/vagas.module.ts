import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VagasUsuarioComponent } from './vagas-usuario/vagas-usuario.component';
import {VagasDetalheComponent} from './vagas-detalhe/vagas-detalhe.component';
import {VagasempresasComponent} from './vagas-empresas/vagasempresas.component';
import {VagasRoutingModule} from './vagas-routing.module';
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {ReactiveFormsModule} from '@angular/forms';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';



@NgModule({
  declarations: [
    VagasUsuarioComponent,
    VagasDetalheComponent,
    VagasempresasComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatSelectModule,
    VagasRoutingModule,

  ]
})
export class VagasModule { }
