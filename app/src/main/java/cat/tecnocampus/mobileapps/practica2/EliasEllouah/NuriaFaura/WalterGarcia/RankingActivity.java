package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //per mostrar fletxa enrere a la part lila
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ALOHA");


    }

    //perquè la fletxa tiri enrere
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}