package program;

import program.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    /*
        ��� ����� ������� ��� �������� ������� ����� ��������� ����� ��������������.
        ������������� ��� ������� �������� ����� ������� ��������, �������������� shortener.
        */
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> keys = new HashSet<>();
        for (String s : strings) {
            keys.add(shortener.getId(s));
        }
        return keys;
    }

    /*
     ����� ����������� ����� �����, �� ������� �������� ������ ��������������.
     ��� ��������� ����������� Shortener �������� �������� � ������ ����� ����� ��������������
     � ������� ������ �� ��� �� �����������, �� ������� �������� ��� ����������.
     */
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (Long k : keys) {
            strings.add(shortener.getString(k));
        }
        return strings;
    }
    /*
     ����� ����������� ������ �������� ������㳿 �� ����� ������� �������� ��������Number.
     */
    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        //�������� ��'� ����� ������㳿. ��'� �� ������� �������� ��'� ������
        Helper.printMessage(strategy.getClass().getSimpleName() + ":");

        //���������� ������� ����� �����, �������������� Helper �� ������ ������� �������� elementsNumber
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; ++i) {
            origStrings.add(Helper.generateRandomString());
        }

        //���������� ��'��� ���� Shortener, �������������� �������� ��������
        Shortener shortener = new Shortener(strategy);

        /*
         ������� � �������� ��� ���������� ��� ������������ ������ getIds ��� ������ ������� ��������.
         ��� ������� � ����������. (����). ���������� ���� ����� � ������������� ��'���� ���� Date.
         */
        Date startTimestamp = new Date();
        Set<Long> keys = getIds(shortener, origStrings);
        Date endTimestamp = new Date();
        long time = endTimestamp.getTime() - startTimestamp.getTime();
        Helper.printMessage("��� ��������� �������������� ��� " + elementsNumber + " �����: " + time);

        /*
         ������� � �������� ���, ���������� ��� ������������ ������ getStrings ��� ������ ������㳿 ��
         ���������� � ������������ ����� ������ ��������������.
        */
        startTimestamp = new Date();
        Set<String> strings = getStrings(shortener, keys);
        endTimestamp = new Date();
        time = endTimestamp.getTime() - startTimestamp.getTime();
        Helper.printMessage("��� ��������� ����� ��� " + elementsNumber + " ��������������: " + time);

        /*
         ���������� ��������� ���� ������� �����, ��� ���� ����������� � �������,
         ��� ���� ��������� ������� getStrings. ���� ������� �������, �� ������ "���� ��������."
         */
        if (origStrings.equals(strings))
            Helper.printMessage("���� ��������.");
        else
            Helper.printMessage("���� �� ��������.");

        Helper.printMessage("");

    }

    public static void main(String[] args) {
        long elementsNumber = 1000;

        testStrategy(new HashMapStorageStrategy(), elementsNumber);

        testStrategy(new HashBiMapStorageStrategy(),elementsNumber);

        testStrategy(new FileStorageStrategy(), elementsNumber);

        testStrategy(new OurHashBiMapStorageStrategy(), elementsNumber);

        testStrategy(new OurHashMapStorageStrategy(), elementsNumber);

        testStrategy(new DualHashBidiMapStorageStrategy(),elementsNumber);
    }
}
