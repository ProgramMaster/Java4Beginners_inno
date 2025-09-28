package HWL_16;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

//Класс покупателей
class Person {
    private String name;
    private double money;
    private List<BaseProduct> bag;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.bag = new ArrayList<>();
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
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }

    public List<BaseProduct> getBag() {
        return bag;
    }

    public boolean buyProduct(BaseProduct product) {
        double price = product.getCurrentPrice();
        if (price > this.money) {
            System.out.printf("%s не может позволить себе %s (цена: %.2f)%n",
                    this.name, product.getName(), price);
            return false;
        } else {
            this.money -= price;
            this.bag.add(product);
            System.out.printf("%s купил(а) %s за %.2f руб.%n",
                    this.name, product.getName(), price);
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.money, money) == 0 &&
                Objects.equals(name, person.name) &&
                Objects.equals(bag, person.bag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, bag);
    }

    @Override
    public String toString() {
        if (bag.isEmpty()) {
            return name + " - Ничего не куплено (осталось денег: " + String.format("%.2f", money) + " руб.)";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" - Купленные продукты:\n");
        double totalSpent = 0;

        for (BaseProduct product : bag) {
            double price = product.getCurrentPrice();
            sb.append("  • ").append(product.getName())
                    .append(" - ").append(String.format("%.2f", price)).append(" руб.");

            if (product instanceof DiscountProduct) {
                DiscountProduct dp = (DiscountProduct) product;
                if (dp.isDiscountActive()) {
                    sb.append(" (скидка ").append(String.format("%.1f", dp.getDiscountPercent())).append("%)");
                }
            }
            sb.append("\n");
            totalSpent += price;
        }

        sb.append("  Всего потрачено: ").append(String.format("%.2f", totalSpent)).append(" руб.\n");
        sb.append("  Осталось денег: ").append(String.format("%.2f", money)).append(" руб.");

        return sb.toString();
    }
}