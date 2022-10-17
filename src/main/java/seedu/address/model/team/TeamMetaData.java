package seedu.address.model.team;

import javafx.collections.ObservableList;

/**
 * Represents the internal information that needs to be recorded in a team.
 */
public class TeamMetaData {
    private final UniqueLinkList links;

    {
        links = new UniqueLinkList();
    }

    public TeamMetaData() {}

    /**
     * Checks if the link is being contained inside the list of links.
     */
    public boolean hasLink(Link link) {
        return links.contains(link);
    }

    /**
     * Adds the link to the list of links.
     */
    public void addLink(Link linkToAdd) {
        links.add(linkToAdd);
    }

    public void deleteLink(Link linkToDelete) {
        links.remove(linkToDelete);
    }

    public ObservableList<Link> getLinkListAsUnmodifiedObservableList() {
        return links.asUnmodifiableObservableList();
    }
}
