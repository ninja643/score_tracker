package rs.ac.ni.pmf.scoretracker;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel
{
	private final ObservableScore observableScore = new ObservableScore();

	private MutableLiveData<String> liveString;

	public ObservableScore getObservableScore()
	{
		return observableScore;
	}

	public MutableLiveData<String> getLiveString()
	{
		if (liveString == null)
		{
			liveString = new MutableLiveData<>();
		}

		return liveString;
	}
}
