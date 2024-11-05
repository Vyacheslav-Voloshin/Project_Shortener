package program;

import program.strategy.StorageStrategy;

public class Shortener {

    /*Це поле буде
    відповідати за останнє значення ідентифікатора, яке було використане при додаванні нового
    рядка до сховища
   */
    private Long lastId = Long.valueOf(0);


    /*
    поле в якому зберігатиметься стратегія зберігання даних
     */
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    // метод повертає ідентифікатор id для заданої строки
    public synchronized Long getId(String string){
        if (storageStrategy.containsValue(string)){
            return storageStrategy.getKey(string);
        } else {
            lastId++;
            storageStrategy.put(lastId,string);
            return lastId;
        }
    }

    // метод буде повертати строку для заданого ідентифікатора або null, якщо передано невірний ідентифікатор.
    public synchronized String getString(Long id){
        return storageStrategy.getValue(id);

    }


}
