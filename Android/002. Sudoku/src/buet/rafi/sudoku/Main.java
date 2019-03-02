package buet.rafi.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {

	private Button newGameButton;
	private Button continueButton;	
	private Button aboutButton;
	private Button exitButton;
	
	private static final String TAG = "Sudoku";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MouseClickHandler handler = new MouseClickHandler();
        
        newGameButton = (Button) findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(handler);
        continueButton = (Button) findViewById(R.id.continue_button);
        continueButton.setOnClickListener(handler);
        aboutButton = (Button) findViewById(R.id.about_button);
        aboutButton.setOnClickListener(handler);
        exitButton = (Button) findViewById(R.id.exit_button);
        exitButton.setOnClickListener(handler);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	Music.play(this, R.raw.music);
    	String gameMode = getPreferences(MODE_PRIVATE).getString("GAME_MODE", "NORMAL");
    	Toast.makeText(this, gameMode, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Music.stop(this);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);        
        getIntent().putExtra("CONTINUE", true);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	boolean isContinue = getIntent().getBooleanExtra("CONTINUE", false);
    	super.onRestoreInstanceState(savedInstanceState);
    	Toast.makeText(this, isContinue + "", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	
    	MenuInflater inFlater = getMenuInflater();
    	inFlater.inflate(R.menu.menu, menu);
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch(item.getItemId()) {
    	case R.id.settings:
    		startActivity(new Intent(this, Preferences.class));
    		return true;
    	}
    	return false;
    }
    
    private void openNewGameDialog() {
    	AlertDialog.Builder newGameDialog = new AlertDialog.Builder(this);
    	
    	newGameDialog.setTitle(R.string.new_game_title);
    	newGameDialog.setItems(R.array.difficulty, new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int i) {
				startGame(i);
				
				switch(i) {
				case 0: 
			    	getPreferences(MODE_PRIVATE).edit().putString("GAME_MODE", "EASY").commit();
			    	break;
				case 1:
			    	getPreferences(MODE_PRIVATE).edit().putString("GAME_MODE", "MEDIUM").commit();
			    	break;
				case 2:
			    	getPreferences(MODE_PRIVATE).edit().putString("GAME_MODE", "HARD").commit();
			    	break;
				}

		    	String gameMode = getPreferences(MODE_PRIVATE).getString("GAME_MODE", "NORMAL");
		    	Toast.makeText(Main.this, gameMode, Toast.LENGTH_SHORT).show();
			}
		});
    	newGameDialog.show();
    }
    
    private void startGame(int i) {
    	Log.d(TAG, "Clicked on " + i);
    }
    
    private class MouseClickHandler implements OnClickListener {

		public void onClick(View v) {
			switch(v.getId()) {
	    	case R.id.new_game_button:
	    		openNewGameDialog();
	    		break;
			case R.id.about_button:
				Intent i = new Intent(Main.this, About.class);
				startActivity(i);
				break;
	    	case R.id.exit_button:
	    		finish();
	    		break;
			}
		}
    	
    }
}