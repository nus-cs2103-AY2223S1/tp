package seedu.address.model;

import seedu.address.storage.ImageStorage;

/**
 * A default ImageStorage stub that have all of its methods failing.
 */
public class ImageStorageStub implements ImageStorage {
    @Override
    public String saveIterationImage(String src) {
        throw new AssertionError("This method should not be called.");
    }
}
