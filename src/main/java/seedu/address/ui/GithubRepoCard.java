package seedu.address.ui;

import org.ocpsoft.prettytime.PrettyTime;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.github.Repo;

/**
 * An UI component that displays information of a {@code Repo}.
 */
public class GithubRepoCard extends UiPart<Region> {

    private static final String FXML = "GithubRepoCard.fxml";

    public final Repo repo;

    @FXML
    private Label name;

    @FXML
    private Label lastUpdated;

    @FXML
    private Label description;

    /**
     * Creates a {@code GithubRepoCard} with the given {@code repo}.
     */
    public GithubRepoCard(Repo repo) {
        super(FXML);
        this.repo = repo;
        name.setText(repo.getRepoName());
        setLabelVisibility(description, repo.getDescription().isPresent());
        repo.getDescription().ifPresent(text -> description.setText(text));
        PrettyTime p = new PrettyTime();
        lastUpdated.setText(p.format(repo.getLastUpdated()));
    }

    private void setLabelVisibility(Label label, boolean visible) {
        // Remove node from tree so it doesn't occupy the space.
        // @see https://stackoverflow.com/a/28559958
        label.setManaged(visible);
        label.setVisible(visible);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        GithubRepoCard card = (GithubRepoCard) other;
        return name.getText().equals(card.name.getText())
                && repo.equals(card.repo);
    }
}
