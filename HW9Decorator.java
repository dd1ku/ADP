import java.util.Scanner;
public class HW9Decorator{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        beverage = new Milk(beverage);
        beverage = new Sugar(beverage);
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        Beverage latte = new Latte();
        latte = new Syrup(latte);
        latte = new WhippedCream(latte);
        System.out.println(latte.getDescription() + " $" + latte.cost());

        Beverage mocha = new Mocha();
        mocha = new Milk(mocha);
        mocha = new WhippedCream(mocha);
        System.out.println(mocha.getDescription() + " $" + mocha.cost());

        in.close();
    }
}
interface Beverage{
    String getDescription();
    double cost();
}
class Espresso implements Beverage{
    public String getDescription(){
        return "Espresso";
    }
    public double cost(){
        return 1.99;
    }
}
class Tea implements Beverage{
    public String getDescription(){
        return "Tea";
    }
    public double cost(){
        return 1.50;
    }
}
class Latte implements Beverage{
    public String getDescription(){
        return "Latte";
    }
    public double cost() {
        return 2.50;
    }
}

class Mocha implements Beverage{
    public String getDescription(){
        return "Mocha";
    }
    public double cost(){
        return 2.75;
    }
}
abstract class BeverageDecorator implements Beverage{
    protected Beverage beverage;
    public BeverageDecorator(Beverage beverage){
        this.beverage = beverage;
    }
    public String getDescription(){
        return beverage.getDescription();
    }
    public double cost(){
        return beverage.cost();
    }
}
class Milk extends BeverageDecorator{
    public Milk(Beverage beverage){
        super(beverage);
    }
    public String getDescription(){
        return beverage.getDescription() + ", Milk";
    }
    public double cost(){
        return beverage.cost() + 0.30;
    }
}

class Sugar extends BeverageDecorator{
    public Sugar(Beverage beverage){
        super(beverage);
    }
    public String getDescription(){
        return beverage.getDescription() + ", Sugar";
    }
    public double cost(){
        return beverage.cost() + 0.20;
    }
}

class WhippedCream extends BeverageDecorator{
    public WhippedCream(Beverage beverage){
        super(beverage);
    }
    public String getDescription(){
        return beverage.getDescription() + ", Whipped Cream";
    }
    public double cost(){
        return beverage.cost() + 0.50;
    }
}

class Syrup extends BeverageDecorator{
    public Syrup(Beverage beverage){
        super(beverage);
    }
    public String getDescription(){
        return beverage.getDescription() + ", Syrup";
    }
    public double cost(){
        return beverage.cost() + 0.40;
    }
}