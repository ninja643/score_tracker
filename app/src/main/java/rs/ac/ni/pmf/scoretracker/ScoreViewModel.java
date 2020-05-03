package rs.ac.ni.pmf.scoretracker;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import rs.ac.ni.pmf.scoretracker.data.GameDetails;

public class ScoreViewModel extends AndroidViewModel
{
	private static final String TAG = "SCORE_TRACKER";

	private final List<ObservableScore> observableScores = new ArrayList<>();

	private MutableLiveData<Integer> currentIndex = new MutableLiveData<>();

	private ScoreTrackerRepository repository;

	public ScoreViewModel(@NonNull final Application application)
	{
		super(application);
		repository = new ScoreTrackerRepository(application);

		initializeData();
	}

	private void initializeData()
	{
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
	}

	public List<ObservableScore> getObservableScores()
	{
		return observableScores;
	}

	public ObservableScore getObservableScore()
	{
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
}
