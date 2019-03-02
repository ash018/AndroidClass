/**
 * Date: 14 July 2012
 */

package buet.rafi.audio;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;

public class PlayingAnAudioFileActivity extends Activity {
	private MediaPlayer mediaPlayer;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	int resId;
    	
    	/*switch(keyCode) {
    	case KeyEvent.KEYCODE_DPAD_DOWN:
    		resId = R.raw.in_the_darkness;
    		break;
    	case KeyEvent.KEYCODE_DPAD_UP:
    		resId = R.raw.in_the_end;
    		break;
    	default:
    		resId = R.raw.into_you;
    	}
    	
    	if(mediaPlayer != null)
    		mediaPlayer.release();
    	
    	mediaPlayer = MediaPlayer.create(this, resId);
    	mediaPlayer.start();*/
    	
    	return true;
    }
}