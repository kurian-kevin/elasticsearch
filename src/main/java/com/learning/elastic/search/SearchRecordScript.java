package com.learning.elastic.search;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.aggregations.Aggregate;
import org.opensearch.client.opensearch.core.SearchTemplateResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.opensearch.client.transport.rest_client.RestClientTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class SearchRecordScript {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchRecordScript.class);

    /**
     * Main method to search records in Elasticsearch.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(final String[] args) {

        String indexName = "record";
        String scriptName = "issue-script";

        try (RestClientTransport transport = new RestClientTransport(
                RestClient.builder(new HttpHost("localhost", 9200)).build(), new JacksonJsonpMapper())) {
            OpenSearchClient osClient = new OpenSearchClient(transport);

            osClient.putScript(r -> r
                    .id(scriptName)
                    .script(s -> s
                            .lang("mustache")
                            .source("{\"size\":1,\"aggs\":{\"ids\":{\"terms\":{\"field\":\"_id\"}}}}")
                    ));

            SearchTemplateResponse<Map> response = osClient.searchTemplate(r -> r
                            .index(indexName)
                            .id(scriptName),
                    Map.class
            );

            List<Hit<Map>> hits = response.hits().hits();
            for (Hit<Map> hit : hits) {
                String sourceAsString = hit.source().toString();
                LOGGER.info("Document: {}", sourceAsString);
            }
        } catch (IOException e) {
            LOGGER.error("IOException: ", e);
        }
    }

    private SearchRecordScript() {
        // hidden constructor
    }
}
