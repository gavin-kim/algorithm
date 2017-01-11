package designpattern.creational.factorymethod;

/**
 * Static Factory Method (e.g. Calendar.getInstance() )
 *
 * advantages
 *
 * 1. Unlike constructors, It has a name BigInteger.probablePrime();
 * 2. It's NOT required to create a new object each time.
 *    (It can cache an object and reuse the object later)
 * 3. It can return any subtype of its type (return some child class)
 *
 * Factory Method : ImageCreator.createImage(arg)
 * return an object depending on the argument
 */

public class Example {

    public static void main(String[] args) {

        // JPGImage class object
        Image image = ImageCreator.createImage("jpg");

        // PNGImage class object
        Image image2 = ImageCreator.createImage("png");
    }
}
