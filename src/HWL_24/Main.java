package HWL_24;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 2. Проверка работы в классе Main
        List<Car> cars = new ArrayList<>();

        // Добавление автомобилей согласно входным данным
        cars.add(new Car("a123me", "Mercedes", "White", 0, 8300000));
        cars.add(new Car("b873of", "Volga", "Black", 0, 673000));
        cars.add(new Car("w487mn", "Lexus", "Grey", 76000, 900000));
        cars.add(new Car("p987hj", "Volga", "Red", 610, 704340));
        cars.add(new Car("c987ss", "Toyota", "White", 254000, 761000));
        cars.add(new Car("o983op", "Toyota", "Black", 698000, 740000));
        cars.add(new Car("p146op", "BMW", "White", 271000, 850000));
        cars.add(new Car("u893ii", "Toyota", "Purple", 210900, 440000));
        cars.add(new Car("l097df", "Toyota", "Black", 108000, 780000));
        cars.add(new Car("y876wd", "Toyota", "Black", 160000, 1000000));

        // 3. Вывод списка автомобилей
        System.out.println("Автомобили в базе:");
        System.out.println("Number Model Color Mileage Cost");
        for (Car car : cars) {
            System.out.println(car);
        }

        // Входные данные для фильтрации
        String colorToFind = "Black";
        long mileageToFind = 0L;
        long minPrice = 700000L;
        long maxPrice = 800000L;
        String modelToFind1 = "Toyota";
        String modelToFind2 = "Volvo";

        // 4. Использование Java Stream API

        // 1) Номера всех автомобилей, имеющих заданный цвет или нулевой пробег
        System.out.print("\nНомера автомобилей по цвету или пробегу: ");
        String numbersByColorOrMileage = cars.stream()
                .filter(car -> car.getColor().equals(colorToFind) || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .collect(Collectors.joining(" "));
        System.out.println(numbersByColorOrMileage);

        // 2) Количество уникальных моделей в ценовом диапазоне
        long uniqueModelsCount = cars.stream()
                .filter(car -> car.getCost() >= minPrice && car.getCost() <= maxPrice)
                .map(Car::getModel)
                .distinct()
                .count();
        System.out.println("Уникальные автомобили: " + uniqueModelsCount + " шт.");

        // 3) Цвет автомобиля с минимальной стоимостью
        String minCostColor = cars.stream()
                .min(Comparator.comparingLong(Car::getCost))
                .map(Car::getColor)
                .orElse("Не найден");
        System.out.println("Цвет автомобиля с минимальной стоимостью: " + minCostColor);

        // 4) Средняя стоимость искомых моделей
        // Для Toyota
        double toyotaAverageCost = cars.stream()
                .filter(car -> car.getModel().equals(modelToFind1))
                .mapToLong(Car::getCost)
                .average()
                .orElse(0.0);
        System.out.printf("Средняя стоимость модели %s: %.2f%n", modelToFind1, toyotaAverageCost);

        // Для Volvo (которой нет в списке)
        double volvoAverageCost = cars.stream()
                .filter(car -> car.getModel().equals(modelToFind2))
                .mapToLong(Car::getCost)
                .average()
                .orElse(0.0);
        System.out.printf("Средняя стоимость модели %s: %.2f%n", modelToFind2, volvoAverageCost);
    }
}
