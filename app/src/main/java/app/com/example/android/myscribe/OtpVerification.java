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
import android.widget.TextView;


public class OtpVerification extends AppCompatActivity
{
    private EditText one_time_password;
    private Button one_time_password_activity;
    private TextView send_code_again_message;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verification);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        one_time_password = (EditText) findViewById(R.id.otp_edit_text_view);
        one_time_password_activity = (Button) findViewById(R.id.otp_activity_button);
        send_code_again_message = (TextView) findViewById(R.id.send_code_again_message_text_view);

        one_time_password_activity.setOnClickListener(new View.OnClickListener()
        {

            String one_time_password_string = String.valueOf(one_time_password);
            @Override
            public void onClick(View view)
            {
                if(submitOtp(one_time_password_string)==true)
                {
                    Intent launch_otp_verification= new Intent(OtpVerification.this,UserDetailsInput.class);
                    startActivity(launch_otp_verification);
                }
                else
                {
                    openDialog();
                }
            }
        });

        send_code_again_message.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SignUp sign_up_object = new SignUp();
                String aadhaar_no_string = sign_up_object.getAadhaarNoString();
                sendOtp(aadhaar_no_string);
            }
        });
    }

    private boolean submitOtp(String one_time_password_string)
    {
        //TODO
        //test
        return true;
    }

    // back to signup activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

    // Invalid Number dialog box
    private void openDialog() {

        AlertDialog alertDialog = new AlertDialog.Builder(OtpVerification.this).create();
        alertDialog.setTitle("Invalid Code");
        alertDialog.setMessage("The code is not valid. Please enter a valid code.");
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
    private void sendOtp(String aadhaar_no) {
        //TODO
    }
}
