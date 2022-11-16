package ru.netology.NegativeIdException.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {


    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book1 = new Book(1, "Шестерка воронов.. ", 300, "Бардуго Ли");
    Product book2 = new Book(2, "Место встречи изменить нельзя ", 698, "Вайнер Аркадий");
    Product book3 = new Book(3, "Жизнь взаймы, или У неба любимчиков нет  ", 440, "Ремарк Эрих Мария");
    Product smartphone1 = new Smartphone(4, " Iphone 13", 76_000, "Apple");
    Product smartphone2 = new Smartphone(5, " ipad", 45_000, "Apple");
    Product product1 = new Product(6, " Хлеб", 45);
    Product product2 = new Product(7, " Молоко", 80);


    @Test
    public void removingANegativeValue() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(smartphone1);
        repository.save(smartphone2);
        repository.save(product1);
        repository.save(product2);
        Assertions.assertThrows(NegativeIdException.class, () -> {
            repository.removeById(-34);
        });
    }

    @Test
    public void deleteAllSmartphone() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(smartphone1);
        repository.save(smartphone2);
        repository.save(product1);
        repository.save(product2);
        repository.removeById(5);
        Product[] expected = {book1, book2, book3, smartphone1, product1, product2};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void doesNotFindById() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(smartphone1);
        repository.save(smartphone2);
        repository.save(product1);
        repository.save(product2);
        Product expected = null;
        int id = 54;
        Assertions.assertEquals(expected, repository.findById(id));
    }


    @Test
    public void suitableId() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(smartphone1);
        repository.save(smartphone2);
        repository.save(product1);
        repository.save(product2);

        Product expected = book3;
        int id = 3;
        Assertions.assertEquals(expected, repository.findById(id));

    }









    @Test

    public void Add() {
        manager.add(book1);
        Product[] expected = {book1};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchingByName() {
        manager.add(smartphone1);
        manager.add(smartphone2);
        String name = "Iphone 13";
        Product[] expected = {smartphone1};
        Product[] actual = manager.searchBy(name);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void searchingByNoName() {
        manager.add(smartphone1);
        manager.add(smartphone1);
        String name = "Sony";
        Product[] expected = {};
        Product[] actual = manager.searchBy(name);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void saveOneItem() {
        repository.save(book3);
        Product[] expected = {book3};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void saveAdd() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(smartphone1);
        repository.save(smartphone2);
        repository.save(product1);
        repository.save(product2);
        Product[] expected = {book1, book2, book3, smartphone1, smartphone2, product1, product2};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void deleteSmartphone() {
        repository.save(smartphone2);
        repository.removeById(5);
        Product[] expected = {};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void deleteAllSmartphone1() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.removeById(1);
        repository.removeById(2);
        repository.removeById(3);
        repository.save(product1);
        repository.save(product2);

        Product[] expected = {product1, product2};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

}

