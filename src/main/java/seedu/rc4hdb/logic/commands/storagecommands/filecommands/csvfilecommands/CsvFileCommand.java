package seedu.rc4hdb.logic.commands.storagecommands.filecommands.csvfilecommands;

import java.nio.file.Path;

import seedu.rc4hdb.logic.commands.storagecommands.filecommands.FileCommand;

/**
 * Encapsulates a command that targets a CSV file.
 */
public abstract class CsvFileCommand extends FileCommand {

    public static final String CSV_POSTFIX = ".csv";

    public CsvFileCommand(Path dir, String fileName) {
        super(dir.resolve(fileName + CSV_POSTFIX));
    }

}
