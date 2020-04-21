package rs.ac.ni.pmf.scoretracker;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel
{
	private static final String TAG = "SCORE_TRACKER";

	private final List<ObservableScore> observableScores = new ArrayList<>();

	private MutableLiveData<Integer> currentIndex = new MutableLiveData<>();

	private void loadData()
	{
		observableScores.add(new ObservableScore("Partizan", "Joventut"));
		observableScores.add(new ObservableScore("Limoges", "Treviso"));
		observableScores.add(new ObservableScore("Joventut", "Olympiacos"));
		observableScores.add(new ObservableScore("Real Madrid", "Olympiacos"));
		currentIndex.setValue(0);
	}

	public List<ObservableScore> getObservableScores()
	{
		if (observableScores.isEmpty())
		{
			loadData();
		}

		return observableScores;
	}

	public ObservableScore getObservableScore()
	{
		if (observableScores.isEmpty())
		{
			loadData();
		}

		return observableScores.get(currentIndex.getValue());
	}

	public MutableLiveData<Integer> getCurrentIndex()
	{
		if (currentIndex == null)
		{
			currentIndex = new MutableLiveData<>();

			if (observableScores.isEmpty())
			{
				loadData();
			}
		}

		return currentIndex;
	}

	public void selectIndex(int index)
	{
		Log.i(TAG, "Setting index in ScoreViewModel: " + index);
		currentIndex.setValue(index);
	}
}
