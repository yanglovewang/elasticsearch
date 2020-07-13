package org.spring.springboot.domain;

import org.elasticsearch.search.SearchHit;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "products")
public class Products  implements Serializable {

    private Integer price;

    private Boolean avaliable;

    private Date date;

    private String productID;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(Boolean avaliable) {
        this.avaliable = avaliable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "Products{" +
                "price=" + price +
                ", avaliable=" + avaliable +
                ", date=" + date +
                ", productID='" + productID + '\'' +
                '}';
    }
}
