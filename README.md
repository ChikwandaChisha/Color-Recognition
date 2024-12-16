# **Webcam-Based Drawing and Region Finder**
## Author: Chikwanda Chisha

## **Overview**
This project implements a webcam-based interactive drawing and region-finding application using Java. It leverages algorithms like flood-fill for region detection, recoloring, and interactive painting. The program allows users to identify and highlight regions of interest in real-time webcam footage and interactively paint or manipulate images based on detected regions.

## **Features**
1. **Interactive Webcam Drawing**:
   - Display real-time webcam footage.
   - Identify and highlight regions of a selected target color using flood-fill algorithms.
   - Paint detected regions onto a blank canvas or save recolored and painted outputs.

2. **Region Finder**:
   - Detect regions of contiguous points with similar colors using flood-fill algorithms.
   - Recolor identified regions with random colors for visualization.
   - Identify and manipulate the largest region found in an image.

3. **Image Processing Utilities**:
   - Convert images to grayscale.
   - Implement lens effects for visual distortions.
   - Draw custom shapes like squares on images.

## **Files Description**
1. **CamPaint.java**:
   - Main application class for webcam-based drawing and painting.
   - Allows users to switch between webcam view, recolored image, and painted canvas.
   - Supports mouse interactions to select target colors and key presses for various functionalities like clearing the canvas or saving images.

2. **DrawingGUI.java**:
   - A utility class to handle GUI rendering and event handling.
   - Provides methods for drawing, handling mouse and keyboard inputs, and managing application events.

3. **ImageProcessor0.java**:
   - Contains image processing functionalities, such as:
     - Grayscale conversion.
     - Drawing shapes (e.g., squares) on images.
     - Lens effect to create visual distortions.

4. **RegionFinder.java**:
   - Implements the flood-fill algorithm to find and process regions in an image.
   - Includes methods to:
     - Identify regions of interest based on color similarity.
     - Highlight regions by recoloring them with random colors.
     - Extract the largest detected region.

5. **RegionsTest.java**:
   - A testing class for validating the functionality of `RegionFinder`.
   - Allows users to load images, specify target colors, and visualize recolored outputs.

6. **Webcam.java**:
   - A base class for handling webcam feed using JavaCV.
   - Provides functionality for capturing, mirroring, and scaling webcam frames.

7. **WebcamTest.java**:
   - A test class to validate the webcam functionality.
   - Ensures that the webcam feed can be captured and displayed correctly.

## **Dependencies**
- **Java Development Kit (JDK)**: Version 8 or higher.
- **JavaCV**: For handling webcam input and processing.
- **Swing**: For GUI rendering and interaction.

## **Usage**
1. **Run the Application**:
   - Compile and run `CamPaint.java` to start the webcam-based drawing tool.
   - Use the following controls:
     - **Mouse Press**: Select the target color to track.
     - **Keys**:
       - `w`: Display live webcam feed.
       - `r`: Display recolored image with detected regions.
       - `p`: Display the painted canvas.
       - `c`: Clear the painted canvas.
       - `s`: Save the painted image.
       - `o`: Save the recolored image.

2. **Test the Region Finder**:
   - Run `RegionsTest.java` with an image and a target color to visualize the identified regions.

3. **Test Webcam Functionality**:
   - Run `WebcamTest.java` to validate the webcam capture and display.


## **Acknowledgments**
Developed as part of Dartmouth CS10 coursework by (Contributors):
- Chris Bailey-Kellogg
- Other contributors listed in individual files.


