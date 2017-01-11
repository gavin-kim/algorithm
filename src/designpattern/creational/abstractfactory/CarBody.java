package designpattern.creational.abstractfactory;

public class CarBody implements Body {
    @Override
    public String getBodyParts() {
        return "Body shell parts for a car";
    }
}
