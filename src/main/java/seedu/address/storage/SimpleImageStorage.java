package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import javax.imageio.ImageIO;

import seedu.address.commons.core.Messages;

/**
 * A simple implementation of ImageStorage which supports reading and saving images
 * to the given `baseDirectoryPath`
 */
public class SimpleImageStorage implements ImageStorage {
    private final Path baseDirectoryPath;

    public SimpleImageStorage(Path baseDirectoryPath) {
        this.baseDirectoryPath = baseDirectoryPath;
    }

    public Path getBaseDirectoryPath() {
        return baseDirectoryPath;
    }

    @Override
    public Path getRandomImagePath() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        Path randomPath;
        do {
            String randomName = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
            randomPath = Paths.get(baseDirectoryPath.toString(),
                    randomName + ".png");
        } while (new File(randomPath.toUri()).exists());
        return randomPath;
    }

    @Override
    public boolean isPathInBaseDirectory(Path path) {
        requireNonNull(path);
        URI imageUri = path.toUri();
        URI relativeUri = this.getBaseDirectoryPath().toUri().relativize(imageUri);
        return !relativeUri.equals(imageUri);
    }

    @Override
    public boolean isValidImage(Path path) {
        if (Files.exists(path)) {
            try {
                String mimeType = Files.probeContentType(path);
                return ImageIO.getImageWritersByMIMEType(mimeType).hasNext();
            } catch (IllegalArgumentException e) {
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * @param imagePath location of the image. Cannot be null
     * @throws IOException if the file format is not as expected.
     */
    public BufferedImage getImage(Path imagePath) throws IOException {
        File imageFile = new File(imagePath.toUri());
        FileInputStream fis = new FileInputStream(imageFile);
        if (!Files.exists(imagePath)) {
            throw new IOException(Messages.MESSAGE_NONEXISTENT_IMAGE_PATH);
        }
        try {
            String fileContentType = Files.probeContentType(imagePath);
            ImageIO.getImageReadersByMIMEType(fileContentType);
            BufferedImage image = ImageIO.read(fis);
            if (image == null) {
                throw new IOException(Messages.MESSAGE_NOT_AN_IMAGE);
            }
            return image;
        } catch (IllegalArgumentException e) {
            throw new IOException(Messages.MESSAGE_NOT_AN_IMAGE);
        }
    }

    @Override
    public void saveImage(BufferedImage image, Path imagePath) throws IOException {
        requireAllNonNull(image, imagePath);
        assert isPathInBaseDirectory(imagePath);
        Files.createDirectories(imagePath);
        File outputFile = new File(imagePath.toUri());
        ImageIO.write(image, "png", outputFile);
    }
}
