package mx.com.cdp.consumirws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText txtUser, txtTitle, txtBody;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser = findViewById(R.id.txtUser);
        txtTitle = findViewById(R.id.txtTitle);
        txtBody = findViewById(R.id.txtBody);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeerWs();
                enviarWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUser.getText().toString());
                actualizarWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUser.getText().toString());
                eliminarWs();
            }
        });

    }

    private void LeerWs() {

        String url = "https://jsonplaceholder.typicode.com/posts/11";

        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    txtUser.setText(jsonObject.getString("userId"));
                    String title = jsonObject.getString("title");
                    txtTitle.setText(title);
                    txtBody.setText(jsonObject.getString("body"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(postResquest);
    }

    private void enviarWs(final String title, final String body, final String userId) {

        String url = "https://jsonplaceholder.typicode.com/posts";

        StringRequest postResquest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "RESULTADO POST = " + response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("body", body);
                params.put("userId", userId);

                return params;
            }
        };
        Volley.newRequestQueue(this).add(postResquest);
    }

    private void actualizarWs(final String title, final String body, final String userId) {

        String url = "https://jsonplaceholder.typicode.com/posts/1";

        StringRequest postResquest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "RESULTADO = " + response, Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", "1");
                params.put("title", title);
                params.put("body", body);
                params.put("userId", userId);

                return params;
            }
        };
        Volley.newRequestQueue(this).add(postResquest);
    }

    private void eliminarWs() {

        String url = "https://jsonplaceholder.typicode.com/posts/1";

        StringRequest postResquest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(MainActivity.this, "RESULTADO = " + response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(postResquest);
    }
}