# Elasticsearch
 Elasticsearch Client Learning

## Project Spec
* Java JDK 17
* Elasticsearch Server v7.17.18/v8.12.2
* Opensearch Rest Client v2.12.0
* Opensearch Java v2.9.0
* Client Doc: https://opensearch.org/docs/latest/clients/java/

## Repos:
* DockerHub: https://hub.docker.com/_/elasticsearch
  * docker pull elasticsearch:7.17.18
  * docker pull elasticsearch:8.12.2
* Elastic: https://www.docker.elastic.co/r/elasticsearch
  * docker pull docker.elastic.co/elasticsearch/elasticsearch:7.17.18
  * docker pull docker.elastic.co/elasticsearch/elasticsearch:8.12.2
* Maven:
```xml
<dependency>
  <groupId>org.opensearch.client</groupId>
  <artifactId>opensearch-rest-client</artifactId>
  <version>2.12.0</version>
</dependency>
<dependency>
  <groupId>org.opensearch.client</groupId>
  <artifactId>opensearch-java</artifactId>
  <version>2.9.0</version>
</dependency>
```
## Startup
* Run ES7: `docker run --rm -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" --name es7 elasticsearch:7.17.18`
* Run ES8: `docker run --rm -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "xpack.security.enabled=false" --name es8 elasticsearch:8.12.2`
