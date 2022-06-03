package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    EditText editText;
    RequestQueue queue; // Declara
    String url = "https://palabras-aleatorias-public-api.herokuapp.com/random";
    String url2 = "https://random-word-api.herokuapp.com/word";
    String random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        queue = Volley.newRequestQueue(getApplicationContext());

        if (mp != null) {
            mp.release();
        }

        mp = MediaPlayer.create(this,R.raw.ambient_5);
        mp.seekTo(1);
        mp.setLooping(true);
        //mp.start();

        editText = findViewById(R.id.nickname_txt);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Aqui ens arriba la resposta de la request que estem fent
                Log.v("TEST", response);
                random = response;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("TEST", "Error al StringRequest");

            }
        }

        );
        queue.add(stringRequest);
    }


    public void startGame(View view) {

        random = random.replaceAll("\\W+","");
        String nickname = editText.getText().toString();
        Intent intent = new Intent(getApplicationContext(),GameActivity.class);
        intent.putExtra("sendNickName",nickname);
        System.out.println("Paraula que volem enviar: "+random);
        intent.putExtra("sendWord",random);
        startActivity(intent);

    }

}