package HWL_16;

import java.util.Objects;
import java.util.regex.Pattern;

// Базовый абстрактный класс для продукта
abstract class BaseProduct {
    private String name;
    private double basePrice;

    public BaseProduct(String name, double basePrice) {
        setName(name);
        setBasePrice(basePrice);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3 символов");
        }
        if (Pattern.matches("^\\d+$", name)) {
            throw new IllegalArgumentException("Название продукта не должно содержать только цифры");
        }
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Стоимость продукта должна быть положительной");
        }
        this.basePrice = basePrice;
    }

    // Абстрактный метод для получения текущей цены
    public abstract double getCurrentPrice();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseProduct that = (BaseProduct) o;
        return Double.compare(that.basePrice, basePrice) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, basePrice);
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f руб.", name, getCurrentPrice());
    }
}
