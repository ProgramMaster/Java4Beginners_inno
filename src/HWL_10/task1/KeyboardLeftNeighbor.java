package HWL_10.task1;

import java.util.Scanner;

public class KeyboardLeftNeighbor {
    public static void main(String[] args) {
        // Создаем карту расположения букв на клавиатуре (qwerty в порядке слева направо)
        String keyboard = "qwertyuiopasdfghjklzxcvbnm";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Java для начинающих. Тема: Класс Object. Класс String");
        System.out.println("Задача: Определение соседней клавиши на английской клавиатуре слева (по кругу).");
        System.out.print("Введите букву английского алфавита: ");
        char inputChar = scanner.next().toLowerCase().charAt(0);  // Получаем символ и приводим его к нижнему регистру

        // Находим индекс введенного символа в строке клавиатуры
        int index = keyboard.indexOf(inputChar);

        if (index == -1) {
            System.out.println("Ошибка: ожидается буква английского алфавита.");
        } else {
            // Вычисляем индекс левого соседа с учетом замкнутости клавиатуры
            int leftIndex = (index - 1 + keyboard.length()) % keyboard.length();
            char leftChar = keyboard.charAt(leftIndex);
            System.out.println("Cлева клавиша с буквой: " + leftChar);
        }

        scanner.close();
    }
}