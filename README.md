# Elasticsearch
 Elasticsearch Client Learning

## Project Spec
* Java JDK 17
* Elasticsearch Server v7.17.18
* Elasticsearch Java v7.17.18
* Client Doc: https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/7.17/index.html

## Repos:
* DockerHub: https://hub.docker.com/_/elasticsearch
  * docker pull elasticsearch:7.17.18
* Elastic: https://www.docker.elastic.co/r/elasticsearch
  * docker pull docker.elastic.co/elasticsearch/elasticsearch:7.17.18
* Maven:
```xml
<dependency>
  <groupId>co.elastic.clients</groupId>
  <artifactId>elasticsearch-java</artifactId>
  <version>7.17.18</version>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.12.3</version>
</dependency>
```
## Startup
* Run: `docker run --rm -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" --name es7 elasticsearch:7.17.18`
