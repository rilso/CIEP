import {UsuarioDao} from './usuario-dao';

export class Empresa {
  id: number;
  razaoSocial: string;
  nomeFant: string;
  cnpj: string;
  usuario: UsuarioDao;
}
