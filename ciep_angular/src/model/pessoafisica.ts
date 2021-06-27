import {UsuarioDao} from './usuario-dao';

export class Pessoafisica {
  id: number;
  nome: string;
  cpf: string;
  sexo: string;
  idade: number;
  descricao: string;
  usuario: UsuarioDao;
}
