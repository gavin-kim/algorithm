package designpattern.structural.decorator;

public class WiFi extends Decorator {

    public WiFi(EBook eBook) {
        super(eBook);
    }

    @Override
    public void powerOn() {
        super.powerOn();
        System.out.println("Scan for Wi-Fi networks");
    }

    public void shareData() {
        System.out.println("Wifi new feature share data");
    }
}
