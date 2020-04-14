package rs.ac.ni.pmf.scoretracker;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ObservableScore extends BaseObservable
{
	private int scoreA;
	private int scoreB;

	@Bindable
	public String getScoreA()
	{
		return String.valueOf(scoreA);
	}

	@Bindable
	public String getScoreB()
	{
		return String.valueOf(scoreB);
	}

	public void addScore(Team team, int score)
	{
		switch (team)
		{
			case TEAM_A:
				scoreA += score;
				notifyPropertyChanged(BR.scoreA);
				break;
			case TEAM_B:
				scoreB += score;
				notifyPropertyChanged(BR.scoreB);
		}
	}

	public void reset()
	{
		scoreA = 0;
		scoreB = 0;

		notifyPropertyChanged(BR.scoreA);
		notifyPropertyChanged(BR.scoreB);
	}

	public enum Team {
		TEAM_A,
		TEAM_B
	}
}
