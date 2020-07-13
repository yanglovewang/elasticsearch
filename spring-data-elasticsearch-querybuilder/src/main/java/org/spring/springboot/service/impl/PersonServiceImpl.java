package org.spring.springboot.service.impl;

import org.spring.springboot.domain.PersonIndex;
import org.spring.springboot.repository.PersonEsReposity;
import org.spring.springboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    PersonEsReposity personEsReposity;


    @Override
    public Iterable<PersonIndex> persionSearch1() {
        Iterable<PersonIndex> all = personEsReposity.findAll();
        return all;
    }

    @Override
    public PersonIndex persionSearch2() {
        Optional<PersonIndex> ps = personEsReposity.findById("1");
        PersonIndex personIndex = ps.get();
        return personIndex;
    }
}
