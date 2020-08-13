package com.sue.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author sue
 * @date 2020/8/13 8:29
 */

@Document(indexName = "sue-ik-items",type="doc",createIndex = false)
@Data
public class Items {
    @Id
    @Field(store = true,type = FieldType.Text,index = false)
    private String itemId;
    @Field(store = true,index = true,type = FieldType.Text)
    private String itemName;
    @Field(store=true,type = FieldType.Text,index = false)
    private String imgUrl;
    @Field(store = true,type = FieldType.Integer)
    private Integer price;
    @Field(store = true,type = FieldType.Integer)
    private Integer sellCounts;
}
