package com.learning.elastic.search;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public final class CreateRecord {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateRecord.class);

    /**
     * The hostname of the Elasticsearch server.
     */
    private static final String ELASTICSEARCH_HOST = "localhost";

    /**
     * The port number where Elasticsearch is listening.
     */
    private static final int ELASTICSEARCH_PORT = 9200;

    /**
     * Main method to create a record in Elasticsearch.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(final String[] args) {

        String indexName = "record";
        String documentId = "1";

        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(ELASTICSEARCH_HOST, ELASTICSEARCH_PORT, "http")))) {

            Map<String, Object> map = new HashMap<>();
            map.put("name", "John Doe");
            map.put("age", "25");
            map.put("company", "IBM");
            IndexRequest indexRequest = new IndexRequest(indexName)
                    .id(documentId)
                    .source(map);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

            LOGGER.info("Create Response: {}", indexResponse.getResult());
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        }
    }

    private CreateRecord() {
        // hidden constructor
    }
}
