package seedu.application.model;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.application.model.application.Application;
import seedu.application.model.application.UniqueApplicationList;
import seedu.application.model.application.interview.Interview;

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
     * Returns true if {@code application} has an interview date and time clashes with another application exists in the
     * application book.
     */
    public boolean hasSameInterviewTime(Application application) {
        requireNonNull(application);
        return applications.hasSameInterviewTimeAs(application);
    }

    /**
     * Returns true if {@code interview} has an interview date and time clashes with another application exists in the
     * application book.
     */
    public boolean hasSameInterviewTime(Interview interview) {
        requireNonNull(interview);
        return applications.hasSameInterviewTimeAs(interview);
    }

    /**
     * Returns true if {@code interview} has an interview date and time clashes with another application exists in the
     * application book, excluding the {@code application}. This enables the edit of the interview.
     */
    public boolean hasSameInterviewTimeExcludeSelf(Interview interview, Application application) {
        requireAllNonNull(interview, application);
        return applications.hasSameInterviewTimeAsExcludeSelf(interview, application);
    }

    /**
     * Adds an application to the application book.
     * The application must not already exist in the application book.
     */
    public void addApplication(Application p) {
        applications.add(p);
    }

    /**
     * Sets an application archive status to true.
     * The application must not be archived.
     */
    public void setArchive(Application p) {
        applications.setApplication(p, p.setToArchive());
    }

    /**
     * Sets an application archive status to false.
     * The application must be archived.
     */
    public void retrieveApplication(Application p) {
        applications.setApplication(p, p.retrieveFromArchive());
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
