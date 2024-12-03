package program.strategy;

import java.util.HashMap;

/*
Стратегія, яка реалізує можливість отримувати строку по ідентифікатору та індефікатор по строці
приблизно за однаковий час
 */
public class OurHashBiMapStorageStrategy implements StorageStrategy{

    HashMap<Long, String> k2v = new HashMap<>(); //мапа зберігає відповідність ключ/значення
    HashMap<String, Long> v2k = new HashMap<>(); //мапа зберігає відповідність значення/ключ


    //Метод containsKey повинен перевіряти, чи міститься отриманий параметр в k2v
    @Override
    public boolean containsKey(Long key) {
        return k2v.containsKey(key);
    }

    //Метод containsValue повинен перевіряти, чи міститься отриманий параметр в v2k
    @Override
    public boolean containsValue(String value) {
        return v2k.containsKey(value);
    }

    //Метод put повинен додавати пару (key, value) до k2v і пару (value, key) до v2k
    @Override
    public void put(Long key, String value) {
        k2v.put(key,value);
        v2k.put(value,key);
    }

    //Метод getKey повинен повертати значення, отримане з v2k
    @Override
    public Long getKey(String value) {
        return v2k.get(value);
    }

    //Метод getValue повинен повертати значення, отримане з k2v
    @Override
    public String getValue(Long key) {
        return k2v.get(key);
    }
}
