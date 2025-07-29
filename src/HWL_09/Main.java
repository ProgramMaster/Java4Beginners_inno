package HWL_09;

import java.util.Scanner;
import java.util.Random;
import java.lang.String;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Создаем телевизоры
        TV tv1 = new TV("Samsung", 55.5);
        TV tv2 = new TV("LG", 42.0);

        // Включаем первый телевизор и управляем им
        tv1.turnOn();
        tv1.setChannel(13);
        tv1.volumeUp();
        tv1.setChannel(15);

        // Проверка недопустимые значения
        tv1.setChannel(150);
        tv1.setVolume(-10);

        // Выводим информацию о телевизорах
        System.out.println("\nИнформация о телевизорах:");
        System.out.println(tv1);
        System.out.println(tv2);

        // Дополнительно: создание телевизора с параметрами с клавиатуры
        TV userTV = createTVFromInput();
        System.out.println("\nСоздан телевизор: " + userTV);

        // Дополнительно: создание телевизора со случайными параметрами
        TV randomTv = createRandomTV();
        System.out.println("\nСлучайный телевизор: " + randomTv);
    }

    // Метод для создания телевизора с параметрами с клавиатуры
    private static TV createTVFromInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nСоздание телевизора:");

        System.out.print("Введите бренд: ");
        String brand = scanner.nextLine();

        System.out.print("Введите диагональ: ");
        double diagonal = scanner.nextDouble();

        return new TV(brand, diagonal);
    }

    // Метод для создания телевизора со случайными параметрами
    private static TV createRandomTV() {
        Random random = new Random();
        String[] brands = {"Sony", "Panasonic", "Philips", "Toshiba", "Sharp"};
        double diagonal = 30 + random.nextDouble() * 70; // от 30 до 100 дюймов

        return new TV(brands[random.nextInt(brands.length)],
                Math.round(diagonal * 10) / 10.0);
    }
}