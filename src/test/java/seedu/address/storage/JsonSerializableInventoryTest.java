package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Inventory;
import seedu.address.testutil.TypicalSupplyItems;

public class JsonSerializableInventoryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableInventoryTest");
    private static final Path TYPICAL_SUPPLY_ITEMS_FILE = TEST_DATA_FOLDER.resolve("typicalInventory.json");
    private static final Path INVALID_SUPPLY_ITEMS_FILE = TEST_DATA_FOLDER.resolve("invalidInventory.json");

    @Test
    public void toModelType_typicalInventory_success() throws DataConversionException, IllegalValueException {
        JsonSerializableInventory dataFromFile = JsonUtil.readJsonFile(TYPICAL_SUPPLY_ITEMS_FILE,
                JsonSerializableInventory.class).get();
        Inventory inventoryFromFile = dataFromFile.toModelType();
        Inventory typicalInventory = TypicalSupplyItems.getTypicalInventory();
        assertEquals(inventoryFromFile, typicalInventory);
    }

    @Test
    public void toModelType_invalidInventory_throwsIllegalValueException() throws Exception {
        JsonSerializableInventory dataFromFile = JsonUtil.readJsonFile(INVALID_SUPPLY_ITEMS_FILE,
                JsonSerializableInventory.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
}
