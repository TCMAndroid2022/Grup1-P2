package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class RankingActivity extends AppCompatActivity implements GameAdapter.OnItemClickListener{

    GameViewModel viewModel;
    GameAdapter gameAdapter;

    TextView playerId;
    TextView playerNick;
    TextView playerPoints;

    RecyclerView recyclerView;

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //per mostrar fletxa enrere a la part lila
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ranking");

        playerId = findViewById(R.id.player_id);
        playerNick = findViewById(R.id.player_nick);
        playerPoints = findViewById(R.id.player_points);

        recyclerView = findViewById(R.id.ranking_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
/*
        playerNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(this, cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia.PlayerActivity.class);

            }
        });
*/

        //crea instancia del ViewModel per accedir a les dades del llistat.
        //ViewModel ens permet desvincular la vista (Activity) de la font de dades.
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        //Observe es una funció de LiveData, que ens permet detectar quan
        // les dades s'han modificat.
        viewModel.getAllGamesByNick().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                //onChanged s'executa quan el llistat es modifica a la bbdd.
                //Si afegiu una tasca, veureu que s'executa aquest codi per
                //actualitzar el llistat (adapter)
                gameAdapter.setGames(games);
            }
        });

        gameAdapter = new GameAdapter((GameAdapter.OnItemClickListener) this);
        recyclerView.setAdapter(gameAdapter);
    }

    //perquè la fletxa tiri enrere
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onItemClick (String nick){
        Intent intent = new Intent(this, cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia.PlayerActivity.class);
        intent.putExtra("nick", nick);
        startActivity(intent);
//        activityResultLauncher.launch(intent);

    }

}