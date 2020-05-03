package rs.ac.ni.pmf.scoretracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import rs.ac.ni.pmf.scoretracker.databinding.GameViewBinding;

public class CurrentGameFragment extends Fragment
{
	private static final String TAG = "SCORE_TRACKER";

	private ScoreViewModel scoreViewModel;

	private GameViewBinding binding;

	@Nullable
	@Override
	public View onCreateView(
			@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
			@Nullable final Bundle savedInstanceState)
	{
		binding = DataBindingUtil.inflate(inflater, R.layout.game_view, container, false);

		final FragmentActivity owner = requireActivity();
		scoreViewModel =
				new ViewModelProvider(owner, new ViewModelProvider.AndroidViewModelFactory(owner.getApplication()))
						.get(ScoreViewModel.class);

		scoreViewModel.getCurrentIndex().observe(getViewLifecycleOwner(), new Observer<Integer>()
		{
			@Override
			public void onChanged(final Integer index)
			{
				Log.i(TAG, "GameFragment: " + index);
				updateGameData();
			}
		});

		View view = binding.getRoot();
		binding.setLifecycleOwner(owner);
		binding.setScore(scoreViewModel.getObservableScore());
		return view;
	}

	private void updateGameData()
	{
		binding.setScore(scoreViewModel.getObservableScore());
	}
}
