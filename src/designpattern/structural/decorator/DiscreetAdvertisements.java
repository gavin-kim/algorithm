package designpattern.structural.decorator;

public class DiscreetAdvertisements extends Decorator {

    public DiscreetAdvertisements(EBook eBook) {
        super(eBook);
    }

    @Override
    public void nextPage() {
        super.nextPage();
        System.out.println("Advertisement: Buy more stuff today!");
    }
}
