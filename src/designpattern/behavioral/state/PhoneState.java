package designpattern.behavioral.state;

public abstract class PhoneState {

    public void sideKeyUp() {
        System.out.println("Play Error Sound");
    }

    public void sideKeyDown() {
        System.out.println("Play Error Sound");
    }

    // A reference to CellPhone is needed because this operation may request a state change
    public void SND_Key(CellPhone cellPhone) {
        System.out.println("Play Error Sound");
    }

    public void END_Key(CellPhone cellphone) {
        System.out.println("Play Error Sound");
    }

    public void launchApp(CellPhone cellPhone) {
        System.out.println("Play Error Sound");
    }
}
