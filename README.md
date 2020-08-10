# 🔍sue-es
分布式搜索引擎
# 💿安装ElasticSearch-7.2.4(CentOS 7)
    解压
        tar -zxvf elasticsearch-7.4.2-linux-x86_64.tar.gz -C /usr/local/
    配置
        进入elasticsearch-7.2.4文件夹中
            目录介绍:
                bin:可执行文件在里面，运行es的命令就在这个里面，包含了一些脚本文件等
                config:配置文件目录
                JDK:java环境
                lib:依赖的jar，类库
                logs:日志文件
                modules:es相关的模块
                plugins:可以自己开发的插件
                data:这个目录没有，自己新建一下，后面要用 -> mkdir data，这个作为索引目录
            修改config文件夹里的elasticearch.yml
                vim elasticearch.yml
                    cluster.name: sue-elasticsearch
                    node.name: es-node1
                    path.data: /usr/local/elasticsearch-7.2.4/data
                    path.logs: /usr/local/elasticsearch-7.2.4/logs
                    network.host: 0.0.0.0
                    cluster.initial_master_nodes: ["es-node1"]
            修改config文件夹里的jvm.options
                vim jvm.options
                    -Xms128m
                    -Xmx128m
            添加用户(注意:因为es不允许root操作直接执行文件，所以需要创建新的es用户)
                useradd esuser
                chown -R esuser:esuser /usr/local/elasticsearch-7.2.4
                su esuser
            修改/etc/security/limits.conf
                vim /etc/security/limits.conf
                    * soft nofile 65536
                    * hard nofile 131072
                    * soft nproc 2048
                    * hard nproc 4096 
            修改/etc/sysctl.conf
                vim /etc/sysctl.conf
                    vm.max_map_count=262145
                sysctl -p
        
    启动
        cd /usr/local/elasticsearch/bin
            ./elasticsearch -d (后台运行模式)            