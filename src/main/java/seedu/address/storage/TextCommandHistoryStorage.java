package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.CommandHistory;

public class TextCommandHistoryStorage implements CommandHistoryStorage {
    private static final Logger logger = LogsCenter.getLogger(TextCommandHistoryStorage.class);

    private Path filePath;

    public TextCommandHistoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getUserCommandHistoryPath() {
        return filePath;
    }

    @Override
    public Optional<CommandHistory> readCommandHistory() throws IOException {
        return Optional.empty();
    }

    @Override
    public void saveCommandHistory(CommandHistory commandHistoryList) throws IOException {

    }
}
