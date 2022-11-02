package seedu.address.storage;

/**
 * Storage loader class for the profile picture.
 */
public class PictureStorageLoader {
    private final String occupation;


    /**
     * Constructor for PictureStorage
     * @param occupation the occupation provided.
     */
    public PictureStorageLoader(String occupation) {
        this.occupation = occupation;
    }

    public String getImageUrl() {
        switch (occupation) {
        case "STUDENT": {
            return "/images/student_pic.png";
        }
        case "PROFESSOR": {
            return "/images/professor_pic.png";
        }
        case "TA": {
            return "/images/ta_pic.png";
        }
        case "NONE": {
            return "/images/default_pic.png";
        }
        default: {
            return null;
        }
        }
    }
}

