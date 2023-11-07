package android.example.evalleyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentPage extends AppCompatActivity implements PaymentResultListener {

    Button pybtn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);
        Checkout.preload(getApplicationContext());

        pybtn = findViewById(R.id.pyabtn);
        textView = findViewById(R.id.textViewforpayment);

        pybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkout.preload(getApplicationContext());
                makepayment();
            }
        });
    }

    private void makepayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_yOUHuDeoRG1fTp");
        /**
         * Instantiate Checkout
         */


        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.ic_launcher_foreground);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Ankit Sahu");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "500");//pass amount in currency subunits
            options.put("prefill.email", "ankitsahu2032@gmail.com");
            options.put("prefill.contact", "8878371821");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 10);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        textView.setText("Your payment is successfull and your slot is booked");
    }

    @Override
    public void onPaymentError(int i, String s) {
        textView.setText("Payment Unsuccessfull hence slot can not be booked");
    }
}
