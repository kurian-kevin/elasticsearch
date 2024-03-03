package com.learning.elastic.search;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SearchRecord {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchRecord.class);

    /**
     * The hostname of the Elasticsearch server.
     */
    private static final String ELASTICSEARCH_HOST = "localhost";

    /**
     * The port number where Elasticsearch is listening.
     */
    private static final int ELASTICSEARCH_PORT = 9200;

    /**
     * Main method to search records in Elasticsearch.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(final String[] args) {

        String indexName = "record";

        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(ELASTICSEARCH_HOST, ELASTICSEARCH_PORT, "http")))) {

            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchQuery("job", "Developer"));
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            SearchHit[] searchHits = searchResponse.getHits().getHits();
            for (SearchHit hit : searchHits) {
                LOGGER.info("Document: {}", hit.getSourceAsString());
            }
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        }
    }

    private SearchRecord() {
        // hidden constructor
    }
}
