package com.learning.elastic.search;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.opensearch.client.opensearch.core.IndexResponse;
import org.opensearch.client.transport.rest_client.RestClientTransport;
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

        try (RestClientTransport transport = new RestClientTransport(
                RestClient.builder(new HttpHost("localhost", 9200)).build(), new JacksonJsonpMapper())) {
            OpenSearchClient esClient = new OpenSearchClient(transport);

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
