package buet.rafi.sudoku;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {

	private static MediaPlayer musicPlayer = null;

	public static void play(Context context, int resource) {
		if(Preferences.getMusic(context)) {
			stop(context);		
			musicPlayer = MediaPlayer.create(context, resource);
			musicPlayer.setLooping(true);
			musicPlayer.start();
		}
	}

	public static void stop(Context context) {
		if(musicPlayer != null) {
			musicPlayer.stop();
			musicPlayer.release();
			musicPlayer = null;
		}
	}
}
