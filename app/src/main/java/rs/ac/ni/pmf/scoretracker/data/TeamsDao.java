package rs.ac.ni.pmf.scoretracker.data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TeamsDao
{
	@Insert(onConflict = OnConflictStrategy.ABORT)
	public long insert(TeamEntity team);

	@Delete
	public void delete(TeamEntity team);

	@Query("SELECT * FROM teams")
	List<TeamEntity> getAllTeams();

	@Query("SELECT * FROM teams WHERE lower(name) = :name LIMIT 1")
	public TeamEntity findByName(String name);

	@Query("SELECT * FROM teams WHERE id = :id LIMIT 1")
	public TeamEntity findById(long id);
}
