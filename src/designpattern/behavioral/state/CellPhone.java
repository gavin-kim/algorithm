package designpattern.behavioral.state;

public class CellPhone {

    private PhoneState state;

    public CellPhone() {
        state = new Standby();
    }

    public void sideKeyUp() {
        state.sideKeyUp();
    }

    public void sideKeyDown() {
        state.sideKeyDown();
    }

    // support this to the other state
    public void SND_Key() {
        state.SND_Key(this);
    }

    public void END_Key() {
        state.END_Key(this);
    }

    public void launchApp() {
        state.launchApp(this);
    }

    // Only internal classes can change state
    void changeState(PhoneState state) {
        this.state = state;
    }
}
