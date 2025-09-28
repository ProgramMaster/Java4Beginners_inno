package HWL_23.task2;

import java.util.Arrays;
import java.util.Scanner;

public class AnagramChecker {

    /**
     * Проверяет, являются ли две строки анаграммами
     * @param s первая строка
     * @param t вторая строка
     * @return true если строки являются анаграммами, иначе false
     */
    public static boolean isAnagram(String s, String t) {
        // Если длины строк разные, они не могут быть анаграммами
        if (s.length() != t.length()) {
            return false;
        }

        // Приводим к нижнему регистру для регистронезависимого сравнения
        char[] sArray = s.toLowerCase().toCharArray();
        char[] tArray = t.toLowerCase().toCharArray();

        // Сортируем массивы символов
        Arrays.sort(sArray);
        Arrays.sort(tArray);

        // Сравниваем отсортированные массивы
        return Arrays.equals(sArray, tArray);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первую строку:");
        String s = scanner.nextLine();

        System.out.println("Введите вторую строку:");
        String t = scanner.nextLine();

        boolean result = isAnagram(s, t);
        System.out.println("Результат: " + result);

        // Проверка примеров из задания
        System.out.println("\nПроверка примеров:");
        System.out.println("Бейсбол - бобслей: " + isAnagram("Бейсбол", "бобслей"));
        System.out.println("Героин - регион: " + isAnagram("Героин", "регион"));
        System.out.println("Клоака - околка: " + isAnagram("Клоака", "околка"));

        scanner.close();
    }
}
