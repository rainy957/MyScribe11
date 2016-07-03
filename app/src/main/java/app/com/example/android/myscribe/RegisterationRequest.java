package app.com.example.android.myscribe;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterationRequest extends StringRequest{

    private final static String registration_request_url="dummy";//TODO
    private Map<String,String> map;

    public RegisterationRequest(String username, String password, String aadhaar_no,
                                Response.Listener<String> listener){

        super(Method.POST,registration_request_url,listener,null);
        map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("aadhaar_no",aadhaar_no);

    }

    @Override
    public Map<String,String> getParams()
    {
        return map;
    }


}
