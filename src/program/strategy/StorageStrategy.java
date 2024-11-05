package program.strategy;

/*���������� Shortener ������������� ��� ������㳿 ��������� �����
(����� �� �� ��������������). �� �� ������㳿 ������������������� �� ���������� StorageStrategy.
 ���� ������� ����������� ����� ���������: ���� � ��������. ������ ���� ������������� �����,
 � ��������� ��� �����.
 */

public interface StorageStrategy {


    //����� ������� ��������� true, ���� ������� ������ ��������� ����
    boolean containsKey(Long key);


    // ����� ������� ��������� true, ���� ������� ������ �������� ��������.
    boolean containsValue(String value);


    //����� ���� �� ������� ���� ���� ���� - ��������.
    void put(Long key, String value);


    //����� ������� ���� ��� ���������� ��������
    Long getKey(String value);


    //����� ������� �������� ��� ���������� �����.
    String getValue(Long key);
}

