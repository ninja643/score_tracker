package rs.ac.ni.pmf.scoretracker.data;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(
		entities = {TeamEntity.class, GameEntity.class},
		views = {GameDetails.class},
		version = 1)
public abstract class ScoresDatabase extends RoomDatabase
{
	public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);
	private static final String DATABASE_NAME = "scores_database";

	private static volatile ScoresDatabase instance;

	private final MutableLiveData<Boolean> databaseCreated = new MutableLiveData<>();

	public static ScoresDatabase getInstance(final Context context)
	{
		if (instance == null)
		{
			synchronized (ScoresDatabase.class)
			{
				if (instance == null)
				{
					instance = createDatabase(context);
					// If the database already exists, then createdDatabase() won't update
					// the mutable live data databaseCreated
					instance.updateDatabaseCreated(context.getApplicationContext());
				}
			}
		}

		return instance;
	}

	private static ScoresDatabase createDatabase(final Context context)
	{
		return Room.databaseBuilder(context.getApplicationContext(),
				ScoresDatabase.class,
				DATABASE_NAME)
				.setQueryExecutor(databaseWriteExecutor)
				.setTransactionExecutor(databaseWriteExecutor)
				.addCallback(new Callback()
				{
					@Override
					public void onCreate(@NonNull final SupportSQLiteDatabase db)
					{
						// Called only once, after the database is created
						super.onCreate(db);

						ScoresDatabase.databaseWriteExecutor.execute(() ->
						{
							final ScoresDatabase database =
									ScoresDatabase.getInstance(context.getApplicationContext());
							importData(database);

							database.setDatabaseCreated();
						});
					}
				})
				.build();
	}

	private static void importData(final ScoresDatabase database)
	{
		database.runInTransaction(() ->
		{
			final long team1id = database.teamsDao().insert(new TeamEntity("Partizan"));
			final long team2id = database.teamsDao().insert(new TeamEntity("Real Madrid"));
			final long team3id = database.teamsDao().insert(new TeamEntity("Joventut"));
			final long team4id = database.teamsDao().insert(new TeamEntity("Olympiacos"));

			database.gamesDao().insert(new GameEntity(team1id, team2id));
			database.gamesDao().insert(new GameEntity(team1id, team3id));
			database.gamesDao().insert(new GameEntity(team1id, team4id));
			database.gamesDao().insert(new GameEntity(team2id, team3id));
			database.gamesDao().insert(new GameEntity(team2id, team4id));
			database.gamesDao().insert(new GameEntity(team3id, team4id));
		});
	}

	public abstract TeamsDao teamsDao();

	public abstract GamesDao gamesDao();

	public MutableLiveData<Boolean> getDatabaseCreated()
	{
		return databaseCreated;
	}

	private void setDatabaseCreated()
	{
		databaseCreated.postValue(true);
	}

	private void updateDatabaseCreated(final Context context)
	{
		if (context.getDatabasePath(DATABASE_NAME).exists())
		{
			setDatabaseCreated();
		}
	}
}
