package br.com.unialfa.ciep.service;


import br.com.unialfa.ciep.domain.Endereco;
import br.com.unialfa.ciep.repository.EnderecoRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/endereco")

public class EnderecoController {

    private final EnderecoRepository enderecoRepository;

    public EnderecoController(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @PostMapping(path = "/add")
    public void cadastrarCliente( @RequestBody Endereco endereco ){
        enderecoRepository.save(endereco);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Endereco> listarEndereco(){
        return enderecoRepository.findAll();
    }

    @PutMapping(path= "/edit")
    public void editarEdereco(@RequestBody Endereco endereco){
        enderecoRepository.save(endereco);
    }

}
