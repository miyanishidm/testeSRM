package br.com.testeSRM.service;

import br.com.testeSRM.entity.EnumTipoIdentificador;
import br.com.testeSRM.entity.Pessoa;
import br.com.testeSRM.repository.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa create(Pessoa pessoa) {
        var tipoIdentificador = getTipoIdentificador(pessoa);
        if(tipoIdentificador != null){
            return pessoaRepository.save(new Pessoa(pessoa.getNome(), pessoa.getIdentificador(), tipoIdentificador));
        }
        throw new InvalidDocumentException("Identificador Inválido!");
    }


    public EnumTipoIdentificador getTipoIdentificador(Pessoa pessoa) {
        if(pessoa.getIdentificador()!= null){
            if(pessoa.getIdentificador().length() == 11){
                return EnumTipoIdentificador.CPF;
            }else if(pessoa.getIdentificador().length() == 14){
                return EnumTipoIdentificador.CNPJ;
            }
        }
        return null;
    }

    public Pessoa merge(String id, Pessoa pessoa) {
        var tipoIdentificador = getTipoIdentificador(pessoa);
        if(tipoIdentificador != null){
            return pessoaRepository.findById(id).map(pessoaDB -> {
                pessoaDB.merge(pessoa, tipoIdentificador);
                pessoaRepository.save(pessoaDB);
                return pessoaDB;
            }).orElseThrow(()-> new InvalidDocumentException("Identificador não Encontrado!"));
        }
        throw new InvalidDocumentException("Identificador Inválido!");
    }


}
