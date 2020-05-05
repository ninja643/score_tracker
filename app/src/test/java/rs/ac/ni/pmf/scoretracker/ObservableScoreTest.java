package rs.ac.ni.pmf.scoretracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ObservableScoreTest
{
	public static final String TAG = "TEST";

	@Mock
	private ScoreTrackerRepository scoreTrackerRepository;

	private ObservableScore observableScore;

	@Before
	public void setUp() throws Exception
	{
		observableScore = new ObservableScore(1L, "Team A", "Team B", scoreTrackerRepository);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void addScore()
	{
		observableScore.addScore(ObservableScore.Team.TEAM_A, 3);
		observableScore.addScore(ObservableScore.Team.TEAM_B, 2);
		observableScore.addScore(ObservableScore.Team.TEAM_B, 4);

		assertThat(observableScore.getScoreA()).isEqualTo("3");
		assertThat(observableScore.getScoreB()).isEqualTo("6");

		verify(scoreTrackerRepository).updateScore(1L, ObservableScore.Team.TEAM_A, 3);
		verify(scoreTrackerRepository).updateScore(1L, ObservableScore.Team.TEAM_B, 2);

		verify(scoreTrackerRepository, times(3))
				.updateScore(anyLong(), any(ObservableScore.Team.class), anyInt());
	}

	@Test
	public void reset()
	{
		observableScore.addScore(ObservableScore.Team.TEAM_A, 3);
		observableScore.addScore(ObservableScore.Team.TEAM_B, 2);
		observableScore.addScore(ObservableScore.Team.TEAM_B, 4);

		assertThat(observableScore.getScoreA()).isEqualTo("3");
		assertThat(observableScore.getScoreB()).isEqualTo("6");

		observableScore.reset();

		assertThat(observableScore.getScoreA()).isEqualTo("0");
		assertThat(observableScore.getScoreB()).isEqualTo("0");
	}
}