package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.OfficeHour;
import seedu.address.model.person.Person;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Specialisation;
import seedu.address.model.person.Student;
import seedu.address.model.person.TeachingAssistant;
import seedu.address.model.person.Year;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private HBox stars;
    @FXML
    private Label number;
    @FXML
    private ImageView phoneImage;
    @FXML
    private Label phone;
    @FXML
    private FlowPane moduleCodes;
    @FXML
    private ImageView gender;
    @FXML
    private ImageView emailImage;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView locationImage;
    @FXML
    private Label locationAt;
    @FXML
    private HBox gitHubPanel;
    @FXML
    private ImageView githubImage;
    @FXML
    private Label githubUsername;
    @FXML
    private Label year;
    @FXML
    private Label specialisation;
    @FXML
    private HBox officeHourPanel;
    @FXML
    private ImageView officeHourImage;
    @FXML
    private Label officeHour;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        phoneImage.setImage(new Image(this.getClass().getResourceAsStream("/images/telephoneblack.png")));
        setUsername();
        gender.setImage(getGenderImage(person));
        email.setText(person.getEmail().value);
        emailImage.setImage(new Image(this.getClass().getResourceAsStream("/images/email.png")));
        locationAt.setText(person.getLocation().value);
        locationImage.setImage(new Image(this.getClass().getResourceAsStream("/images/locationbig2.png"),
                16, 16, true, true));

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        if (person instanceof Professor) {
            Professor prof = (Professor) person;
            moduleCodes.getChildren().add(new Label(prof.getModuleCode().value));
            title.setText("Professor");
            renderRating(prof.getRating());
            year.setManaged(false);
            setSpecialisation(prof);
            setOfficeHour(prof);
        }
        if (person instanceof TeachingAssistant) {
            TeachingAssistant ta = (TeachingAssistant) person;
            moduleCodes.getChildren().add(new Label(((TeachingAssistant) person).getModuleCode().value));
            title.setText("Teaching\nAssistant");
            renderRating(ta.getRating());
            year.setManaged(false);
            specialisation.setManaged(false);
            officeHourPanel.setManaged(false);
        }
        if (person instanceof Student) {
            Student student = (Student) person;
            student.getModuleCodes().stream()
                    .sorted((m1, m2) -> -1 * m1.compareTo(m2))
                    .forEach(moduleCode -> moduleCodes.getChildren().add(new Label(moduleCode.value)));
            title.setText("Student");
            setYear(student);
            specialisation.setManaged(false);
            officeHourPanel.setManaged(false);
        }
    }

    private void setYear(Student student) {
        String studentYear = student.getYear().value;
        if (!studentYear.equals(Year.EMPTY_YEAR)) {
            year.setManaged(true);
            year.setText("Year: " + studentYear);
        } else {
            year.setManaged(false);
        }
    }

    private void setSpecialisation(Professor professor) {
        String profSpecialisation = professor.getSpecialisation().value;
        if (!profSpecialisation.equals(Specialisation.EMPTY_SPECIALISATION)) {
            specialisation.setManaged(true);
            specialisation.setText(profSpecialisation);
        } else {
            specialisation.setManaged(false);
        }
    }

    private void setUsername() {
        String username = person.getUsername().value;
        if (!username.equals(GithubUsername.DEFAULT_USERNAME)) {
            gitHubPanel.setManaged(true);
            githubImage.setImage(new Image(this.getClass().getResourceAsStream("/images/GitHub.png")));
            githubUsername.setText(person.getUsername().value);
        } else {
            gitHubPanel.setManaged(false);
        }
    }

    private void setOfficeHour(Professor professor) {
        String profOfficeHour = professor.getOfficeHour().value;
        if (!profOfficeHour.equals(OfficeHour.EMPTY_OFFICE_HOUR)) {
            officeHourPanel.setManaged(true);
            officeHourImage.setImage(new Image(this.getClass().getResourceAsStream("/images/clock.png")));
            officeHour.setText(profOfficeHour);
        } else {
            officeHourPanel.setManaged(false);
        }
    }


    private Image getGenderImage(Person person) {
        String gender = person.getGender().value;
        if (gender.equals("M")) {
            return new Image(this.getClass().getResourceAsStream("/images/maleicon.png"),
                    24, 24, true, true);
        } else {
            return new Image(this.getClass().getResourceAsStream("/images/femaleicon.png"),
                    24, 24, true, true);
        }
    }

    private void renderRating(Rating rating) {
        if (rating.value.equals(rating.EMPTY_RATING)) {
            stars.getChildren().clear();
        } else {
            fillStars(rating);
        }
    }

    private void fillStars(Rating rating) {
        int numberOfStars = Integer.valueOf(rating.value);
        for (int i = 0; i < numberOfStars; i++) {
            ImageView filledStarImage = new ImageView(
                    new Image(this.getClass().getResourceAsStream("/images/FilledStar.png"),
                            20, 20, true, true));
            stars.getChildren().add(filledStarImage);
        }
        for (int j = 0; j < 5 - numberOfStars; j++) {
            ImageView emptyStarImage = new ImageView(
                    new Image(this.getClass().getResourceAsStream("/images/emptystar.png"),
                            20, 20 , true, true));
            stars.getChildren().add(emptyStarImage);
        }
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
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
