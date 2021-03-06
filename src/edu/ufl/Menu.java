package edu.ufl;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity
{
	//basic startup menu
	String classes[] = {"thegame", "Help", "Settings", "Level", "High Score"};
	
    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,classes));
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
    	String act = classes[position];
    	try{
    	super.onListItemClick(l, v, position, id);
    	Class myClass = Class.forName("edu.ufl." + act);
    	Intent intent = new Intent(Menu.this, myClass);
    	startActivity(intent);
    } catch(ClassNotFoundException e){
    	e.printStackTrace();
    	}
    }
    
}
