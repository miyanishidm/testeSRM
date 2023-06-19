package br.com.testeSRM.repository;

import br.com.testeSRM.entity.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String> {

}
