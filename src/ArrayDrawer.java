
import java.awt.*;

/**
 * This class walks the user through iteration through arrays with for loops!
 */
public class ArrayDrawer extends Canvas {
    private Color[] dataset;
    private Graphics graphics;

    /*
    Constructor for array drawer. It sets dataset to be what is passed from the declaration and it uses it to instantiate the field. It also calls the constructor of Canvas.
     */
    public ArrayDrawer(Color[][] input) {
        super();
        dataset = input[0];
        /*Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Sorting!");
                bubbleSort(dataset);
            }
        };
        r.run();*/
        bubbleSort(dataset);
    }

    private void bubbleSort(Color[] dataset) {

    }

    public void refresh(Color[] input){
        this.dataset = input;
        invalidate();
        setIgnoreRepaint(false);
        repaint();
    }

    /*
        This method draws stuff.

        Here, our goal is to draw the Colors from dataset, a 2d array of Color objects.

        We must use for loops to iterate through the array and draw the image.
     */
    public void paint(Graphics g) {
        this.graphics = g;
        int pixelsPerItemVer = getHeight() / dataset.length;
        for (int i = 0; i < dataset.length; i++) {                              //We cannot use a foreach loop here because we must use the numbers i and j for scaling
            /*int lengthAtThisIndex = dataset[i].length;                          //Calculate the length of the array at this index to size the color accordingly
            int pixelsPerItemHorAtIndex = getWidth() / lengthAtThisIndex;       //Calculating how many pixels each color will take up
            for (int j = 0; j < dataset[i].length; j++) {                    */   //Nested for loop to iterate through the array at this height
                g.setColor(dataset[i]);                                      //Set the color to the one a this index of the color array
                g.fillRect(i * pixelsPerItemVer, 0, getWidth() / dataset.length,
                        getHeight());                    //Draw the rectangle. We multiply the index by the pixels per item to get the position.
            }
        }


}