// File: ImageFileInfoHandler.java
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class ImageFileInfoHandler extends FileInfoHandler {

    @Override
    public void printInfo(Path filePath) {
        try {
            BufferedImage image = ImageIO.read(filePath.toFile());
            Dimension size = new Dimension(image.getWidth(), image.getHeight());
            System.out.println(filePath + ": Image size - " + size.width + "x" + size.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// ... And similarly for TextFileInfoHandler and ProgramFileInfoHandler
