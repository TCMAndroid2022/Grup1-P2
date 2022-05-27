package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Game {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nickname")
    public String nickname;

    @ColumnInfo(name = "points")
    public int points;

}
