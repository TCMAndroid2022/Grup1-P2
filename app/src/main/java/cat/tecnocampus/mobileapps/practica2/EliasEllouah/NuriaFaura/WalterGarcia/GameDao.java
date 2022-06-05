package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM Game")
    LiveData<List<Game>> getAllGames();

    @Insert
    void insertGame(Game game);

    @Query ("SELECT id, nickname, sum(points) as points, cont FROM Game GROUP BY nickname ORDER BY id asc")
    LiveData<List<Game>> getAllGamesByNick();

    @Query("SELECT * FROM Game WHERE nickname = :nickname")
    LiveData<List<Game>> getNickGames(String nickname);
}
