package rs.ac.ni.pmf.scoretracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import rs.ac.ni.pmf.scoretracker.databinding.GameListViewBinding;

public class GamesListAdapter extends BaseAdapter
{
	private final Context context;
	private final List<ObservableScore> observableScores;
	private final LifecycleOwner lifecycleOwner;

	public GamesListAdapter(
			final Context context, final List<ObservableScore> observableScores,
			final LifecycleOwner lifecycleOwner)
	{
		this.context = context;
		this.observableScores = observableScores;
		this.lifecycleOwner = lifecycleOwner;
	}

	@Override
	public int getCount()
	{
		return observableScores.size();
	}

	@Override
	public Object getItem(final int position)
	{
		return observableScores.get(position);
	}

	@Override
	public long getItemId(final int position)
	{
		return position;
	}

	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent)
	{
		final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final GameListViewBinding gameItemBinding = DataBindingUtil.inflate(inflater, R.layout.game_list_view, parent, false);

		gameItemBinding.setLifecycleOwner(lifecycleOwner);
		gameItemBinding.setScore(observableScores.get(position));

		return gameItemBinding.getRoot();
	}
}
