package program.tests;

import org.junit.Assert;
import org.junit.Test;
import program.Helper;
import program.Shortener;
import program.strategy.HashBiMapStorageStrategy;
import program.strategy.HashMapStorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
����� ���� ��������, �� �������� ������������� ��� ����� �� ��������� ������㳿
HashBiMapStorageStrategy ����� ������, �� �� ��������� ������㳿 HashMapStorageStrategy
 */
public class SpeedTest {

    //����� ������� ��� � ����������, ���������� ��� ��������� �������������� ��� ��� ����� � strings
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date start = new Date();
        for (String line:strings) {
            ids.add(shortener.getId(line));
        }
        Date end = new Date();
        return end.getTime()-start.getTime();
    }

    //����� ������� ��� � ����������, ���������� ��� ��������� ����� ��� ��� �������������� � ids.
    // ����� ������ ���� ������� � strings
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date start = new Date();
        for (long id:ids) {
            strings.add(shortener.getString(id));
        }
        Date end = new Date();
        return end.getTime()-start.getTime();
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<String>strings1 = new HashSet<>();
        Set<Long>longs1  = new HashSet<>();
        Set<String>strings2 = new HashSet<>();
        Set<Long>longs2  = new HashSet<>();
        long s1 = getTimeToGetIds(shortener1,origStrings,longs1);
        long s2 = getTimeToGetIds(shortener2,origStrings,longs2);
        System.out.println("��� ��������� �������������� ��� HashMapStorageStrategy: "+s1);
        System.out.println("��� ��������� �������������� ��� HashBiMapStorageStrategy: "+s2);
        Assert.assertTrue(s1>s2); // ���������� �� ��� � ��������� ��� shortener1 �����, �� ��� shortener2
        System.out.println();
        s1 = getTimeToGetStrings(shortener1,longs1,strings1);
        s2 = getTimeToGetStrings(shortener2,longs2,strings2);
        System.out.println("��� ��������� ����� ��� HashMapStorageStrategy: "+s1);
        System.out.println("��� ��������� ����� ��� HashBiMapStorageStrategy: "+s2);
        Assert.assertEquals(s1,s2,30); //// ���������� �� ��� � ��������� ��� shortener1 ��������� ������� ���� ��� shortener2


    }

}

