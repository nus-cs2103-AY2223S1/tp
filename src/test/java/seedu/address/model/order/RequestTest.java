package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;

public class RequestTest {

    @Test
    public void getRequestedAge() {
        Age age = new Age(3);
        Color color = new Color("pink");
        ColorPattern colorPattern = new ColorPattern("polka-dots");
        Species species = new Species("chinchilla");

        Request request = new Request(age, color, colorPattern, species);
        assertEquals(request.getRequestedAge(), age);
        assertNotEquals(request.getRequestedAge(), new Age(66));
    }

    @Test
    public void getRequestedColor() {
        Age age = new Age(3);
        Color color = new Color("pink");
        ColorPattern colorPattern = new ColorPattern("polka-dots");
        Species species = new Species("chinchilla");

        Request request = new Request(age, color, colorPattern, species);
        assertEquals(request.getRequestedColor(), color);
        assertNotEquals(request.getRequestedColor(), new Color("sky-blue"));
    }

    @Test
    public void getRequestedColorPattern() {
        Age age = new Age(3);
        Color color = new Color("pink");
        ColorPattern colorPattern = new ColorPattern("polka-dots");
        Species species = new Species("chinchilla");

        Request request = new Request(age, color, colorPattern, species);
        assertEquals(request.getRequestedColorPattern(), colorPattern);
        assertNotEquals(request.getRequestedColorPattern(), new ColorPattern("stripes"));
    }

    @Test
    public void getRequestedSpecies() {
        Age age = new Age(3);
        Color color = new Color("pink");
        ColorPattern colorPattern = new ColorPattern("polka-dots");
        Species species = new Species("chinchilla");

        Request request = new Request(age, color, colorPattern, species);
        assertEquals(request.getRequestedSpecies(), species);
        assertNotEquals(request.getRequestedColorPattern(), new Species("capybara"));
    }

    @Test
    public void equals() {
        Age firstAge = new Age(3);
        Color firstColor = new Color("pink");
        ColorPattern firstColorPattern = new ColorPattern("polka-dots");
        Species firstSpecies = new Species("chinchilla");

        Request firstRequest = new Request(firstAge, firstColor, firstColorPattern, firstSpecies);

        Age secondAge = new Age(9);
        Color secondColor = new Color("turquoise");
        ColorPattern secondColorPattern = new ColorPattern("stripes");
        Species secondSpecies = new Species("parrot");

        Request secondRequest = new Request(secondAge, secondColor, secondColorPattern, secondSpecies);

        assertEquals(firstRequest, firstRequest);
        assertNotEquals(firstRequest, 1);
        assertNotEquals(firstRequest, null);

        Request firstRequestCopy = new Request(firstAge, firstColor, firstColorPattern, firstSpecies);
        assertEquals(firstRequest, firstRequestCopy);
        assertNotEquals(firstRequest, secondRequest);
    }

    @Test
    public void equals_differentAge_returnsFalse() {
        Age firstAge = new Age(3);
        Color firstColor = new Color("pink");
        ColorPattern firstColorPattern = new ColorPattern("polka-dots");
        Species firstSpecies = new Species("chinchilla");

        Request firstRequest = new Request(firstAge, firstColor, firstColorPattern, firstSpecies);

        Age secondAge = new Age(9);
        Color secondColor = new Color("pink");
        ColorPattern secondColorPattern = new ColorPattern("polka-dots");
        Species secondSpecies = new Species("chinchilla");

        Request secondRequest = new Request(secondAge, secondColor, secondColorPattern, secondSpecies);

        assertNotEquals(firstRequest, secondRequest);
    }

    @Test
    public void equals_differentColor_returnsFalse() {
        Age firstAge = new Age(3);
        Color firstColor = new Color("pink");
        ColorPattern firstColorPattern = new ColorPattern("polka-dots");
        Species firstSpecies = new Species("chinchilla");

        Request firstRequest = new Request(firstAge, firstColor, firstColorPattern, firstSpecies);

        Age secondAge = new Age(3);
        Color secondColor = new Color("magenta");
        ColorPattern secondColorPattern = new ColorPattern("polka-dots");
        Species secondSpecies = new Species("chinchilla");

        Request secondRequest = new Request(secondAge, secondColor, secondColorPattern, secondSpecies);

        assertNotEquals(firstRequest, secondRequest);
    }

    @Test
    public void equals_differentColorPattern_returnsFalse() {
        Age firstAge = new Age(3);
        Color firstColor = new Color("pink");
        ColorPattern firstColorPattern = new ColorPattern("polka-dots");
        Species firstSpecies = new Species("chinchilla");

        Request firstRequest = new Request(firstAge, firstColor, firstColorPattern, firstSpecies);

        Age secondAge = new Age(3);
        Color secondColor = new Color("pink");
        ColorPattern secondColorPattern = new ColorPattern("streaks");
        Species secondSpecies = new Species("chinchilla");

        Request secondRequest = new Request(secondAge, secondColor, secondColorPattern, secondSpecies);

        assertNotEquals(firstRequest, secondRequest);
    }

    @Test
    public void equals_differentSpecies_returnsFalse() {
        Age firstAge = new Age(3);
        Color firstColor = new Color("pink");
        ColorPattern firstColorPattern = new ColorPattern("streaks");
        Species firstSpecies = new Species("chinchilla");

        Request firstRequest = new Request(firstAge, firstColor, firstColorPattern, firstSpecies);

        Age secondAge = new Age(3);
        Color secondColor = new Color("pink");
        ColorPattern secondColorPattern = new ColorPattern("streaks");
        Species secondSpecies = new Species("hamster");

        Request secondRequest = new Request(secondAge, secondColor, secondColorPattern, secondSpecies);

        assertNotEquals(firstRequest, secondRequest);
    }
}
