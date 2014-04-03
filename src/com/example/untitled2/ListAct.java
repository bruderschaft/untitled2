package com.example.untitled2;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class ListAct extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter mAdapter;
    LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

    private static final int LOADER_ID = 1;
    private static final String[] PROJECTION =  new  String[]{"_id", "name", "age"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int[] viewsID = {R.id.textName, R.id.textAge};

        mAdapter = new SimpleCursorAdapter(this, R.layout.list_item, null, PROJECTION, viewsID, 0);

        setListAdapter(mAdapter);
        mCallbacks = this;
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, mCallbacks);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(ListAct.this, MyActivity.CONTACT_CONTENT_URI, PROJECTION, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case LOADER_ID:
                mAdapter.swapCursor(data);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
