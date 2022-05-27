package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    TextView nicktext;
    int letterSize;
    String replace="";
    String paraula[];
    String ancerts [];
    String initial="";


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
        ancerts = new String[text.length()];
        for (int i = 0; i< letterSize;i++) {
            ancerts[i] = " _ ";
            paraula[i] = text.substring(i,i+1);
            System.out.println("la paraula es: "+paraula[i]);
            initial = initial+" _ ";

        }


        textView.setText(initial);


    }

    public void searchChar(View view) {
        String letter = editText.getText().toString();
        editText.setText("");
        if (letter == null || letter.equals("")) {
            //fer TOAST
            System.out.println("escriu alguna cosa");
        }
        else {

            for (int i = 0; i<letterSize; i++) {
                System.out.println(paraula[i]);
            }

            for (int i = 0; i<letterSize;i++) {
                System.out.println("abans de comparar lletra es "+letter+ " caixa es: "+paraula[i]);
                if (paraula[i].equals(letter)) {
                    ancerts[i] = letter;

                }

                else {
                    //sonido de error TODO
                }
            }
            for (int r = 0; r < letterSize;r++) {
                replace = replace + ancerts[r];
            }
            textView.setText(replace);
            replace = "";

        }

    }
}