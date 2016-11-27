package com.toshevski.nemesis.fridge.View;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.toshevski.nemesis.fridge.Database.StaticData;
import com.toshevski.nemesis.fridge.Model.Recipe;
import com.toshevski.nemesis.fridge.R;

import java.util.ArrayList;

public class MyRecipesActivity extends AppCompatActivity {
    RecipeAdapter ra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ra = new RecipeAdapter();
        ListView marketsInListView = (ListView)findViewById(R.id.listRecipes);
        marketsInListView.setAdapter(ra);
        ra.notifyDataSetChanged();
        marketsInListView.setAlpha(1);
    }

    public class RecipeAdapter extends BaseAdapter {

        ArrayList<Recipe> recipes = StaticData.getRecipes();

        @Override
        public int getCount() {
            return recipes.size();
        }

        @Override
        public Recipe getItem(int arg0) {
            return recipes.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            LayoutInflater inflater = (LayoutInflater) MyRecipesActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arg1 = inflater.inflate(R.layout.recipes_list_view, arg2, false);

            TextView name = (TextView)arg1.findViewById(R.id.textView2);


            Recipe recipe = recipes.get(arg0);

            name.setText(recipe.Name);

            return arg1;
        }
    }


}