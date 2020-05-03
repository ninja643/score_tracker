package rs.ac.ni.pmf.scoretracker.data;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;

@DatabaseView("SELECT g.id, a.name as team_a, b.name as team_b, g.score_a, g.score_b " +
		"FROM games g " +
		"JOIN teams a ON a.id = g.team_a_id " +
		"JOIN teams b ON b.id = g.team_b_id ")
public class GameDetails
{
	@ColumnInfo(name = "id")
	private long id;

	@ColumnInfo(name = "team_a")
	private String teamA;

	@ColumnInfo(name = "team_b")
	private String teamB;

	@ColumnInfo(name = "score_a")
	private int scoreA;

	@ColumnInfo(name = "score_b")
	private int scoreB;

	public GameDetails(final long id, final String teamA, final String teamB, final int scoreA, final int scoreB)
	{
		this.id = id;
		this.teamA = teamA;
		this.teamB = teamB;
		this.scoreA = scoreA;
		this.scoreB = scoreB;
	}

	public long getId()
	{
		return id;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public String getTeamA()
	{
		return teamA;
	}

	public void setTeamA(final String teamA)
	{
		this.teamA = teamA;
	}

	public String getTeamB()
	{
		return teamB;
	}

	public void setTeamB(final String teamB)
	{
		this.teamB = teamB;
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
