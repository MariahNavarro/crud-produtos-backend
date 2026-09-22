package com.projeto.fullstack.repository;

import com.projeto.fullstack.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
}
