package designpattern.creational.abstractfactory;

/**
 * Purpose: Provide factory interfaces to create related parts
 *
 *          Body, Chassis and Windows are shared but implemented differently
 *
 *          Body - CarBody
 *               - VanBody
 *
 *          Abstract Factory: provides Body, Chassis and Windows
 *
 *          CarFactory class: provides CarBody, CarChassis and CarWindows
 *          VanFactory class: provides VanBody, VanChassis and VanWindows
 *
 *          But All factory returns Body, Chassis and Windows interface types
 *
 *          A clients chooses a particular factory then the factory provides related parts
 */



public class Example {

    public static void main(String[] args) {

        String choice = "car"; // car or van

        AbstractVehicleFactory factory = null;

        /** get a factory object by user choice */
        switch (choice) {
            case "car":
                factory = new CarFactory();
                break;
            case "van":
                factory = new VanFactory();
                break;
            default:
                return;
        }

        Body vehicleBody = factory.createBody();
        Chassis vehicleChassis = factory.createChassis();
        Windows vehicleWindows = factory.createWindows();

        System.out.println(vehicleBody.getBodyParts());
        System.out.println(vehicleChassis.getChassisParts());
        System.out.println(vehicleWindows.getWindowParts());

    }
}
