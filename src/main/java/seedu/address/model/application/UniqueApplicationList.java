package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.application.exceptions.ApplicationNotFoundException;
import seedu.address.model.application.exceptions.DuplicateApplicationException;
import seedu.address.model.application.interview.exceptions.DuplicateInterviewException;

/**
 * A list of applications that enforces uniqueness between its elements and does not allow nulls.
 * An application is considered unique by comparing using {@code Application#isSameApplication(Application)}.
 * As such, adding and updating of applications uses Application#isSameApplication(Application) for equality
 * ensure that the application being added or updated is unique in terms of identity in the UniqueApplicationList.
 * However, the removal of an application uses Application#equals(Object) to ensure that the application with
 * exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Application#isSameApplication(Application)
 */
public class UniqueApplicationList implements Iterable<Application> {

    private final ObservableList<Application> internalList = FXCollections.observableArrayList();
    private final ObservableList<Application> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent application as the given argument.
     */
    public boolean contains(Application toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameApplication);
    }

    /**
     * Returns true if the list contains an equivalent interview as the given argument.
     */
    public boolean hasSameInterviewTimeAs(Application toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().map(Application::getInterview).anyMatch(toCheck.getInterview()::isOnSameTime);
    }

    /**
     * Adds an application to the list.
     * The application must not already exist in the list and application cannot have clashing interview slot.
     */
    public void add(Application toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateApplicationException();
        } else if (hasSameInterviewTimeAs(toAdd)) {
            throw new DuplicateInterviewException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the application {@code target} in the list with {@code editedApplication}.
     * {@code target} must exist in the list.
     * The application identity of {@code editedApplication} must not be the same as another application in the list.
     */
    public void setApplication(Application target, Application editedApplication) {
        requireAllNonNull(target, editedApplication);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ApplicationNotFoundException();
        }

        if (!target.isSameApplication(editedApplication) && contains(editedApplication)) {
            throw new DuplicateApplicationException();
        } else if (hasSameInterviewTimeAs(editedApplication)) {
            throw new DuplicateInterviewException();
        }

        internalList.set(index, editedApplication);
    }

    /**
     * Removes the equivalent application from the list.
     * The application must exist in the list.
     */
    public void remove(Application toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ApplicationNotFoundException();
        }
    }

    public void setApplications(UniqueApplicationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code applications}.
     * {@code applications} must not contain duplicate applications.
     */
    public void setApplications(List<Application> applications) {
        requireAllNonNull(applications);
        if (!applicationsAreUnique(applications)) {
            throw new DuplicateApplicationException();
        } else if (!interviewsAreUnique(applications)) {
            throw new DuplicateInterviewException();
        }

        internalList.setAll(applications);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Application> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Application> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueApplicationList // instanceof handles nulls
                        && internalList.equals(((UniqueApplicationList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code applications} contains only unique applications.
     */
    private boolean applicationsAreUnique(List<Application> applications) {
        for (int i = 0; i < applications.size() - 1; i++) {
            for (int j = i + 1; j < applications.size(); j++) {
                if (applications.get(i).isSameApplication(applications.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if {@code applications} contains only unique applications.
     */
    private boolean interviewsAreUnique(List<Application> applications) {
        for (int i = 0; i < applications.size() - 1; i++) {
            for (int j = i + 1; j < applications.size(); j++) {
                if (applications.get(i).getInterview().isOnSameTime(applications.get(j).getInterview())) {
                    return false;
                }
            }
        }
        return true;
    }
}
