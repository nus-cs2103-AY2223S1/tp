package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.application.Application;
import seedu.address.model.application.UniqueApplicationList;

/**
 * Wraps all data at the application-book level.
 * Duplicates are not allowed (by .isSameApplication comparison).
 */
public class ApplicationBook implements ReadOnlyApplicationBook {

    private final UniqueApplicationList applications;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        applications = new UniqueApplicationList();
    }

    public ApplicationBook() {}

    /**
     * Creates an {@code ApplicationBook} using the Applications in the {@code toBeCopied}.
     */
    public ApplicationBook(ReadOnlyApplicationBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the application list with {@code applications}.
     * {@code applications} must not contain duplicate applications.
     */
    public void setApplications(List<Application> applications) {
        this.applications.setApplications(applications);
    }

    /**
     * Resets the existing data of this {@code ApplicationBook} with {@code newData}.
     */
    public void resetData(ReadOnlyApplicationBook newData) {
        requireNonNull(newData);

        setApplications(newData.getApplicationList());
    }

    //// application-level operations

    /**
     * Returns true if an application with the same identity as {@code application} exists in the application book.
     */
    public boolean hasApplication(Application application) {
        requireNonNull(application);
        return applications.contains(application);
    }

    /**
     * Returns true if an application with the same interview date and time as {@code application} exists
     * in the application book.
     */
    public boolean hasSameInterviewTime(Application application) {
        requireNonNull(application);
        return applications.hasSameInterviewTimeAs(application);
    }

    /**
     * Adds an application to the application book.
     * The application must not already exist in the application book.
     */
    public void addApplication(Application p) {
        applications.add(p);
    }

    /**
     * Replaces the given application {@code target} in the list with {@code editedApplication}.
     * {@code target} must exist in the application book.
     * The application identity of {@code editedApplication} must not be the same as
     * another existing application in the application book.
     */
    public void setApplication(Application target, Application editedApplication) {
        requireNonNull(editedApplication);

        applications.setApplication(target, editedApplication);
    }

    /**
     * Removes {@code key} from this {@code ApplicationBook}.
     * {@code key} must exist in the application book.
     */
    public void removeApplication(Application key) {
        applications.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return applications.asUnmodifiableObservableList().size() + " applications";
        // TODO: refine later
    }

    @Override
    public ObservableList<Application> getApplicationList() {
        return applications.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplicationBook // instanceof handles nulls
                && applications.equals(((ApplicationBook) other).applications));
    }

    @Override
    public int hashCode() {
        return applications.hashCode();
    }
}
