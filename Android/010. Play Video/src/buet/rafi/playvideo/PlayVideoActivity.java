/**
 * Date: 14 July 2012
 */

package buet.rafi.playvideo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.VideoView;

public class PlayVideoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        VideoView view = (VideoView) findViewById(R.id.videoView1);
        view.setVideoPath("/data/YouTube - Ehsaas - doorie - Atif Aslam.3gp");
        view.start();
    }
}