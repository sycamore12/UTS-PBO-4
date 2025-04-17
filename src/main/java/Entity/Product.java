package Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private double price;
    // getters & setters
}

@Entity
public class NonPerishableProduct extends Product {}

@Entity
public class PerishableProduct extends Product {
    private LocalDate expiryDate;
    // getters & setters
}

@Entity
public class BundleProduct extends Product {
    @ManyToMany
    private List<Product> items;
    public double getDiscountedPrice() {
        double sum = items.stream().mapToDouble(Product::getPrice).sum();
        return sum - getPrice();
    }
}

@Entity
public class DigitalProduct extends Product {
    private URL url;
    private String vendorName;
    // getters & setters
}

public interface Payable {
    double calculateTotal();
    void processTransaction();
    void serializeTransaction();
}

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transaction {
    @Id @GeneratedValue
    private Long transactionId;
    private LocalDateTime date;
    // getters & setters
}

@Entity
public class PurchaseTransaction extends Transaction implements Payable {
    private double amountPaid;
    private double total;
    // ... mapping items omitted for brevity
    @Override public double calculateTotal() { return total; }
    @Override public void processTransaction() { System.out.println("Transaksi " + getTransactionId() + " diproses"); }
    @Override public void serializeTransaction() { }
}

@Entity
public class RefundTransaction extends Transaction implements Payable {
    private double refundAmount;
    @Override public double calculateTotal() { return refundAmount; }
    @Override public void processTransaction() { System.out.println("Refund " + getTransactionId() + " diproses"); }
    @Override public void serializeTransaction() { }
    // getters & setters
}