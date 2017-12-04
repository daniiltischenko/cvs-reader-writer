package com.github.daniiltischenko.cvsio.io.write;

import java.util.List;

/**
 * Writes {@code List} of entities to the file.
 *
 * @author Daniil Tyshchenko
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class FileWriter {

    public abstract <T> void write(String path, List<T> entries);
}
