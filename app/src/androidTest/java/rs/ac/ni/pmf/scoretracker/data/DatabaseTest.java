package rs.ac.ni.pmf.scoretracker.data;

import android.content.Context;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest
{
	private static final String TAG = "SCORE_TRACKER";
	private TeamsDao teamsDao;
	private GamesDao gamesDao;

	private ScoresDatabase database;

	@Before
	public void createDatabase()
	{
		final Context context = ApplicationProvider.getApplicationContext();
		database = Room.inMemoryDatabaseBuilder(context, ScoresDatabase.class).build();
		teamsDao = database.teamsDao();
		gamesDao = database.gamesDao();
	}

	@After
	public void closeDatabase()
	{
		database.close();
	}

	@Test
	public void shouldTestFindByName()
	{
		final TeamEntity team1 = new TeamEntity("Partizan");
		final TeamEntity team2 = new TeamEntity("Partizan");

		final long team1Id = teamsDao.insert(team1);
		final long team2Id = teamsDao.insert(team2);

		final TeamEntity partizan = teamsDao.findByName(team1.getName().toLowerCase());
		assertThat(partizan).isNotNull();
		assertThat(partizan.getName()).isEqualTo(team1.getName());
		assertThat(partizan.getId()).isEqualTo(team1Id);

		Log.i(TAG, partizan.toString());
	}

	@Test
	public void shouldInsertAndReadTeams()
	{
		final TeamEntity team1 = new TeamEntity("Partizan");
		final TeamEntity team2 = new TeamEntity("Real Madrid");

		final long team1Id = teamsDao.insert(team1);
		assertThat(team1Id).isEqualTo(1);

		final long team2Id = teamsDao.insert(team2);
		assertThat(team2Id).isEqualTo(2);

		final List<TeamEntity> teams = teamsDao.getAllTeams();

		assertThat(teams.size()).isEqualTo(2);
		assertThat(teams.stream().map(TeamEntity::getName).collect(Collectors.toList()))
				.containsExactlyInAnyOrder(team1.getName(), team2.getName());

		final TeamEntity partizan = teamsDao.findByName(team1.getName().toLowerCase());
		assertThat(partizan).isNotNull();
		assertThat(partizan.getName()).isEqualTo(team1.getName());

		assertThat(teamsDao.findByName("Partizan")).isNull();

		assertThat(teamsDao.findById(team2Id).getName()).isEqualTo(team2.getName());
	}

	@Test
	public void shouldAddAndRetrieveGames()
	{
		final TeamEntity team1 = new TeamEntity("Partizan");
		final TeamEntity team2 = new TeamEntity("Real Madrid");

		final long team1Id = teamsDao.insert(team1);
		Log.i(TAG, "Added team " + team1.getName() + " with id " + team1Id);
		final long team2Id = teamsDao.insert(team2);
		Log.i(TAG, "Added team " + team2.getName() + " with id " + team2Id);

		final GameEntity gameEntity = new GameEntity(team1Id, team2Id);
		long gameId = gamesDao.insert(gameEntity);
		Log.i(TAG, "Added game with id " + gameId);

		final List<GameDTO> games = gamesDao.getGames();

		assertThat(games.size()).isEqualTo(1);
		Log.i(TAG, games.get(0).toString());
	}

	@Test
	public void shouldIncreaseScoreCorrectly()
	{
		final TeamEntity team1 = new TeamEntity("Partizan");
		final TeamEntity team2 = new TeamEntity("Real Madrid");

		final long team1Id = teamsDao.insert(team1);
		final long team2Id = teamsDao.insert(team2);

		final GameEntity gameEntity = new GameEntity(team1Id, team2Id);
		long gameId = gamesDao.insert(gameEntity);

		gamesDao.updateScoreA(gameId, 2);
		gamesDao.updateScoreB(gameId, 3);
		gamesDao.updateScoreA(gameId, 1);
		gamesDao.updateScoreA(gameId, 1);
		gamesDao.updateScoreB(gameId, 2);

		final GameDTO game = gamesDao.findById(gameId);

		assertThat(game.getScoreA()).isEqualTo(4);
		assertThat(game.getScoreB()).isEqualTo(5);
	}

	@Test
	public void shouldUseView()
	{
		final TeamEntity team1 = new TeamEntity("Partizan");
		final TeamEntity team2 = new TeamEntity("Real Madrid");

		final long team1Id = teamsDao.insert(team1);
		final long team2Id = teamsDao.insert(team2);

		final GameEntity gameEntity = new GameEntity(team1Id, team2Id);
		long gameId = gamesDao.insert(gameEntity);

		final List<GameDetails> details = gamesDao.getDetails();
		assertThat(details.size()).isEqualTo(1);
		assertThat(details.get(0).getTeamA()).isEqualTo(team1.getName());
	}
}
