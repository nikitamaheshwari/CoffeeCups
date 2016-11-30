package com.example.hp.birthdaycard;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p/>
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    int quantity = 1, chocoPrice = 0, whippedPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //  display(quantity);
        // displayPrice(quantity * 5);

        CheckBox chkbox1 = (CheckBox) findViewById(R.id.whipped_checkbox_view);
        boolean chkWhipped = chkbox1.isChecked();
        CheckBox checkbox2 = (CheckBox) findViewById(R.id.choclate_checkbox_view);
        boolean chkChoco = checkbox2.isChecked();
        EditText nameedittext = (EditText) findViewById(R.id.name_Edittext);
        String customerName = nameedittext.getText().toString();
        int price = calculatePrice(quantity, 5, chkWhipped, chkChoco);
        String priceMessage = createOrderSummary(price, chkWhipped, chkChoco, customerName);
//        displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        intent.putExtra(Intent.EXTRA_SUBJECT,"JUST JAVA ORDER FOR "+customerName);
        if (intent.resolveActivity(getPackageManager())!=null)
            startActivity(intent);
    }

    /*
    * This method is called when + button is clicked
    * */
    public void increment(View view) {
        /*
        Condition to take order of cofee not more than 100 else it will show  a msg
         */
        if (quantity == 100) {
            /*
                        Shows an error msg when condition true

             */
            Toast.makeText(this, "Not more than 100 Cup of Cofees!!", Toast.LENGTH_LONG).show();
           /*
                       exits the execution here oonly.

            */
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    /*
    * This method is called when - button is clicked
    * */
    public void decrement(View view) {
        /*
        Condition to take order of cofee  Atleast 1 else it will show  a msg

         */
        if (quantity == 1) {
            /*
            Shows an error msg when condition true
             */
            Toast toast = Toast.makeText(this, "Please input 1 or more than one Cofee!!", Toast.LENGTH_LONG);
            toast.show();
            /*
            exits the execution here oonly.
             */
            return;
        }

        quantity--;
        displayQuantity(quantity);
    }

    /*
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the message value on the screen.
     */
    /*private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }*/

    /**
     * This method displays the price value with currency on the screen.

     private void displayPrice(int number) {
     TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
     priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));

     }*/

    /**
     * This method is used to calculate price value on the screen.
     *
     * @param quantity  is the no of coffee cups ordered.
     * @param basePrice is the price per coffee cup.
     * @param chkChoco  is the price of choclate cream.
     * @price is total price of the current order.
     */
    private int calculatePrice(int quantity, int basePrice, boolean chkWhipped, boolean chkChoco) {

        if (chkChoco == true)
            basePrice = basePrice + 2;

        if (chkWhipped == true)
            basePrice = basePrice + 1;

        int price = quantity * (basePrice);
        return price;
    }

    /*
    Method to create an order summary like bill.
     */
    private String createOrderSummary(int price, boolean chkWhipped, boolean chkChoco, String customerName) {
        String summary = getString(R.string.ordersummaryname)+ " "+ customerName + "\n" + getString(R.string.whipped_cream) +" "+
                chkWhipped + "\n" + getString(R.string.chocolate_cream) +" "+
                chkChoco + "\n" + getString(R.string.quantity)+": " + quantity + "\n" + getString(R.string.total_rs) + price + (R.string.thanku);

        //displayMessage(summary);
        return summary;
    }
}

