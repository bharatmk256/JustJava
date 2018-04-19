package com.bharatmk.justjava;



         import android.os.Bundle;
         import android.support.v7.app.AppCompatActivity;
         import android.util.Log;
         import android.view.View;
         import android.widget.CheckBox;
         import android.widget.EditText;
         import android.widget.TextView;
         import android.widget.Toast;

         import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the + button is clicked.
     */

        public void increment(View view) {
            if (quantity == 100){
                Toast.makeText(this, "You Cannot Have More Than 100 Coffees", Toast.LENGTH_SHORT).show();
                return;
            }
            quantity = quantity + 1;
            displayQuantity(quantity);
        }

        /**
         * This method is called when the - button is clicked.
         */
        public void decrement(View view) {
            if (quantity == 1){
                Toast.makeText(this, "You Cannot Have Less Than 1 Coffees", Toast.LENGTH_SHORT).show();
                return;
            }
            quantity = quantity - 1;
                displayQuantity(quantity);
            }

        public void submitOrder(View View) {
            EditText text = (EditText)findViewById(R.id.Name_field);
            String name = text.getText().toString();
            Log.v("MainActivity","Name: "+name);

            // Figure Out WhippedCream topping
            CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
            boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();

            // Figure Out Chocolate topping
            CheckBox Chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
            boolean hasChocolate =Chocolate.isChecked();

            Log.v("MainActivity", "Has Whipped Cream:" + hasWhippedCream + hasChocolate);

            int price= calculatePrice(hasWhippedCream, hasChocolate);
            String priceMessage=createOrderSummary(name, price, hasWhippedCream, hasChocolate);
            displayMessage(priceMessage);

        }
    /**
     * Calculates the price of the order.
     *
     * @return total
     */
    private int calculatePrice(boolean addwhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addwhippedCream){
            basePrice=basePrice + 1;
        }
        if (addChocolate){
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;
    }

    private String createOrderSummary(String name, int price,boolean addWhippedCream,boolean addChocolate){
        String priceMessage=getString(R.string.order_summary_name, name);
        priceMessage+="\nAdd Whipped Cream?"+ addWhippedCream;
        priceMessage+="\nAdd Chocolate?"+ addChocolate;
        priceMessage+="\nQuantity:"+quantity;
        priceMessage+="\nTotal: $"+ price;
        priceMessage+="\nThank_You!";
        return priceMessage;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.Quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}