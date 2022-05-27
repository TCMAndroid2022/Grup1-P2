package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GameDao {
    //LiveData xq??
    @Query("SELECT * FROM Game")
    LiveData<List<Game>> getAllGames();

    @Insert
    void insertGame(Game game);

    //https://academiaandroid.com/sqlite-en-app-android-actualizar-eliminar-y-consultar-datos/
    @Update
    void updatePoints(Game updatedGame);

}
