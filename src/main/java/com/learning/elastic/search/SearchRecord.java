package com.learning.elastic.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
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

        try (ElasticsearchTransport transport = new RestClientTransport(
                RestClient.builder(new HttpHost("localhost", 9200)).build(), new JacksonJsonpMapper())) {
            ElasticsearchClient esClient = new ElasticsearchClient(transport);

            MatchQuery matchQuery = MatchQuery.of(t -> t
                    .field("job")
                    .query("Developer")
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
