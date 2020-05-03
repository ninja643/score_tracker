package rs.ac.ni.pmf.scoretracker.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "games",
		indices = {@Index("team_a_id"), @Index("team_b_id")},
		foreignKeys = {
				@ForeignKey(entity = TeamEntity.class,
						parentColumns = "id", childColumns = {"team_a_id"},
						onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.NO_ACTION),
				@ForeignKey(entity = TeamEntity.class,
						parentColumns = "id", childColumns = {"team_b_id"},
						onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.NO_ACTION)
		})
public class GameEntity
{
	@PrimaryKey(autoGenerate = true)
	private long id;

	@ColumnInfo(name = "team_a_id")
	private long teamAId;

	@ColumnInfo(name = "team_b_id")
	private long teamBId;

	@ColumnInfo(defaultValue = "0", name = "score_a")
	private int scoreA;

	@ColumnInfo(defaultValue = "0", name = "score_b")
	private int scoreB;

	public GameEntity(final long teamAId, final long teamBId)
	{
		this.teamAId = teamAId;
		this.teamBId = teamBId;
	}

	public long getId()
	{
		return id;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public long getTeamAId()
	{
		return teamAId;
	}

	public void setTeamAId(final long teamAId)
	{
		this.teamAId = teamAId;
	}

	public long getTeamBId()
	{
		return teamBId;
	}

	public void setTeamBId(final long teamBId)
	{
		this.teamBId = teamBId;
	}

	public int getScoreA()
	{
		return scoreA;
	}

	public void setScoreA(final int scoreA)
	{
		this.scoreA = scoreA;
	}

	public int getScoreB()
	{
		return scoreB;
	}

	public void setScoreB(final int scoreB)
	{
		this.scoreB = scoreB;
	}
}
