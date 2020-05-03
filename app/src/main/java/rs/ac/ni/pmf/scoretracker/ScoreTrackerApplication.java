package rs.ac.ni.pmf.scoretracker;

import android.app.Application;

import rs.ac.ni.pmf.scoretracker.data.ScoresDatabase;

public class ScoreTrackerApplication extends Application
{
	public ScoreTrackerRepository getRepository()
	{
		final ScoresDatabase database = ScoresDatabase.getInstance(this);
		final ScoreTrackerRepository instance = ScoreTrackerRepository.getInstance(database);

		instance.getGamesDetails();

		return instance;
	}
}
