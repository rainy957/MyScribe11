package app.com.example.android.myscribe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ForgotPassword extends AppCompatActivity
{
    private EditText aadhaar_no;
    private Button submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        // action bar support for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aadhaar_no = (EditText) findViewById(R.id.input_aadhaar_number_edit_text_view);
        submit_button = (Button) findViewById(R.id.submit_button);


        submit_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String aadhaar_no_string = getAadhaarNoString();

                if(aadhaar_no_string.length()!=12)
                {
                    openDialog();

                }
                else
                {
                    boolean check = authenticate_aadhaar_no(aadhaar_no_string);
                    if(check==true)
                    {
                        //test:
                        sendOtp(aadhaar_no_string);
                        Intent launch_otp_verification= new Intent(ForgotPassword.this,OtpVerification.class);
                        startActivity(launch_otp_verification);
                    }
                    else
                    {
                        openDialog();
                    }
                }
            }
        });
    }

    // return aadhaar no. string
    public  String getAadhaarNoString()
    {
        return String.valueOf(aadhaar_no.getText());
    }

    // Invalid Number dialog box
    private void openDialog()
    {

        AlertDialog alertDialog = new AlertDialog.Builder(ForgotPassword.this).create();
        alertDialog.setTitle("Invalid Number");
        alertDialog.setMessage("The aadhaar number you entered is not valid. Please enter a valid aadhaar number.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();

    }
    // authenticate aadhaar no
    private boolean authenticate_aadhaar_no(String aadhaar_no_string)
    {
        //TODO
        return true;
    }

    // back to login activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //send otp
    private void sendOtp(String aadhaar_no)
    {
        //TODO;
    }
}
