package designpattern.behavioral.state;

public class Standby extends PhoneState {
    @Override
    public void sideKeyUp() {
        System.out.println("Increase ringer volume");
    }

    @Override
    public void sideKeyDown() {
        System.out.println("Decrease ringer volume");
    }

    @Override
    public void SND_Key(CellPhone cellPhone) {
        cellPhone.changeState(new Call());
    }

    @Override
    public void launchApp(CellPhone cellPhone) {
        cellPhone.changeState(new Application());
    }
}
