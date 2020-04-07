package rs.ac.ni.pmf.scoretracker;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import rs.ac.ni.pmf.scoretracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
	private ScoreViewModel scoreViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Log.i("SCORE_TRACKER", "onCreate() called");

		scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);

		ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		binding.setScore(scoreViewModel.getObservableScore());
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		Log.i("SCORE_TRACKER", "onStart() called");
	}
}
