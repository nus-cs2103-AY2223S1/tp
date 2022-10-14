package seedu.address.storage;

/**
 * Represents a storage for images used by the application.
 */
public interface ImageStorage {
    static String IMAGE_STORAGE_PATH = "/data/images/";

    /**
     * Makes a local copy of the image at the source path.
     * @param src Path to the source image
     * @return Path to the local copy of the source image.
     */
    String saveIterationImage(String src);
}
