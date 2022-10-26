package seedu.taassist.commons.core.csv;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Container for CSV file configurations.
 */
public class CsvConfig {
    public static final Path CSV_EXPORT_PATH = Paths.get("data");
    public static final String CSV_EXTENSION = ".csv";
    public static final String CSV_LINE_BREAK = "\n";
    public static final String CSV_SEPARATOR = ", ";
    public static final String CSV_NAME_COLUMN_HEADER = "Name";
    public static final String CSV_EMPTY_GRADE = "-";
}
