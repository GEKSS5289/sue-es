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
# ------------------------------------------------------------------------------
  