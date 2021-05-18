package com.example.demo.graphql.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DgsAutoConfiguration.class, ShowsDataFetcher.class})
class ShowsDataFetcherTest {

    @Autowired
    private DgsQueryExecutor queryExecutor;

    @Test
    void getShows() {
        var result = queryExecutor.executeAndExtractJsonPath("{\n" +
                "  shows(titleFilter: \"title_1\") {\n" +
                "    title,\n" +
                "    reviews {\n" +
                "      starScore\n" +
                "    }\n" +
                "  }\n" +
                "}", "data.shows[*].title");

        System.out.println(result);
    }
}