import java.util.Scanner;
public class HW9Adapter{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        IPaymentProcessor paypalProcessor = new PayPalPaymentProcessor();
        paypalProcessor.processPayment(100.0);

        StripePaymentService stripeService = new StripePaymentService();
        IPaymentProcessor stripeAdapter = new StripePaymentAdapter(stripeService);
        stripeAdapter.processPayment(200.0);

        SquarePaymentService squareService = new SquarePaymentService();
        IPaymentProcessor squareAdapter = new SquarePaymentAdapter(squareService);
        squareAdapter.processPayment(300.0);

        in.close();
    }
}
interface IPaymentProcessor{
    void processPayment(double amount);
}
class PayPalPaymentProcessor implements IPaymentProcessor{
    public void processPayment(double amount){
        System.out.println("Processing payment of $" + amount + " via PayPal.");
    }
}
class StripePaymentService{
    public void makeTransaction(double totalAmount){
        System.out.println("Processing payment of $" + totalAmount + " via Stripe.");
    }
}
class StripePaymentAdapter implements IPaymentProcessor{
    private StripePaymentService stripePaymentService;
    public StripePaymentAdapter(StripePaymentService stripePaymentService){
        this.stripePaymentService = stripePaymentService;
    }
    public void processPayment(double amount){
        stripePaymentService.makeTransaction(amount);
    }
}
class SquarePaymentService{
    public void pay(double amount){
        System.out.println("Processing payment of $" + amount + " via Square.");
    }
}
class SquarePaymentAdapter implements IPaymentProcessor{
    private SquarePaymentService squarePaymentService;
    public SquarePaymentAdapter(SquarePaymentService squarePaymentService){
        this.squarePaymentService = squarePaymentService;
    }
    public void processPayment(double amount){
        squarePaymentService.pay(amount);
    }
}