package HWL_23.task1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UniqueElements {

    /**
     * Метод принимает ArrayList<T> и возвращает набор уникальных элементов
     * @param <T> тип элементов в списке
     * @param list входной список элементов
     * @return Set<T> содержащий только уникальные элементы
     */
    public static <T> Set<T> getUniqueElements(ArrayList<T> list) {
        // HashSet автоматически удаляет дубликаты при добавлении
        return new HashSet<>(list);
    }

    // Пример использования
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(2);
        numbers.add(3);
        numbers.add(1);

        Set<Integer> uniqueNumbers = getUniqueElements(numbers);
        System.out.println("Уникальные элементы: " + uniqueNumbers);
        // Вывод: Уникальные элементы: [1, 2, 3]
    }
}
