package app.com.example.android.myscribe;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import junit.framework.Test;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    private EditText username;
    private EditText password;
    private Button login_activity_button;
    private Button facebook_login_activity_button;
    private TextView forgot_password_redirect;
    private TextView sign_up_redirect;

    private String username_string;
    private String password_string;

    public static final String username_key = "username";
    public static final String password_key = "password";

    public static final String url="dummy";//TODO add url

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //facebook sdk initialisation
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        
        // track app installs(facebook)
        AppEventsLogger.activateApp(this);

        username = (EditText) findViewById(R.id.input_username_edit_text_view);
        password = (EditText) findViewById(R.id.input_password_edit_text_view);
        login_activity_button = (Button) findViewById(R.id.login_activity_button);
        forgot_password_redirect = (TextView) findViewById(R.id.forgot_password_redirect_text_view);
        sign_up_redirect = (TextView) findViewById(R.id.sign_up_redirect_text_view);
        facebook_login_activity_button = (Button) findViewById(R.id.facebook_login_activity_button);

        if(!checkNetwork())
        {
            showToast("No Internet connection");
            finish();
        }

        login_activity_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                submitForm();
            }
        });
        forgot_password_redirect.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                forgotPasswordRedirect();
                //test

            }
        });
        sign_up_redirect.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                signUpRedirect();
            }
        });

        facebook_login_activity_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO;
            }
        });
    }

    private void showToast(String text)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private boolean checkNetwork()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnected())
        return true;
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //submit login details
    private void submitForm()
    {
        username_string = String.valueOf(username.getText()).trim();
        password_string = String.valueOf(password.getText()).trim();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if(username_string.length()==0 || password_string.length()==0)
        {
            showToast("Invalid Username or Password.");
        }
        else
        {

               Response.Listener<String> listener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("200 OK");

                        if(success)
                        {
                            showToast("Login successful");
                            String username_response = jsonObject.getString("username");
                            //TODO replace SignUp.class
                            Intent launch_activity = new Intent(MainActivity.this, Test.class);
                            launch_activity.putExtra("username",username_response);
                            startActivity(launch_activity);
                        }
                        else
                        {
                            showToast(response);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            };

            LoginRequest loginRequest = new LoginRequest(username_string,password_string,listener);
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(loginRequest);

        }
    }

    //redirect to forgot password
    private void forgotPasswordRedirect()
    {
        Intent launch_forgot_password = new Intent(MainActivity.this, ForgotPassword.class);
        startActivity(launch_forgot_password);
    }

    //redirect to sign up activity
    private void signUpRedirect()
    {
        Intent launch_sign_up = new Intent(MainActivity.this, SignUp.class);
        startActivity(launch_sign_up);
    }

    //validate login details
    /**dummy for testing
    private boolean validate(String username_string, String password_string)
    {
        if (username_string.equals("rainy") && password_string.equals("rainy"))
            return true;
        return false;
    }
     ***/


    /***

     public class Background extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {

    HttpURLConnection httpURLConnection = null;
    BufferedReader bufferedReader = null;
    String result = "";


    try {
    URL url = new URL("dummy");//TODO
    String urlparams = "username = "+params[0]+"&password = "+params[1];
    httpURLConnection = (HttpURLConnection) url.openConnection();
    httpURLConnection.connect();

    httpURLConnection.setDoOutput(true);
    OutputStream outputStream = httpURLConnection.getOutputStream();
    outputStream.write(urlparams.getBytes());
    outputStream.flush();
    outputStream.close();

    InputStream inputStream = httpURLConnection.getInputStream();
    int var;

    while ((var=inputStream.read())!=-1) {
    result+= (char) var;
    }
    inputStream.close();
    return result;

    } catch (MalformedURLException e) {
    e.printStackTrace();
    } catch (IOException e) {
    e.printStackTrace();
    }

    finally
    {
    if (httpURLConnection != null) {
    httpURLConnection.disconnect();
    }
    }
    return null;
    }

    @Override
    protected void onPostExecute(String result) {
    super.onPostExecute(result);
    Context context = getApplicationContext();
    int duration = Toast.LENGTH_SHORT;
    Toast toast = Toast.makeText(context, result.toString(), duration);
    toast.show();
    }
    }
     }
     */

}
