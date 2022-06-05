package cat.tecnocampus.mobileapps.practica2.EliasEllouah.NuriaFaura.WalterGarcia;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private List<Game> data = Collections.emptyList();
    OnItemClickListener listener;
    String nickname;

    public GameAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setGames(List<Game> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setFilteredGames(List<Game> data){
        List<Game> games = new ArrayList<>(data);
        for(Game game: data){
            if(!game.getNickname().equals(nickname)){
                games.remove(game);
            }
        }
        setGames(games);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game currentGame = data.get(position);
        holder.id.setText(currentGame.getId() + "");
        holder.nickname.setText(currentGame.getNickname() + "");
        holder.points.setText(currentGame.getPoints() + "");
    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView id;
        public TextView nickname;
        public TextView points;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.player_id);
            nickname = itemView.findViewById(R.id.player_nick);
            points = itemView.findViewById(R.id.player_points);
            nickname.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            listener.onItemClick(data.get(getAdapterPosition()).getNickname());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String nick);
    }

    public void setNickname(String nick){
        this.nickname =  nick;
    }

    public void arrangeData(){
        Comparator<Game> compareById = (Game g1, Game g2) ->
                g1.getNickname().compareTo(g2.getNickname());

        Collections.sort(data, compareById);
        System.out.println("MIRANDO NOTIFIY");
        notifyDataSetChanged();
    }


}
