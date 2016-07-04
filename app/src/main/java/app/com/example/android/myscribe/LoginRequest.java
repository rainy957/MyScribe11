package app.com.example.android.myscribe;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{

    private final static String login_request_url="dummy";//TODO
    private Map<String,String> map;

    public LoginRequest(String username, String password,
                                Response.Listener<String> listener){

        super(Method.POST,login_request_url,listener,null);
        map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);

    }

    @Override
    public Map<String,String> getParams()
    {
        return map;
    }


}
