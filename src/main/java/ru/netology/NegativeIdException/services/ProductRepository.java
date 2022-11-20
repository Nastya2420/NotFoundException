package ru.netology.NegativeIdException.services;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void save(Product product) {
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NegativeIdException(
                    "ID не найден " + id);
        }
        Product[] tmp = new Product[products.length - 1];
        int copy = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copy] = product;
                copy++;
            }
        }
        products = tmp;
    }


    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}