package seedu.address.model.interfaces;

import seedu.address.model.list.NotFoundException;

import java.util.function.Function;

/**
 * Interface for any class whose objects can be identified by an integer ID.
 */
public interface HasIntegerIdentifier<T> {

    /**
     * Method to get the integer identifier of an object
     * @return an integer
     */
    int getID();

    /**
     * Generate the next object ID.
     * @return the max ID in list + 1
     */
    public static <T extends HasIntegerIdentifier> int generateNextID(Iterable<T> i) {
        int maxID = 0;
        for (T t: i) {
            maxID = t.getID() > maxID ? t.getID() : maxID;
        }
        return maxID + 1;
    }

    /**
     * Check if a list contains an object with the given id
     * @return true if list contains object.
     */
    public static <T extends HasIntegerIdentifier> boolean containsID(Iterable<T> i, int id) {
        for (T t: i) {
            if (t.getID() == id) {
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
            if (item.getID() == id) {
                return item;
            }
        }
        throw new NotFoundException();
    }

    /**
     * A method to construct most of the object, without the ID (when you need a list to generate said ID)
     * @return a function that returns a new object.
     */
    Function<Iterable<T>, T> newSupplierWithoutID(T object);

}
