package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.CommandHistory;
import seedu.address.model.ReadOnlyCommandHistory;

/**
 * A class to access CommandHistory data stored as a text file on the hard disk.
 */
public class TextCommandHistoryStorage implements CommandHistoryStorage {
    private static final Logger logger = LogsCenter.getLogger(TextCommandHistoryStorage.class);

    private Path filePath;

    public TextCommandHistoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getCommandHistoryFilePath() {
        return filePath;
    }

    private File getFile() {
        requireNonNull(filePath);
        File file = new File(filePath.toString());
        try {
            if (file.createNewFile()) {
                logger.fine("A new CommandHistory file is created!");
            } else {
                logger.fine("CommandHistory file already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert file != null;
        return file;
    }

    @Override
    public ReadOnlyCommandHistory readCommandHistory() throws FileNotFoundException {
        return readCommandHistory(filePath);
    }

    /**
     * Similar to {@link #readCommandHistory()}.
     * @param filePath location of the data. Cannot be null
     * @return ReadOnlyCommandHistory that can only be copied and not be modified directly
     * @throws FileNotFoundException if unable to find file
     */
    @Override
    public ReadOnlyCommandHistory readCommandHistory(Path filePath) throws FileNotFoundException {
        File file = getFile();
        List<String> commandHistoryList = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String commandString = sc.nextLine();
            commandHistoryList.add(commandString);
        }
        sc.close();
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.setCommandHistoryList(commandHistoryList);
        return commandHistory;
    }


    @Override
    public void saveCommandHistory(ReadOnlyCommandHistory commandHistory) throws IOException {
        saveCommandHistory(commandHistory, filePath);
    }

    /**
     * Similar to {@link #saveCommandHistory(ReadOnlyCommandHistory)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveCommandHistory(ReadOnlyCommandHistory commandHistory, Path filePath) throws IOException {
        requireNonNull(commandHistory);
        requireNonNull(filePath);
        List<String> commandHistoryList = commandHistory.getCommandHistoryList();
        FileWriter fw = new FileWriter(filePath.toString());
        for (int i = 0; i < commandHistoryList.size(); i++) {
            fw.write(commandHistoryList.get(i) + "\n");
        }
        fw.close();
    }

}
