package seedu.application.model;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.application.model.application.Application;
import seedu.application.model.application.exceptions.ApplicationNotFoundException;
import seedu.application.model.application.exceptions.DuplicateApplicationException;

public class UniqueArchiveList implements Iterable<Application> {
    private final ObservableList<Application> internalArchiveList = FXCollections.observableArrayList();
    private final ObservableList<Application> internalArchiveUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalArchiveList);

    /**
     * Returns true if the list contains an equivalent application as the given argument.
     */
    public boolean contains(Application toCheck) {
        requireNonNull(toCheck);
        return internalArchiveList.stream().anyMatch(toCheck::isSameApplication);
    }

    /**
     * Adds an application to the list.
     * The application must not already exist in the list.
     */
    public void addArchive(Application toArchive) {
        requireNonNull(toArchive);
        if (contains(toArchive)) {
            throw new DuplicateApplicationException();
        }
        internalArchiveList.add(toArchive);
    }

    /**
     * Replaces the application {@code target} in the archive list with {@code editedApplication}.
     * {@code target} must exist in the list.
     * The application identity of {@code editedApplication} must not be the same as another application in the list.
     */
    public void setArchive(Application target, Application editedApplication) {
        requireAllNonNull(target, editedApplication);

        int index = internalArchiveList.indexOf(target);
        if (index == -1) {
            throw new ApplicationNotFoundException();
        }
        if (!target.isSameApplication(editedApplication) && contains(editedApplication)) {
            throw new DuplicateApplicationException();
        }
        internalArchiveList.set(index, editedApplication);
    }
    /**
     * Removes the equivalent application from the list.
     * The application must exist in the list.
     */
    public void removeArchive(Application toRetrieve) {
        requireNonNull(toRetrieve);
        if (!internalArchiveList.remove(toRetrieve)) {
            throw new ApplicationNotFoundException();
        }
    }

    public void setArchives(UniqueArchiveList replacement) {
        requireNonNull(replacement);
        internalArchiveList.setAll(replacement.internalArchiveList);
    }

    /**
     * Replaces the contents of this list with {@code applications}.
     * {@code applications} must not contain duplicate applications.
     */
    public void setArchives(List<Application> archives) {
        requireAllNonNull(archives);
        if (!archivesAreUnique(archives)) {
            throw new DuplicateApplicationException();
        }

        internalArchiveList.setAll(archives);
    }
    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Application> asUnmodifiableObservableList() {
        return internalArchiveUnmodifiableList;
    }

    @Override
    public Iterator<Application> iterator() {
        return internalArchiveList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueArchiveList // instanceof handles nulls
                && internalArchiveList.equals(((UniqueArchiveList) other).internalArchiveList));
    }

    @Override
    public int hashCode() {
        return internalArchiveList.hashCode();
    }

    /**
     * Returns true if {@code applications} contains only unique applications.
     */
    private boolean archivesAreUnique(List<Application> archives) {
        for (int i = 0; i < archives.size() - 1; i++) {
            for (int j = i + 1; j < archives.size(); j++) {
                if (archives.get(i).isSameApplication(archives.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

