package seedu.address.logic.commands.iteration;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import seedu.address.model.StorageStub;

class StorageWithImageStub extends StorageStub {
    public static final String BASE_DIRECTORY = System.getProperty("user.dir") + "/src/test/data/images/";
    private final Map<Path, BufferedImage> images = new HashMap<>();
    @Override
    public Path getRandomImagePath() {
        return Paths.get(BASE_DIRECTORY, images.size() + 1 + ".png");
    }

    public Path getCurrentPath() {
        return Paths.get(BASE_DIRECTORY, images.size() + ".png");
    }
    @Override
    public void saveImage(BufferedImage image, Path path) {
        images.put(path, image);
    }

    @Override
    public BufferedImage getImage(Path path) throws IOException {
        if (!images.containsKey(path)) {
            throw new IOException("Image not found");
        }
        return images.get(path);
    }
}
