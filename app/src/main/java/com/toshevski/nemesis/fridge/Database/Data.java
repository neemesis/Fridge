package com.toshevski.nemesis.fridge.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

import com.toshevski.nemesis.fridge.Model.Fridge;
import com.toshevski.nemesis.fridge.Model.Market;
import com.toshevski.nemesis.fridge.Model.Product;
import com.toshevski.nemesis.fridge.Model.Recipe;

import java.util.ArrayList;

public class Data {

    private static DB dbc;

    public Data(Context ctx) {
         dbc = new DB(ctx);
    }

    public void insertIntoMarket(Market m) {
        ContentValues cv = new ContentValues();
        cv.put(DB.Markets.NAME, m.Name);
        cv.put(DB.Markets.LAT, m.Location.getLatitude());
        cv.put(DB.Markets.LON, m.Location.getLongitude());

        dbc.getWritableDatabase().insert(DB.Markets.TABLE_NAME, null, cv);
    }

    public void deleteFromMarkets(Market m) {
        SQLiteDatabase db = dbc.getReadableDatabase();
        String query = "DELETE FROM " + DB.Markets.TABLE_NAME + " WHERE name = " + m.Name;

        db.rawQuery(query, null);
    }

    public void insertIntoReceipts(Recipe r) {
        ContentValues cv = new ContentValues();
        cv.put(DB.Recipes.NAME, r.Name);
        cv.put(DB.Recipes.DESC, r.Description);

        dbc.getWritableDatabase().insert(DB.Recipes.TABLE_NAME, null, cv);
    }


    public void insertIntoProducts(Product m) {
        ContentValues cv = new ContentValues();
        cv.put(DB.Products.NAME, m.Name);
        cv.put(DB.Products.AVAIL, m.IsAvailable ? 1 : 0);
        cv.put(DB.Products.QTY, m.Quantity);

        dbc.getWritableDatabase().insert(DB.Products.TABLE_NAME, null, cv);
    }

    public void deleteFromProducts(Product m) {
        SQLiteDatabase db = dbc.getReadableDatabase();
        String query = "DELETE FROM " + DB.Products.TABLE_NAME + " WHERE name = " + m.Name;

        db.rawQuery(query, null);
    }

    public void insertIntoFridges(Fridge f) {
        ContentValues cv = new ContentValues();
        cv.put(DB.Products.NAME, f.Name);
        dbc.getWritableDatabase().insert(DB.Fridge.TABLE_NAME, null, cv);
    }

    public void deleteFromFridges(Fridge m) {
        SQLiteDatabase db = dbc.getReadableDatabase();
        String query = "DELETE FROM " + DB.Fridge.TABLE_NAME + " WHERE name = " + m.Name;

        db.rawQuery(query, null);
    }

    public ArrayList<Market> getAllMarkets() {
        ArrayList<Market> m = new ArrayList<>();

        SQLiteDatabase db = dbc.getReadableDatabase();

        String query = "SELECT  * FROM " + DB.Markets.TABLE_NAME;

        Cursor c = db.rawQuery(query, null);

        Market mark = null;
        if (c.moveToFirst()) {
            do {
                String name = c.getString(1);
                double LAT = Double.parseDouble(c.getString(2));
                double LON = Double.parseDouble(c.getString(3));

                Location l = new Location("");
                l.setLatitude(LAT);
                l.setLongitude(LON);

                mark = new Market(name, l);

                m.add(mark);
            } while (c.moveToNext());
        }

        c.close();
        return m;
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> m = new ArrayList<>();

        SQLiteDatabase db = dbc.getReadableDatabase();

        String query = "SELECT  * FROM " + DB.Products.TABLE_NAME;

        Cursor c = db.rawQuery(query, null);

        Product mark = null;
        if (c.moveToFirst()) {
            do {
                String name = c.getString(1);
                boolean avail = Integer.parseInt(c.getString(2)) != 0;
                double qty = Double.parseDouble(c.getString(3));

                mark = new Product(name, qty, avail);

                m.add(mark);
            } while (c.moveToNext());
        }

        c.close();
        return m;
    }

    public ArrayList<Recipe> getAllReceipts() {
        ArrayList<Recipe> m = new ArrayList<>();

        SQLiteDatabase db = dbc.getReadableDatabase();

        String query = "SELECT  * FROM " + DB.Products.TABLE_NAME;

        Cursor c = db.rawQuery(query, null);

        Recipe mark = null;
        if (c.moveToFirst()) {
            do {
                String name = c.getString(1);
                boolean avail = Integer.parseInt(c.getString(2)) != 0;
                double qty = Double.parseDouble(c.getString(3));

                //mark = new Recipe();

                m.add(mark);
            } while (c.moveToNext());
        }

        c.close();
        return m;
    }

    public ArrayList<Fridge> getAllFridges() {
        ArrayList<Fridge> m = new ArrayList<>();

        SQLiteDatabase db = dbc.getReadableDatabase();

        String query = "SELECT  * FROM " + DB.Fridge.TABLE_NAME;

        Cursor c = db.rawQuery(query, null);

        Fridge mark = null;
        if (c.moveToFirst()) {
            do {
                String name = c.getString(1);
                mark = new Fridge();
                mark.Name = name;


                m.add(mark);
            } while (c.moveToNext());
        }

        c.close();
        return m;
    }
}
