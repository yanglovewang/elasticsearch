package org.spring.springboot.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "personindex", shards = 1, replicas = 1)
public class PersonIndex {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String firstName;

    @Field(type = FieldType.Text)
    private String lastName;

    @Field(type = FieldType.Text)
    private String desc;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Scaled_Float)
    private Float height;

    @Field(index = false, type = FieldType.Keyword)
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
