package seedu.address.model.team;

import javafx.collections.ObservableList;

public class TeamMetaData {
    private final UniqueLinkList links;

    {
        links = new UniqueLinkList();
    }

    public TeamMetaData() {}

    public void addLink(Link linkToAdd) {
        links.add(linkToAdd);
    }

    public void deleteLink(Link linkToDelete) {
        links.remove(linkToDelete);
    }

    public ObservableList<Link> getLinkList() {
        return links.asUnmodifiableObservableList();
    }
}
