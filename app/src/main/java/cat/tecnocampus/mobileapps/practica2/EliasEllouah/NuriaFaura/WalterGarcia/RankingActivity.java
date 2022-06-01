package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class RankingActivity extends AppCompatActivity {

    GameViewModel viewModel;
    GameAdapter gameAdapter;

    TextView playerId;
    TextView playerNick;
    TextView playerPoints;
    TextView playerGames;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_ranking);

        //per mostrar fletxa enrere a la part lila
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ranking");

        playerId = findViewById(R.id.player_id);
        playerNick = findViewById(R.id.player_nick);
        playerPoints = findViewById(R.id.playerPoints);
        playerGames = findViewById(R.id.playerPoints);



        //crea instancia del ViewModel per accedir a les dades del llistat.
        //ViewModel ens permet desvincular la vista (Activity) de la font de dades.
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        //Observe es una funció de LiveData, que ens permet detectar quan
        // les dades s'han modificat.
        viewModel.getAllGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                //onChanged s'executa quan el llistat es modifica a la bbdd.
                //Si afegiu una tasca, veureu que s'executa aquest codi per
                //actualitzar el llistat (adapter)
                gameAdapter.setGames(games);
            }
        });

        gameAdapter = new GameAdapter();
        recyclerView.setAdapter(gameAdapter);




    }

    //perquè la fletxa tiri enrere
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}