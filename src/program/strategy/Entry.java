package program.strategy;
import java.io.Serializable;
import java.util.Objects;
/*
����� �������� OurHashMapStorageStrategy.
���� �� ���� ��������������� ������� HashMap � ���������� ��������, � ���� ���� ���������.
 */
public class Entry implements Serializable {

    Long key;      // ���� ����
    String value;  // ���� ��������
    Entry next;
    int hash;

    public Entry (int hash, Long key, String value, Entry next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Long getKey(){
        return this.key;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return this.key + "=" + this.value;
    }
}

