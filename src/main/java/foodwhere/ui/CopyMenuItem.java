package foodwhere.ui;

import static foodwhere.commons.util.CollectionUtil.requireAllNonNull;

import java.util.logging.Logger;

import foodwhere.commons.core.LogsCenter;
import foodwhere.model.review.Review;
import foodwhere.model.stall.Stall;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 * MenuItem that is used to copy content to clipboard.
 */
public class CopyMenuItem<T> extends MenuItem {

    private static final Logger logger = LogsCenter.getLogger(CopyMenuItem.class);

    /**
     * Enum to decide what content is copied.
     */
    public enum Action {
        FIELDS_ADDRESS, FIELDS_CONTENT, FIELDS_DATE, FIELDS_NAME, FIELDS_RATING, FIELDS_TAG;

        /**
         * Returns the content of the Stall according to the type.
         *
         * @param stallItem Stall object.
         * @param action Type of content to return.
         * @return String content to be copied to clipboard.
         */
        public String describeStall(Stall stallItem, Action action, String tag) {
            switch(action) {
            case FIELDS_ADDRESS:
                return stallItem.getAddress().toString();
            case FIELDS_NAME:
                return stallItem.getName().toString();
            case FIELDS_TAG:
                return stallItem.getTagString();
            case FIELDS_CONTENT:
                // fallthrough
            case FIELDS_DATE:
                // fallthrough
            case FIELDS_RATING:
                // fallthrough
            default:
                CopyMenuItem.throwRunTimeError(action, tag, stallItem.getClass().getName());
                break;
            }

            // should not reach here.
            return null;
        }

        /**
         * Returns the content of the Review according to the type.
         *
         * @param reviewItem Review object.
         * @param action Type of content to return.
         * @return String content to be copied to clipboard.
         */
        public String describeReview(Review reviewItem, Action action, String tag) {
            switch(action) {
            case FIELDS_CONTENT:
                return reviewItem.getContent().toString();
            case FIELDS_DATE:
                return reviewItem.getDate().toString();
            case FIELDS_NAME:
                return reviewItem.getName().toString();
            case FIELDS_RATING:
                return reviewItem.getRating().toString();
            case FIELDS_TAG:
                return reviewItem.getTagString();
            case FIELDS_ADDRESS:
                return reviewItem.getAddress().toString();
            default:
                CopyMenuItem.throwRunTimeError(action, tag, reviewItem.getClass().getName());
                break;
            }

            // should not reach here.
            return null;
        }
    }

    private final T item;
    private final Clipboard clipboard;
    private final Action action;
    private final String tag;

    /**
     * Every field must be present and not null.
     */
    public CopyMenuItem(String tag, T item, Clipboard clipboard, Action action) {
        super(tag);
        requireAllNonNull(tag, item, clipboard, action);
        this.item = item;
        this.clipboard = clipboard;
        this.action = action;
        this.tag = tag;
        this.setOnAction();
    }

    private static void throwRunTimeError(Action action, String tag, String className) {
        String errorFormat = "Invalid type of object passed in. The Action passed in is %s, the Tag is %s "
                + "and the Type of object is %s";
        String errorMessage = String.format(errorFormat, action, tag, className);
        logger.info(errorMessage);
        throw new RuntimeException(errorMessage);
    }

    /**
     * Sets the content of the clipboard according to the type of the item.
     */
    public void setOnAction() {
        ClipboardContent content = new ClipboardContent();

        String result = "";
        if (this.item instanceof Stall) {
            result = this.action.describeStall((Stall) this.item, this.action, this.tag);
        } else if (this.item instanceof Review) {
            result = this.action.describeReview((Review) this.item, this.action, this.tag);
        } else {
            CopyMenuItem.throwRunTimeError(this.action, this.tag, this.item.getClass().getName());
        }

        if (result != null && !result.isEmpty()) {
            content.putString(result);
            super.setOnAction((ActionEvent actionEvent) -> this.clipboard.setContent(content));
        }
    }

}

