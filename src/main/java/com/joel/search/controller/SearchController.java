package com.joel.search.controller;

import com.algolia.search.SearchIndex;
import com.algolia.search.models.indexing.Query;
import com.algolia.search.models.indexing.SearchResult;
import com.joel.search.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final SearchIndex<Product> index;

    @Autowired
    public SearchController(SearchIndex<Product> index) {
        this.index = index;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {

        SearchResult<Product>  productSearchResult = this.index.search(new Query(query));

        return new ResponseEntity<>(productSearchResult.getHits(), HttpStatus.OK);
    }
}
