package rs.ac.ni.pmf.scoretracker;

import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel
{
	private final ObservableScore observableScore = new ObservableScore();

	public ObservableScore getObservableScore()
	{
		return observableScore;
	}
}
