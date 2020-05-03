package rs.ac.ni.pmf.scoretracker.data;

import androidx.room.ColumnInfo;

public class GameDTO
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

	public GameDTO(final long id, final String teamA, final String teamB, final int scoreA, final int scoreB)
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

	@Override
	public String toString()
	{
		return "GameDTO{" +
				"id=" + id +
				", teamA='" + teamA + '\'' +
				", teamB='" + teamB + '\'' +
				", scoreA=" + scoreA +
				", scoreB=" + scoreB +
				'}';
	}
}
