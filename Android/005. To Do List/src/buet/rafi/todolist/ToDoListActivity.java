package buet.rafi.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ToDoListActivity extends Activity {
	
	static final private int ADD_NEW_TODO = Menu.FIRST;
	static final private int REMOVE_TODO = Menu.FIRST + 1;
	
	EditText inputField;
	ListView list;
	ArrayList<String> toDoItems;
	ArrayAdapter<String> toDoListArrayAdapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        inputField  = (EditText) findViewById(R.id.inputField);
        list = (ListView) findViewById(R.id.list);
        
        toDoItems = new ArrayList<String>();
        toDoListArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, toDoItems);
        
        list.setAdapter(toDoListArrayAdapter);
        
        inputField.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction() == KeyEvent.ACTION_DOWN)
					if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
						toDoItems.add(0, inputField.getText().toString());
						
						toDoListArrayAdapter.notifyDataSetChanged();
						inputField.setText("");
						
						Toast.makeText(ToDoListActivity.this, R.string.confirmation_message, Toast.LENGTH_SHORT).show();
						return true;
					}
				return false;
			}
		});
        
        registerForContextMenu(list);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	
    	MenuItem itemAdd = menu.add(0, ADD_NEW_TODO, Menu.NONE, R.string.add_new);
    	MenuItem itemRemove = menu.add(0, REMOVE_TODO, Menu.NONE, R.string.remove);
    	
    	itemAdd.setIcon(R.drawable.new_task);
    	itemRemove.setIcon(R.drawable.remove_task);
    	
    	itemAdd.setShortcut('0', 'N');
    	itemRemove.setShortcut('1', 'R');
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	super.onOptionsItemSelected(item);
    	
    	switch(item.getItemId()) {
    	case ADD_NEW_TODO:
    		inputField.requestFocus();
    		inputField.setText("");
    		return true;
    	}
    	
    	return false;
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	
    	menu.setHeaderTitle("Selected To Do Item");
    	menu.add(0, REMOVE_TODO, Menu.NONE, R.string.remove);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	super.onContextItemSelected(item);
    	
    	switch(item.getItemId()) {
    	case REMOVE_TODO:
    		toDoItems.remove(list.getSelectedItemPosition());
    		toDoListArrayAdapter.notifyDataSetChanged();
    	}
    	return false;
    }
}