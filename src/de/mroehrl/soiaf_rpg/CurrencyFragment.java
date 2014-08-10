package de.mroehrl.soiaf_rpg;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link CurrencyActivity}
 * on handsets.
 */
public class CurrencyFragment extends Fragment {
	
	// Some constants
	private final static int GOLDDRAGONS   = 0;
	private final static int SILVERSTAGS   = 1;
	private final static int SILVERMOONS   = 2;
	private final static int COPPERPENNIES = 3;
	
	// Current selection (starts with Golddragons)
	private int mSelection = GOLDDRAGONS;
	
	// GUI
	private TextView mResultView;
    private EditText mValueEdit;
    private Spinner mCurrencySpinner;
    private Button mCalcButton;
    private View mRootView;
    	
	
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "1";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CurrencyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        mRootView = inflater.inflate(R.layout.fragment_currencies, container, false);
        
        mResultView      = (TextView) mRootView.findViewById(R.id.resultText);
        mValueEdit       = (EditText) mRootView.findViewById(R.id.edittext);    	        
        mCurrencySpinner = (Spinner)  mRootView.findViewById(R.id.spinner);
        mCalcButton      = (Button)   mRootView.findViewById(R.id.calcButton);

        // Populate the spinner and react on changes
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mRootView.getContext(),R.array.currency, android.R.layout.simple_spinner_item);        
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);        
        mCurrencySpinner.setAdapter(adapter);
        mCurrencySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
        	@Override
        	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        		mSelection = pos;
            }
        	@Override
            public void onNothingSelected(AdapterView<?> parent) {
        		mSelection = 0;
            }	
        });        	
        
        // Register the click-Listener        
        mCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View v) {
            	onCalculate();        
            }
        });        
        
        return mRootView;
    }
    
    /**
     * Calculates the currencies.
     * This function concentrates on Golddragons, Stags, Moons and Pennies.
     * 
     * This information is taken from:
     *       http://awoiaf.westeros.org/index.php/Currency
     *       
     * Copper Coins:
	 *   Penny and Halfpenny.
     *   Half Groat, 2 pennies.
     *   Groat, 4 pennies.
     *   Star, 8 pennies. 
     *
	 * Silver Coins:
     *   Stag, 7 stars or 56 pennies
     *   Moon, 49 stars or 392 pennies
     *    
     * Gold Coins:
	 *   Dragons, 30 Moons, 210 Stags or 11,760 pennies. 
     *
     * The coins most commonly encountered are: Half-Pennies, Pennies, Stars, 
     * Stags and Dragons; rarely does anyone have the change on levels between.
     * 
     */
    protected void onCalculate() {
    	StringBuffer buffer = new StringBuffer();
    	int golddragons = 0, silverstags = 0, silvermoons = 0, copperpennies = 0;
    	
        try {
        	int value = Integer.parseInt(mValueEdit.getText().toString());
        	
        	switch (mSelection) {
        	case GOLDDRAGONS:
        		golddragons   = value;        		
        		silvermoons   = value * 30;
        		silverstags   = value * 30 * 7;
        		copperpennies = value * 30 * 7 * 56;        		
        		break;
        	
        	case SILVERMOONS:
        		golddragons   = value / 30;        		
        		silvermoons   = value;
        		silverstags   = value * 7;
        		copperpennies = value * 7 * 56;
        		break;
        		
        	case SILVERSTAGS:
        		golddragons   = value / (7*30);
        		silvermoons   = value / 7;
        		silverstags   = value;        		
        		copperpennies = value * 56;
        		break;
        		
        	case COPPERPENNIES:
        		golddragons   = value / (56*7*30);
        		silvermoons   = value / (56*7);
        		silverstags   = value / 56;        		
        		copperpennies = value;        		
        		break;	
        	}        	      	
        	
        } catch (NumberFormatException e) {        	
        }
        
          
        buffer.append(golddragons).  append(" ").append(getString(R.string.Golddragons)).  append("\n")
        	  .append(silvermoons).  append(" ").append(getString(R.string.Silvermoons)).  append("\n")
	          .append(silverstags).  append(" ").append(getString(R.string.Silverstags)).  append("\n")	              
	          .append(copperpennies).append(" ").append(getString(R.string.Copperpennies)).append("\n");
	
        mResultView.setText(buffer.toString());	
    }

}
