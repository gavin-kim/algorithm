package designpattern.creational.factorymethod;

public abstract class ImageCreator {

    public static Image createImage(String fileName) {
        if (fileName.endsWith("jpg"))
            return new JPGImage(fileName);
        else if (fileName.endsWith("png"))
            return new PNGImage(fileName);
        else
            return null;


    }
}
