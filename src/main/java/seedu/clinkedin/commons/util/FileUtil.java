package seedu.clinkedin.commons.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import seedu.clinkedin.commons.exceptions.EmptyFileException;

/**
 * Writes and reads files
 */
public class FileUtil {

    private static final String CHARSET = "UTF-8";
    private static final String UTF8_BOM = "\uFEFF"; // the UTF-8 byte order mark (BOM)

    public static boolean isFileExists(Path file) {
        return Files.exists(file) && Files.isRegularFile(file);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via {@code Paths#get(String)},
     * otherwise returns false.
     * @param path A string representing the file path. Cannot be null.
     */
    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException ipe) {
            return false;
        }
        return true;
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path file) throws IOException {
        if (!isFileExists(file)) {
            createFile(file);
        }
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     */
    public static void createFile(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }

        createParentDirsOfFile(file);

        Files.createFile(file);
    }

    /**
     * Creates parent directories of file if it has a parent directory
     */
    public static void createParentDirsOfFile(Path file) throws IOException {
        Path parentDir = file.getParent();

        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }
    }

    /**
     * Assumes file exists
     */
    public static String readFromFile(Path file) throws IOException {
        return new String(Files.readAllBytes(file), CHARSET);
    }

    /**
     * Writes given string to a file.
     * Will create the file if it does not exist yet.
     */
    public static void writeToFile(Path file, String content) throws IOException {
        Files.write(file, content.getBytes(CHARSET));
    }

    /**
     * Exports data to file path.
     */
    public static void exportToCsvFile(String filePath, List<String[]> data) throws IOException {
        File file = new File(filePath);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file, false);
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        outputStream.write(0xef);
        outputStream.write(0xbb);
        outputStream.write(0xbf);
        writer.writeAll(data);
        writer.close();
    }

    /**
     * Imports data from file path.
     */
    public static ArrayList<ArrayList<String[]>> importFromCsvFile(String filePath) throws IOException,
            EmptyFileException {
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException("File couldn't be found!");
        }

        FileReader filereader = new FileReader(file);
        CSVReader reader = new CSVReader(filereader);
        ArrayList<ArrayList<String[]>> data = new ArrayList<>();
        ArrayList<String[]> person = new ArrayList<>();
        String[] line;
        line = reader.readNext();
        if (line == null) {
            throw new EmptyFileException("File is empty!");
        }
        if (line.length > 0 && line[0].startsWith(UTF8_BOM)) {
            line[0] = line[0].substring(1);
            person.add(line);
        }
        while ((line = reader.readNext()) != null) {
            if (line.length == 0 || line[0].isBlank()) {
                data.add(person);
                person = new ArrayList<>();
            } else {
                person.add(line);
            }
        }
        return data;

    }

}
