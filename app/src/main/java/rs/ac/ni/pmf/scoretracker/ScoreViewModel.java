package rs.ac.ni.pmf.scoretracker;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import rs.ac.ni.pmf.scoretracker.data.GameDetails;

public class ScoreViewModel extends AndroidViewModel
{
	private static final String TAG = "SCORE_TRACKER";

	private final List<ObservableScore> observableScores = new ArrayList<>();

	private MutableLiveData<Integer> currentIndex = new MutableLiveData<>();

	private MutableLiveData<Boolean> ready;
	private boolean initialized = false;

	private ScoreTrackerRepository repository;

	public ScoreViewModel(@NonNull final Application application)
	{
		super(application);
		repository = ((ScoreTrackerApplication) application).getRepository();

		ready = repository.isReady();

		Log.i(TAG, "ScoreViewModel - Initializing. Ready: " + (ready.getValue() != null ?
				String.valueOf(ready.getValue()) :
				"null"));

		if (!initialized)
		{
			initializeData();
		}
	}

	private synchronized void initializeData()
	{
		if (!initialized)
		{
			Log.i(TAG, "ScoreViewModel - Initializing data");

			for (final GameDetails gameDetails : repository.getGamesDetails())
			{
				final ObservableScore gameScore = new ObservableScore(gameDetails.getId(), gameDetails.getTeamA(),
						gameDetails.getTeamB(), repository);
				gameScore.addScore(ObservableScore.Team.TEAM_A, gameDetails.getScoreA());
				gameScore.addScore(ObservableScore.Team.TEAM_B, gameDetails.getScoreB());

				observableScores.add(gameScore);
			}

			if (!observableScores.isEmpty())
			{
				currentIndex.setValue(0);
			}

			initialized = true;
		}
	}

	public List<ObservableScore> getObservableScores()
	{
		if (!initialized && ready.getValue() != null)
		{
			initializeData();
		}

		return observableScores;
	}

	public ObservableScore getObservableScore()
	{
		if (!initialized && ready.getValue() != null)
		{
			initializeData();
		}

		return observableScores.get(currentIndex.getValue());
	}

	public MutableLiveData<Integer> getCurrentIndex()
	{
		if (currentIndex == null)
		{
			currentIndex = new MutableLiveData<>();
		}

		return currentIndex;
	}

	public void selectIndex(int index)
	{
		Log.i(TAG, "Setting index in ScoreViewModel: " + index);
		currentIndex.setValue(index);
	}

	public static class Factory extends ViewModelProvider.AndroidViewModelFactory
	{

		/**
		 * Creates a {@code AndroidViewModelFactory}
		 *
		 * @param application an application to pass in {@link AndroidViewModel}
		 */
		public Factory(@NonNull final Application application)
		{
			super(application);
		}
	}
}
