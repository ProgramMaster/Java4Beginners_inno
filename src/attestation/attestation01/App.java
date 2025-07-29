package attestation.attestation01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Главный класс приложения для обработки покупок
 */
public class App {
    /**
     * Точка входа в приложение
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод покупателей (первая строка)
        System.out.println("Введите покупателей в формате: Имя1 = Сумма1; Имя2 = Сумма2; ...");
        String peopleInput = scanner.nextLine().trim();

        // Завершаем работу, так как нам не с кем работать
        if (peopleInput.isEmpty()) {
            System.out.println("Ничего не введено");
            System.exit(0);
        }
        List<Person> people = parsePeople(peopleInput);

        // Ввод продуктов (вторая строка)
        System.out.println("Введите продукты в формате: Продукт1 = Цена1; Продукт2 = Цена2; ...");
        String productsInput = scanner.nextLine().trim();
        if (productsInput.isEmpty()) {
            System.out.println("Ничего не введено");
            System.exit(0);
        }
        List<Product> products = parseProducts(productsInput);

        // Обработка покупок (последующие строки до END)
        System.out.println("Введите покупки (по одной в строке) в формате: Имя - Продукт");
        System.out.println("Для завершения введите END");

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("END")) {
                break;
            }

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split("-", 2);
            if (parts.length != 2) {
                System.out.println("Неверный формат ввода покупки");
                continue;
            }

            String personName = parts[0].trim();
            String productName = parts[1].trim();

            Person person = findPerson(people, personName);
            Product product = findProduct(products, productName);

            if (person != null && product != null) {
                person.buyProduct(product);
            } else {
                System.out.println("Покупатель или продукт не найдены");
            }
        }

        // Вывод результатов
        System.out.println("\nРезультаты покупок:");
        for (Person person : people) {
            System.out.println(person);
        }

        scanner.close();
    }

    /**
     * Парсинг строки с покупателями
     * @param input строка с данными покупателей
     * @return список покупателей
     */
    private static List<Person> parsePeople(String input) {
        List<Person> people = new ArrayList<>();
        String[] entries = input.split(";");

        for (String entry : entries) {
            String trimmedEntry = entry.trim();
            if (trimmedEntry.isEmpty()) continue;

            String[] parts = trimmedEntry.split("=");
            if (parts.length != 2) {
                System.out.println("Неверный формат ввода покупателя: " + entry);
                System.exit(0);
            }

            String name = parts[0].trim();
            double money = 0;
            try {
                money = Double.parseDouble(parts[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат денег у покупателя " + name);
                System.exit(0);
            }

            people.add(new Person(name, money));
        }

        if (people.isEmpty()) {
            System.out.println("Не введено ни одного покупателя");
            System.exit(0);
        }
        return people;
    }

    /**
     * Парсинг строки с продуктами
     * @param input строка с данными продуктов
     * @return список продуктов
     */
    private static List<Product> parseProducts(String input) {
        List<Product> products = new ArrayList<>();
        String[] entries = input.split(";");

        for (String entry : entries) {
            String trimmedEntry = entry.trim();
            if (trimmedEntry.isEmpty()) continue;

            String[] parts = trimmedEntry.split("=");
            if (parts.length != 2) {
                System.out.println("Неверный формат ввода продукта: " + entry);
                System.exit(0);
            }

            String name = parts[0].trim();
            double cost = 0;
            try {
                cost = Double.parseDouble(parts[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат стоимости у продукта " + name);
                System.exit(0);
            }

            products.add(new Product(name, cost));
        }

        if (products.isEmpty()) {
            System.out.println("Не введено ни одного продукта");
            System.exit(0);
        }
        return products;
    }

    /**
     * Поиск покупателя по имени
     * @param people список покупателей
     * @param name имя для поиска
     * @return найденный покупатель или null
     */
    private static Person findPerson(List<Person> people, String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    /**
     * Поиск продукта по названию
     * @param products список продуктов
     * @param name название для поиска
     * @return найденный продукт или null
     */
    private static Product findProduct(List<Product> products, String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
}