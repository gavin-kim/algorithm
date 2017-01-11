package designpattern.creational.abstractfactory;

public class CarChassis implements Chassis {
    @Override
    public String getChassisParts() {
        return "Chassis parts for a car";
    }
}
