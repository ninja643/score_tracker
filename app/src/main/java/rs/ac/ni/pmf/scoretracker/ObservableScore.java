package rs.ac.ni.pmf.scoretracker;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ObservableScore extends BaseObservable
{
	private final long gameId;
	private final String teamA;
	private final String teamB;

	private final ScoreTrackerRepository repository;

	private int scoreA;
	private int scoreB;

	public ObservableScore(
			final long gameId, final String teamA, final String teamB,
			final ScoreTrackerRepository repository)
	{
		this.gameId = gameId;
		this.teamA = teamA;
		this.teamB = teamB;
		this.repository = repository;
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
		repository.updateScore(gameId, team, score);

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

		repository.clearScore(gameId);

		notifyPropertyChanged(BR.scoreA);
		notifyPropertyChanged(BR.scoreB);
	}

	public enum Team
	{
		TEAM_A,
		TEAM_B
	}
}
