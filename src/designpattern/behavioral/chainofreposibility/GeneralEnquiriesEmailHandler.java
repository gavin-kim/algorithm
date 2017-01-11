package designpattern.behavioral.chainofreposibility;

public class GeneralEnquiriesEmailHandler extends AbstractEmailHandler {


    @Override
    protected String[] getMatchingWords() {
        return new String[0]; // matching anything
    }

    @Override
    protected void handleHere(String email) {
        System.out.println("General Enquiry: " + email);
    }
}
