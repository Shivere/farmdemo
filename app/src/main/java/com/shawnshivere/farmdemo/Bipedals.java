package com.shawnshivere.farmdemo;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Bipedals extends AppCompatActivity implements BipedalsAdapter.BipedalsAdapterListener{
    private static final String TAG = Bipedals.class.getSimpleName();

    private RecyclerView recyclerView;
    private List<Animal> animalList;
    private BipedalsAdapter mAdapter;
    private SearchView searchView;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bipedals);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        //Set Recycler view
        recycler();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddAnimal.class);
                startActivity(intent);
            }
        });
    }

    public void recycler(){
        recyclerView = findViewById(R.id.bipedals_recycler_view);
        coordinatorLayout = findViewById(R.id.bipedals_coordinator_layout);
        animalList = new ArrayList<>();
        mAdapter = new BipedalsAdapter(this, animalList,this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        prepareAnimalData();
    }

    private void prepareAnimalData() {
        int[] images = new int[]{
                R.drawable.biped,
                R.drawable.biped4,
                R.drawable.biped5,
                R.drawable.biped6,
                R.drawable.biped7
        };
        Animal animal = new Animal(1,"Chicken 1",3,"Female","Chicken 2","Layers", images[0]);
        animalList.add(animal);
        animal = new Animal(2,"Dove 1",2,"Female","Dove 2", "Feral", images[1]);
        animalList.add(animal);
        animal = new Animal(3,"Dove 2",3,"Male","Dove1", "Homing", images[2]);
        animalList.add(animal);
        animal = new Animal(2,"Chicken 1",2,"Female","Chicken 3", "Layers", images[3]);
        animalList.add(animal);
        animal = new Animal(3,"Chicken 3",3,"Male","Chicken 1", "Layers", images[4]);
        animalList.add(animal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

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
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            case R.id.action_search:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onAnimalSelected(Animal animal) {
        Toast.makeText(getApplicationContext(), "Selected: " + animal.getName() + ", " + animal.getGender(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), BipedalsProfile.class);
        intent.putExtra("bipedals_img", animal.getThumbnail());
        intent.putExtra("bipedals_name", animal.getName());
        intent.putExtra("bipedals_type", animal.getType());
        intent.putExtra("bipedals_gender", animal.getGender());
        startActivity(intent);
    }

}
