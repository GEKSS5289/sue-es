//package com.sue.test;
//
//import com.sue.Application;
//import com.sue.pojo.Stu;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
//import org.elasticsearch.search.sort.FieldSortBuilder;
//import org.elasticsearch.search.sort.SortBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.SearchResultMapper;
//import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
//import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
//import org.springframework.data.elasticsearch.core.query.*;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author sue
// * @date 2020/8/13 8:27
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//public class ESTest {
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @Test
//    public void createIndexStu(){
//        Stu stu = new Stu();
//        stu.setStuId(1001L);
//        stu.setName("bat man");
//        stu.setAge(18);
//        stu.setMoney(18.8f);
//        stu.setSign("sue");
//        stu.setDescription("I need sue");
//        IndexQuery build = new IndexQueryBuilder().withObject(stu).build();
//        elasticsearchTemplate.index(build);
//    }
//
//    @Test
//    public void deleteIndexStu(){
//        elasticsearchTemplate.deleteIndex(Stu.class);
//    }
//
//    @Test
//    public void updateStuDoc(){
//
//        Map<String,Object> sourceMap = new HashMap<>();
//        sourceMap.put("sign","sue sue sue");
//        sourceMap.put("money",88.6f);
//        sourceMap.put("age",33);
//        IndexRequest indexRequest = new IndexRequest();
//        indexRequest.source(sourceMap);
//
//        UpdateQuery updateQuery = new  UpdateQueryBuilder()
//                .withClass(Stu.class)
//                .withId("1001")
//                .withIndexRequest(indexRequest)
//                .build();
//
//        elasticsearchTemplate.update(updateQuery);
//    }
//
//    @Test
//    public void getStuDoc(){
//        GetQuery query = new GetQuery();
//        query.setId("1002");
//        Stu stu = elasticsearchTemplate.queryForObject(query,Stu.class);
//        System.out.println(stu);
//    }
//
//    @Test
//    public void deleteStuDoc(){
//        elasticsearchTemplate.delete(Stu.class,"1002");
//    }
//
//
//    @Test
//    public void searchStuDoc(){
//
//        Pageable pageable = PageRequest.of(0,10);
//
//        SearchQuery query = new NativeSearchQueryBuilder()
//                            .withQuery(QueryBuilders.matchQuery("description","sue"))
//                            .withPageable(pageable)
//                            .build();
//
//        AggregatedPage<Stu> stus = elasticsearchTemplate.queryForPage(query, Stu.class);
//        System.out.println("检索后的总分页数目为:"+stus.getTotalPages());
//        List<Stu> content = stus.getContent();
//        for(Stu s: stus){
//            System.out.println(s);
//        }
//
//    }
//
//    @Test
//    public void highlightStuDoc(){
//
//        String preTag = "<font color = 'red'>";
//        String postTag = "</font>";
//
//        Pageable pageable = PageRequest.of(0,10);
//
//        SortBuilder sortBuilder = new FieldSortBuilder("money").order(SortOrder.ASC);
//
//        SearchQuery query = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.matchQuery("description","sue"))
//                .withHighlightFields(new HighlightBuilder.Field("description").preTags(preTag).postTags(postTag))
//                .withPageable(pageable)
//                .withSort(sortBuilder)
//                .build();
//
//        AggregatedPage<Stu> stus = elasticsearchTemplate.queryForPage(query, Stu.class, new SearchResultMapper() {
//            @Override
//            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
//
//                List<Stu> stuListHightLight = new ArrayList<>();
//                SearchHits hits = searchResponse.getHits();
//
//                for(SearchHit h: hits){
//                    HighlightField highlightField = h.getHighlightFields().get("description");
//
//                    String description = highlightField.getFragments()[0].toString();
//
//                    Object stuId = (Object)h.getSourceAsMap().get("stuId");
//                    String name = (String)h.getSourceAsMap().get("name");
//                    Integer age = (Integer)h.getSourceAsMap().get("age");
//                    String sign = (String)h.getSourceAsMap().get("sign");
//                    Object money = (Object)h.getSourceAsMap().get("money");
//                    Stu stuHL = new Stu();
//                    stuHL.setDescription(description);
//                    stuHL.setStuId(Long.valueOf(stuId.toString()));
//                    stuHL.setName(name);
//                    stuHL.setAge(age);
//                    stuHL.setSign(sign);
//                    stuHL.setMoney(Float.valueOf(money.toString()));
//                    stuListHightLight.add(stuHL);
//
//                }
//
//                if(stuListHightLight.size()>0){
//                    return new AggregatedPageImpl<>((List<T>)stuListHightLight);
//                }
//
//                return null;
//            }
//        });
//
//
//        System.out.println("检索后的总分页数目为:"+stus.getTotalPages());
//        List<Stu> content = stus.getContent();
//        for(Stu s: stus){
//            System.out.println(s);
//        }
//    }
//}
