import java.awt.*;
import java.awt.image.*;
import java.util.*;

/**
 * Region growing algorithm: finds and holds regions in an image.
 * Each region is a list of contiguous points with colors similar to a target color.
 * Scaffold for PS-1, Dartmouth CS 10, Fall 2016
 *
 * @author Chikwanda Chisha
 */
public class RegionFinder {
    private static final int maxColorDiff = 20;				// how similar a pixel color must be to the target color, to belong to a region
    private static final int minRegion = 50;// how many points in a region to be worth considering
    private static BufferedImage image;                            // the image in which to find regions
    private BufferedImage recoloredImage; // the image with identified regions recolored

    private ArrayList<ArrayList<Point>> regions;			// a region is a list of points
    // so the identified regions are in a list of lists of points

    public RegionFinder() {
        this.image = null;
        regions = new ArrayList<>();
    }

    public RegionFinder(BufferedImage image) {
        this.image = image;
        regions = new ArrayList<>();
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage getRecoloredImage() {
        return recoloredImage;
    }

    /**
     * Sets regions to the flood-fill regions in the image, similar enough to the trackColor.
     */
    public void findRegions(Color targetColor){
        // TODO: YOUR CODE HERE
        BufferedImage visited = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        // looping over all the pixels in the image

        for (int y = 0; y <  image.getHeight(); y++){
            for (int x = 0; x < image.getWidth(); x++){

                // check if the pixel is unvisited and of the correct color
                if (colorMatch(targetColor, new Color(image.getRGB(x, y))) && visited.getRGB(x, y) == 0){
                    ArrayList<Point> region = new ArrayList<>();  // start a new region everytime an unvisited pixel with the targetColor is visited
                    ArrayList<Point> toVisit = new ArrayList<>(); // create new visited array each time new region arraylist is created
                    toVisit.add(new Point(x, y));

                    // loop over as long as visited is not empty
                    while (!toVisit.isEmpty()){
                        Point presentPixel =  toVisit.remove(toVisit.size()-1); // remove pixel from the end of the arraylist
                        region.add(presentPixel);
                        visited.setRGB(presentPixel.x, presentPixel.y, 1); //mark pixel as visited

                        // loop around Point pixel.
                        for (int j = Math.max(presentPixel.y-1, 0); j <= Math.min(presentPixel.y+1, image.getHeight()-1); j++){
                            for (int i = Math.max(presentPixel.x-1, 0); i <= Math.min(presentPixel.x+1, image.getWidth()-1); i++) {
                                 // check if pixel is unvisited and of the right color
                                if (colorMatch(targetColor, new Color(image.getRGB(i, j))) && visited.getRGB(i, j) == 0) {
                                    toVisit.add(new Point(i, j));
                                }
                            }
                        }
                    }
                    if (region.size() >= minRegion){
                        regions.add(region);
                    }
                }
            }
        }
    }


    /**
     * Tests whether the two colors are "similar enough" (your definition, subject to the maxColorDiff threshold, which you can vary).
     */
    private static boolean colorMatch(Color c1, Color c2) {
        // TODO: YOUR CODE HERE
        int diff = Math.abs(c1.getRed() - c2.getRed()) + Math.abs(c1.getBlue() - c2.getBlue()) + Math.abs(c1.getGreen() - c2.getGreen());

        return diff <= maxColorDiff; // return boolean value of statement
    }

    /**
     * Returns the largest region detected (if any region has been detected)
     */
    public ArrayList<Point> largestRegion() {
        // TODO: YOUR CODE HERE
        int min = 0;
        ArrayList<Point> largest = new ArrayList<>();
            for (ArrayList<Point> region: regions)
                if (region.size() > min){
                    min = region.size();
                    largest = region;
                }
        return largest;
    }

    /**
     * Sets recoloredImage to be a copy of image,
     * but with each region a uniform random color,
     * so we can see where they are
     */

    public void recolorImage() {
        // First copy the original
        recoloredImage = new BufferedImage(image.getColorModel(), image.copyData(null), image.getColorModel().isAlphaPremultiplied(), null);
        // Now recolor the regions in it
        // TODO: YOUR CODE HERE
        // loop over points of each region
            for (ArrayList<Point> arrayList : regions) {
                int color = (int)(Math.random()*(255*255*255)); // to have a random color for each region
                for (Point point : arrayList) {
                    recoloredImage.setRGB(point.x, point.y, color);
                }
            }
    }
}