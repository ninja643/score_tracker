package rs.ac.ni.pmf.scoretracker;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ObservableScore extends BaseObservable
{
	private int scoreA;
	private int scoreB;

	private int x;
	private boolean enabled;

	@Bindable
	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(final boolean enabled)
	{
		this.enabled = enabled;
		notifyPropertyChanged(BR.enabled);
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

	@Bindable
	public String getX()
	{
		return String.valueOf(x);
	}

	public void setX(String x)
	{
		this.x = Integer.valueOf(x);
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

		Log.i("SCORE_TRACKER", "x = " + x);
	}

	public enum Team {
		TEAM_A,
		TEAM_B
	}
}
