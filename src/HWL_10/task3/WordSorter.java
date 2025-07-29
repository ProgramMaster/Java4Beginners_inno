package HWL_10.task3;

import java.util.Arrays;
import java.util.Scanner;

public class WordSorter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Java для начинающих. Тема: Класс Object. Класс String");
        System.out.println("Задача 3*: Введите строку из слов (последовательностей букв), состоящую из букв английского алфавита, разделенных пробелами:");
        String input = scanner.nextLine();

        // Разбиваем строку на слова
        String[] words = input.split(" ");

        // Обрабатываем каждое слово
        for (int i = 0; i < words.length; i++) {
            // Приводим к нижнему регистру
            String lowerWord = words[i].toLowerCase();

            // Преобразуем слово в массив символов и сортируем
            char[] chars = lowerWord.toCharArray();
            Arrays.sort(chars);

            // Собираем обратно в строку
            words[i] = new String(chars);
        }

        // Собираем результат
        String result = String.join(" ", words);
        System.out.println("Результат:");
        System.out.println(result);

        scanner.close();
    }
}
