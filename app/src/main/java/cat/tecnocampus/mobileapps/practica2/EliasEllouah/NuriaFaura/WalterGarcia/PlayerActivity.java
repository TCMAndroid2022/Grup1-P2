package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class PlayerActivity extends AppCompatActivity implements GameAdapter.OnItemClickListener {

    TextView nickName;
    GameViewModel viewModel;
    GameAdapter gameAdapter;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //per mostrar fletxa enrere a la part lila
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ranking");

        nickName = findViewById(R.id.tv_personNick_alInfo);
        recyclerView = findViewById(R.id.playerRanking_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        nickName.setText(getIntent().getStringExtra("nick"));


        viewModel = new ViewModelProvider(this).get(GameViewModel.class);

        viewModel.getAllGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                //onChanged s'executa quan el llistat es modifica a la bbdd.
                //Si afegiu una tasca, veureu que s'executa aquest codi per
                //actualitzar el llistat (adapter)
                gameAdapter.setFilteredGames(games);
            }
        });

        gameAdapter = new GameAdapter((GameAdapter.OnItemClickListener) this);
        gameAdapter.setNickname(getIntent().getStringExtra("nick"));
        recyclerView.setAdapter(gameAdapter);

    }

    //perquè la fletxa tiri enrere
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onItemClick(String nick) {

    }
}