package com.learning.elastic.search;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ReadRecord {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadRecord.class);

    /**
     * The hostname of the Elasticsearch server.
     */
    private static final String ELASTICSEARCH_HOST = "localhost";

    /**
     * The port number where Elasticsearch is listening.
     */
    private static final int ELASTICSEARCH_PORT = 9200;

    /**
     * Main method to read a record in Elasticsearch.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(final String[] args) {

        String indexName = "record";
        String documentId = "1";

        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(ELASTICSEARCH_HOST, ELASTICSEARCH_PORT, "http")))) {

            GetRequest getRequest = new GetRequest(indexName, documentId);
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

            if (getResponse.isExists()) {
                String sourceAsString = getResponse.getSourceAsString();
                LOGGER.info("Document: {}", sourceAsString);
            } else {
                LOGGER.warn("Document not found");
            }
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        }
    }

    private ReadRecord() {
        // hidden constructor
    }
}
