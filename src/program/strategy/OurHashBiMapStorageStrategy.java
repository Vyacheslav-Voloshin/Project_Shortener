package program.strategy;

import java.util.HashMap;

/*
��������, ��� ������ ��������� ���������� ������ �� �������������� �� ����������� �� ������
��������� �� ��������� ���
 */
public class OurHashBiMapStorageStrategy implements StorageStrategy{

    HashMap<Long, String> k2v = new HashMap<>(); //���� ������ ���������� ����/��������
    HashMap<String, Long> v2k = new HashMap<>(); //���� ������ ���������� ��������/����


    //����� containsKey ������� ���������, �� �������� ��������� �������� � k2v
    @Override
    public boolean containsKey(Long key) {
        return k2v.containsKey(key);
    }

    //����� containsValue ������� ���������, �� �������� ��������� �������� � v2k
    @Override
    public boolean containsValue(String value) {
        return v2k.containsKey(value);
    }

    //����� put ������� �������� ���� (key, value) �� k2v � ���� (value, key) �� v2k
    @Override
    public void put(Long key, String value) {
        k2v.put(key,value);
        v2k.put(value,key);
    }

    //����� getKey ������� ��������� ��������, �������� � v2k
    @Override
    public Long getKey(String value) {
        return v2k.get(value);
    }

    //����� getValue ������� ��������� ��������, �������� � k2v
    @Override
    public String getValue(Long key) {
        return k2v.get(key);
    }
}
