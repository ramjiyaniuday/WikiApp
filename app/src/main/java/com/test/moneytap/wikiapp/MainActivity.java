package com.test.moneytap.wikiapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private WikiResponse wikiResponse;
    private SearchView searchView;
    private WikiAdapter adapter=null;
    private List<Page> searchList=new ArrayList<>();
    private TextView tvNoSearchFound;
    private static String FILENAME="SEARCH_HISTORY";
    private String url="https://en.wikipedia.org//w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpslimit=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        tvNoSearchFound=findViewById(R.id.tv_no_recent);
        fillRecentData();
    }

    private void fillRecentData(){
        readFromFile();
        if(searchList!=null && searchList.size()>0){
            WikiResponse wikiResponse = new WikiResponse();
            Query q=new Query();
            q.setPages(searchList);
            wikiResponse.setQuery(q);
            populateData(wikiResponse);
        }
    }

    private void readFromFile(){
        try {
            searchList= (List<Page>) CacheDataToFile.readObject(MainActivity.this,FILENAME);
            if(searchList!=null && searchList.size()>0)
                tvNoSearchFound.setVisibility(View.GONE);
            else
                tvNoSearchFound.setVisibility(View.VISIBLE);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void writeToFile(Page page){
        readFromFile();
        if(searchList==null)
            searchList=new ArrayList<>();
        searchList.add(0,page);
        if(searchList.size()==6)
            searchList.remove(5);
        try {
            CacheDataToFile.writeObject(MainActivity.this,FILENAME,searchList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
               /* if (!query.isEmpty()){
                    url=url+"&gpssearch="+query;
                    fetchData();
                }*/
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed

                if (!query.isEmpty()){
                    url=url+"&gpssearch="+query;
                    fetchData();
                }else if (/*query.length()==0 &&*/ adapter!=null){
                    adapter.clearAll();
                    fillRecentData();
                }
                return false;
            }
        });
        return true;
    }

    /**
     * fetches json by making http calls
     */
    private void fetchData() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response == null) {
                    Toast.makeText(getApplicationContext(), "Couldn't fetch the contacts! Pleas try again.", Toast.LENGTH_LONG).show();
                    return;
                }

                Log.d(TAG,response.toString());
                 WikiResponse items = new Gson().fromJson(response.toString(), new TypeToken<WikiResponse>() {}.getType());

                 if(items!=null){
                     populateData(items);
                 }

                Log.d(TAG,items.toString());
                // refreshing recycler view
                //mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApplication.getInstance().addToRequestQueue(request);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            if (/*query.length()==0 &&*/ adapter!=null){
                adapter.clearAll();
                fillRecentData();
            }
            return;
        }
        super.onBackPressed();
    }

    private void populateData(WikiResponse response){
        if(response!=null && response.getQuery()!=null && response.getQuery().getPages()!=null) {

            List<Page> pages =response.getQuery().getPages();
            adapter = new WikiAdapter(MainActivity.this, pages, new WikiAdapter.WikiAdapterListener() {
                @Override
                public void onItemSelected(Page page) {
                    String url="https://en.wikipedia.org/wiki/"+page.getTitle();
                    Intent webIntent =new Intent(MainActivity.this,WebviewActivity.class);
                    webIntent.putExtra("URL",url);
                    startActivity(webIntent);

                    writeToFile(page);
                }
            });

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
    }
}
