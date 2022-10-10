package seedu.address.model.iteration;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Iteration's image in the address book.
 * Guarantees: immutable; any image is valid
 */
public class ImagePath {

    public final String path;

    /**
     * Constructs a {@code Feedback}.
     *
     * @param path A non-null path to the image.
     */
    public ImagePath(String path) {
        requireNonNull(path);
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ImagePath)) {
            return false;
        }

        ImagePath otherImagePath = (ImagePath) other;
        return otherImagePath.path.equals(path);
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}
