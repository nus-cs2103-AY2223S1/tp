package foodwhere.ui;

import static foodwhere.commons.util.CollectionUtil.requireAllNonNull;

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

    /**
     * Enum to decide what content is copied.
     */
    public enum Action {
        FIELDS_ADDRESS, FIELDS_CONTENT, FIELDS_DATE, FIELDS_NAME, FIELDS_TAG
    }

    private final T item;
    private Clipboard clipboard;
    private Action action;

    /**
     * Every field must be present and not null.
     */
    public CopyMenuItem(String tag, T item, Clipboard clipboard, Action action) {
        super(tag);
        requireAllNonNull(tag, item, clipboard, action);
        this.item = item;
        this.clipboard = clipboard;
        this.action = action;
        this.setOnAction();
    }

    private void setError() {
        throw new RuntimeException("Invalid type!");
    }

    /**
     * Sets the content of the clipboard according to the type of the item
     */
    public void setOnAction() {
        ClipboardContent content = new ClipboardContent();

        switch(this.action) {
        case FIELDS_ADDRESS:
            if (this.item instanceof Stall) {
                Stall stallItem = (Stall) this.item;
                content.putString(stallItem.getAddress().toString());
            } else {
                setError();
            }
            break;
        case FIELDS_CONTENT:
            if (this.item instanceof Review) {
                Review reviewItem = (Review) this.item;
                content.putString(reviewItem.getContent().toString());
            } else {
                setError();
            }
            break;
        case FIELDS_DATE:
            if (this.item instanceof Review) {
                Review reviewItem = (Review) this.item;
                content.putString(reviewItem.getDate().toString());
            } else {
                setError();
            }
            break;
        case FIELDS_NAME:
            if (this.item instanceof Review) {
                Review reviewItem = (Review) this.item;
                content.putString(reviewItem.getName().toString());
            } else if (this.item instanceof Stall) {
                Stall stallItem = (Stall) this.item;
                content.putString(stallItem.getName().toString());
            } else {
                setError();
            }
            break;
        case FIELDS_TAG:
            if (this.item instanceof Review) {
                Review reviewItem = (Review) this.item;
                content.putString(reviewItem.getTags().toString());
            } else if (this.item instanceof Stall) {
                Stall stallItem = (Stall) this.item;
                content.putString(stallItem.getTags().toString());
            } else {
                setError();
            }
            break;
        default:
            setError();
            break;
        }

        if (content.hasString()) {
            super.setOnAction((ActionEvent actionEvent) -> {
                this.clipboard.setContent(content);
            });
        }
    }

}
