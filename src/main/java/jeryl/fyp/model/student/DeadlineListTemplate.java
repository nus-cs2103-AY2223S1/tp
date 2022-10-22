package jeryl.fyp.model.student;

import java.util.List;

import javafx.collections.ObservableList;
import jeryl.fyp.commons.core.index.Index;

/**
 * An interface created for DeadlineList, for test purpose
 */
public interface DeadlineListTemplate extends Iterable<Deadline> {
    boolean contains(Deadline toCheck);

    void add(Deadline toAdd);

    void setDeadline(Deadline target, Deadline editedDeadline);

    void remove(Deadline toRemove);

    void setDeadlines(DeadlineList replacement);

    void setDeadlines(List<Deadline> deadlines);

    ObservableList<Deadline> asUnmodifiableObservableList();

    Deadline getDeadlineByName(String taskName);

    Index getIndexByName(String taskName);

    // Pending implementation
    Deadline getDeadlineByRank(Integer rank);
}
