package seedu.address.logic.commands.iteration;

import seedu.address.model.StorageStub;

class StorageWithImageStub extends StorageStub {
    @Override
    public String saveIterationImage(String src) {
        return "/images/placeholderart.png";
    }
}
