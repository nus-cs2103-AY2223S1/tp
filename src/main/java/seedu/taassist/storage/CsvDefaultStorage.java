package seedu.taassist.storage;

import static seedu.taassist.commons.core.csv.CsvConfig.CSV_LINE_BREAK;
import static seedu.taassist.commons.core.csv.CsvConfig.CSV_SEPARATOR;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import seedu.taassist.commons.util.FileUtil;

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
        String content = fileData.stream()
                .map(ls -> ls.stream().collect(Collectors.joining(CSV_SEPARATOR)))
                .collect(Collectors.joining(CSV_LINE_BREAK));

        FileUtil.writeToFile(filePath, content);

        return filePath;
    }

}
