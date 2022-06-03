package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    TextView nicktext;
    int letterSize;
    String replace="";
    String paraula[];
    String encerts[];
    String initial="";
    int puntuacio = 0;


    GameViewModel viewModel;
    GameAdapter gameAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        textView = findViewById(R.id.randomWord);
        editText = findViewById(R.id.lletra);
        nicktext = findViewById(R.id.textNick);


        String text = getIntent().getStringExtra("sendWord");
        String nick = getIntent().getStringExtra("sendNickName");

        nicktext.setText(nick);



        letterSize = text.length();
        paraula = new String[text.length()];
        encerts = new String[text.length()];
        for (int i = 0; i< letterSize;i++) {
            encerts[i] = " _ ";
            paraula[i] = text.substring(i,i+1);
            System.out.println("la paraula es: "+paraula[i]);
            initial = initial+" _ ";

        }


        textView.setText(initial);



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

        createDummyDatabase();
        gameAdapter = new GameAdapter();
    }

    private void createDummyDatabase(){
        Game newGame = new Game("Pedro",10);
        viewModel.insert(newGame);

        Game newGame2 = new Game("AAA",100);
        viewModel.insert(newGame2);

        Game newGame3 = new Game("AETSRE",30);
        viewModel.insert(newGame);
    }


    public void searchChar(View view) {
        String letter = editText.getText().toString();
        editText.setText("");
        if (letter == null || letter.equals("")) {
            //fer TOAST
            System.out.println("Letter must have a value!");
        }
        else {

            for (int i = 0; i<letterSize; i++) {
                System.out.println(paraula[i]);
            }

            for (int i = 0; i<letterSize;i++) {
                System.out.println("abans de comparar lletra es "+letter+ " caixa es: "+paraula[i]);
                if (paraula[i].equalsIgnoreCase(letter)) {
                    encerts[i] = letter;
                }

                else {
                    //sonido de error TODO
                }
            }
            for (int r = 0; r < letterSize;r++) {
                replace = replace + encerts[r];
            }
            textView.setText(replace.toUpperCase(Locale.ROOT));
            replace = "";

        }

    }

    public void finishGameClicked (View view){

        //CALCULEM PUNTS
        int enteredLetters = 0;
        for(int i = 0; i < this.encerts.length; i++){
            if (!this.encerts[i].equals(" - "))
                enteredLetters++;
        }

        this.puntuacio = ((this.letterSize - enteredLetters)/this.letterSize)*10;


        int points = puntuacio;
        String nick = nicktext.getText().toString();


        //OBJECTE GAME
        Game newGame = new Game(nick, points);


        //INSERIM A LA BBDD
        viewModel.insert(newGame);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_ranking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if(item.getItemId()== R.id.menu_agregar){
            Intent intent = new Intent(this, cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia.RankingActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}