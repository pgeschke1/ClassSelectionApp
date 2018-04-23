package com.pgeschke.classselectionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainClassList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_class_list);
        final Switch swDegreeCert = (Switch) findViewById(R.id.swDegreeCert);
        final Spinner spnDegree = (Spinner)findViewById(R.id.spnMajor);
        final Spinner spnCert = (Spinner) findViewById(R.id.spnCert);
        final TextView txtCertificate = (TextView)findViewById(R.id.labelCert2);
        final TextView txtDegree = (TextView) findViewById(R.id.labelMajor);
        final Button btnNext = (Button) findViewById(R.id.btnNext);

        final EditText firstName = (EditText)findViewById(R.id.txtFirstName);
        final EditText lastName = (EditText)findViewById(R.id.txtLastName);
        final EditText phone = (EditText)findViewById(R.id.txtPhone);

        final Spinner spnMonth = (Spinner)findViewById(R.id.spnDOBMonth);
        final EditText txtDay = (EditText)findViewById(R.id.txtDOBDay);
        final EditText txtYear = (EditText)findViewById(R.id.txtDOBYear);


        firstName.requestFocus();

        swDegreeCert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    spnDegree.setVisibility(View.VISIBLE);
                    txtDegree.setVisibility(View.VISIBLE);
                    spnCert.setVisibility(View.GONE);
                    txtCertificate.setVisibility(View.GONE);
                }else{
                    spnDegree.setVisibility(View.GONE);
                    txtDegree.setVisibility(View.GONE);
                    spnCert.setVisibility(View.VISIBLE);
                    txtCertificate.setVisibility(View.VISIBLE);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()){
                    String doBirth = "";
                    doBirth = spnMonth.getSelectedItem().toString() + "/" + txtDay.getText().toString()
                            + "/" +txtYear.getText().toString();

                    Intent nextScreen = new Intent(MainClassList.this, ChooseClass.class);
                    nextScreen.putExtra("FirstName", firstName.getText().toString());
                    nextScreen.putExtra("LastName", lastName.getText().toString());
                    nextScreen.putExtra("Phone", phone.getText().toString());
                    nextScreen.putExtra("Birth Date", doBirth);

                    if (spnDegree.getVisibility() == View.VISIBLE){
                        nextScreen.putExtra("isDegreeCert", "Degree");
                        nextScreen.putExtra("degreeCert", spnDegree.getSelectedItem().toString());

                    }else {nextScreen.putExtra("isDegreeCert", "Certificate");
                        nextScreen.putExtra("degreeCert", spnCert.getSelectedItem().toString());

                    }
                    startActivity(nextScreen);
                }

            }
        });
    }

    private boolean checkData(){
        final EditText firstName = (EditText)findViewById(R.id.txtFirstName);
        final EditText lastName = (EditText)findViewById(R.id.txtLastName);
        final EditText phone = (EditText)findViewById(R.id.txtPhone);
        final EditText txtDay = (EditText)findViewById(R.id.txtDOBDay);
        final EditText txtYear = (EditText)findViewById(R.id.txtDOBYear);

        if (firstName.getText().toString().isEmpty()){
            firstName.setError("Invalid First Name");
            firstName.requestFocus();
            return false;
        }
        if (lastName.getText().toString().isEmpty()){
            lastName.setError("Invalid Last Name");
            lastName.requestFocus();
            return false;
        }
        if (phone.getText().toString().isEmpty()){
            phone.setError("Invalid Phone Numebr");
            phone.requestFocus();
            return false;
        }
        if (txtDay.getText().toString().isEmpty()){
            txtDay.setError("Invalid Birth Day");
            txtDay.requestFocus();
            return false;
        }
        if (txtYear.getText().toString().isEmpty()){
            txtYear.setError("Invalid Birth Year");
            txtYear.requestFocus();
            return false;
        }
        return true;
    }
}
