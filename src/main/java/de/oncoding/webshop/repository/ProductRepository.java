package de.oncoding.webshop.repository;

import de.oncoding.webshop.model.ProductResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {

    List<ProductResponse> products = Arrays.asList(
            new ProductResponse(
                    "1",
                    "AMD Ryzen 9 5950X",
                    "grsehz dt rfz jzjs j tjzfgk gjse rzj6drj sej",
                    79900,
                    Arrays.asList("AMD", "Processor")
            ),
            new ProductResponse(
                    "2",
                    "Intel Core i9-9900KF",
                    "grsehz dt rfz jzjs j tjzfgk gjse rzj6drj sej",
                    37900,
                    Arrays.asList("Intel", "Processor")
            ),
            new ProductResponse(
                    "3",
                    "NVIDIA GeForce GTX 1080 Ti Black Edition 11GB",
                    "grsehz dt rfz jzjs j tjzfgk gjse rzj6drj sej",
                    74900,
                    Arrays.asList("NVIDIA", "graphics")
            )
    );

    public List<ProductResponse> findAll(String tag) {

        if (tag == null)
            return products;
        else {
            String lowerCaseTag = tag.toLowerCase();

            return products.stream()
                    .filter(p -> lowercaseTags(p).contains(lowerCaseTag))
                    .collect(Collectors.toList());
        }
    }

    private List<String> lowercaseTags(ProductResponse p) {
        return p.getTags().stream()
                .map(tag -> tag.toLowerCase())
                .collect(Collectors.toList());
    }

}
