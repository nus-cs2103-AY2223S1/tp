package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.team.exceptions.DuplicateLinkException;
import seedu.address.model.team.exceptions.LinkNotFoundException;

/**
 * A list of links that enforces uniqueness between its elements and does not allow nulls.
 * A link is considered unique solely based on the name of the team.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueLinkList implements Iterable<Link> {

    private final ObservableList<Link> internalLinks = FXCollections.observableArrayList();

    private final ObservableList<Link> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalLinks);

    /**
     * Returns true if the list contains a link with the same name as the given argument.
     */
    public boolean contains(Link linkToCheck) {
        requireNonNull(linkToCheck);
        return internalLinks.stream().anyMatch(linkToCheck::isSameLink);
    }

    /**
     * Adds a link to the list.
     * That link must not already exist in the list.
     */
    public void add(Link linkToAdd) {
        requireNonNull(linkToAdd);
        if (contains(linkToAdd)) {
            throw new DuplicateLinkException();
        }
        internalLinks.add(linkToAdd);
    }

    /**
     * Removes the equivalent link from the list.
     */
    public void remove(Link linkToRemove) {
        requireNonNull(linkToRemove);
        if (!internalLinks.remove(linkToRemove)) {
            throw new LinkNotFoundException();
        }
    }
    /**
     * Replaces the link {@code target} in the list with {@code editedLink}.
     * {@code target} must exist in the list.
     * The link identity of {@code editedLink} must not be the same as another existing link in the list.
     */
    public void setLink(Link target, Link editedLink) {
        requireAllNonNull(target, editedLink);

        int index = internalLinks.indexOf(target);
        if (index == -1) {
            throw new LinkNotFoundException();
        }

        if (!target.isSameLink(editedLink) && contains(editedLink)) {
            throw new DuplicateLinkException();
        }

        internalLinks.set(index, editedLink);
    }

    /**
     * Replaces the contents of this list with {@code links}
     */
    public void setLinks(List<Link> links) {
        requireNonNull(links);
        if (!linksAreUnique(links)) {
            throw new DuplicateLinkException();
        }
        internalLinks.setAll(links);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */

    public ObservableList<Link> asUnmodifiableObservableList() {
        return this.internalUnmodifiableList;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Link> iterator() {
        return internalLinks.iterator();
    }

    /**
     * Returns true if {@code link} contains only unique links.
     */
    private boolean linksAreUnique(List<Link> links) {
        for (int i = 0; i < links.size() - 1; i++) {
            for (int j = i + 1; j < links.size(); j++) {
                if (links.get(i).isSameLink(links.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


}
