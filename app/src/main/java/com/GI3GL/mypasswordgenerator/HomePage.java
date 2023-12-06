package com.GI3GL.mypasswordgenerator;

import static com.GI3GL.mypasswordgenerator.models.PasswordGenerator.checkPasswordStrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.GI3GL.mypasswordgenerator.models.LowerCaseGenerator;
import com.GI3GL.mypasswordgenerator.models.NumericGenerator;
import com.GI3GL.mypasswordgenerator.models.PasswordGenerator;
import com.GI3GL.mypasswordgenerator.models.SpecialCharGenerator;
import com.GI3GL.mypasswordgenerator.models.UpperCaseGenerator;

public class HomePage extends AppCompatActivity {
    private EditText editPasswordSize;
    private TextView textErrorMessage;
    private EditText textPasswordGenerated;
    private CheckBox checkLower, checkUpper,checkSpecialChar, checkNumeric;
    private Button btnGenerate, btnCopy , btnPaste;

    private TextView textStrength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initViews();

        clickListeners();
    }


    private void setColorBasedOnStrength(String strength) {
        int color;
        switch (strength.toLowerCase()) {
            case "weak":
                color = ContextCompat.getColor(this, R.color.colorWeak); // Define colorWeak in your resources
                break;
            case "moderate":
                color = ContextCompat.getColor(this, R.color.colorModerate); // Define colorModerate in your resources
                break;
            case "strong":
                color = ContextCompat.getColor(this, R.color.colorStrong); // Define colorStrong in your resources
                break;
            default:
                color = ContextCompat.getColor(this, android.R.color.black);
                break;
        }

        textStrength.setTextColor(color);
    }
    private void clickListeners() {
        btnGenerate.setOnClickListener(view -> {



            int passwordSize = Integer.parseInt(editPasswordSize.getText().toString());

            textErrorMessage.setText("");

            if(passwordSize<4){
                textErrorMessage.setText("Password Size must be greater than 4");
                return;
            }
            if(passwordSize>15){
                textErrorMessage.setText("Password Size must be smaller than 15");
                return;
            }


            PasswordGenerator.clear();
            if(checkLower.isChecked()) PasswordGenerator.add(new LowerCaseGenerator());
            if(checkNumeric.isChecked()) PasswordGenerator.add(new NumericGenerator());
            if(checkUpper.isChecked()) PasswordGenerator.add(new UpperCaseGenerator());
            if(checkSpecialChar.isChecked()) PasswordGenerator.add(new SpecialCharGenerator());


            if(PasswordGenerator.isEmpty()){
                textErrorMessage.setText("Please select at least one password content type");
                return;
            }

            String passwrd = PasswordGenerator.generatePassword(passwordSize);
            textPasswordGenerated.setText(passwrd);
            // The generated password
            String generatedPassword = PasswordGenerator.generatePassword(passwordSize);
            // Check the strength of the generated password
            String strength = checkPasswordStrength(generatedPassword);
            // Display the strength somewhere (e.g., in a TextView)
            textStrength.setText("Password Strength: " + strength);
            // Display the strength in the TextView "rate"

            textStrength.setVisibility(View.VISIBLE); // Make the TextView visible
            setColorBasedOnStrength(strength);
            textStrength.setText( strength);


        });

        btnPaste.setOnClickListener(view -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

            if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
                ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);
                String clipboardText = item.getText().toString();

                if (!clipboardText.isEmpty()) {
                    textPasswordGenerated.setText(clipboardText);
                    String strength = checkPasswordStrength(clipboardText);
                    setColorBasedOnStrength(strength);
                    textStrength.setText(strength);
                    textStrength.setVisibility(View.VISIBLE);
                }
            }
        });




        btnCopy.setOnClickListener(view ->{
            ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            manager.setPrimaryClip(ClipData.newPlainText("password",textPasswordGenerated.getText().toString()));
            Toast.makeText(this, "Password Copied", Toast.LENGTH_SHORT).show();
        });






    }

    private void initViews() {

        editPasswordSize = findViewById(R.id.edit_pwd_size);
        textPasswordGenerated = findViewById(R.id.text_password_result);
        textStrength = findViewById(R.id.rate);  // Initialize textStrength here
        textErrorMessage = findViewById(R.id.text_error);
        checkLower = findViewById(R.id.check_lower);
        checkUpper = findViewById(R.id.check_upper);
        checkSpecialChar = findViewById(R.id.check_special_char);
        checkNumeric = findViewById(R.id.check_numeric);
        btnGenerate = findViewById(R.id.btn_generate);
        btnCopy = findViewById(R.id.btn_copy);
        btnPaste = findViewById(R.id.btn_paste);

    }
}