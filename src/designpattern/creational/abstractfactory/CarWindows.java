package designpattern.creational.abstractfactory;

public class CarWindows implements Windows {
    @Override
    public String getWindowParts() {
        return "Window glassware for a car";
    }
}
