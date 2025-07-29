package attestation.attestation01;

import java.util.Objects;

/**
 * Класс, представляющий продукт
 */
class Product {
    /** Название продукта (не может быть пустым) */
    private String name;

    /** Стоимость продукта (не может быть отрицательной) */
    private double cost;

    /**
     * Конструктор продукта
     * @param name название продукта
     * @param cost стоимость продукта
     */
    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    /**
     * Получить название продукта
     * @return название продукта
     */
    public String getName() {
        return name;
    }

    /**
     * Установить название продукта
     * @param name новое название
     * @throws IllegalArgumentException если название не соответствует требованиям
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Название продукта не может быть пустым");
            System.exit(0);
        }
        this.name = name;
    }

    /**
     * Получить стоимость продукта
     * @return стоимость продукта
     */
    public double getCost() {
        return cost;
    }

    /**
     * Установить стоимость продукта
     * @param cost новая стоимость
     * @throws IllegalArgumentException если стоимость отрицательная
     */
    public void setCost(double cost) {
        if (cost < 0) {
            System.out.println("Стоимость продукта не может быть отрицательной");
            System.exit(0);
        }
        this.cost = cost;
    }

    /**
     * Сравнение продуктов
     * @param o объект для сравнения
     * @return true если продукты одинаковые
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.cost, cost) == 0 &&
                Objects.equals(name, product.name);
    }

    /**
     * Получение хэш-кода продукта
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }
}
