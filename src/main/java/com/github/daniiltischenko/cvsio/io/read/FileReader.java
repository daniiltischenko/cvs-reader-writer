package com.github.daniiltischenko.cvsio.io.read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Reads file from file system and returns
 * List of parsed entities.
 *
 * @author Daniil Tyshchenko
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class FileReader {

    public abstract <T> List<T> read(String path, Class<T> clazz);

    protected Scanner readFile(String filePath) {
        try {
            return new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can not find file by provided path", e);
        }
    }
}
