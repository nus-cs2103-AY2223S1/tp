package seedu.address.model.item;

public enum EntryType {
    USER(false),
    TEAM(true),
    TASK(true);

    private boolean isOpenable;

    EntryType(boolean isOpenable) {
        this.isOpenable = isOpenable;
    }

    public boolean getIsOpenable() {
        return this.isOpenable;
    }
}
