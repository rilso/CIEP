import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSliderModule} from '@angular/material/slider';
import {HttpClientModule} from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {IConfig, NgxMaskModule} from 'ngx-mask';
import {RouterModule} from '@angular/router';
import {AppRoutingModule} from './app-routing.module';
import {LoginModule} from './login/login.module';
import {UsuarioModule} from './usuario/usuario.module';
import {CadastroModule} from './cadastro/cadastro.module';
import {HomeModule} from './home/home.module';
import {AcessoModule} from './acesso/acesso.module';
import {MatTableModule} from '@angular/material/table';
import {VagasModule} from './vagas/vagas.module';



export const options: Partial<IConfig> | (() => Partial<IConfig>) = null;

@NgModule({
  declarations: [
    AppComponent,
  ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MatSliderModule,
        HttpClientModule,
        MatSnackBarModule,
        NgxMaskModule.forRoot(),
        RouterModule,
        AppRoutingModule,
        LoginModule,
        UsuarioModule,
        CadastroModule,
        HomeModule,
        AcessoModule,
        MatTableModule,
        VagasModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
