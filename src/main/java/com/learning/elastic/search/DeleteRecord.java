package com.learning.elastic.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
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

        try (ElasticsearchTransport transport = new RestClientTransport(
                RestClient.builder(new HttpHost("localhost", 9200)).build(), new JacksonJsonpMapper())) {
            ElasticsearchClient esClient = new ElasticsearchClient(transport);

            DeleteRequest request = DeleteRequest.of(g -> g
                    .index(indexName)
                    .id(documentId));

            DeleteResponse response = esClient.delete(request);

            LOGGER.info("Delete Response: {}", response.result());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private DeleteRecord() {
        // hidden constructor
    }
}
