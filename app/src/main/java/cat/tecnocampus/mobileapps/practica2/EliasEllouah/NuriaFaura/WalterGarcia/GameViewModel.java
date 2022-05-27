package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GameViewModel extends AndroidViewModel {
    //S'encarrega de fer la crida al ViewController i té les dades

    private DatabaseController repository; //Instancia al controller
    private LiveData<List<Game>> allGames; //LiveData

    public GameViewModel(@NonNull Application application) {
        super(application);
        repository = new DatabaseController(application);
        allGames = repository.fetchAll();//Agafem tot el que hi hagi a la taula de tasques
    }

    LiveData<List<Game>> getAllGames() { return allGames;}//retornar totes les dades

    void insert(String task) {
        repository.setTodo(task);//crida al controller que farà l'insert
    }


}
