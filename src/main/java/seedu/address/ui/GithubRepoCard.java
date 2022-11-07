package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import org.ocpsoft.prettytime.PrettyTime;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.github.Repo;

/**
 * An UI component that displays information of a {@code Repo}.
 */
public class GithubRepoCard extends UiPart<Region> {

    private static final String FXML = "GithubRepoCard.fxml";
    public final Repo repo;
    private final Logger logger = LogsCenter.getLogger(GithubRepoCard.class);
    @FXML
    private Hyperlink nameLabel;

    @FXML
    private Label lastUpdatedLabel;

    @FXML
    private Label descriptionLabel;

    /**
     * Creates a {@code GithubRepoCard} with the given {@code repo}.
     */
    public GithubRepoCard(Repo repo) {
        super(FXML);
        this.repo = repo;
        nameLabel.setText(repo.getRepoName());
        setLabelVisibility(descriptionLabel, repo.getDescription().isPresent());
        repo.getDescription().ifPresent(text -> descriptionLabel.setText(text));
        PrettyTime p = new PrettyTime();
        lastUpdatedLabel.setText(p.format(repo.getLastUpdated()));

        Alert a = new Alert(Alert.AlertType.ERROR);
        nameLabel.setOnAction(e -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI(repo.getRepoUrl()));
                } catch (IOException ex) {
                    logger.severe("Error occurred when user clicked the link, " + ex);
                    a.setContentText("An internal error has occurred, unable to open browser.");
                    a.show();
                } catch (URISyntaxException ex) {
                    logger.warning("Repo url is invalid " + ex);
                    a.setContentText("Repo url is invalid, unable to open in browser.");
                    a.show();
                }
            }
        });
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
        return nameLabel.getText().equals(card.nameLabel.getText())
            && repo.equals(card.repo);
    }
}
