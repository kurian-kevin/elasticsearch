package com.learning.elastic.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
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

        try (ElasticsearchTransport transport = new RestClientTransport(
                RestClient.builder(new HttpHost("localhost", 9200)).build(), new JacksonJsonpMapper())) {
            ElasticsearchClient esClient = new ElasticsearchClient(transport);

            GetRequest request = GetRequest.of(g -> g
                    .index(indexName)
                    .id(documentId));

            GetResponse<Map> response = esClient.get(request, Map.class);

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
