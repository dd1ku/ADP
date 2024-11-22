import java.util.Scanner;
import java.util.Date;
public class P9Decorator{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        IReport salesReport = new SalesReport();
        salesReport = new DateFilterDecorator(salesReport, new Date(2023, 1, 1), new Date(2023, 12, 31));
        salesReport = new SortingDecorator(salesReport, "date");
        salesReport = new CsvExportDecorator(salesReport);
        System.out.println(salesReport.generate());

        IReport userReport = new UserReport();
        userReport = new UserAttributeFilterDecorator(userReport, "active");
        userReport = new PdfExportDecorator(userReport);
        System.out.println(userReport.generate());

        IReport filteredSalesReport = new SalesReport();
        filteredSalesReport = new AmountFilterDecorator(filteredSalesReport, 100.0, 300.0);
        filteredSalesReport = new CsvExportDecorator(filteredSalesReport);
        filteredSalesReport = new PdfExportDecorator(filteredSalesReport);
        System.out.println(filteredSalesReport.generate());

        in.close();
    }
}
interface IReport{
    String generate();
}
class SalesReport implements IReport{
    public String generate(){
        return "Sales Report Data\nSale1: $100, Sale2: $200, Sale3: $150";
    }
}
class UserReport implements IReport{
    public String generate(){
        return "User Report Data\nUser1: John, User2: Jane, User3: Alice";
    }
}
abstract class ReportDecorator implements IReport{
    protected IReport report;
    public ReportDecorator(IReport report){
        this.report = report;
    }
    public String generate(){
        return report.generate();
    }
}
class DateFilterDecorator extends ReportDecorator{
    private Date startDate;
    private Date endDate;
    public DateFilterDecorator(IReport report, Date startDate, Date endDate){
        super(report);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public String generate(){
        return report.generate() + "\nFiltered by dates: " + startDate + " to " + endDate;
    }
}
class SortingDecorator extends ReportDecorator{
    private String sortBy;
    public SortingDecorator(IReport report, String sortBy){
        super(report);
        this.sortBy = sortBy;
    }
    public String generate(){
        return report.generate() + "\nSorted by: " + sortBy;
    }
}
class CsvExportDecorator extends ReportDecorator{
    public CsvExportDecorator(IReport report){
        super(report);
    }
    public String generate(){
        return report.generate() + "\nExported as CSV";
    }
}
class PdfExportDecorator extends ReportDecorator{
    public PdfExportDecorator(IReport report){
        super(report);
    }
    public String generate(){
        return report.generate() + "\nExported as PDF";
    }
}
class AmountFilterDecorator extends ReportDecorator{
    private double minAmount;
    private double maxAmount;
    public AmountFilterDecorator(IReport report, double minAmount, double maxAmount){
        super(report);
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }
    public String generate(){
        return report.generate() + "\nFiltered by amount: $" + minAmount + " to $" + maxAmount;
    }
}
class UserAttributeFilterDecorator extends ReportDecorator{
    private String attribute;
    public UserAttributeFilterDecorator(IReport report, String attribute){
        super(report);
        this.attribute = attribute;
    }
    public String generate(){
        return report.generate() + "\nFiltered by user attribute: " + attribute;
    }
}
