package com.sue.test;

import com.sue.Application;
import com.sue.pojo.Stu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author sue
 * @date 2020/8/13 8:27
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ESTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void createIndexStu(){
        Stu stu = new Stu();
        stu.setStuId(1001L);
        stu.setName("bat man");
        stu.setAge(18);
        stu.setMoney(18.8f);
        stu.setSign("sue");
        stu.setDescription("I need sue");
        IndexQuery build = new IndexQueryBuilder().withObject(stu).build();
        elasticsearchTemplate.index(build);
    }

}
