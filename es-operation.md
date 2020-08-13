# 📍索引操作
## ❤集群健康
    GET     /_cluster/health
## 🔨创建索引
    PUT     /index_test
    {
        "settings": {
            "index": {
                "number_of_shards": "2",
                "number_of_replicas": "0"
            }
        }
    }
## 🔍查看索引
    GET     _cat/indices?v
## 🗑删除索引
    DELETE      /index_test
## 🔨创建索引的同时创建mappings
    PUT     /index_str
    {
        "mappings": {
            "properties": {
                "realname": {
                	"type": "text",
                	"index": true
                },
                "username": {
                	"type": "keyword",
                	"index": false
                }
            }
        }
    }
## 🔍查看分词效果
    GET         /index_mapping/_analyze
    {
    	"field": "realname",
    	"text": "imooc is good"
    }
## ✏尝试修改
    POST        /index_str/_mapping
    {
        "properties": {
            "name": {
            	   "type": "long"
            }
        }
    }
## 🔨为已存在的索引创建或创建mapping
    POST        /index_str/_mapping
    {
        "properties": {
            "id": {
            	"type": "long"
            },
            "age": {
            	"type": "integer"
            },
            "nickname": {
                "type": "keyword"
            },
            "money1": {
                "type": "float"
            },
            "money2": {
                "type": "double"
            },
            "sex": {
                "type": "byte"
            },
            "score": {
                "type": "short"
            },
            "is_teenager": {
                "type": "boolean"
            },
            "birthday": {
                "type": "date"
            },
            "relationship": {
                "type": "object"
            }
        }
    }
    注：某个属性一旦被建立，就不能修改了，但是可以新增额外属性
## 📙主要数据属性
    text, keyword, string
    long, integer, short, byte
    double, float
    boolean
    date
    object
    数组不能混，类型一致
## 🉐字符串
    text：文字类需要被分词被倒排索引的内容，比如商品名称，商品详情，商品介绍，使用text。
    keyword：不会被分词，不会被倒排索引，直接匹配搜索，比如订单状态，用户qq，微信号，手机号等，这些精确匹配，无需分词。
## 📃添加文档数据
    POST /my_doc/_doc/1 -> {索引名}/_doc/{索引ID}（是指索引在es中的id，而不是这条记录的id，比如记录的id从数据库来是1001，并不是这个。如果不写，则自动生成一个字符串。建议和数据id保持一致> ）
    
    {
        "id": 1001,
        "name": "imooc-1",
        "desc": "imooc is very good, 慕课网非常牛！",
        "create_date": "2019-12-24"
    }
    
    {
        "id": 1002,
        "name": "imooc-2",
        "desc": "imooc is fashion, 慕课网非常时尚！",
        "create_date": "2019-12-25"
    }
    
    {
        "id": 1003,
        "name": "imooc-3",
        "desc": "imooc is niubility, 慕课网很好很强大！",
        "create_date": "2019-12-26"
    }
    
    {
        "id": 1004,
        "name": "imooc-4",
        "desc": "imooc is good~！",
        "create_date": "2019-12-27"
    }
    
    {
        "id": 1005,
        "name": "imooc-5",
        "desc": "慕课网 is 强大！",
        "create_date": "2019-12-28"
    }
    
    {
        "id": 1006,
        "name": "imooc-6",
        "desc": "慕课是一个强大网站！",
        "create_date": "2019-12-29"
    }
    
    {
        "id": 1007,
        "name": "imooc-7",
        "desc": "慕课网是很牛网站！",
        "create_date": "2019-12-30"
    }
    
    {
        "id": 1008,
        "name": "imooc-8",
        "desc": "慕课网是很好看！",
        "create_date": "2019-12-31"
    }
    
    {
        "id": 1009,
        "name": "imooc-9",
        "desc": "在慕课网学习很久！",
        "create_date": "2020-01-01"
    }
    ⛑注：如果索引没有手动建立mappings，那么当插入文档数据的时候，会根据文档类型自动设置属性类型。这个就是es的动态映射，帮我们在index索引库中去建立数据结构的相关配置信息。
    “fields”: {“type”: “keyword”}
    对一个字段设置多种索引模式，使用text类型做全文检索，也可使用keyword类型做聚合和排序
    “ignore_above” : 256
    设置字段索引和存储的长度最大值，超过则被忽略
