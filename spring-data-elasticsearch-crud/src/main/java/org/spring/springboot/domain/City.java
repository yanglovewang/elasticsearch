package org.spring.springboot.domain;

import org.junit.Test;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 城市实体类
 * <p>
 * Created by bysocket on 03/05/2017.
 */
@Document(indexName = "province")
public class City implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 描述
     */
    @Field(analyzer = "standard")
    private String description;

    /**
     * 城市评分
     */
    private Integer score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
