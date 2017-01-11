package designpattern.creational.abstractfactory;

public class VanWindows implements Windows {
    @Override
    public String getWindowParts() {
        return "Window glassware for a van";
    }
}
