import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserInterface extends Application {

    private BorderPane bp = new BorderPane();
    private GridPane g1 = new GridPane();
    private GridPane g2 = new GridPane();
    private FlowPane fp = new FlowPane();
    private VBox vb1 = new VBox();
    private VBox vb2 = new VBox();
    private VBox vb3 = new VBox();


    private ObservableList<Item> data;

    public void start(Stage s) {
        TableView<Item> tbl;
        tbl = new TableView<>();
        Alert alerts = new Alert(Alert.AlertType.INFORMATION);
        Alert alerte = new Alert(Alert.AlertType.ERROR);
        Scene sc1, sc2, sc3;
        sc1 = new Scene(new Group());
        s.sizeToScene();

        Button b2 = new Button("Add");
        Button b3 = new Button("Update");
        Button b4 = new Button("Remove");
        fp.getChildren().addAll( b2, b3, b4);
        fp.setPadding(new Insets(10, 5, 5,5));


        data = FXCollections.observableArrayList();
        s.setWidth(800);
        s.setHeight(100);
        final Label l1 = new Label("Items List");
        l1.setFont(new Font(24));
        TableColumn namecol = new TableColumn("Item Name");
        namecol.setMinWidth(100);
        namecol.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));

        TableColumn pricecol = new TableColumn("Unit Price $");
        pricecol.setMinWidth(40);
        pricecol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("price"));

        TableColumn itemcol = new TableColumn("Item Code");
        itemcol.setMinWidth(10);
        itemcol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("item"));

        TableColumn quantitycol = new TableColumn("Quantity");
        quantitycol.setMinWidth(10);
        quantitycol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));

        TableColumn discountcol = new TableColumn("Discount %");
        discountcol.setMinWidth(10);
        discountcol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("discount"));


        tbl.setEditable(true);
        tbl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY );
        tbl.setItems(data);
        tbl.getColumns().addAll(namecol, itemcol, pricecol, quantitycol, discountcol);
        vb1.getChildren().addAll(l1, tbl);
        final Label ltitle = new Label("Item Information");
        ltitle.setFont(new Font(22));
        Label ln = new Label();
        ln.setText("Name");
        Label l2 = new Label();
        l2.setText("Item Code (1-Clothing, 2-Accessories, 3-Electronic Gadgets, 4-Kitchen Utensils, 5-Bedding)");
        Label ln22 = new Label();
        ln22.setText("Price");
        Label ln222 = new Label();
        ln222.setText("Quantity");
        Label ln2222 = new Label();
        ln2222.setText("Discount %");

        TextField tn = new TextField();
        TextField ta = new TextField();
        TextField tn22 = new TextField();
        TextField tn222 = new TextField();
        TextField tn2222 = new TextField();

        Button b1 = new Button("Add");
        Button pb = new Button("Print Bill");

        g1.add(ltitle, 0, 0);
        g1.add(ln, 0, 1);
        g1.add(tn, 1, 1);
        g1.add(l2, 0, 2);
        g1.add(ta, 1, 2);
        g1.add(ln22, 0, 3);
        g1.add(tn22, 1, 3);
        g1.add(ln222, 0, 4);
        g1.add(tn222, 1, 4);
        g1.add(ln2222, 0, 5);
        g1.add(tn2222, 1, 5);

        g1.setPadding(new Insets(10, 10, 10, 10));
        g1.setHgap(10);
        g1.setVgap(10);
        vb2.setSpacing(5);
        vb2.setPadding(new Insets(10, 5, 5,5));
        vb2.getChildren().addAll( g1, b1, pb);
        vb2.setVisible(false);
        bp.setPadding(new Insets(10, 10, 10, 10));
        bp.setLeft(vb2);
        bp.setRight(vb1);
        bp.setTop(fp);

        EventHandler<ActionEvent> eventPane = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                vb2.setVisible(true);
            }
        };
        b2.setOnAction(eventPane);
        EventHandler<ActionEvent> eventAdd = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                data.add(new Item(tn.getText(), Integer.parseInt(ta.getText()), Double.parseDouble(tn22.getText()), Integer.parseInt(tn222.getText()), Double.parseDouble(tn2222.getText())));
                tn.clear();
                ta.clear();
                tn22.clear();
                tn2222.clear();
                tn222.clear();
            }
        };

        b1.setOnAction(eventAdd);

        EventHandler<ActionEvent> eventPrint = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double billTotal;
                double total1 = 0;
                double total2 = 0;
                double total3 = 0;
                double total4 = 0;
                double total5 = 0;
                for (int i = 0; i < tbl.getItems().size(); i++) {
                    double totalPrice;
                    double discountPrice;
                    double tax = 8.25;
                    double taxPrice;
                    double subtotal;
                    double clothingDiscount;
                    double beddingDiscount;
                    double kitchenDiscount;
                    String Name = tbl.getItems().get(i).getName();
                    int code = tbl.getItems().get(i).getItem();
                    double price = tbl.getItems().get(i).getPrice();
                    double units = tbl.getItems().get(i).getQuantity();
                    double discount = tbl.getItems().get(i).getDiscount();
                    subtotal = units * price;
                    discountPrice = units * price * (discount/100);
                    taxPrice = (units * price - discountPrice) * (tax/100);
                    totalPrice = units * price - (units * price * (discount/100)) + taxPrice;
                    clothingDiscount = 25;
                    beddingDiscount = 25;
                    kitchenDiscount = 10;

                    switch(tbl.getItems().get(i).getItem())
                    {
                        case 1: //clothing 25$ off 100$ spent
                            if (totalPrice >= 100){
                                total1 += (totalPrice - clothingDiscount);
                                alerts.setTitle("Receipt");
                                alerts.setHeaderText(null);
                                alerts.setContentText("ItemCode: "+ code + "  (Clothing)" + "\n" +"\n"
                                        + "Name: " + Name + "\n" +"\n"
                                        + "Unit Price: $" + price + "\n" +"\n"
                                        + "Units: " + units + "\n" +"\n"
                                        + "Subtotal: $" + subtotal + "\n" +"\n"
                                        + "Savings: -$" + discountPrice + "\n" +"\n"
                                        + "Tax Amount: +$" + taxPrice + "\n" +"\n"
                                        + "Clothing Special Discount: -$" + clothingDiscount + "\n" +"\n"
                                        + "Final Price: $" + (totalPrice - clothingDiscount) + "\n");
                                alerts.showAndWait();
                            }    else{
                                total1 += totalPrice;
                                alerts.setTitle("Receipt");
                                alerts.setHeaderText(null);
                                alerts.setContentText("ItemCode: "+ code + "  (Clothing)" + "\n" +"\n"
                                        + "Name: " + Name + "\n" +"\n"
                                        + "Unit Price: $" + price + "\n" +"\n"
                                        + "Units: " + units + "\n" +"\n"
                                        + "Subtotal: $" + subtotal + "\n" +"\n"
                                        + "Savings: -$" + discountPrice + "\n" +"\n"
                                        + "Tax Amount: +$" + taxPrice + "\n" +"\n"
                                        + "Final Price: $" + (totalPrice) + "\n");
                                alerts.showAndWait();
                            }

                            break;

                        case 2:
                            total2 += totalPrice;
                            alerts.setTitle("Receipt");
                            alerts.setHeaderText(null);
                            alerts.setContentText("ItemCode: "+ code + "  (Accessories)" + "\n" +"\n"
                                    + "Name: " + Name + "\n" +"\n"
                                    + "Unit Price: $" + price + "\n" +"\n"
                                    + "Units: " + units + "\n" +"\n"
                                    + "Subtotal: $" + subtotal + "\n" +"\n"
                                    + "Savings: -$" + discountPrice + "\n" +"\n"
                                    + "Tax Amount: +$" + taxPrice + "\n" +"\n"
                                    + "Final Price: $" + (totalPrice) + "\n");
                            alerts.showAndWait();
                            break;

                        case 3:
                            total3 += totalPrice;
                            alerts.setTitle("Receipt");
                            alerts.setHeaderText(null);
                            alerts.setContentText("ItemCode: "+ code + "  (Electronic Gadgets)" + "\n" +"\n"
                                    + "Name: " + Name + "\n" +"\n"
                                    + "Unit Price: $" + price + "\n" +"\n"
                                    + "Units: " + units + "\n" +"\n"
                                    + "Subtotal: $" + subtotal + "\n" +"\n"
                                    + "Savings: -$" + discountPrice + "\n" +"\n"
                                    + "Tax Amount: +$" + taxPrice + "\n" +"\n"
                                    + "Final Price: $" + (totalPrice) + "\n");
                            alerts.showAndWait();
                            break;

                        case 4: // kitchen 10$ off for 25$ spent
                            if (totalPrice >= 25){
                                total4 += (totalPrice - kitchenDiscount);
                                alerts.setTitle("Receipt");
                                alerts.setHeaderText(null);
                                alerts.setContentText("ItemCode: "+ code + "  (Kitchen Utensils)" + "\n" +"\n"
                                        + "Name: " + Name + "\n" +"\n"
                                        + "Unit Price: $" + price + "\n" +"\n"
                                        + "Units: " + units + "\n" +"\n"
                                        + "Subtotal: $" + subtotal + "\n" +"\n"
                                        + "Savings: -$" + discountPrice + "\n" +"\n"
                                        + "Tax Amount: +$" + taxPrice + "\n" +"\n"
                                        + "Kitchen Special Discount: -$" + kitchenDiscount + "\n" +"\n"
                                        + "Final Price: $" + (totalPrice - kitchenDiscount) + "\n");
                                alerts.showAndWait();
                            }    else {
                                total4 += totalPrice;
                                alerts.setTitle("Receipt");
                                alerts.setHeaderText(null);
                                alerts.setContentText("ItemCode: " + code + "  (Kitchen Utensils)" + "\n" +"\n"
                                        + "Name: " + Name + "\n" +"\n"
                                        + "Unit Price: $" + price + "\n" +"\n"
                                        + "Units: " + units + "\n" +"\n"
                                        + "Subtotal: $" + subtotal + "\n" +"\n"
                                        + "Savings: -$" + discountPrice + "\n" +"\n"
                                        + "Tax Amount: +$" + taxPrice + "\n" +"\n"
                                        + "Final Price: $" + (totalPrice) + "\n");
                                alerts.showAndWait();
                            }
                            break;

                        case 5: //bedding 25$ off 100$ spent
                            if (totalPrice >= 100){
                                total5 += (totalPrice - beddingDiscount);
                                alerts.setTitle("Receipt");
                                alerts.setHeaderText(null);
                                alerts.setContentText("ItemCode: "+ code + "  (Bedding)" + "\n" +"\n"
                                        + "Name: " + Name + "\n" +"\n"
                                        + "Unit Price: $" + price + "\n" +"\n"
                                        + "Units: " + units + "\n" +"\n"
                                        + "Subtotal: $" + subtotal + "\n" +"\n"
                                        + "Savings: -$" + discountPrice + "\n" +"\n"
                                        + "Tax Amount: +$" + taxPrice + "\n" +"\n"
                                        + "Bedding Special Discount: -$" + beddingDiscount + "\n" +"\n"
                                        + "Final Price: $" + (totalPrice - beddingDiscount) + "\n");
                                alerts.showAndWait();
                            }    else{
                                total5 += totalPrice;
                                alerts.setTitle("Receipt");
                                alerts.setHeaderText(null);
                                alerts.setContentText("ItemCode: "+ code + "  (Bedding)" + "\n" +"\n"
                                        + "Name: " + Name + "\n" +"\n"
                                        + "Unit Price: $" + price + "\n" +"\n"
                                        + "Units: " + units + "\n" +"\n"
                                        + "Subtotal: $" + subtotal + "\n" +"\n"
                                        + "Savings: -$" + discountPrice + "\n" +"\n"
                                        + "Tax Amount: +$" + taxPrice + "\n" +"\n"
                                        + "Final Price: $" + (totalPrice) + "\n");
                                alerts.showAndWait();

                            }
                    }
                }
                billTotal = total1 + total2 + total3+ total4+ total5;
                alerts.setTitle("Total Bill Amount");
                alerts.setHeaderText(null);
                alerts.setContentText("Clothing Total: $"+ total1 + "\n" +"\n"
                        + "Accessories Total: $" + total2 + "\n" +"\n"
                        + "Electronic Gadgets Total: $" + total3 + "\n" +"\n"
                        + "Kitchen Utensils Total: $" + total4 + "\n" +"\n"
                        + "Bedding Total: $" + total5 + "\n" +"\n"
                        + "Total Bill Amount: $" + billTotal + "\n");
                alerts.showAndWait();
            }
        };

        pb.setOnAction(eventPrint);



        ((Group) sc1.getRoot()).getChildren().add(bp);
        s.setScene(sc1);
        s.show();
        Label ul2 = new Label("Enter the item's name and code to update");
        ul2.setFont(new Font("Arial", 14));
        Label ln2 = new Label("Item Name");
        Label l22 = new Label("Item Code");
        Label l222 = new Label("Price");
        Label l2222 = new Label("Quantity");
        Label l22222 = new Label("Discount %");


        TextField tn2 = new TextField();
        TextField ta2 = new TextField();
        TextField ta22 = new TextField();
        TextField ta222 = new TextField();
        TextField ta2222 = new TextField();


        Button b12 = new Button("Update");
        g2.add(ln2, 0, 0);
        g2.add(tn2, 1, 0);
        g2.add(l22, 0, 1);
        g2.add(ta2, 1, 1);
        g2.add(l222, 0, 2);
        g2.add(ta22, 1, 2);
        g2.add(l2222, 0, 3);
        g2.add(ta222, 1, 3);
        g2.add(l22222, 0, 4);
        g2.add(ta2222, 1, 4);
        Label lu= new Label();
        g2.setPadding(new Insets(10, 10, 10, 10));
        g2.setHgap(10);
        g2.setVgap(10);
        vb3.getChildren().addAll(ul2, g2, b12, lu);
        vb3.setSpacing(10);
        vb3.setPadding(new Insets(10, 10, 10, 10));
        EventHandler<ActionEvent> eventUpdate = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sid = tn2.getText();
                boolean found=false;
                for (int i = 0; i < tbl.getItems().size(); i++) {
                    if (((String)tbl.getItems().get(i).getName()).equals(sid)) {
                        Item p = new Item(tn2.getText(), Integer.parseInt(ta2.getText()), Double.parseDouble(ta22.getText()), Integer.parseInt(ta222.getText()),Double.parseDouble(ta2222.getText()));
                        tbl.getItems().set(i, p);
                        found=true;
                    }
                }
                if (found) {
                    alerts.setTitle("Update");
                    alerts.setHeaderText(null);
                    alerts.setContentText("Item Updated");
                    alerts.showAndWait();
                }

                else {
                    alerte.setTitle("Error");
                    alerte.setHeaderText(null);
                    alerte.setContentText("Item not found..");
                    alerte.showAndWait();
                }
                tn2.clear();
                ta2.clear();
                s.setScene(sc1);
            }
        };
        b12.setOnAction(eventUpdate);

        sc2 = new Scene(vb3, 400, 400);
        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                s.setScene(sc2);
            }
        };
        b3.setOnAction(event3);
        VBox vb4= new VBox();
        Label rl= new Label("Enter the Item's Code to remove");
        TextField tr= new TextField();
        Button dl= new Button("Delete");
        vb4.getChildren().addAll(rl, tr, dl);
        vb4.setPadding(new Insets(10, 10, 10, 10));
        vb4.setSpacing(10);
        sc3= new Scene(vb4, 400, 150);

        EventHandler<ActionEvent> eventDelete = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int getNumber = Integer.parseInt(tr.getText());
                boolean match =false;
                for (int i = 0; i < tbl.getItems().size(); i++) {
                    if (tbl.getItems().get(i).getItem() == getNumber) {
                        tbl.getItems().remove(i);
                        match=true;
                    }
                }
                if (match){
                    alerts.setTitle("Remove");
                    alerts.setHeaderText(null);
                    alerts.setContentText("Item Removed");
                    alerts.showAndWait();
                }

                else {
                    alerte.setTitle("Error");
                    alerte.setHeaderText(null);
                    alerte.setContentText("Item not found!" + "\n" + "Make sure you enter the Item Code and not name");
                    alerte.showAndWait();
                }
                tn2.clear();
                s.setScene(sc1);

            }
        };
        dl.setOnAction(eventDelete);
        EventHandler<ActionEvent> eventRemove = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                s.setScene(sc3);
            }
        };
        b4.setOnAction(eventRemove);
    }
    public static void main (String[]args){
        launch(args);
    }
}