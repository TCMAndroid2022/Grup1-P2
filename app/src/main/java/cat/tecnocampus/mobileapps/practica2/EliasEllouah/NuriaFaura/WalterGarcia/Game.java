package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import android.os.Parcel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Game {
    int cont = 0;
    public Game(String nickname, int points) {
        this.id = cont;
        this.nickname = nickname;
        this.points = points;

        cont++;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nickname")
    public String nickname;

    @ColumnInfo(name = "points")
    public int points;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    protected Game(Parcel in) {
        id = in.readInt();
        nickname = in.readString();
        points = in.readInt();
    }


}
