package org.spring.springboot.repository;

import org.spring.springboot.domain.PersonIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface PersonEsReposity extends ElasticsearchRepository<PersonIndex, String> {

    Optional<PersonIndex> findById(String id);
}
