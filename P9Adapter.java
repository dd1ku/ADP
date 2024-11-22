import java.util.Date;
import java.util.Scanner;
public class P9Adapter{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        IInternalDeliveryService deliveryService;

        deliveryService = DeliveryServiceFactory.getDeliveryService("internal");
        deliveryService.deliverOrder("123");
        System.out.println(deliveryService.getDeliveryStatus("123"));
        System.out.println("Delivery cost: $" + deliveryService.calculateDeliveryCost("123"));

        deliveryService = DeliveryServiceFactory.getDeliveryService("externalA");
        deliveryService.deliverOrder("456");
        System.out.println(deliveryService.getDeliveryStatus("456"));
        System.out.println("Delivery cost: $" + deliveryService.calculateDeliveryCost("456"));

        deliveryService = DeliveryServiceFactory.getDeliveryService("externalB");
        deliveryService.deliverOrder("789");
        System.out.println(deliveryService.getDeliveryStatus("789"));
        System.out.println("Delivery cost: $" + deliveryService.calculateDeliveryCost("789"));

        deliveryService = DeliveryServiceFactory.getDeliveryService("externalC");
        deliveryService.deliverOrder("101");
        System.out.println(deliveryService.getDeliveryStatus("101"));
        System.out.println("Delivery cost: $" + deliveryService.calculateDeliveryCost("101"));

        in.close();
    }
}
interface IInternalDeliveryService{
    void deliverOrder(String orderId);
    String getDeliveryStatus(String orderId);
    double calculateDeliveryCost(String orderId);
}
class InternalDeliveryService implements IInternalDeliveryService{
    public void deliverOrder(String orderId){
        System.out.println("Internal delivery for order " + orderId);
    }
    public String getDeliveryStatus(String orderId){
        return "Internal delivery status for order " + orderId;
    }
    public double calculateDeliveryCost(String orderId){
        return 10.0;
    }
}
class ExternalLogisticsServiceA{
    public void shipItem(int itemId){
        System.out.println("Shipping item " + itemId + " via ExternalLogisticsServiceA");
    }
    public String trackShipment(int shipmentId){
        return "Tracking status from ExternalLogisticsServiceA for shipment " + shipmentId;
    }
    public double estimateCost(int itemId){
        return 15.0;
    }
}
class ExternalLogisticsServiceB{
    public void sendPackage(String packageInfo){
        System.out.println("Sending package: " + packageInfo + " via ExternalLogisticsServiceB");
    }
    public String checkPackageStatus(String trackingCode){
        return "Status from ExternalLogisticsServiceB for tracking code " + trackingCode;
    }
    public double getPackageCost(String packageInfo){
        return 20.0;
    }
}
class LogisticsAdapterA implements IInternalDeliveryService{
    private ExternalLogisticsServiceA externalServiceA;
    public LogisticsAdapterA(ExternalLogisticsServiceA externalServiceA){
        this.externalServiceA = externalServiceA;
    }
    public void deliverOrder(String orderId){
        int itemId = Integer.parseInt(orderId);
        externalServiceA.shipItem(itemId);
    }
    public String getDeliveryStatus(String orderId){
        int shipmentId = Integer.parseInt(orderId);
        return externalServiceA.trackShipment(shipmentId);
    }
    public double calculateDeliveryCost(String orderId){
        int itemId = Integer.parseInt(orderId);
        return externalServiceA.estimateCost(itemId);
    }
}
class LogisticsAdapterB implements IInternalDeliveryService{
    private ExternalLogisticsServiceB externalServiceB;
    public LogisticsAdapterB(ExternalLogisticsServiceB externalServiceB){
        this.externalServiceB = externalServiceB;
    }
    public void deliverOrder(String orderId){
        externalServiceB.sendPackage("Order ID: " + orderId);
    }
    public String getDeliveryStatus(String orderId){
        return externalServiceB.checkPackageStatus(orderId);
    }
    public double calculateDeliveryCost(String orderId){
        return externalServiceB.getPackageCost("Order ID: " + orderId);
    }
}
class ExternalLogisticsServiceC{
    public void initiateDelivery(String id){
        System.out.println("Initiating delivery for " + id + " via ExternalLogisticsServiceC");
    }
    public String getDeliveryUpdate(String id){
        return "Status from ExternalLogisticsServiceC for " + id;
    }
    public double fetchCost(String id){
        return 25.0;
    }
}
class LogisticsAdapterC implements IInternalDeliveryService{
    private ExternalLogisticsServiceC externalServiceC;
    public LogisticsAdapterC(ExternalLogisticsServiceC externalServiceC){
        this.externalServiceC = externalServiceC;
    }
    public void deliverOrder(String orderId){
        externalServiceC.initiateDelivery(orderId);
    }
    public String getDeliveryStatus(String orderId){
        return externalServiceC.getDeliveryUpdate(orderId);
    }
    public double calculateDeliveryCost(String orderId){
        return externalServiceC.fetchCost(orderId);
    }
}
class DeliveryServiceFactory{
    public static IInternalDeliveryService getDeliveryService(String serviceType){
        switch (serviceType){
            case "internal":
                return new InternalDeliveryService();
            case "externalA":
                return new LogisticsAdapterA(new ExternalLogisticsServiceA());
            case "externalB":
                return new LogisticsAdapterB(new ExternalLogisticsServiceB());
            case "externalC":
                return new LogisticsAdapterC(new ExternalLogisticsServiceC());
            default:
                throw new IllegalArgumentException("Unknown delivery service type");
        }
    }
}