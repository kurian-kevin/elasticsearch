# Elasticsearch
 Elasticsearch Client Learning

## Project Spec
* Java JDK 17
* Elasticsearch Server v8.12.2
* Elasticsearch Java v8.12.2
* Client Doc: https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/8.12/index.html

## Repos:
* DockerHub: https://hub.docker.com/_/elasticsearch
  * docker pull elasticsearch:8.12.2
* Elastic: https://www.docker.elastic.co/r/elasticsearch
  * docker pull docker.elastic.co/elasticsearch/elasticsearch:8.12.2
* Maven:
```xml
<dependency>
  <groupId>co.elastic.clients</groupId>
  <artifactId>elasticsearch-java</artifactId>
  <version>8.12.2</version>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.12.3</version>
</dependency>
```
## Startup
* Run: `docker run --rm -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "xpack.security.enabled=false" --name es8 elasticsearch:8.12.2`
