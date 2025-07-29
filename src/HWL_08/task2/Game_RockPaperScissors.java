package HWL_08.task2;

import java.util.Random;

public class Game_RockPaperScissors {

    // Основной запускаемый метод
    public static void main(String[] args) {
        // Создаем генератор случайных чисел
        Random random = new Random();

        // Генерируем выбор Васи и Пети (0-камень, 1-ножницы, 2-бумага)
        int vasyaChoice = random.nextInt(3);
        int petyaChoice = random.nextInt(3);

        // Выводим выбор игроков
        System.out.println("Вася показал: " + getChoiceName(vasyaChoice));
        System.out.println("Петя показал: " + getChoiceName(petyaChoice));

        // Определяем победителя с помощью отдельного метода
        String result = determineWinner(vasyaChoice, petyaChoice);
        System.out.println(result);
    }

    // Метод для определения победителя
    private static String determineWinner(int player1, int player2) {
        if (player1 == player2) {
            return "Ничья!";
        } else if ((player1 == 0 && player2 == 1) ||
                (player1 == 1 && player2 == 2) ||
                (player1 == 2 && player2 == 0)) {
            return "Вася выиграл!";
        } else {
            return "Петя выиграл!";
        }
    }

    // Метод для преобразования числового выбора в название фигуры
    private static String getChoiceName(int choice) {
        switch (choice) {
            case 0: return "камень";
            case 1: return "ножницы";
            case 2: return "бумага";
            default: return "неизвестно";
        }
    }
}
