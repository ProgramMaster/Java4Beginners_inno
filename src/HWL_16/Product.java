package HWL_16;

// Класс для обычного продукта
class Product extends BaseProduct {
    public Product(String name, double basePrice) {
        super(name, basePrice);
    }

    @Override
    public double getCurrentPrice() {
        return getBasePrice();
    }

    @Override
    public String toString() {
        return "Обычный продукт: " + super.toString();
    }
}

