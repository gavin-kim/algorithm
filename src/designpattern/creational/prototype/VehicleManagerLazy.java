package designpattern.creational.prototype;

public class VehicleManagerLazy {

    private Vehicle saloon, coupe, sport, boxVan, pickup;

    public VehicleManagerLazy() {
    }

    public Vehicle createSaloon() {
        if (saloon == null) {
            saloon = new Saloon();
            return (Vehicle) saloon.clone();
        } else {
            return (Vehicle) saloon.clone();
        }
    }

    public Vehicle createCoupe() {
        if (coupe == null) {
            coupe = new Coupe();
            return (Vehicle) coupe.clone();
        } else {
            return (Vehicle) coupe.clone();
        }
    }

    public Vehicle createSport() {
        if (sport == null) {
            sport = new Sport();
            return (Vehicle) sport.clone();
        } else {
            return (Vehicle) sport.clone();
        }
    }

    public Vehicle createBoxVan() {
        if (boxVan == null) {
            boxVan = new BoxVan();
            return (Vehicle) boxVan.clone();
        } else {
            return (Vehicle) boxVan.clone();
        }
    }

    public Vehicle createPickup() {
        if (pickup == null) {
            pickup = new Pickup();
            return (Vehicle) pickup.clone();
        } else {
            return (Vehicle) pickup.clone();
        }
    }
}
