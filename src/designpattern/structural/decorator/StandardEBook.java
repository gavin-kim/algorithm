package designpattern.structural.decorator;

public class StandardEBook implements EBook {
    @Override
    public void powerOn() {
        System.out.println("Power on Standard E-Book");
    }

    @Override
    public void powerOff() {
        System.out.println("Power off Standard E-Book");
    }

    @Override
    public void nextPage() {
        System.out.println("Next page Standard E-Book");
    }
}
