import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty item;
    private SimpleDoubleProperty discount;
    private SimpleIntegerProperty quantity;


    public Item(String n, int a, double b, int c, double d){
        name=new SimpleStringProperty(n);
        item=new SimpleIntegerProperty(a);
        price=new SimpleDoubleProperty(b);
        quantity=new SimpleIntegerProperty(c);
        discount=new SimpleDoubleProperty(d);

    }
    public String getName(){
        return name.get();
    }
    public double getPrice(){
        return price.get();
    }
    public int getItem(){
        return item.get();
    }
    public double getDiscount(){
        return discount.get();
    }
    public int getQuantity(){
        return quantity.get();
    }

}