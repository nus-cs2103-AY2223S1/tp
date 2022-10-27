package jeryl.fyp.model.student;

import java.util.List;

import javafx.collections.ObservableList;
import jeryl.fyp.commons.core.index.Index;

/**
 * An interface created for DeadlineList, for test purpose
 */
public interface DeadlineListTemplate extends Iterable<Deadline> {
    boolean contains(Deadline toCheck);

    void add(Deadline toAdd) throws RuntimeException;

    void setDeadline(Deadline target, Deadline editedDeadline) throws RuntimeException;

    void remove(Deadline toRemove) throws RuntimeException;

    void setDeadlines(DeadlineList replacement);

    void setDeadlines(List<Deadline> deadlines) throws RuntimeException;

    ObservableList<Deadline> asUnmodifiableObservableList();

    Deadline getDeadlineByName(String taskName);

    Index getIndexByName(String taskName);

    Deadline getDeadlineByRank(Integer rank) throws RuntimeException;

    int size();
}
