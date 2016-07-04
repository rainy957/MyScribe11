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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class UserDetailsInput extends AppCompatActivity
{
    private EditText username;
    private EditText password;
    private EditText confirm_password;
    private Button submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_input);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = (EditText) findViewById(R.id.submit_username_edit_text_view);
        password = (EditText) findViewById(R.id.submit_password_edit_text_view);
        confirm_password = (EditText) findViewById(R.id.submit_confirmpassword_edit_text_view);
        submit_button = (Button) findViewById(R.id.submit_user_details_activity_button);

        submit_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String username_string = String.valueOf(username.getText());
                String password_string = String.valueOf(password.getText());
                String confirm_password_string = String.valueOf(confirm_password.getText());
                if(username_string.length()<4)
                {
                    String title="Username too short";
                    String message="Username must be atleast 4 characters.";
                    openDialog(title,message);
                }
                else if(password_string.length()<6)
                {
                    String title = "Password too short";
                    String message="Password must be atleast 6 characters.";
                    openDialog(title,message);
                }
                else if(!password_string.equals(confirm_password_string))
                {
                    String title = "Incorrect Password";
                    String message = "The two password fields didn't match.";
                    openDialog(title,message);
                }
                else
                {

                    registerUser(username_string,password_string);

                }
            }
        });
    }

    // Invalid Number dialog box
    private void openDialog(String title,String message)
    {

        AlertDialog alertDialog = new AlertDialog.Builder(UserDetailsInput.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
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

    // back to otp verfication activity
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void registerUser(String username,String password)
    {
        SignUp signUp = new SignUp();
        String aadhaar_no = signUp.getAadhaarNoString();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("200 OK");
                    if(success){
                        String title = "Registration Successful";
                        String message = "Thank you for registering.Please login to activate you account.";
                        openDialog(title,message);
                        Intent launch_activity = new Intent(UserDetailsInput.this, MainActivity.class);
                        startActivity(launch_activity);
                    }
                    else{
                        String title = "Registration Failed";
                        String message = "Registration process failed.Please retry.";
                        openDialog(title,message);
                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        RegisterationRequest registerationRequest = new RegisterationRequest(username,password,aadhaar_no,listener);
        RequestQueue requestQueue = Volley.newRequestQueue(UserDetailsInput.this);
        requestQueue.add(registerationRequest);

    }


}
