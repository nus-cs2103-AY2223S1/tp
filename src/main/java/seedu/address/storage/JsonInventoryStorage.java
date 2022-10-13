package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyInventory;

/**
 * A class to access Supply Items stored in the hard disk as a json file
 */
public class JsonInventoryStorage implements InventoryStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonInventoryStorage.class);

    private Path filePath;

    public JsonInventoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getInventoryFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyInventory> readInventory() throws DataConversionException {
        return readInventory(filePath);
    }

    /**
     * Similar to {@link #readInventory()}
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    @Override
    public Optional<ReadOnlyInventory> readInventory(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableInventory> jsonInventory = JsonUtil.readJsonFile(
                filePath, JsonSerializableInventory.class);
        if (!jsonInventory.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonInventory.get().toModelType());
        } catch (Exception e) {
            logger.info("Illegal values found in " + filePath + ": " + e.getMessage());
            throw new DataConversionException(e);
        }
    }

    @Override
    public void saveInventory(ReadOnlyInventory inventory) throws IOException {
        saveInventory(inventory, filePath);
    }

    /**
     * Similar to {@link #saveInventory(ReadOnlyInventory)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInventory(ReadOnlyInventory inventory, Path filePath) throws IOException {
        requireNonNull(inventory);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInventory(inventory), filePath);
    }
}

