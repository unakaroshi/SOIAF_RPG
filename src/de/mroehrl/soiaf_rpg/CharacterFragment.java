package de.mroehrl.soiaf_rpg;

import android.os.Bundle;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import de.mroehrl.RPG.RPGCharacter;
import de.mroehrl.RPG.SqlOpenHelper;


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link CurrencyActivity}
 * on handsets.
 */
public class CharacterFragment extends Fragment {
		
    private View rootView;
    
    private TextView rpg_char_name;
    private TextView rpg_char_money;
    
    private RPGCharacter rpgCharacter = new RPGCharacter();
    
	   
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharacterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadCharacter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {    	
        rootView = inflater.inflate(R.layout.fragment_character, container, false);    
        
        rpg_char_name  = (TextView) rootView.findViewById(R.id.rpg_char_name);
        rpg_char_money = (TextView) rootView.findViewById(R.id.rpg_char_money);
 
        rpg_char_name.setText(rpgCharacter.getName());
        rpg_char_money.setText(Integer.toString(rpgCharacter.getCopperPennies()));
        
        return rootView;
    }   
    
    private void loadCharacter() {
        SqlOpenHelper helper = new SqlOpenHelper(getActivity());
        SQLiteDatabase database = helper.getWritableDatabase();
                
        try  {
        	Cursor listCursor = database.query(
    			SqlOpenHelper.DBContract.CharacterEntry.TABLE_NAME, 
    			new String[] { 
    					SqlOpenHelper.DBContract.CharacterEntry.NAME,
    					SqlOpenHelper.DBContract.CharacterEntry.MONEY
    			}, 
    			null, 
    			null, 
    			null, 
    			null, 
    			null
        	);
        	
        	listCursor.moveToFirst();
        	if (!listCursor.isAfterLast()) {
        		rpgCharacter.setName(listCursor.getString(0));
        		rpgCharacter.setCopperPennies(listCursor.getInt(0));        		
        	} 

        	listCursor.close();
        	
        } finally {
        	database.close();
        }
        	
    }
    

}
