package com.learning.elastic.search;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.GetRequest;
import org.opensearch.client.opensearch.core.GetResponse;
import org.opensearch.client.transport.rest_client.RestClientTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public final class ReadRecord {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadRecord.class);

    /**
     * Main method to read a record in Elasticsearch.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(final String[] args) {

        String indexName = "record";
        String documentId = "1";

        try (RestClientTransport transport = new RestClientTransport(
                RestClient.builder(new HttpHost("localhost", 9200)).build(), new JacksonJsonpMapper())) {
            OpenSearchClient osClient = new OpenSearchClient(transport);

            GetRequest request = GetRequest.of(g -> g
                    .index(indexName)
                    .id(documentId));

            GetResponse<Map> response = osClient.get(request, Map.class);

            if (response.found()) {
                String sourceAsString = response.source().toString();
                LOGGER.info("Document: {}", sourceAsString);
            } else {
                LOGGER.warn("Document not found");
            }
        } catch (IOException e) {
            LOGGER.error("IOException: ", e);
        }
    }

    private ReadRecord() {
        // hidden constructor
    }
}
