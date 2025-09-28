package HWL_16;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ShoppingApp {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, BaseProduct> productsMap = new HashMap<>();
        Map<String, Person> peopleMap = new HashMap<>();
        List<String> purchaseHistory = new ArrayList<>();

        System.out.println("=== УЛУЧШЕННАЯ СИСТЕМА ПОКУПОК ===\n");

        // Ввод покупателей
        System.out.println("Введите покупателей в формате: Имя1 = Сумма1; Имя2 = Сумма2; ...");
        System.out.print("Ввод: ");
        String peopleInput = scanner.nextLine().trim();
        parsePeople(peopleInput, peopleMap);

        // Ввод обычных продуктов
        System.out.println("\nВведите обычные продукты в формате: Продукт1 = Цена1; Продукт2 = Цена2; ...");
        System.out.print("Ввод: ");
        String regularProductsInput = scanner.nextLine().trim();
        parseRegularProducts(regularProductsInput, productsMap);

        // Ввод скидочных продуктов
        System.out.println("\nВведите скидочные продукты в формате: Продукт1 = Цена1(Скидка1%|Дата1<" + DATE_FORMAT +">); Продукт2 = Цена2(Скидка2%|Дата2<" + DATE_FORMAT +">); ...");
        System.out.print("Ввод: ");
        String discountProductsInput = scanner.nextLine().trim();
        parseDiscountProducts(discountProductsInput, productsMap);

        // Вывод информации о доступных продуктах
        System.out.println("\n=== ДОСТУПНЫЕ ПРОДУКТЫ ===");
        for (BaseProduct product : productsMap.values()) {
            System.out.println("• " + product);
        }

        // Процесс покупки
        System.out.println("\n=== ПРОЦЕСС ПОКУПКИ ===");
        System.out.println("Вводите покупки в формате: Покупатель = Название продукта");
        System.out.println("Для завершения введите END\n");

        while (true) {
            System.out.print("Введите покупку: ");
            String purchaseInput = scanner.nextLine().trim();

            if (purchaseInput.equalsIgnoreCase("END")) {
                break;
            }

            if (purchaseInput.isEmpty()) {
                continue;
            }

            try {
                processPurchase(purchaseInput, peopleMap, productsMap, purchaseHistory);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        // Вывод отчета о покупках
        System.out.println("\n=== ОТЧЕТ О ПОКУПКАХ ===");
        printPurchaseReport(peopleMap);

        scanner.close();
    }

    // Парсинг списка покупателей
    private static void parsePeople(String input, Map<String, Person> peopleMap) {
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        String[] peopleEntries = input.split(";");
        for (String entry : peopleEntries) {
            try {
                String trimmedEntry = entry.trim();
                if (trimmedEntry.isEmpty()) continue;

                String[] parts = trimmedEntry.split("=");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат записи покупателя: " + entry);
                }

                String name = parts[0].trim();
                double money = Double.parseDouble(parts[1].trim());

                Person person = new Person(name, money);
                peopleMap.put(name, person);
                System.out.println("Добавлен покупатель: " + name + " с бюджетом " + money + " руб.");

            } catch (NumberFormatException e) {
                System.out.println("Ошибка извлечения денежной суммы в записи: " + entry);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка при добавлении покупателя: " + e.getMessage());
            }
        }
    }

    // Парсинг обычных продуктов
    private static void parseRegularProducts(String input, Map<String, BaseProduct> productsMap) {
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        String[] productEntries = input.split(";");
        for (String entry : productEntries) {
            try {
                String trimmedEntry = entry.trim();
                if (trimmedEntry.isEmpty()) continue;

                String[] parts = trimmedEntry.split("=");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат записи продукта: " + entry);
                }

                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());

                Product product = new Product(name, price);
                productsMap.put(name, product);
                System.out.println("Добавлен обычный продукт: " + name + " - " + price + " руб.");

            } catch (NumberFormatException e) {
                System.out.println("Ошибка извлечения цены в записи: " + entry);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка при добавлении продукта: " + e.getMessage());
            }
        }
    }

    // Парсинг скидочных продуктов
    private static void parseDiscountProducts(String input, Map<String, BaseProduct> productsMap) {
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        String[] productEntries = input.split(";");
        for (String entry : productEntries) {
            try {
                String trimmedEntry = entry.trim();
                if (trimmedEntry.isEmpty()) continue;

                // Разделяем основную часть и часть со скидкой
                String[] mainParts = trimmedEntry.split("\\(");
                if (mainParts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат скидочного продукта: " + entry);
                }

                // Обрабатываем основную часть (название и цену)
                String[] namePriceParts = mainParts[0].split("=");
                if (namePriceParts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат скидочного продукта: " + entry);
                }

                String name = namePriceParts[0].trim();
                double price = Double.parseDouble(namePriceParts[1].trim());

                // Обрабатываем часть со скидкой (убираем закрывающую скобку)
                String discountPart = mainParts[1].replace(")", "");
                String[] discountParts = discountPart.split("\\|");
                if (discountParts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат скидки: " + entry);
                }

                double discountPercent = Double.parseDouble(discountParts[0].trim().replace("%", ""));
                LocalDate endDate = LocalDate.parse(discountParts[1].trim(), DATE_FORMATTER);

                DiscountProduct product = new DiscountProduct(name, price, discountPercent, endDate);
                productsMap.put(name, product);
                System.out.println("Добавлен скидочный продукт: " + name + " - " + price + " руб. (" + discountPercent + "% до " + endDate + ")");

            } catch (NumberFormatException e) {
                System.out.println("Ошибка парсинга числа в записи: " + entry);
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка парсинга даты в записи: " + entry + " (используйте формат ГГГГ.ММ.ДД)");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка при добавлении скидочного продукта: " + e.getMessage());
            }
        }
    }

    // Обработка покупки
    private static void processPurchase(String purchaseInput, Map<String, Person> peopleMap,
                                        Map<String, BaseProduct> productsMap, List<String> purchaseHistory) {
        String[] parts = purchaseInput.split("=");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Неверный формат покупки. Используйте: Покупатель = Название продукта");
        }

        String personName = parts[0].trim();
        String productName = parts[1].trim();

        // Проверяем существование покупателя
        Person person = peopleMap.get(personName);
        if (person == null) {
            throw new IllegalArgumentException("Покупатель '" + personName + "' не найден");
        }

        // Проверяем существование продукта
        BaseProduct product = productsMap.get(productName);
        if (product == null) {
            throw new IllegalArgumentException("Продукт '" + productName + "' не найден");
        }

        // Пытаемся совершить покупку
        if (person.buyProduct(product)) {
            String purchaseInfo = String.format("%s купил(а) '%s' за %.2f руб.",
                    personName, productName, product.getCurrentPrice());
            purchaseHistory.add(purchaseInfo);
            System.out.println("✓ " + purchaseInfo);
        } else {
            String errorInfo = String.format("%s не может позволить себе '%s' (цена: %.2f руб., доступно: %.2f руб.)",
                    personName, productName, product.getCurrentPrice(), person.getMoney());
            System.out.println("✗ " + errorInfo);
        }
    }

    // Вывод отчета о покупках
    private static void printPurchaseReport(Map<String, Person> peopleMap) {
        for (Person person : peopleMap.values()) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("ПОКУПАТЕЛЬ: " + person.getName());
            System.out.println("Остаток денег: " + String.format("%.2f", person.getMoney()) + " руб.");
            System.out.println("-".repeat(50));

            if (person.getBag().isEmpty()) {
                System.out.println("Ничего не куплено");
            } else {
                System.out.println("Купленные продукты:");
                double totalSpent = 0;

                for (BaseProduct product : person.getBag()) {
                    double price = product.getCurrentPrice();
                    totalSpent += price;

                    String productInfo = String.format("  • %s - %.2f руб.",
                            product.getName(), price);

                    if (product instanceof DiscountProduct) {
                        DiscountProduct dp = (DiscountProduct) product;
                        if (dp.isDiscountActive()) {
                            productInfo += String.format(" (скидка %.1f%%)", dp.getDiscountPercent());
                        }
                    }

                    System.out.println(productInfo);
                }

                System.out.println("-".repeat(30));
                System.out.println("Всего потрачено: " + String.format("%.2f", totalSpent) + " руб.");
            }
        }
        System.out.println("\n" + "=".repeat(50));
    }
}
