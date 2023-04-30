package com.example.pfesinginlogin1;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Singin extends AppCompatActivity {
    TextView btn;
    RadioGroup rG;

    private EditText inputUserN,inputPass,inputmail,inputConfPass,inputPhoneNbr;
    Button btnRegister;
    private ProgressDialog mLoadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);
        btn=findViewById(R.id.alreadyHaveAcc);
        inputUserN=findViewById(R.id.inputUser);
        inputmail=findViewById(R.id.inputEmail);
        inputPhoneNbr=findViewById(R.id.inputPhone);
        inputPass=findViewById(R.id.inputLPassword);
        rG=findViewById(R.id.radioGroup);
        inputConfPass=findViewById(R.id.inputCPassword);
        mLoadingBar=new ProgressDialog(Singin.this);

        rG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.rb1) {
                    startActivity(new Intent(Singin.this,MainActivity.class));

                } else if (i == R.id.rb2) {
                    startActivity(new Intent(Singin.this,Login2Parking.class));

                }
            }
        });



        btnRegister=findViewById(R.id.btnSingin);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCrededentials();

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Singin.this,Login.class));
            }
        });
    }

    private void checkCrededentials() {
        String username=inputUserN.getText().toString();
        String email=inputmail.getText().toString();
        String password=inputPass.getText().toString();
        String phoneNbr=inputPhoneNbr.getText().toString();
        String condPhoneNbr = "^[+]\\d{10,13}$"; //^[+]?[0-9]{10,13}$ // \\d=[0-9]
        String confrmPassword=inputConfPass.getText().toString();

        if(username.isEmpty() || username.length()<7){
            showError(inputUserN,"votre nom d'utilisateur n'est pas valide! (doit contenir au moins 7 caractères) ");
        }
        else if (email.isEmpty() || !email.contains("@")) {
            showError(inputmail,"votre Email adress n'est pas valide!");
        }
        else if (phoneNbr.isEmpty() || !phoneNbr.matches(condPhoneNbr)) {
            showError(inputPhoneNbr,"votre numéro de téléphone n'est pas valide");
        }
        else if (password.isEmpty() || password.length()<8  ) {
            showError(inputPass,"votre mot de passe n'est pas valide!(doit contenir au moins 8 caractères)");
        }
        else if (confrmPassword.isEmpty() || !confrmPassword.equals(password)) {
            showError(inputConfPass,"le mot de passe ne correspond pas");
        }
        else {
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("veuillez patienter pendant que vous vérifiez vos informations d'identification.");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();


        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}