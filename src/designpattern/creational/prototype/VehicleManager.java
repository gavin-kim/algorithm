package designpattern.creational.prototype;

public class VehicleManager {

    private Vehicle saloon, coupe, sport, boxVan, pickup;

    public VehicleManager() {
        saloon = new Saloon();
        coupe = new Coupe();
        sport = new Sport();
        boxVan = new BoxVan();
        pickup = new Pickup();
    }

    public Vehicle createSaloon() {
        return (Vehicle) saloon.clone();
    }

    public Vehicle createCoupe() {
        return (Vehicle) coupe.clone();
    }

    public Vehicle createSport() {
        return (Vehicle) sport.clone();
    }

    public Vehicle createBoxVan() {
        return (Vehicle) boxVan.clone();
    }

    public Vehicle createPickup() {
        return (Vehicle) pickup.clone();
    }
}
