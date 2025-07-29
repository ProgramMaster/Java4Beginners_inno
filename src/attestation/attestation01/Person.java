package attestation.attestation01;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс, представляющий Покупателя
 */
class Person {
    /** Имя покупателя (не может быть пустым или короче 3 символов) */
    private String name;

    /** Количество денег у покупателя (не может быть отрицательным) */
    private double money;

    /** Список купленных продуктов */
    private List<Product> cart;

    /**
     * Конструктор покупателя
     * @param name имя покупателя
     * @param money количество денег у покупателя
     */
    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.cart = new ArrayList<>();
    }

    /**
     * Получить имя покупателя
     * @return имя покупателя
     */
    public String getName() {
        return name;
    }

    /**
     * Установить имя покупателя
     * @param name новое имя покупателя
     * @throws IllegalArgumentException если имя не соответствует требованиям
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Имя не может быть пустым");
            System.exit(0);
        }
        if (name.length() < 3) {
            System.out.println("Имя не может быть короче 3 символов");
            System.exit(0);
        }
        this.name = name;
    }

    /**
     * Получить количество денег покупателя
     * @return количество денег
     */
    public double getMoney() {
        return money;
    }

    /**
     * Установить количество денег покупателя
     * @param money новое количество денег
     * @throws IllegalArgumentException если деньги отрицательные
     */
    public void setMoney(double money) {
        if (money < 0) {
            System.out.println("Деньги не могут быть отрицательными");
            System.exit(0);
        }
        this.money = money;
    }

    /**
     * Получить список купленных продуктов
     * @return список продуктов
     */
    public List<Product> getCart() {
        return cart;
    }

    /**
     * Покупка продукта
     * @param product продукт для покупки
     * @return true если покупка совершена, false если недостаточно денег
     */
    public boolean buyProduct(Product product) {
        if (product.getCost() <= money) {
            cart.add(product);
            money -= product.getCost();
            System.out.printf("%s купил %s%n", name, product.getName());
            return true;
        }
        System.out.printf("%s не может позволить себе %s%n", name, product.getName());
        return false;
    }

    /**
     * Преобразование в строку для вывода информации о покупателе
     * @return строка с информацией о покупках
     */
    @Override
    public String toString() {
        if (cart.isEmpty()) {
            return name + " - Ничего не куплено";
        }
        StringBuilder result = new StringBuilder(name + " - ");
        for (int i = 0; i < cart.size(); i++) {
            result.append(cart.get(i).getName());
            if (i < cart.size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    /**
     * Сравнение покупателей
     * @param o объект для сравнения
     * @return true если покупатели одинаковые
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.money, money) == 0 &&
                Objects.equals(name, person.name) &&
                Objects.equals(cart, person.cart);
    }

    /**
     * Получение хэш-кода покупателя
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, money, cart);
    }
}