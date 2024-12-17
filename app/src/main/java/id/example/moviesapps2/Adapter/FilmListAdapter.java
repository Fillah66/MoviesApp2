package id.example.moviesapps2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import id.example.moviesapps2.Activity.DetailActivity;
import id.example.moviesapps2.Domain.ListFilm;
import id.example.moviesapps2.Domain.FilmItem;
import id.example.moviesapps2.R;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.ViewHolder> {
	ListFilm items;
	Context context;

	public FilmListAdapter(ListFilm items) {
		this.items = items;
	}

	@NonNull
	@Override
	public FilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film, parent, false);
		context = parent.getContext();
		return new ViewHolder(inflate);
	}

	@Override
	public void onBindViewHolder(@NonNull FilmListAdapter.ViewHolder holder, int position) {
	holder.titleTxt.setText(items.getData().get(position).getTitle());
	holder.ScoreTxt.setText("" + items.getData().get(position).getImdbRating());

		Glide.with(holder.itemView.getContext())
				.load(items.getData().get(position).getPoster())
				.into(holder.pic);

		holder.itemView.setOnClickListener(v -> {
			Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
			intent.putExtra("id", items.getData().get(position).getId());
			holder.itemView.getContext().startActivity(intent);
		});
	}

	@Override
	public int getItemCount() {
		return items.getData().size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView titleTxt, ScoreTxt;
		ImageView pic;
		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			titleTxt = itemView.findViewById(R.id.titleTxt);
			ScoreTxt = itemView.findViewById(R.id.scoreTxt);
			pic = itemView.findViewById(R.id.pic);
		}
	}
}
