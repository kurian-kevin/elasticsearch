package com.learning.elastic.search;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.UpdateRequest;
import org.opensearch.client.opensearch.core.UpdateResponse;
import org.opensearch.client.transport.rest_client.RestClientTransport;
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

        try (RestClientTransport transport = new RestClientTransport(
                RestClient.builder(new HttpHost("localhost", 9200)).build(), new JacksonJsonpMapper())) {
            OpenSearchClient esClient = new OpenSearchClient(transport);

            Map<String, Object> map = new HashMap<>();
            map.put("job", "Backend Developer");

            UpdateRequest<Map, Map> request = UpdateRequest.of(u -> u
                    .index(indexName)
                    .id(documentId)
                    .doc(map)
            );

            UpdateResponse<Map> response = esClient.update(request, Map.class);

            LOGGER.info("Update Response: {}", response.result());
        } catch (IOException e) {
            LOGGER.error("IOException: ", e);
        }
    }

    private UpdateRecord() {
        // hidden constructor
    }
}
