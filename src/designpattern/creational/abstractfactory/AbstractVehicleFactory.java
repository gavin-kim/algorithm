package designpattern.creational.abstractfactory;

public abstract class AbstractVehicleFactory {
    public abstract Body createBody();
    public abstract Chassis createChassis();
    public abstract Windows createWindows();
}
