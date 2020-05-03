package rs.ac.ni.pmf.scoretracker;

import java.util.List;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import rs.ac.ni.pmf.scoretracker.data.GameDetails;
import rs.ac.ni.pmf.scoretracker.data.ScoresDatabase;

public class ScoreTrackerRepository
{
	private static ScoreTrackerRepository instance;

	private final ScoresDatabase database;

	private ScoreTrackerRepository(final ScoresDatabase database)
	{
		this.database = database;
	}

	public static ScoreTrackerRepository getInstance(final ScoresDatabase database)
	{
		if (instance == null)
		{
			synchronized (ScoreTrackerRepository.class)
			{
				if (instance == null)
				{
					instance = new ScoreTrackerRepository(database);
				}
			}
		}

		return instance;
	}

	public List<GameDetails> getGamesDetails()
	{
		return database.gamesDao().getDetails();
	}

	public void updateScore(long gameId, ObservableScore.Team team, int score)
	{
		if (team == ObservableScore.Team.TEAM_A)
		{
			ScoresDatabase.databaseWriteExecutor.execute(() -> database.gamesDao().updateScoreA(gameId, score));
		}
		else
		{
			ScoresDatabase.databaseWriteExecutor.execute(() -> database.gamesDao().updateScoreB(gameId, score));
		}
	}

	public void clearScore(long gameId)
	{
		ScoresDatabase.databaseWriteExecutor.execute(() -> database.gamesDao().clearScore(gameId));
	}

	public MutableLiveData<Boolean> isReady()
	{
		return database.getDatabaseCreated();
	}
}
