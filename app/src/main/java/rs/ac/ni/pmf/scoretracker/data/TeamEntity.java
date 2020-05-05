package rs.ac.ni.pmf.scoretracker.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "teams")
public class TeamEntity
{
	@PrimaryKey(autoGenerate = true)
	private long id;

	@NonNull
	private String name;

	public TeamEntity(final String name)
	{
		this.name = name;
	}

	public long getId()
	{
		return id;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	@NonNull
	public String getName()
	{
		return name;
	}

	public void setName(@NonNull final String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "TeamEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
