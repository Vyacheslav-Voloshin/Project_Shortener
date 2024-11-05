package program;

import program.strategy.StorageStrategy;

public class Shortener {

    /*�� ���� ����
    ��������� �� ������ �������� ��������������, ��� ���� ����������� ��� �������� ������
    ����� �� �������
   */
    private Long lastId = Long.valueOf(0);


    /*
    ���� � ����� �������������� �������� ��������� �����
     */
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    // ����� ������� ������������� id ��� ������ ������
    public synchronized Long getId(String string){
        if (storageStrategy.containsValue(string)){
            return storageStrategy.getKey(string);
        } else {
            lastId++;
            storageStrategy.put(lastId,string);
            return lastId;
        }
    }

    // ����� ���� ��������� ������ ��� �������� �������������� ��� null, ���� �������� ������� �������������.
    public synchronized String getString(Long id){
        return storageStrategy.getValue(id);

    }


}
