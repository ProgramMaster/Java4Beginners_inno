package HWL_23.task3;

import java.util.HashSet;
import java.util.Set;


public class PowerfulSet {

    /**
     * Возвращает пересечение двух наборов (элементы, присутствующие в обоих наборах)
     * @param <T> тип элементов в наборах
     * @param set1 первый набор
     * @param set2 второй набор
     * @return Set<T> содержащий элементы, присутствующие в обоих наборах
     */
    public <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1); // Создаем копию первого набора
        result.retainAll(set2); // Оставляем только элементы, которые есть во втором наборе
        return result;
    }

    /**
     * Возвращает объединение двух наборов (все уникальные элементы из обоих наборов)
     * @param <T> тип элементов в наборах
     * @param set1 первый набор
     * @param set2 второй набор
     * @return Set<T> содержащий все уникальные элементы из обоих наборов
     */
    public <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1); // Создаем копию первого набора
        result.addAll(set2); // Добавляем все элементы из второго набора
        return result;
    }

    /**
     * Возвращает относительное дополнение (элементы первого набора без элементов второго)
     * @param <T> тип элементов в наборах
     * @param set1 первый набор
     * @param set2 второй набор
     * @return Set<T> содержащий элементы из set1, которых нет в set2
     */
    public <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1); // Создаем копию первого набора
        result.removeAll(set2); // Удаляем элементы, которые есть во втором наборе
        return result;
    }

    // -----------------------------------------------------------------------
    // Демонстрация работы класса
    // -----------------------------------------------------------------------
    public static void main(String[] args) {
        PowerfulSet powerfulSet = new PowerfulSet();

        // Создаем тестовые наборы как в примере
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(0);
        set2.add(1);
        set2.add(2);
        set2.add(4);

        // Тестируем методы
        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);

        Set<Integer> intersection = powerfulSet.intersection(set1, set2);
        System.out.println("Пересечение: " + intersection); // Ожидаем: [1, 2]

        Set<Integer> union = powerfulSet.union(set1, set2);
        System.out.println("Объединение: " + union); // Ожидаем: [0, 1, 2, 3, 4]

        Set<Integer> relativeComplement = powerfulSet.relativeComplement(set1, set2);
        System.out.println("Относительное дополнение: " + relativeComplement); // Ожидаем: [3]

        // Дополнительный пример со строками
        Set<String> stringSet1 = new HashSet<>();
        stringSet1.add("яблоко");
        stringSet1.add("банан");
        stringSet1.add("апельсин");

        Set<String> stringSet2 = new HashSet<>();
        stringSet2.add("банан");
        stringSet2.add("вишня");
        stringSet2.add("киви");

        System.out.println("\nПример со строками:");
        System.out.println("Пересечение: " + powerfulSet.intersection(stringSet1, stringSet2));
        System.out.println("Объединение: " + powerfulSet.union(stringSet1, stringSet2));
        System.out.println("Относительное дополнение: " +
                powerfulSet.relativeComplement(stringSet1, stringSet2));
    }
}
