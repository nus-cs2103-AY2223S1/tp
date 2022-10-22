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


    /**
     *
     * @return
     */
    @Override
    public ReadOnlyCommandHistory readCommandHistory() {
        File file = getFile();
        List<String> commandHistoryList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String commandString = sc.nextLine();
                commandHistoryList.add(commandString);
            }
        } catch (FileNotFoundException e) {
            logger.fine("CommandHistory file not found");
        }
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.setCommandHistoryList(commandHistoryList);
        return commandHistory;
    }

    @Override
    public void saveCommandHistory(ReadOnlyCommandHistory commandHistory) {
        try {
            List<String> commandHistoryList = commandHistory.getCommandHistoryList();
            FileWriter fw = new FileWriter(filePath.toString());
            for (int i = 0; i < commandHistoryList.size(); i++) {
                fw.write(commandHistoryList.get(i) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            logger.fine("Unable to save CommandHistory File: " + e.getMessage() + e.getStackTrace());
        }
    }
}
