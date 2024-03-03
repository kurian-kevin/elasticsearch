package com.learning.elastic.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
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

public final class UpdateRecord {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateRecord.class);

    /**
     * Main method to update a record in Elasticsearch.
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
            map.put("job", "Backend Developer");

            UpdateRequest<Map<String, Object>, Map<String, Object>> request = UpdateRequest.of(u -> u
                    .index(indexName)
                    .id(documentId)
                    .doc(map)
            );

            UpdateResponse<Map<String, Object>> response = esClient.update(request, Map.class);

            LOGGER.info("Update Response: {}", response.result());
        } catch (IOException e) {
            LOGGER.error("IOException: ", e);
        }
    }

    private UpdateRecord() {
        // hidden constructor
    }
}
