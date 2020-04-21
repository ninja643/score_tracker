package rs.ac.ni.pmf.scoretracker;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import rs.ac.ni.pmf.scoretracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
	private static final String TAG = "SCORE_TRACKER";

	private ScoreViewModel scoreViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null)
		{
			Log.i("SCORE_TRACKER", "got saved instance state " + savedInstanceState.toString());
		}

		scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);
		final MutableLiveData<Integer> currentIndex = scoreViewModel.getCurrentIndex();
		currentIndex.observe(this, new Observer<Integer>()
		{
			@Override
			public void onChanged(final Integer index)
			{
				Log.i(TAG, "Index: " + index);
			}
		});

		ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		binding.setLifecycleOwner(this);
		binding.setScore(scoreViewModel.getObservableScore());
	}

	@Override
	protected void onSaveInstanceState(@NonNull final Bundle outState)
	{
		super.onSaveInstanceState(outState);
		Log.i("SCORE_TRACKER", "Saving instance state");
	}

	@Override
	protected void onStart()
	{
		super.onStart();
//		Log.i("SCORE_TRACKER", "onStart() called");
	}

	@Override
	protected void onRestart()
	{
		super.onRestart();
//		Log.i("SCORE_TRACKER", "onRestart() called");
	}

	@Override
	protected void onResume()
	{
		super.onResume();
//		Log.i("SCORE_TRACKER", "onResume() called");
	}

	@Override
	protected void onPause()
	{
		super.onPause();
//		Log.i("SCORE_TRACKER", "onPause() called");
	}

	@Override
	protected void onStop()
	{
		super.onStop();
//		Log.i("SCORE_TRACKER", "onStop() called");
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
//		Log.i("SCORE_TRACKER", "onDestroy() called");
	}
}
