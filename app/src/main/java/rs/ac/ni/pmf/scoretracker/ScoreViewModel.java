package rs.ac.ni.pmf.scoretracker;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel
{
	private final List<ObservableScore> observableScores = new ArrayList<>();

	private MutableLiveData<Integer> currentIndex = new MutableLiveData<>();

	public ObservableScore getObservableScore()
	{
		if (observableScores.isEmpty())
		{
			observableScores.add(new ObservableScore("Partizan", "Kinder"));
			currentIndex.setValue(0);
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
}
