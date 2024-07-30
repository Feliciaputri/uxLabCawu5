package com.example.labux_felis;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;

public class ProductDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_product_detail);

        ImageView imageView = findViewById(R.id.productImage);
        TextView productTitle = findViewById(R.id.productTitle);
        TextView productDescription = findViewById(R.id.productDescription);
        EditText emailInput = findViewById(R.id.emailInput);
        Spinner paymentMethodSpinner = findViewById(R.id.paymentMethodSpinner);
        Button orderButton = findViewById(R.id.orderButton);

        Intent intent = getIntent();
        String title = intent.getStringExtra("productTitle");
        String description = intent.getStringExtra("productDescription");
        int imageResId = intent.getIntExtra("productImage", -1);

        productTitle.setText(title);
        productDescription.setText(description);
        imageView.setImageResource(imageResId);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.payment_methods, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        paymentMethodSpinner.setAdapter(adapter);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String paymentMethod = paymentMethodSpinner.getSelectedItem().toString();

                if (email.isEmpty()) {
                    showDialog("Please enter your email", false);
                } else if (paymentMethod.equals("-- Select Payment Method --")) {
                    showDialog("Please select a payment method", false);
                } else {
                    showDialog("Order placed successfully!", true);
                }
            }
        });
    }

    private void showDialog(String message, boolean redirect) {
        // Inflate the custom dialog layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_message, null);

        TextView dialogMessage = dialogView.findViewById(R.id.dialogMessage);
        Button dialogButton = dialogView.findViewById(R.id.dialogButton);

        dialogMessage.setText(message);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (redirect) {
                    Intent intent = new Intent(ProductDetailActivity.this, ProductListActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Close this activity so the user cannot return to it
                }
            }
        });

        dialog.show();
    }
}
