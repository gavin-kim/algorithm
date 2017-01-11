package designpattern.behavioral.chainofreposibility;

public interface EmailHandler {
    EmailHandler setNextHandler(Class<? extends EmailHandler> c);
    void processHandler(String email);
}
