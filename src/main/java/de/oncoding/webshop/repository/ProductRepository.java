package de.oncoding.webshop.repository;

import de.oncoding.webshop.model.ProductResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<ProductResponse> findAll(String tag){

        if (tag == null)
            return products;
        else {
            tag = tag.toLowerCase();

            List<ProductResponse> filtered = new ArrayList<>();

            for (ProductResponse p : products) {

                if (lowercaseTags(p).contains(tag))
                    filtered.add(p);
            }
            return filtered;
        }
    }

    private List<String> lowercaseTags(ProductResponse p) {
        List<String> lowercaseTags = new ArrayList<>();
        for (String t : p.getTags()) {
            lowercaseTags.add(t.toLowerCase());
        }
        return lowercaseTags;
    }

}
