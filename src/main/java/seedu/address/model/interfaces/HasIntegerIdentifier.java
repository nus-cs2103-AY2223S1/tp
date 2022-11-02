package seedu.address.model.interfaces;

import seedu.address.model.list.NotFoundException;

/**
 * Interface for any class whose objects can be identified by an integer ID.
 */
public interface HasIntegerIdentifier<T> {

    /**
     * Method to get the integer identifier of an object
     * @return an integer
     */
    int getId();

    /**
     * Generate the next object ID.
     * @return the max ID in list + 1
     */
    public static <T extends HasIntegerIdentifier> int generateNextId(Iterable<T> i) {
        int maxId = 0;
        for (T t: i) {
            maxId = t.getId() > maxId ? t.getId() : maxId;
        }
        return maxId + 1;
    }

    /**
     * Check if a list contains an object with the given id
     * @return true if list contains object.
     */
    public static <T extends HasIntegerIdentifier> boolean containsId(Iterable<T> i, int id) {
        for (T t: i) {
            if (t.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get an element from a list of objects by ID
     * @param iterable iterable to queyr
     * @param id id of item to retrieve
     * @param <T> object type
     * @return
     */
    public static <T extends HasIntegerIdentifier> T getElementById(Iterable<T> iterable, int id) {
        for (T item: iterable) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new NotFoundException();
    }

}
