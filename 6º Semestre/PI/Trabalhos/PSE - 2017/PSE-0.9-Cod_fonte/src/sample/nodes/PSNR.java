package sample.nodes;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import sample.workspace.Workspace;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;

/**
 * Calculates the Peak Signal-to-Noise Ration (PSNR) from
 * two images.
 * @author Adhonay, Daniel.
 * @since 2017-16-11
 */
public class PSNR extends NodeBox {

    private int emptyPos = 0;
    private final int INPUT_MAX = 2;
    private BufferedImage[] imagePeer = new BufferedImage[INPUT_MAX];

    /**
     * Setup the appearance: title, icon and workspace.
     *
     * @param title          NodeBox title
     * @param root           Root element that should contain this NodeBox.
     * @param actionIconName NodeBox icon path.
     */
    public PSNR(String title, Workspace root, String actionIconName) {
        super(title, root, actionIconName);
        getHeader().removeSupport();
        for( int i = 0; i < imagePeer.length ; ++i )
            imagePeer[i] = SwingFXUtils.fromFXImage(auxImg,null);
    }

    /**
     * Receives an image from another node.
     * @param image Input image
     */
    @Override
    public void update(Image image) {
        imagePeer[ this.emptyPos++ % 2 ] = SwingFXUtils.fromFXImage(image,null);

        if( getInputNumber() == INPUT_MAX ){

            if (imagePeer[0] == null || imagePeer[1] == null )
                System.out.println("Image NULL!");

            String result = PSNR(imagePeer[0], imagePeer[1]);

            Graphics2D g2d;

            BufferedImage bImg = new BufferedImage(80 , 60, BufferedImage.TYPE_INT_RGB);
            g2d = bImg.createGraphics();
            g2d.drawString(result+"",5   ,bImg.getHeight()/2);
            setImage(SwingFXUtils.toFXImage(bImg,null));
            System.out.printf("PSNR: %s\n",result);
            super.update(getImage());
        }
    }

    /**
     * Quick alias to get log in base 10.
     * @param x Input number.
     * @return Returns the log10(x).
     */
    public static double logbase10(double x) {
        return Math.log(x) / Math.log(10);
    }

    /**
     * Calculates the PSNR from two images.
     * @param im1 Image 1.
     * @param im2 Image 2.
     * @return Returns the PSNR.
     */
    public static String  PSNR(BufferedImage im1, BufferedImage im2) {
        assert(
                im1.getType() == im2.getType()
                        && im1.getHeight() == im2.getHeight()
                        && im1.getWidth() == im2.getWidth());

        double mse = 0;
        int width = im1.getWidth();
        int height = im1.getHeight();
        Raster r1 = im1.getRaster();
        Raster r2 = im2.getRaster();
        for (int j = 0; j < height; j++)
            for (int i = 0; i < width; i++)
                mse += Math.pow(r1.getSample(i, j, 0) - r2.getSample(i, j, 0), 2);

        mse /= (double) (width * height);
        double psnr = 10.0 * logbase10(Math.pow(255, 2) / mse);
        System.err.println("PSNR = " + psnr);
        return String.format("%.4f", psnr );
    }

    /**
     * Execute alias: converts the input image,
     * calls the algorithm, convert back, and
     * stores.
     */
    @Override
    public void execute() {
    }

    /**
     * Installs the node, i.e: adds into the workspace.
     */
    @Override
    public void install() {

    }
}
