package rs.ac.ni.pmf.scoretracker;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements GamesListFragment.OnGameSelectedListener
{
	private static final String TAG = "SCORE_TRACKER";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
		if (findViewById(R.id.portrait_view_container) != null)
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
