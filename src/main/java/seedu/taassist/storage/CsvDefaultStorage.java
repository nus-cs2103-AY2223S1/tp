package seedu.taassist.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class to manage storage of CSV files at the default filepath.
 */
public class CsvDefaultStorage implements CsvStorage {

    private Path defaultPath = Paths.get("data");

    public Path getCsvDefaultPath() {
        return defaultPath;
    }

    /**
     * Creates a CSV file with the specified {@code fileName} at the default file location and
     * writes {@code fileData} into the file.
     * @param fileName Name of CSV file to save as.
     * @param fileData Data to be saved in the CSV file.
     * @return Path of the created CSV file.
     *
     * @throws IOException If there is an error when writing the data.
     */
    @Override
    public Path saveAsCsvFile(String fileName, List<List<String>> fileData) throws IOException {
        Path filePath = getCsvDefaultPath().resolve(fileName + ".csv");
        File csvFile = new File(filePath.toString());

        FileWriter writer = new FileWriter(csvFile);
        writer.write(fileData.stream()
                .map(ls -> ls.stream().collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n")));
        writer.write("\n");
        writer.close();

        return filePath;
    }

}
