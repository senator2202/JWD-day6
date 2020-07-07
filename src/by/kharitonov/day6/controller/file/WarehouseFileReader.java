package by.kharitonov.day6.controller.file;

import by.kharitonov.day6.model.exception.BookProjectException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WarehouseFileReader {
    public String read(String fileName) throws BookProjectException {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new BookProjectException("Error during reading file!",
                    e.getCause());
        } catch (NullPointerException e) {
            throw new BookProjectException("Null pointer detected!",
                    e.getCause());
        }
    }
}
