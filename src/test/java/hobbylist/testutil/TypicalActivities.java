package hobbylist.testutil;

import static hobbylist.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ANIME;
import static hobbylist.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOXING;
import static hobbylist.logic.commands.CommandTestUtil.VALID_NAME_ANIME;
import static hobbylist.logic.commands.CommandTestUtil.VALID_NAME_BOXING;
import static hobbylist.logic.commands.CommandTestUtil.VALID_TAG_ENTERTAINMENT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hobbylist.model.HobbyList;
import hobbylist.model.activity.Activity;

/**
 * A utility class containing a list of {@code Activity} objects to be used in tests.
 */
public class TypicalActivities {

    public static final Activity ACTIVITY_A = new ActivityBuilder().withName("And Then There Were None")
            .withDescription("Mystery novel by Agatha Christie")
            .withTags("book").build();
    public static final Activity ACTIVITY_B = new ActivityBuilder().withName("Battlefield 4")
            .withDescription("First person shooter by EA")
            .withTags("video_game").withReview("decent game").build();
    public static final Activity ACTIVITY_C = new ActivityBuilder().withName("Charlotte")
            .withDescription("Comedy drama anime").withReview("decent anime with weird ending").build();
    public static final Activity ACTIVITY_D = new ActivityBuilder().withName("Despicable Me 3")
        .withDescription("Comedy film directed by Pierre Coffin and Kyle Balda").withTags("movie").build();
    public static final Activity ACTIVITY_E = new ActivityBuilder().withName("Exercise")
            .withDescription("100 push-ups, 100 sit-ups, 100 squats and 10km run").build();
    public static final Activity ACTIVITY_F = new ActivityBuilder().withName("Fried Chicken")
            .withDescription("At some place").build();
    public static final Activity ACTIVITY_G = new ActivityBuilder().withName("Golf").withRating(2)
            .withDescription("At Marina Bay Golf Course").withStatus("COMPLETED").build();

    // Manually added
    public static final Activity ACTIVITY_H = new ActivityBuilder().withName("House of Cards")
            .withDescription("Featuring Kevin Spacey").build();
    public static final Activity ACTIVITY_I = new ActivityBuilder().withName("Indiana Jones Raiders of the Lost Ark")
            .withDescription("First Indiana Jones movie").build();

    // Manually added - Activity's details found in {@code CommandTestUtil}
    public static final Activity ANIME = new ActivityBuilder().withName(VALID_NAME_ANIME)
            .withDescription(VALID_DESCRIPTION_ANIME).withTags(VALID_TAG_ENTERTAINMENT).build();
    public static final Activity BOXING = new ActivityBuilder().withName(VALID_NAME_BOXING)
            .withDescription(VALID_DESCRIPTION_BOXING).withTags(VALID_TAG_ENTERTAINMENT, VALID_TAG_ENTERTAINMENT)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalActivities() {} // prevents instantiation

    /**
     * Returns an {@code HobbyList} with all the typical activities.
     */
    public static HobbyList getTypicalHobbyList() {
        HobbyList ab = new HobbyList();
        for (Activity activity : getTypicalActivities()) {
            ab.addActivity(activity);
        }
        return ab;
    }

    public static List<Activity> getTypicalActivities() {
        return new ArrayList<>(Arrays.asList(ACTIVITY_A, ACTIVITY_B, ACTIVITY_C, ACTIVITY_D, ACTIVITY_E, ACTIVITY_F,
                ACTIVITY_G));
    }
}
