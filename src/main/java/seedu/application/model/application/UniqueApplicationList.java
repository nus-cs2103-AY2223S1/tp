package seedu.application.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.application.model.application.exceptions.ApplicationNotFoundException;
import seedu.application.model.application.exceptions.DuplicateApplicationException;
import seedu.application.model.application.interview.Interview;
import seedu.application.model.application.interview.exceptions.DuplicateInterviewException;

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
     * Returns true if the list contains an equivalent interview in the {@code toCheck} as the given argument.
     */
    public boolean hasSameInterviewTime(Application toCheck) {
        requireNonNull(toCheck);
        if (!toCheck.hasInterview()) {
            return false;
        }
        for (Application application : internalList) {
            if (!application.hasInterview() || application.isSameApplication(toCheck)) {
                continue;
            }
            if (application.getInterview().get().isOnSameTime(toCheck.getInterview().get())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the list contains an equivalent {@code interview} as the given argument.
     */
    public boolean hasSameInterviewTime(Interview toCheck) {
        requireNonNull(toCheck);
        for (Application application : internalList) {
            if (!application.hasInterview()) {
                continue;
            }
            if (application.getInterview().get().isOnSameTime(toCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the list contains an equivalent interview in the {@code interview} as the given argument.
     * It excludes the check of the {@code application} itself in order to allow the modification of
     * {@code application}.
     */
    public boolean hasSameInterviewTimeExcludeSelf(Interview interview, Application application) {
        requireAllNonNull(interview, application);
        if (!application.hasInterview()) {
            return false;
        }
        for (Application otherApplication : internalList) {
            if (!otherApplication.hasInterview() || otherApplication.isSameApplication(application)) {
                continue;
            }
            if (otherApplication.getInterview().get().isOnSameTime(interview)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an application to the list.
     * The application must not already exist in the list and application cannot have clashing interview slot.
     */
    public void add(Application toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateApplicationException();
        } else if (hasSameInterviewTime(toAdd)) {
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
        //boolean to bypass duplicate interview checking
        boolean hasSameArchiveStatus = target.hasSameArchiveStatus(editedApplication);
        if (!target.isSameApplication(editedApplication) && contains(editedApplication)) {
            // check if applications other than target are equivalent to editedApplication
            throw new DuplicateApplicationException();
        } else if (!target.getInterview().flatMap(x -> editedApplication.getInterview().map(x::isOnSameTime))
                .orElse(false) && hasSameInterviewTime(editedApplication) && hasSameArchiveStatus) {
            // check if applications other than target happen at the same time as editedApplication
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

    /**
     * Replaces the entire list by another UniqueApplicationList.
     *
     * @param replacement to replace the original list.
     */
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
            if (!applications.get(i).hasInterview()) {
                continue;
            }
            for (int j = i + 1; j < applications.size(); j++) {
                if (!applications.get(j).hasInterview()) {
                    continue;
                }
                if (applications.get(i).getInterview().get().isOnSameTime(applications.get(j).getInterview().get())) {
                    return false;
                }
            }
        }
        return true;
    }
}
