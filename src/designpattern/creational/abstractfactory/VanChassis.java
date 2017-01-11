package designpattern.creational.abstractfactory;

public class VanChassis implements Chassis {
    @Override
    public String getChassisParts() {
        return "Chassis parts for a van";
    }
}
