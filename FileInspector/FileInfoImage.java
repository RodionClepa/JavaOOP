package FileInspector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileInfoImage extends FileInfo {

    @Override
    public void getDetailInfo(String filePathString){
        try {
            File imageFile = new File(filePathString);
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth();
            int height = image.getHeight();
            System.out.println("Image Width: " + width);
            System.out.println("Image Height: " + height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
