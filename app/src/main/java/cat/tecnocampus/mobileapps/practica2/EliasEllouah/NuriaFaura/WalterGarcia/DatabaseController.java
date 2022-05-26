package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DatabaseController {
    private PlayerDao playerDao;
    private LiveData<List<Player>> allPlayers;

    public DatabaseController(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        playerDao = db.playerDao();
        allPlayers = playerDao.getAllPlayers();
    }

    public LiveData<List<Player>> fetchAll() {
        return allPlayers;
    }

    public void setTodo(String task) {
        Player current = new Player();
        new insertAsyncTask(playerDao).execute(current);
    }

    /*private static class insertAsyncTask extends AsyncTask<Player, Void, Void> {
        private PlayerDao asyncDao;

        insertAsyncTask(PlayerDao dao) {
            asyncDao = dao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            asyncDao.insertPlayer(players[0]);
            return null;
        }
    }*/

    private static class insertAsyncTask {
        private PlayerDao asyncDao;
        private Executor executor = Executors.newSingleThreadExecutor();

        insertAsyncTask(PlayerDao dao) {
            asyncDao = dao;
        }

        public void execute(Player player) {
            this.doInBackground(player);
        }

        private void doInBackground(final Player player) {
            this.executor.execute(new Runnable() {
                @Override
                public void run() {
                    asyncDao.insertPlayer(player);
                }
            });
        }
    }
}
