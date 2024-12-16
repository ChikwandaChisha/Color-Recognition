import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProcessor0 {
    private BufferedImage image;		// the current image being processed

    /**
     * @param image		the original
     */
    public ImageProcessor0(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * @gray method changes the image color to gray
     */
    public void gray() { //changes the image color to gray
        // Nested loop over every pixel
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                // Get current color; scale each channel (but don't exceed 255); put new color
                Color color = new Color(image.getRGB(x, y));
                int red = 127;
                int green = 127;
                int blue = 127;
                Color newColor = new Color(red, green, blue);
                image.setRGB(x, y, newColor.getRGB());
            }
        }
    }
    public void drawSquare(int cx, int cy, int r, Color color) {
        // Nested loop over nearby pixels
        for (int y = Math.max(0, cy-r); y < Math.min(image.getHeight(), cy+r); y++) {
            for (int x = Math.max(0, cx-r); x < Math.min(image.getWidth(), cx+r); x++) {
                image.setRGB(x, y, color.getRGB());
            }
        }
    }
    public void lens(int cx, int cy, int r) {
        // Create a copy of the current image - need to be able to look at current as modify it
        BufferedImage result = createCopyResult();

        // Nested loop over every pixel
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                // Only do lens out to specified radius.
                double dist = 0.1* Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy)) / r;
                if (dist <= 1) {
                    // Determine source pixel by lens function, but constrain to image size.
                    int sx = (int) constrain(x + ((x - cx) * dist), 0, image.getWidth() - 1);
                    int sy = (int) constrain(y + ((y - cy) * dist), 0, image.getHeight() - 1);
                    result.setRGB(x, y, image.getRGB(sx, sy));
                }
            }
        }

        // Make the current image be this new image.
        image = result;
    }
    public static double constrain(double val, double min, double max) {
        if (val < min) {
            return min;
        }
        else if (val > max) {
            return max;
        }
        return val;
    }
    private BufferedImage createCopyResult() {
        return new BufferedImage(image.getColorModel(), image.copyData(null), image.getColorModel().isAlphaPremultiplied(), null);
    }
}
