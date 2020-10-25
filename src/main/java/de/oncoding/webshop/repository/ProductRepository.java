package de.oncoding.webshop.repository;

import de.oncoding.webshop.model.ProductCreateRequest;
import de.oncoding.webshop.model.ProductResponse;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepository {

    List<ProductResponse> products = new ArrayList<>();

    public ProductRepository() {
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "AMD Ryzen 9 5950X",
                        "grsehz dt rfz jzjs j tjzfgk gjse rzj6drj sej",
                        79900,
                        Arrays.asList("AMD", "Processor")
                ));
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "Intel Core i9-9900KF",
                        "grsehz dt rfz jzjs j tjzfgk gjse rzj6drj sej",
                        37900,
                        Arrays.asList("Intel", "Processor")
                ));
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "NVIDIA GeForce GTX 1080 Ti Black Edition 11GB",
                        "grsehz dt rfz jzjs j tjzfgk gjse rzj6drj sej",
                        74900,
                        Arrays.asList("NVIDIA", "graphics")
                ));
    }

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

    public Optional<ProductResponse> findById(String id) {
        Optional<ProductResponse> product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return product;
    }

    public void deleteById(String id) {
        this.products = products.stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());
    }

    public ProductResponse save(ProductCreateRequest request) {
        ProductResponse response = new ProductResponse(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getPriceInCent(),
                request.getTags()
        );
        products.add(response);
        return response;
    }
}
