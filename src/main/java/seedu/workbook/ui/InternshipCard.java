package seedu.workbook.ui;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.Stage;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class InternshipCard extends UiPart<Region> {

    private static final String FXML = "InternshipListCard.fxml";


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Internship internship;

    //Independent tips window residing in each internship list card controller
    private TipsWindow tipsWindow;

    private final Stage internshipStage;
    private final boolean stageHasTips;

    @FXML
    private HBox cardPane;
    @FXML
    private Label company;
    @FXML
    private Label role;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private Label stage;
    @FXML
    private Label dateTime;
    @FXML
    private FlowPane tags;
    @FXML
    private Button tipsButton;


    /**
     * Creates a {@code InternshipCode} with the given {@code Internship} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);
        this.internship = internship;
        this.internshipStage = internship.getStage();
        //this.stageHasTips = Stage.hasTips(this.internshipStage);
        this.stageHasTips = true;

        if(!stageHasTips) {
            tipsButton.setVisible(false);
        }

        id.setText(displayedIndex + ". ");
        company.setText(internship.getCompany().name);
        role.setText(internship.getRole().value);
        email.setText(internship.getEmail().value);
        stage.setText(internshipStage.value);
        dateTime.setText(internship.getDateTime().value);
        internship.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        tipsWindow = new TipsWindow();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipCard)) {
            return false;
        }

        // state check
        InternshipCard card = (InternshipCard) other;
        return id.getText().equals(card.id.getText())
                && internship.equals(card.internship);
    }

    /**
     * Opens the tip window depending on the internship stage.
     */
    @FXML
    private void showTips() {
        if (!this.stageHasTips) {
            return;
        }

        //Set header of tips to be name of stage.
        tipsWindow.setTipsHeader(this.internshipStage.value);

        List<String> tips = new ArrayList<String>();

        //Dummy tips for testing purposes.
        tips.add("Contact the hiring manager/recruitment by submitting a short follow-up email to "
            + "show interest in the company and stand out!");

        tips.add("Keep a copy of your resume by the phone so that you can refer to them and answer"
            + " questions if the company were to call you for a short interview!");

        tips.add("Conduct a detailed research on the organisation you applied for, especially their"
            + "mission statement, policies, initiatives and programmes to prepare for a potential interview!");

        tips.add("Prepare for interviews and assessment by brushing up on your interview and coding"
            + "skills! Refer to our tips included in “Online Assessment”, “Technical Interview” and"
            + "“Behavioural Interview” for more advice!");

        tips.add("Short tip formatting test");

        tipsWindow.populateTips(tips);

        if (!tipsWindow.isShowing()) {
            tipsWindow.show();
        } else {
            tipsWindow.focus();
        }
    }
}
