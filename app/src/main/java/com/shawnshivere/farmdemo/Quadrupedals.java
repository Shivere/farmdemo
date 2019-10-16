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
import android.support.v7.widget.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Quadrupedals extends AppCompatActivity implements QuadrupedalsAdapter.AnimalsAdapterListener{

    private static final String TAG = Quadrupedals.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Animal> animalList;
    private QuadrupedalsAdapter mAdapter;
    private SearchView searchView;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadrupedals);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        recyclerview();

//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Animal animal = animalList.get(position);
//                Toast.makeText(getApplicationContext(), animal.getName() + " is selected!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//                Animal animal = animalList.get(position);
//                Snackbar.make(coordinatorLayout, "Do you want to delete " + animal.getName() + "?", Snackbar.LENGTH_LONG)
//                        .setAction("Delete", null).show();
//            }
//        }));

        prepareAnimalData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "This adds animals", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), AddAnimal.class);
                startActivity(intent);
            }
        });
    }

    private void recyclerview(){
        recyclerView = findViewById(R.id.recycler_view);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        animalList = new ArrayList<>();
        mAdapter = new QuadrupedalsAdapter(this,animalList,this);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }
    private void prepareAnimalData() {
        Animal animal = new Animal(1,"Cow",3,"Female","Cow10","Friesian", R.drawable.cow);
        animalList.add(animal);
        animal = new Animal(2,"Cow1",2,"Female","Cow9", "Ayrshire", R.drawable.cow1);
        animalList.add(animal);
        animal = new Animal(3,"Cow2",3,"Male","Cow8", "Angus", R.drawable.cow9);
        animalList.add(animal);
        animal = new Animal(4,"Cow3",2,"Female","Cow7", "Friesian", R.drawable.cow3);
        animalList.add(animal);
        animal = new Animal(5,"Cow4",4,"Female","Cow6", "Guernsey", R.drawable.cow4);
        animalList.add(animal);
        animal = new Animal(6,"Cow5",2,"Male","Cow5", "Hereford", R.drawable.cow5);
        animalList.add(animal);
        animal = new Animal(7,"Cow6",1,"Female","Cow4", "Jersey", R.drawable.cow6);
        animalList.add(animal);
        animal = new Animal(8,"Cow7",2,"Female","Cow3", "Friesian", R.drawable.cow7);
        animalList.add(animal);
        animal = new Animal(9,"Cow8",3,"Female","Cow2", "Jersey", R.drawable.cow8);
        animalList.add(animal);
        animal = new Animal(10,"Cow9",5,"Female","Cow1", "Ayrshire", R.drawable.cow2);
        animalList.add(animal);
        animal = new Animal(11,"Cow10",3,"Female","Cow", "Guernsey", R.drawable.cow10);
        animalList.add(animal);
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
                return true;

            case R.id.action_search:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public void onBackPressed() {
//        // close search view on back button pressed
//        if (!searchView.isIconified()) {
//            searchView.setIconified(true);
//            return;
//        }
//        super.onBackPressed();
//    }

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
        Intent intent = new Intent(getApplicationContext(), QuadrupedalsProfile.class);
        intent.putExtra("img", animal.getThumbnail());
        intent.putExtra("name", animal.getName());
        intent.putExtra("type", animal.getType());
        intent.putExtra("gender", animal.getGender());
        startActivity(intent);
    }

    public void totalMilk_graph(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(),total_milk_graph.class);
        startActivity(intent);
    }
}
