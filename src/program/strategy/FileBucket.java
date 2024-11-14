package program.strategy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {

    public Path path;  // ���� ���� �� �����

    public FileBucket() {
        try {
            /*
            ���������� path ���������� ������.
            ���� ������� ���� ��������� � �������� ��� ���������� ����� �� ���� ��������� ��'�.
             */
            path = Files.createTempFile(Integer.toHexString(hashCode()), ".tmp");
            /*
              ��������� ����� �� ��� ������ � ��������.
             */
            path.toFile().deleteOnExit();
            /*
              ��������� ����� ���� �� ��������� path. ���� ����� ���� ��� �, �������� ����
             */
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {

        }
    }

    //����� ������� ��������� ����� �����, �� ���� ����� path.
    public long getFileSize(){
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ����� ������� ����������� ��������� entry � ����.
    // ������ �����, ����� entry ���� ������ �� ���� entry
    public void putEntry(Entry entry){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path));
            oos.writeObject(entry);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //����� ������� �������� entry �� �����. ���� ���� �� �������� �����, ��������� null
    public Entry getEntry(){
        if (getFileSize()>0) {
            try {
                ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
                return (Entry) ois.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    //����� ������� ����, �� ���� ����� path
    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

