package smolyan.java.operation;


import java.io.IOException;

public interface Operation {
    void grep(String pattern, String pathToFile) throws IOException;
    void remove(String pathToFile) throws IOException;
    void move(String locatePathFile, String newPathFile) throws IOException;
    void copy(String originalFile, String targetFile) throws IOException;
    void search(String pattern, String filePath) throws IOException;
}