package rs.ac.ni.pmf.scoretracker;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

public class GamesListFragment extends ListFragment
{
	private static final String TAG = "SCORE_TRACKER";

	private ScoreViewModel scoreViewModel;

	private OnGameSelectedListener gameSelectedListener;

	@Override
	public void onAttach(@NonNull final Context context)
	{
		super.onAttach(context);

		Log.i(TAG, "Attaching GamesListFragment to activity");

		try
		{
			gameSelectedListener = (OnGameSelectedListener) context;
		}
		catch (final ClassCastException e)
		{
			throw new ClassCastException((context.toString() + " must implement GamesListFragment" +
					".OnGameSelectedListener interface"));
		}
	}

	@Override
	public void onDetach()
	{
		super.onDetach();

		gameSelectedListener = null;
		Log.i(TAG, "Detaching GamesListFragment from activity");
	}

	@Override
	public void onCreate(@Nullable final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		final FragmentActivity activity = requireActivity();

		Log.i(TAG, "GamesListFragment - Creating the view model");

		scoreViewModel =
				new ViewModelProvider(activity, new ViewModelProvider.AndroidViewModelFactory(activity.getApplication()))
						.get(ScoreViewModel.class);

		setListAdapter(new GamesListAdapter(activity, scoreViewModel.getObservableScores(), activity));
	}

	@Override
	public void onStart()
	{
		super.onStart();

		final Fragment gameFragment = getFragmentManager().findFragmentById(R.id.current_game_fragment);
		if (gameFragment != null && gameFragment.isInLayout())
		{
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			getListView().setItemChecked(scoreViewModel.getCurrentIndex().getValue(), true);
		}
		else
		{
			getListView().setChoiceMode(ListView.CHOICE_MODE_NONE);
		}
	}

	@Override
	public void onListItemClick(@NonNull final ListView l, @NonNull final View v, final int position, final long id)
	{
		scoreViewModel.selectIndex(position);
		gameSelectedListener.onGameSelected(position);
	}

	public interface OnGameSelectedListener
	{
		void onGameSelected(int gameIndex);
	}
}
