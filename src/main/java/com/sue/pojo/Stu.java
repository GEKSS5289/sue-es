package com.sue.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.lang.annotation.Documented;

/**
 * @author sue
 * @date 2020/8/13 8:29
 */

@Document(indexName = "stu",type="_doc")
@Data
public class Stu {

    @Id
    private Long stuId;
    @Field(store = true,index = false)
    private String name;
    @Field(store = true)
    private Integer age;
    @Field(store = true,type = FieldType.Keyword)
    private String sign;
    @Field(store = true)
    private String description;
    @Field(store = true)
    private Float money;

}
