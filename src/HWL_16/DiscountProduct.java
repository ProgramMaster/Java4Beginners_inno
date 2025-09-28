package HWL_16;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;


// Класс для скидочного продукта
class DiscountProduct extends BaseProduct {
    private double discountPercent;
    private LocalDate discountEndDate;

    public DiscountProduct(String name, double basePrice, double discountPercent, LocalDate discountEndDate) {
        super(name, basePrice);
        setDiscountPercent(discountPercent);
        setDiscountEndDate(discountEndDate);
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Скидка должна быть в диапазоне от 0 до 100%");
        }
        this.discountPercent = discountPercent;
    }

    public LocalDate getDiscountEndDate() {
        return discountEndDate;
    }

    public void setDiscountEndDate(LocalDate discountEndDate) {
        if (discountEndDate == null) {
            throw new IllegalArgumentException("Дата окончания скидки не может быть пустой");
        }
        this.discountEndDate = discountEndDate;
    }

    // Проверяем, действует ли скидка
    public boolean isDiscountActive() {
        return LocalDate.now().isBefore(discountEndDate) || LocalDate.now().isEqual(discountEndDate);
    }

    @Override
    public double getCurrentPrice() {
        if (isDiscountActive()) {
            double discountAmount = getBasePrice() * (discountPercent / 100);
            return getBasePrice() - discountAmount;
        }
        return getBasePrice();
    }

    public double getDiscountAmount() {
        return getBasePrice() * (discountPercent / 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountProduct that = (DiscountProduct) o;
        return Double.compare(that.discountPercent, discountPercent) == 0 && Objects.equals(discountEndDate, that.discountEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discountPercent, discountEndDate);
    }

    @Override
    public String toString() {
        String status = isDiscountActive() ? "активна" : "неактивна";
        if (isDiscountActive()) {
            return String.format("Скидочный продукт: %s - %.2f руб. (скидка %.1f%%, действует до %s)",
                    getName(), getCurrentPrice(), discountPercent,
                    discountEndDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
        } else {
            return String.format("Скидочный продукт: %s - %.2f руб. (скидка %.1f%%, закончилась %s)",
                    getName(), getCurrentPrice(), discountPercent,
                    discountEndDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
        }
    }
}

