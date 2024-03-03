package com.learning.elastic.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class CreateRecord {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateRecord.class);

    /**
     * Main method to create a record in Elasticsearch.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(final String[] args) {

        String indexName = "record";
        String documentId = "1";

        try (ElasticsearchTransport transport = new RestClientTransport(
                RestClient.builder(new HttpHost("localhost", 9200)).build(), new JacksonJsonpMapper())) {
            ElasticsearchClient esClient = new ElasticsearchClient(transport);

            Map<String, Object> map = new HashMap<>();
            map.put("name", "John Doe");
            map.put("age", "25");
            map.put("company", "IBM");

            IndexRequest<Map<String, Object>> request = IndexRequest.of(i -> i
                    .index(indexName)
                    .id(documentId)
                    .document(map)
            );

            IndexResponse response = esClient.index(request);

            LOGGER.info("Create Response: {}", response.result());
        } catch (IOException e) {
            LOGGER.error("IOException: ", e);
        }
    }

    private CreateRecord() {
        // hidden constructor
    }
}
