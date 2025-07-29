package HWL_08.task1;

import java.util.Scanner;

public class Greeting {

    // Основной запускаемый метод
    public static void main(String[] args) {
        // Создаем объект Scanner для чтения ввода с консоли
        Scanner scanner = new Scanner(System.in);

        // Запрашиваем имя пользователя
        System.out.print("Введите ваше имя: ");
        String userName = scanner.nextLine();

        // Выводим приветственное сообщение
        System.out.println("Привет, " + userName);

        // Закрываем Scanner
        scanner.close();
    }
}