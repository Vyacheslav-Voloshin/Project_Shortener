package program.strategy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {

    public Path path;  // поле путь до файлу

    public FileBucket() {
        try {
            /*
            Ініціалізуємо path тимчасовим файлом.
            Файл повинен бути розміщений у директорії для тимчасових файлів та мати випадкове ім'я.
             */
            path = Files.createTempFile(Integer.toHexString(hashCode()), ".tmp");
            /*
              Видалення файлу під час виходу з програми.
             */
            path.toFile().deleteOnExit();
            /*
              Створюємо новий файл за допомогою path. Якщо такий файл вже є, замінюємо його
             */
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {

        }
    }

    //Метод повинен повертати розмір файлу, на який вказує path.
    public long getFileSize(){
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Метод повинен серіалізувати переданий entry у файл.
    // Зверни увагу, кожен entry може містити ще один entry
    public void putEntry(Entry entry){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path));
            oos.writeObject(entry);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Метод повинен забирати entry із файлу. Якщо файл має нульовий розмір, повернути null
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

    //Метод видаляє файл, на який вказує path
    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

