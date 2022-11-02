package seedu.address.storage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import seedu.address.model.social.Social;

/**
 * Storage class for the profile picture.
 */
public class PictureStorage {
    private final int displayedIndex;
    private final Social social;
    private final String newImageUrl;

    /**
     * Constructor for PictureStorage
     * @param displayedIndex the index of the person to save.
     * @param social the Social class.
     * @param newImageUrl the image url to be saved.
     */
    public PictureStorage(int displayedIndex, Social social, String newImageUrl) {
        this.displayedIndex = displayedIndex;
        this.social = social;
        this.newImageUrl = newImageUrl;
    }

    /**
     * Saves the updated profile picture to the JSON file.
     * @throws IOException
     * @throws ParseException
     */
    public void saveStorage() throws IOException, ParseException {
        Reader reader = new FileReader("data/univusal.json");
        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(reader);
        JSONArray data2 = (JSONArray) data.get("persons");
        JSONObject data3 = (JSONObject) data2.get(displayedIndex - 1);
        data3.put("social", social.toString());
        @SuppressWarnings("resource")
        FileWriter file2 = new FileWriter("data/univusal.json");
        file2.write(data.toJSONString());
        file2.flush();
    }
}
