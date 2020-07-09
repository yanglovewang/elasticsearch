package org.spring.springboot.service.impl;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.domain.City;
import org.spring.springboot.repository.CityRepository;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * 城市 ES 业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityESServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityESServiceImpl.class);

    // 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
    private static final Integer pageNumber = 0;
    private static final Integer pageSize = 10;

    String SCORE_MODE_SUM = "sum"; // 权重分求和模式
    Float  MIN_SCORE = 10.0F;      // 由于无相关性的分值默认为 1 ，设置权重分最小值为 10

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    // ES 操作类
    @Autowired
    CityRepository cityRepository;

    public Long saveCity(City city) {
        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    public List<City> findByDescriptionAndScore(String description, Integer score) {
        return cityRepository.findByDescriptionAndScore(description, score);
    }

    public List<City> findByDescriptionOrScore(String description, Integer score) {
        return cityRepository.findByDescriptionOrScore(description, score);
    }

    public List<City> findByDescription(String description) {
        return null;
//        return cityRepository.findByDescription(description, pageable).getContent();
    }

    public List<City> findByDescriptionNot(String description) {
        return null;
//        return cityRepository.findByDescriptionNot(description, pageable).getContent();
    }

    public List<City> findByDescriptionLike(String description) {
        return null;
//        return cityRepository.findByDescriptionLike(description, pageable).getContent();
    }

    public void search() throws IOException {
        SearchRequest searchRequest = buildSearchRequest("province");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .boolQuery()
                .must(QueryBuilders
                        .matchQuery("score",29));
        searchSourceBuilder.query(queryBuilder);
        // 设置查询属性
        searchRequest.source(searchSourceBuilder);
        // 查询
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    public void search2() throws IOException {
        SearchRequest searchRequest = buildSearchRequest("province");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .matchQuery("name","zhang");
        searchSourceBuilder.query(queryBuilder);
        // 设置查询属性
        searchRequest.source(searchSourceBuilder);
        // 查询
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response);
    }


    private SearchRequest buildSearchRequest(String index) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        return searchRequest;
    }


}
