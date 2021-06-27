import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AcessoComponent} from './acesso/acesso.component';
import {MatButtonModule} from '@angular/material/button';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { AcessoRoutingModule } from './acesso-routing.module';




@NgModule({
  declarations: [
    AcessoComponent
  ],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatCardModule,
    MatTableModule,
    MatInputModule,
    MatDatepickerModule,
    AcessoRoutingModule,
  ]
})
export class AcessoModule { }
