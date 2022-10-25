package seedu.nutrigoals.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * TipList class to store all tips and the random tip generator.
 * Meant for developer use as a built-in repository of healthy-lifestyle
 * tips.
 */
public class TipList {
    private final ArrayList<Tip> listOfTips = new ArrayList<>();
    private final Random r = new Random();

    /**
     * Constructs a {@code TipList} with a default list of tips.
     */
    public TipList() {
        Tip tip1 = new Tip("Opt for unsweetened versions of your favourite beverages!\n"
                + "Drink stalls in canteens around NUS will readily provide 'siew dai'\n"
                + "(less sugar) versions of your go-to drinks!");
        Tip tip2 = new Tip("NUS offers a total of 44 sport CCAs! Join one (or more) to get your\n"
                + "weekly fix of exercise and make new friends!");
        Tip tip3 = new Tip("NUS boasts a host of sports facilities - from a swimming pool\n"
                + "to badminton courts! Use the REBOKS system to book a sports facility\n"
                + "and get active!");
        Tip tip4 = new Tip("Occupying an area of 1.7 square kilometers, NUS' Kent Ridge Campus\n"
                + "is rather small... Opt to walk to your destinations to beat the traffic\n"
                + "and get your steps in!");
        Tip tip5 = new Tip("Don't like to run on a track? Fret not - NUS' Kent Ridge campus\n"
                + "has a perimeter that is approximately 4.5km long! Add in the hilly terrain\n"
                + "and you've got a route for your weekly run!");
        Tip tip6 = new Tip("Tummy growling for some food? Look out for the healthier choice logo\n"
                + "at stalls across NUS canteens for a healthier diet!");
        Tip tip7 = new Tip("Want to add on a few pounds of muscle? NUS has a total of 4 gyms\n"
                + "that are free-to-use for NUS students and staff!");
        Tip tip8 = new Tip("More than sports, NUS offers a wide variety of services from\n"
                + "health check-ups to mental wellness events. Make use of these services\n"
                + "for a better you!");
        Tip tip9 = new Tip("Like how NUS is partially situated on a hill, your classes might\n"
                + "be at the upper floors of your faculties. Take the stairs instead of the\n"
                + "elevator for a short workout before your next lesson!");
        Tip tip10 = new Tip("Did you know that there are 159 water coolers on NUS' Kent Ridge\n"
                + "campus (and counting!)? Use these to stay hydrated throughout the day!");
        listOfTips.add(tip1);
        listOfTips.add(tip2);
        listOfTips.add(tip3);
        listOfTips.add(tip4);
        listOfTips.add(tip5);
        listOfTips.add(tip6);
        listOfTips.add(tip7);
        listOfTips.add(tip8);
        listOfTips.add(tip9);
        listOfTips.add(tip10);
    }

    /**
     * Generates a random index number and retrieves the tip at that index number from
     * listOfTips.
     *
     * @return a randomly generated tip
     */
    public Tip generateTip() {
        int randomIndex = r.nextInt(listOfTips.size());
        return listOfTips.get(randomIndex);
    }
}
