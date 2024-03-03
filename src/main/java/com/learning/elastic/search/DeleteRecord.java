package com.learning.elastic.search;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DeleteRecord {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteRecord.class);

    /**
     * The hostname of the Elasticsearch server.
     */
    private static final String ELASTICSEARCH_HOST = "localhost";

    /**
     * The port number where Elasticsearch is listening.
     */
    private static final int ELASTICSEARCH_PORT = 9200;

    /**
     * Main method to delete a record in Elasticsearch.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(final String[] args) {

        String indexName = "record";
        String documentId = "1";

        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(ELASTICSEARCH_HOST, ELASTICSEARCH_PORT, "http")))) {

            DeleteRequest request = new DeleteRequest(indexName, documentId);
            DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);

            LOGGER.info("Delete Response: {}", deleteResponse.getResult());
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        }
    }

    private DeleteRecord() {
        // hidden constructor
    }
}
