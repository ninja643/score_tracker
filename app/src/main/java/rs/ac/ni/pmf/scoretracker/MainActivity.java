package rs.ac.ni.pmf.scoretracker;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity implements GamesListFragment.OnGameSelectedListener
{
	private static final String TAG = "SCORE_TRACKER";

//	private ScoreViewModel scoreViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);

//		final MutableLiveData<Integer> currentIndex = scoreViewModel.getCurrentIndex();
//		currentIndex.observe(this, new Observer<Integer>()
//		{
//			@Override
//			public void onChanged(final Integer index)
//			{
//				Log.i(TAG, "Index from main: " + index);
//				onGameSelected(index);
//			}
//		});

		if (findViewById(R.id.portrait_view_container) != null)
		{
			if (savedInstanceState != null)
			{
				return;
			}

			final GamesListFragment gamesListFragment = new GamesListFragment();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.portrait_view_container, gamesListFragment)
					.commit();
		}
	}

	@Override
	public void onGameSelected(final int gameIndex)
	{
		final CurrentGameFragment currentGameFragment =
				(CurrentGameFragment) getSupportFragmentManager()
						.findFragmentById(R.id.current_game_fragment);

		if (currentGameFragment == null)
		{
			Log.i(TAG, "Creating game fragment");
			final CurrentGameFragment gameFragment = new CurrentGameFragment();

			final FragmentTransaction fragmentTransaction = getSupportFragmentManager()
					.beginTransaction();
			fragmentTransaction.replace(R.id.portrait_view_container, gameFragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		}
		else
		{
			Log.i(TAG, "Game fragment already exists");
		}
	}
}
