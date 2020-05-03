package rs.ac.ni.pmf.scoretracker.data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GamesDao
{
	@Insert(onConflict = OnConflictStrategy.ABORT)
	public long insert(GameEntity gameEntity);

	@Delete
	public void delete(GameEntity gameEntity);

	@Update
	public void update(GameEntity gameEntity);

	@Query("SELECT g.id, a.name as team_a, b.name as team_b, g.score_a, g.score_b " +
			"FROM games g " +
			"JOIN teams a ON a.id = g.team_a_id " +
			"JOIN teams b ON b.id = g.team_b_id ")
	public List<GameDTO> getGames();

	@Query("SELECT g.id, a.name as team_a, b.name as team_b, g.score_a, g.score_b " +
			"FROM games g " +
			"JOIN teams a ON a.id = g.team_a_id " +
			"JOIN teams b ON b.id = g.team_b_id " +
			"WHERE g.id = :gameId " +
			"LIMIT 1")
	public GameDTO findById(long gameId);

	@Query("UPDATE games SET score_a = score_a + :score WHERE id = :gameId")
	public void updateScoreA(long gameId, int score);

	@Query("UPDATE games SET score_b = score_b + :score WHERE id = :gameId")
	public void updateScoreB(long gameId, int score);

	@Query("SELECT * FROM gamedetails")
	List<GameDetails> getDetails();

	@Query("UPDATE games SET score_a = 0, score_b = 0 WHERE id = :gameId")
	public void clearScore(long gameId);
}
