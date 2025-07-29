package HWL_10.task2;

import java.util.Scanner;

public class FindArrows {
    public static void main(String[] args) {
        System.out.println("Java для начинающих. Тема: Класс Object. Класс String");
        System.out.println("Задача: подсчитать количество вхождений подстрок \">>-->\" и \"<--<<\" в заданной строке.");
        System.out.print("Введите строку из символов ‘>’, ‘<’ и ‘-‘ (без пробелов): ");

        Scanner scanner = new Scanner(System.in);
        String input_str = scanner.nextLine();

        int count = 0;

        for (int i = 0; i <= input_str.length() - 5; i++) {
            String substring = input_str.substring(i, i + 5);
            if (substring.equals(">>-->") || substring.equals("<--<<")) {
                count++;
            }
        }

        System.out.println("Количество найденных стрелок: " + count);
        scanner.close();
    }

}
