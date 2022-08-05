package Sprites;

import Interfaces.Sprite;
import biuoop.DrawSurface;


import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * The type Back ground from image.
 */
public class ImageBackground implements Sprite {
    private BufferedImage image;
    private String path;

    /**
     * Instantiates a new Back ground from image.
     *
     * @param p in the path to the background image
     */
    public ImageBackground(String p) {
//        System.out.println("Before Trim: " + p);
        p = p.trim();
//        this.path = "./resources/" + p;
        this.path = p;
//        System.out.println("After Trim: " + this.path);
        try {
            this.image = ImageIO.read(new File(this.path));
        } catch (Exception e) {
            System.out.println("Failed loading Background Image");
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
    }

    @Override
    public void timePassed() {

    }
}