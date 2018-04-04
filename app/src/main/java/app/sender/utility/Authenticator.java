package app.sender.utility;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alegr on 04/04/2018.
 */

public class Authenticator {
    //provvisorio
    private final String AUTH_URL = new String("http://192.168.1.42:8080/reciever/webresources/authenticate");

    private RequestQueue queue;


    public Authenticator(Context activityContext){
        queue = Volley.newRequestQueue(activityContext);
    }

    public void validate(Map<String, String> postParam) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                AUTH_URL, new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("EEEEEEEEEEEEEERRRROREEEEEEEEEEEEEEEEEEEE   " + error.getMessage());
                //System.out.println(error.networkResponse.statusCode);
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }



        };
        // Adding request to request queue
        System.out.println("Invio");
        System.out.println(jsonObjReq);
        queue.add(jsonObjReq);

        // Cancelling request
    /* if (queue!= null) {
    queue.cancelAll(TAG);
    } */

    }
}
