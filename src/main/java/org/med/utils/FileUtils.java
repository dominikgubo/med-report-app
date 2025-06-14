package org.med.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;

public class FileUtils {
    public static InputStream getInputStream(String path) throws IllegalArgumentException{
        return FileUtils.class.getClassLoader().getResourceAsStream(path);
    }

    public static byte[] readFile(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
}
