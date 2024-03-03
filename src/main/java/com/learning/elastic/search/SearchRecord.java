package com.learning.elastic.search;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch._types.query_dsl.MatchQuery;
import org.opensearch.client.opensearch._types.query_dsl.Query;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.opensearch.client.transport.rest_client.RestClientTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class SearchRecord {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchRecord.class);

    /**
     * Main method to search records in Elasticsearch.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(final String[] args) {

        String indexName = "record";

        try (RestClientTransport transport = new RestClientTransport(
                RestClient.builder(new HttpHost("localhost", 9200)).build(), new JacksonJsonpMapper())) {
            OpenSearchClient esClient = new OpenSearchClient(transport);

            MatchQuery matchQuery = MatchQuery.of(t -> t
                    .field("job")
                    .query(FieldValue.of("Developer"))
            );

            Query query = Query.of(q -> q.match(matchQuery));

            SearchRequest request = SearchRequest.of(s -> s
                    .index(indexName)
                    .query(query)
            );

            SearchResponse<Map> response = esClient.search(request, Map.class);

            List<Hit<Map>> hits = response.hits().hits();
            for (Hit<Map> hit : hits) {
                String sourceAsString = hit.source().toString();
                LOGGER.info("Document: {}", sourceAsString);
            }
        } catch (IOException e) {
            LOGGER.error("IOException: ", e);
        }
    }

    private SearchRecord() {
        // hidden constructor
    }
}
