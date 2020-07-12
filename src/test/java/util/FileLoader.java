package util;

import java.util.Objects;

public class FileLoader {
    private FileLoader() {
    }

    public static synchronized String getFile(String fileName) {
        return Objects.requireNonNull(FileLoader.class.getClassLoader().getResource(fileName)).getPath();
    }
}
