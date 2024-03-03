# Elasticsearch
 Elasticsearch Client Learning

## Project Spec
* Java JDK 8
* Elasticsearch v7.17.18
* Elasticsearch Client v7.17.18

## Repos:
* DockerHub: https://hub.docker.com/_/elasticsearch
  * docker pull elasticsearch:7.17.18
* Docker Elastic: https://www.docker.elastic.co/r/elasticsearch
  * docker pull docker.elastic.co/elasticsearch/elasticsearch:7.17.18
* Maven: https://mvnrepository.com/artifact/org.elasticsearch.client/elasticsearch-rest-high-level-client
```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>7.17.18</version>
</dependency>
```
## Startup
* Run: `docker run --rm -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" --name es7 elasticsearch:7.17.18`
