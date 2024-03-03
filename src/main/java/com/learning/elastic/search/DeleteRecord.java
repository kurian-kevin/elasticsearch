package com.learning.elastic.search;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.DeleteRequest;
import org.opensearch.client.opensearch.core.DeleteResponse;
import org.opensearch.client.transport.rest_client.RestClientTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class DeleteRecord {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteRecord.class);

    /**
     * Main method to delete a record in Elasticsearch.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(final String[] args) {

        String indexName = "record";
        String documentId = "1";

        try (RestClientTransport transport = new RestClientTransport(
                RestClient.builder(new HttpHost("localhost", 9200)).build(), new JacksonJsonpMapper())) {
            OpenSearchClient osClient = new OpenSearchClient(transport);

            DeleteRequest request = DeleteRequest.of(g -> g
                    .index(indexName)
                    .id(documentId));

            DeleteResponse response = osClient.delete(request);

            LOGGER.info("Delete Response: {}", response.result());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private DeleteRecord() {
        // hidden constructor
    }
}
