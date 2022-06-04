package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class GameActivity extends AppCompatActivity  {
    TextView textView;
    EditText editText;
    TextView nicktext;
    TextView enteredWord;
    TextView finalPoints;
    TextView endGame;

    int letterSize;
    String replace="";
    String paraula[];
    String encerts[];
    String initial="";
    int puntuacio = 0;
    int enteredLetters=0;


    GameViewModel viewModel;
    //GameAdapter gameAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        textView = findViewById(R.id.randomWord);
        editText = findViewById(R.id.lletra);
        nicktext = findViewById(R.id.textNick);
        enteredWord = findViewById(R.id.et_writeWord);
        endGame = findViewById(R.id.tv_gameFinished);
        finalPoints = findViewById(R.id.tv_points);


        String text = getIntent().getStringExtra("sendWord");
        String nick = getIntent().getStringExtra("sendNickName");

        nicktext.setText("Nickname: "+nick);
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

        createDummyDatabase();
    }

    private void createDummyDatabase(){
        Game newGame = new Game("BBBBB",10);
        viewModel.insert(newGame);

        Game newGame2 = new Game("CCCCC",100);
        viewModel.insert(newGame2);

        Game newGame3 = new Game("DDDDD",30);
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
                System.out.print(paraula[i]);
            }

            for (int i = 0; i<letterSize;i++) {
                System.out.println("abans de comparar lletra es "+letter+ " caixa es: "+paraula[i]);
                if (paraula[i].equalsIgnoreCase(letter)) {
                    encerts[i] = letter;
                    this.enteredLetters++;
                    Log.v("Ha encertat!",this.enteredLetters+"");
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

            if (enteredLetters == letterSize){
                finishGameClicked(view);
            }

        }

    }

    public void finishGameClicked (View view){
        String entered = enteredWord.getText().toString();
        String paraulaFinal="";


        if(wordIsCorrect(paraula, entered) || enteredLetters == letterSize){

            //CALCULEM PUNTUACIO
            float resta = letterSize - enteredLetters;
            double divisio = resta/(float)letterSize;
            double mult = divisio*10;
            this.puntuacio = (int)Math.round(mult);

            //PRINT JOC ACABAT
            endGame.setText("GAME FINISHED! You win, congrats!! Go check the ranking...");

        } else{
            this.puntuacio = 0;
            //TPRINT JOC ACABAT
            endGame.setText("GAME FINISHED! You lost, go check the ranking...");
        }

        String nick = nicktext.getText().toString();

        //EL JOC HA ACABAT: PRINTEM LA PARAULA
        for (int i = 0; i< letterSize;i++) {
            paraulaFinal+=paraula[i];
        }

        textView.setText(paraulaFinal.toUpperCase());

        //PRINTEM LA PUNTUACIÓ DE LA PARTIDA
        finalPoints.setText("You have earned "+this.puntuacio+" POINTS");

        //OBJECTE GAME
        Game newGame = new Game(nick, this.puntuacio);

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

    private boolean wordIsCorrect(String [] paraula, String entered){
        String word = "";
        for(int i=0;i< paraula.length;i++){
            word += paraula[i];
        }

        return word.equalsIgnoreCase(entered);
    }

}