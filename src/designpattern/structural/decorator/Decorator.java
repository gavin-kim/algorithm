package designpattern.structural.decorator;

public abstract class Decorator implements EBook {

    private EBook component;

    public Decorator(EBook eBook) {
        component = eBook;
    }

    @Override
    public void powerOn() {
        component.powerOn();
    }

    @Override
    public void powerOff() {
        component.powerOff();
    }

    @Override
    public void nextPage() {
        component.nextPage();
    }
}
