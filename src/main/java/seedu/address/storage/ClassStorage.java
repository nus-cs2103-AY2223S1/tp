package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Class;
import seedu.address.model.person.Person;

/**
 * Manages storage of AddressBook class data.
 */
public class ClassStorage {

    private static HashMap<LocalDate, List<Person>> classes;
    private ReadOnlyAddressBook addressBook;

    /**
     * Constructs a {@code ClassStorage} with the given addressBook.
     *
     * @param addressBook ReadOnlyAddressBook object.
     */
    public ClassStorage(ReadOnlyAddressBook addressBook) {
        this.addressBook = addressBook;
        this.classes = initialiseClass();
    }

    /**
     * Initialises HashMap classes field.
     *
     * @return HashMap object.
     */
    public HashMap<LocalDate, List<Person>> initialiseClass() {
        HashMap<LocalDate, List<Person>> map = new HashMap<>();
        ObservableList<Person> listOfPersons = addressBook.getPersonList();
        for (int i = 0; i < listOfPersons.size(); i++) {
            Person person = listOfPersons.get(i);
            Class classOfPerson = person.getAClass();
            if (!classOfPerson.classDateTime.equals("")) {
                if (!map.containsKey(classOfPerson.date)) {
                    List<Person> ls = new ArrayList<>();
                    ls.add(person);
                    map.put(classOfPerson.date, ls);
                } else {
                    map.get(classOfPerson.date).add(person);
                }
            }
        }
        return map;
    }

    /**
     * Saves added classes into storage if there is no conflict between the timings of the classes.
     *
     * @param editedPerson Person object.
     * @throws CommandException if there is a conflict between the timings of the classes.
     */
    public static void saveClass(Person editedPerson) throws CommandException {
        LocalDate date = editedPerson.getAClass().date;
        LocalTime start = editedPerson.getAClass().startTime;
        LocalTime end = editedPerson.getAClass().endTime;
        if (!classes.containsKey(date)) {
            List<Person> ls = new ArrayList<>();
            ls.add(editedPerson);
            classes.put(date, ls);
        } else {
            // Gets the list of person who have classes with same date
            List<Person> listOfPerson = classes.get(date);
            for (int i = 0; i < listOfPerson.size(); i++) {
                Person currPerson = listOfPerson.get(i);
                LocalTime startOfCurrClass = currPerson.getAClass().startTime;
                LocalTime endOfCurrClass = currPerson.getAClass().endTime;
                if (hasConflict(start, end, startOfCurrClass, endOfCurrClass)
                        && !currPerson.allEqualsExceptClass(editedPerson)) {
                    throw new CommandException(EditCommand.MESSAGE_CLASS_CONFLICT);
                }
            }
            listOfPerson.add(editedPerson);
        }
    }

    /**
     * Checks if there is a conflict between class timings.
     *
     * @param start LocalTime object.
     * @param end LocalTime object.
     * @param startOfCurrClass LocalTime object.
     * @param endOfCurrClass LocalTime object.
     * @return True if there is a conflict.
     */
    public static boolean hasConflict(LocalTime start, LocalTime end, LocalTime startOfCurrClass,
                                      LocalTime endOfCurrClass) {
        if (start == null || end == null || startOfCurrClass == null || endOfCurrClass == null) {
            return false;
        }

        return start.equals(startOfCurrClass) || end.equals(endOfCurrClass)
                || start.isAfter(startOfCurrClass) && start.isBefore(endOfCurrClass)
                || start.isBefore(startOfCurrClass) && end.isAfter(startOfCurrClass);
    }

    /**
     * Removes the existing class from storage.
     * This frees up the class slot for other students to take.
     *
     * @param personToEdit Person object.
     */
    public static void removeExistingClass(Person personToEdit) {
        if (!personToEdit.getAClass().classDateTime.equals("")) {
            LocalDate date = personToEdit.getAClass().date;
            // Removes the pre-existing class from storage to prevent future conflicts
            ClassStorage.classes.get(date).remove(personToEdit);
        }

    }
}
