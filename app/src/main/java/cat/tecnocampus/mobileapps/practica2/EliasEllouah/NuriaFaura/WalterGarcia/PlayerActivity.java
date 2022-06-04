package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {

    TextView nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //per mostrar fletxa enrere a la part lila
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ranking");

        nickName = findViewById(R.id.tv_personNick_alInfo);

        nickName.setText(getIntent().getStringExtra("nick"));

    }

    //perquè la fletxa tiri enrere
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}