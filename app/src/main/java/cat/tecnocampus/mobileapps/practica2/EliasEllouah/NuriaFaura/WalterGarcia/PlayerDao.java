package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import android.content.ContentValues;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayerDao {
    //LiveData xq??
    @Query("SELECT * FROM player")
    LiveData<List<Player>> getAllPlayers();

    @Insert
    void insertPlayer(Player player);

    //https://academiaandroid.com/sqlite-en-app-android-actualizar-eliminar-y-consultar-datos/
    @Update
    void updatePoints(Player updatedPlayer);

}
