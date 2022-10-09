package eatwhere.foodguide.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import eatwhere.foodguide.commons.exceptions.DataConversionException;
import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.ReadOnlyFoodGuide;
import eatwhere.foodguide.testutil.Assert;
import eatwhere.foodguide.testutil.TypicalEateries;

public class JsonFoodGuideStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFoodGuide_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readFoodGuide(null));
    }

    private java.util.Optional<ReadOnlyFoodGuide> readFoodGuide(String filePath) throws Exception {
        return new JsonFoodGuideStorage(Paths.get(filePath)).readFoodGuide(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFoodGuide("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, () -> readFoodGuide("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readFoodGuide_invalidFoodGuide_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readFoodGuide("invalidPersonAddressBook.json"));
    }

    @Test
    public void readFoodGuide_invalidAndValidFoodGuide_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, ()
                -> readFoodGuide("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveFoodGuide_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        FoodGuide original = TypicalEateries.getTypicalFoodGuide();
        JsonFoodGuideStorage jsonFoodGuideStorage = new JsonFoodGuideStorage(filePath);

        // Save in new file and read back
        jsonFoodGuideStorage.saveFoodGuide(original, filePath);
        ReadOnlyFoodGuide readBack = jsonFoodGuideStorage.readFoodGuide(filePath).get();
        assertEquals(original, new FoodGuide(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEatery(TypicalEateries.HOON);
        original.removeEatery(TypicalEateries.ALICE);
        jsonFoodGuideStorage.saveFoodGuide(original, filePath);
        readBack = jsonFoodGuideStorage.readFoodGuide(filePath).get();
        assertEquals(original, new FoodGuide(readBack));

        // Save and read without specifying file path
        original.addEatery(TypicalEateries.IDA);
        jsonFoodGuideStorage.saveFoodGuide(original); // file path not specified
        readBack = jsonFoodGuideStorage.readFoodGuide().get(); // file path not specified
        assertEquals(original, new FoodGuide(readBack));

    }

    @Test
    public void saveFoodGuide_nullFoodGuide_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveFoodGuide(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveFoodGuide(ReadOnlyFoodGuide foodGuide, String filePath) {
        try {
            new JsonFoodGuideStorage(Paths.get(filePath))
                    .saveFoodGuide(foodGuide, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFoodGuide_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveFoodGuide(new FoodGuide(), null));
    }
}
