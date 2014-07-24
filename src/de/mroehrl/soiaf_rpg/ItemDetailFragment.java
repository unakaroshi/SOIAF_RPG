package de.mroehrl.soiaf_rpg;

import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
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


import de.mroehrl.soiaf_rpg.content.SOIAFContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
	private final static int GOLDDRACHEN = 0;
	private final static int SILBERHIRSCHEN = 1;
	private final static int SILBERMONDE = 2;
	private final static int KUPFERSTUECKE = 3;
	
	TextView textView;
    EditText textEdit;
    
	
	private int selection = GOLDDRACHEN;
	
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private SOIAFContent.SOIAFItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = SOIAFContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        
        final Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(),R.array.currency, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
        	@Override
        	public void onItemSelected(AdapterView<?> parent, View view, 
                    int pos, long id) {
        		selection = pos;
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }
        	@Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
        		selection = 0;
            }	
        });
        	
        
        final Button button = (Button) rootView.findViewById(R.id.calcButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	onCalculate();        
            }
        });
        
        textView = (TextView) rootView.findViewById(R.id.resultText);
        textEdit = (EditText) rootView.findViewById(R.id.edittext);
    	


        // Show the dummy content as text in a TextView.
        //if (mItem != null) {
        //    (rootView.findViewById(R.id.)).setText(mItem.content);
        //}

        return rootView;
    }
    
    protected void onCalculate() {
        
        //final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        
        try {
        	int value = Integer.parseInt(textEdit.getText().toString());
        	
        	StringBuffer buffer = new StringBuffer();
        	
        	int gd = 0;
        	int sh = 0;
        	int sm = 0;
        	int ks = 0;
        	switch (selection) {
        	case GOLDDRACHEN:
        		gd = value;        		
        		sm = value * 30;
        		sh = value * 30 * 7;
        		ks = value * 30 * 7 * 56;
        		
        		break;
        	
        	case SILBERMONDE:
        		gd = value / 30;        		
        		sm = value;
        		sh = value * 7;
        		ks = value * 7 * 56;        		
        		
        		break;
        		
        	case SILBERHIRSCHEN:
        		gd = value / (7*30);
        		sm = value / 7;
        		sh = value;        		
        		ks = value * 56;
        		
        		break;
        		
        	case KUPFERSTUECKE:
        		gd = value / (56*7*30);
        		sm = value / (56*7);
        		sh = value / 56;        		
        		ks = value;        		
        		break;	
        	}        	
        	buffer.append(gd).append(" ").append(getString(R.string.golddrachen)).append("\n")
        	      .append(sm).append(" ").append(getString(R.string.silbermonde)).append("\n")
                  .append(sh).append(" ").append(getString(R.string.silberhirschen)).append("\n")	              
	              .append(ks).append(" ").append(getString(R.string.kupferstuecke)).append("\n");            
        	textView.setText(buffer.toString());        	
        } catch (NumberFormatException e) {
        	textView.setText("You pressed Calc!");
        }
    }

}
