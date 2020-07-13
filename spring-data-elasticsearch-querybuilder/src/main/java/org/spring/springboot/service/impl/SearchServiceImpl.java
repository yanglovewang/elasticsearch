package org.spring.springboot.service.impl;

import org.apache.commons.codec.binary.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.spring.springboot.domain.Products;
import org.spring.springboot.domain.ResultVO;
import org.spring.springboot.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Qualifier("restClient")
    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Override
    public void search2() throws IOException {
        String index = "province";
        SearchRequest searchRequest = buildSearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .matchQuery("name","zhang");
        searchSourceBuilder.query(queryBuilder);
        // 设置查询属性
        searchRequest.source(searchSourceBuilder);
        // 查询
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("hits " + hits.toString());
        System.out.println(response);
    }


    @Override
    public void search3() throws IOException {
        String index = "products";
        SearchRequest searchRequest = buildSearchRequest(index);
        // 最外层的大括号
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // query 属性
        QueryBuilder queryBuilder = QueryBuilders
                .boolQuery().must(QueryBuilders.termQuery("price", "30"))
                .filter(QueryBuilders.termQuery("avaliable", "true"))
                .mustNot(QueryBuilders.rangeQuery("price").lte(10))
                .should(QueryBuilders.termQuery("productID.keyword","JODL-X-1937-#pV7"))
                .should(QueryBuilders.termQuery("productID.keyword","XHDK-A-1293-#fJ3"));

        searchSourceBuilder.query(queryBuilder);
        // 设置查询属性
        searchRequest.source(searchSourceBuilder);
        // 查询
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

    }

    /**
     * @param scrollTimeInMillis：cursor保留时长
     * @param searchQuery：查询条件
     * @param clazz：映射实体
     * @return
     */
    public <T> List<T> scrollSearch(long scrollTimeInMillis, Query searchQuery, Class<T> clazz) {
        elasticsearchRestTemplate.searchScrollStart(scrollTimeInMillis, searchQuery, clazz, IndexCoordinates.of(""));
        return null;
    }

    public ResultVO template1() {
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder
                .must(QueryBuilders.termQuery("price", "30"))
                .filter(QueryBuilders.termQuery("avaliable", "true"))
                .mustNot(QueryBuilders.rangeQuery("price").lte(10))
                .should(QueryBuilders.termQuery("productID.keyword","JODL-X-1937-#pV7"))
                .should(QueryBuilders.termQuery("productID.keyword","XHDK-A-1293-#fJ3"));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery build = nativeSearchQueryBuilder.withQuery(booleanQueryBuilder).build();
        SearchScrollHits<Products> products = elasticsearchRestTemplate.searchScrollStart(2 * 60 * 1000, build, Products.class, IndexCoordinates.of("products"));
        ResultVO resultVO = new ResultVO();
        resultVO.setData(products);
        return resultVO;
    }

    public void bulkIndex(List<Products> devices) {
        List<IndexQuery> queries = new ArrayList<>();
        for (Products device : devices) {
            IndexQuery indexQuery = new IndexQuery();
            queries.add(indexQuery);
        }
        elasticsearchRestTemplate.bulkIndex(queries, IndexCoordinates.of("products"));
    }


    private SearchRequest buildSearchRequest(String index) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        return searchRequest;
    }
}
