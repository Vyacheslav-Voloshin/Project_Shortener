package program;

import program.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    /*
        Цей метод повинен для переданої множини рядків повертати безліч ідентифікаторів.
        Ідентифікатор для кожного окремого рядка потрібно одержати, використовуючи shortener.
        */
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> keys = new HashSet<>();
        for (String s : strings) {
            keys.add(shortener.getId(s));
        }
        return keys;
    }

    /*
     Метод повертатиме безліч рядків, що відповідає переданій безлічі ідентифікаторів.
     При реальному використанні Shortener завдання отримати з безлічі рядків безліч ідентифікаторів
     і навпаки швидше за все не зустрінеться, це потрібно виключно для тестування.
     */
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (Long k : keys) {
            strings.add(shortener.getString(k));
        }
        return strings;
    }
    /*
     Метод тестуватиме роботу переданої стратегії на певній кількості елементів елементаNumber.
     */
    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        //Виводити ім'я класу стратегії. Ім'я не повинно включати ім'я пакета
        Helper.printMessage(strategy.getClass().getSimpleName() + ":");

        //Генерувати тестове безліч рядків, використовуючи Helper та задану кількість елементів elementsNumber
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; ++i) {
            origStrings.add(Helper.generateRandomString());
        }

        //Створювати об'єкт типу Shortener, використовуючи передану стратегію
        Shortener shortener = new Shortener(strategy);

        /*
         Заміряти і виводити час необхідний для відпрацювання методу getIds для заданої множини елементів.
         Час вивести в мілісекундах. (дати). Вимірювання часу зроби з використанням об'єктів типу Date.
         */
        Date startTimestamp = new Date();
        Set<Long> keys = getIds(shortener, origStrings);
        Date endTimestamp = new Date();
        long time = endTimestamp.getTime() - startTimestamp.getTime();
        Helper.printMessage("Час отримання ідентифікаторів для " + elementsNumber + " строк: " + time);

        /*
         Заміряти і виводити час, необхідний для відпрацювання методу getStrings для заданої стратегії та
         отриманого в попередньому пункті безлічі ідентифікаторів.
        */
        startTimestamp = new Date();
        Set<String> strings = getStrings(shortener, keys);
        endTimestamp = new Date();
        time = endTimestamp.getTime() - startTimestamp.getTime();
        Helper.printMessage("Час отримання строк для " + elementsNumber + " ідентифікаторів: " + time);

        /*
         Порівнювати однаковий вміст множини рядків, яке було згенеровано і множини,
         яке було повернуто методом getStrings. Якщо множини однакові, то виведи "Тест пройдено."
         */
        if (origStrings.equals(strings))
            Helper.printMessage("Тест пройдено.");
        else
            Helper.printMessage("Тест не пройдено.");

        Helper.printMessage("");

    }

    public static void main(String[] args) {
        long elementsNumber = 1000;

        testStrategy(new HashMapStorageStrategy(), elementsNumber);

        testStrategy(new HashBiMapStorageStrategy(),elementsNumber);

        testStrategy(new FileStorageStrategy(), elementsNumber);

        testStrategy(new OurHashBiMapStorageStrategy(), elementsNumber);

        testStrategy(new OurHashMapStorageStrategy(), elementsNumber);
    }
}
