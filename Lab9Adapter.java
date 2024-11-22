import java.util.Scanner;
public class Lab9Adapter{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        IPaymentProcessor internalProcessor = new InternalPaymentProcessor();
        internalProcessor.processPayment(100.0);
        internalProcessor.refundPayment(50.0);

        ExternalPaymentSystemA externalSystemA = new ExternalPaymentSystemA();
        IPaymentProcessor adapterA = new PaymentAdapterA(externalSystemA);
        adapterA.processPayment(200.0);
        adapterA.refundPayment(100.0);

        ExternalPaymentSystemB externalSystemB = new ExternalPaymentSystemB();
        IPaymentProcessor adapterB = new PaymentAdapterB(externalSystemB);
        adapterB.processPayment(300.0);
        adapterB.refundPayment(150.0);

        in.close();
    }
}
interface IPaymentProcessor{
    void processPayment(double amount);
    void refundPayment(double amount);
}

class InternalPaymentProcessor implements IPaymentProcessor{
    public void processPayment(double amount){
        System.out.println("Processing payment of " + amount + " via internal system.");
    }
    public void refundPayment(double amount){
        System.out.println("Refunding payment of " + amount + " via internal system.");
    }
}
class ExternalPaymentSystemA{
    public void makePayment(double amount){
        System.out.println("Making payment of " + amount + " via External Payment System A.");
    }
    public void makeRefund(double amount){
        System.out.println("Making refund of " + amount + " via External Payment System A.");
    }
}
class ExternalPaymentSystemB{
    public void sendPayment(double amount){
        System.out.println("Sending payment of " + amount + " via External Payment System B.");
    }
    public void processRefund(double amount){
        System.out.println("Processing refund of " + amount + " via External Payment System B.");
    }
}
class PaymentAdapterA implements IPaymentProcessor{
    private ExternalPaymentSystemA externalSystemA;
    public PaymentAdapterA(ExternalPaymentSystemA externalSystemA){
        this.externalSystemA = externalSystemA;
    }
    public void processPayment(double amount){
        externalSystemA.makePayment(amount);
    }
    public void refundPayment(double amount){
        externalSystemA.makeRefund(amount);
    }
}
class PaymentAdapterB implements IPaymentProcessor{
    private ExternalPaymentSystemB externalSystemB;
    public PaymentAdapterB(ExternalPaymentSystemB externalSystemB){
        this.externalSystemB = externalSystemB;
    }
    public void processPayment(double amount){
        externalSystemB.sendPayment(amount);
    }
    public void refundPayment(double amount){
        externalSystemB.processRefund(amount);
    }
}


