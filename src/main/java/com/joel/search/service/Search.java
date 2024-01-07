package com.joel.search.service;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.settings.IndexSettings;
import com.joel.search.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Search {

    private final Environment env;
    private final String applicationId;

    private final String apiKey;

    private final SearchClient client;

    @Autowired
    public Search(Environment env) {
        this.env = env;

        this.applicationId = env.getProperty("applicationId");
        this.apiKey = env.getProperty("apiKey");

        client =
                DefaultSearchClient.create(applicationId, apiKey);
    }

    @Bean
    public SearchIndex<Product> indexCreator() {

        SearchIndex<Product> index = client.initIndex("products", Product.class);

        var product1 = new Product();
        product1.setObjectID(1L);
        product1.setName("iPhone 15 Pro Max");
        product1.setDescription("Apple iPhone 11 Pro Max");
        product1.getTags().add("iphone");
        product1.getTags().add("smart");
        index.saveObject(product1);

        var product2 = new Product();
        product2.setObjectID(2L);
        product2.setName("Samsung Galaxy S23 Ultra");
        product2.setDescription("Samsung Galaxy S23 Ultra");
        product2.getTags().add("Galaxy");
        product2.getTags().add("smart");
        index.saveObject(product2);

        index.setSettings(new IndexSettings().setSearchableAttributes(
                Arrays.asList("name", "description", "tags", "id")
        ));

        return index;
    }

}
