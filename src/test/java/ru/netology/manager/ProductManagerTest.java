package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class ProductManagerTest {

    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);


    Book book1 = new Book(1, "Fahrenheit 451", 550, "Ray Bradbury");
    Book book2 = new Book(2, "Master and Margarita", 450, "Michael Bulgakov");
    Smartphone smartphone1 = new Smartphone(3, "Iphone", 39990, "Apple");
    Smartphone smartphone2 = new Smartphone(4, "GalaxyFold", 179990, "Samsung");

    @BeforeEach
    void init() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);
    }

    @Test
    void shouldSearchByNameBook() {
        Product[] expected = new Product[]{book1};
        assertArrayEquals(expected, manager.searchBy("Fahrenheit 451"));
    }

    @Test
    void shouldSearchByNameBookIncorrect() {
        Product[] expected = {};
        assertArrayEquals(expected, manager.searchBy("Java"));
    }

    @Test
    void shouldSearchByAuthorBook() {
        Product[] expected = new Product[]{book2};
        assertArrayEquals(expected, manager.searchBy("Michael Bulgakov"));
    }

    @Test
    void shouldSearchByAuthorBookIncorrect() {
        Product[] expected =  {};
        assertArrayEquals(expected, manager.searchBy("Лев Толстой"));
    }

    @Test
    void shouldSearchBySmartphoneName() {
        Product[] expected = new Product[]{smartphone2};
        assertArrayEquals(expected, manager.searchBy("GalaxyFold"));
    }

    @Test
    void shouldSearchBySmartphoneNameIncorrect() {
        Product[] expected = {};
        assertArrayEquals(expected, manager.searchBy("Iphone 8"));
    }

    @Test
    void shouldSearchBySmartphoneManufacturer() {
        Product[] expected = new Product[]{smartphone1};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBySmartphoneManufacturerIncorrect() {
        Product[] expected = {};
        assertArrayEquals(expected, manager.searchBy("Nokia"));
    }
}