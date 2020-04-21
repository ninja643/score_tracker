package rs.ac.ni.pmf.scoretracker;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ObservableScore extends BaseObservable
{
	private final String teamA;
	private final String teamB;

	private int scoreA;
	private int scoreB;

	public ObservableScore(final String teamA, final String teamB)
	{
		this.teamA = teamA;
		this.teamB = teamB;
	}

	@Bindable
	public String getTeamA()
	{
		return teamA;
	}

	@Bindable
	public String getTeamB()
	{
		return teamB;
	}

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
