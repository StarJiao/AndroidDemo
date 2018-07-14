package com.star.demo.viewswitcher;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ViewSwitcher;

import com.star.demo.R;

public class ViewSwitcherDemoActivity extends ListActivity implements
        OnClickListener {
    //sample list items  
    static final String[] ITEMS = new String[]{"List Item 1", "List Item 2",
            "List Item 3", "List Item 4", "List Item 5", "List Item 6"};

    //the ViewSwitcher  
    private ViewSwitcher switcher;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //no window title  
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //create the ViewSwitcher in the current context  
        switcher = new ViewSwitcher(this);

        //footer Button: see XML1  
        Button footer = (Button) View.inflate(this,
                R.layout.viewswitcher_btn_loadmore,
                null);

        //progress View: see XML2  
        View progress = View.inflate(this,
                R.layout.viewswitcher_loading_footer,
                null);

        //add the views (first added will show first)  
        switcher.addView(footer);
        switcher.addView(progress);

        //add the ViewSwitcher to the footer  
        getListView().addFooterView(switcher);

        //add items to the ListView  
        setListAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ITEMS));
    }

    @Override
    /* Load More Button Was Clicked */
    public void onClick(View arg0) {
        //first view is showing, show the second progress view  
        switcher.showNext();
        //and start background work  
        new LoadDataTask().execute();
    }

    /**
     * Background Task To Get More Items
     **/
    private class LoadDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            //code to add more items  
            //...  
            try {
                Thread.sleep(3000); //only to demonstrate  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        /* Background Task is Done */
        protected void onPostExecute(Void result) {
            //go back to the first view  
            System.out.println("switch finish");
            switcher.showPrevious();
            //update the ListView  
        }
    }
}
