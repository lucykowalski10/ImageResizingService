import com.image.service.ImageResizer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

public class ImageResizerTest {

    private final static String SRC_IMG_DIR = "https://s3.eu-west-2.amazonaws.com/bucket-10/resources/sourceImages/";
    private final static String VALID_TYPE_NAME = "thumbnail";
    private final static String VALID_FILE_NAME = "c.jpg";

    @Test
    public void resizeImageTest() throws IOException {

        URL sourceImageUrl = new URL( SRC_IMG_DIR + VALID_FILE_NAME);
        BufferedImage sourceImage = ImageIO.read(sourceImageUrl);

        ImageResizer imageResizer = new ImageResizer();
        byte[] bytes = imageResizer.resizeImage(sourceImage, VALID_TYPE_NAME);

        Assertions.assertNotEquals(null, bytes);
    }
}
