//package com.yoyo.finalproject.data.local;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import com.yoyo.finalproject.ImageSize;
//import com.yoyo.finalproject.data.models.Favorite;
//import com.yoyo.finalproject.data.models.TvShow;
//
//public class FavoriteHelper extends SQLiteOpenHelper {
//    private static final String DB_NAME = "favorite.db";
//    private static final int VERSION = 1;
//    private static final String TABLE_NAME = "favorie_tv";
//    private static final String ID = "id";
//    private static final String NAME = "name";
//    private static final String IMG_URL = "img_url";
//    private static final String RATE = "rate";
//
//    public FavoriteHelper(Context context) {
//        super(context, DB_NAME, null, VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String query = String.format(
//                "CREATE TABLE %s (%s INTEGER PRIMARY KEY NOT NULL, %s TEXT, %s TEXT, %s TEXT);",
//                TABLE_NAME, ID, NAME, IMG_URL, RATE
//        );
//        sqLiteDatabase.execSQL(query);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//    }
//
//    public long insert(TvShow tvShow) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues val = new ContentValues();
//        val.put(ID, tvShow.getId());
//        val.put(NAME, tvShow.getName());
//        val.put(IMG_URL, tvShow.getPosterPath(ImageSize.W154));
//        val.put(RATE, String.valueOf(tvShow.getVoteAverage()));
//        return db.insert(TABLE_NAME, null, val);
//    }
//
//    public boolean isFavorite(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = String.format(Locale.getDefault(),
//                "SELECT * FROM %s WHERE id = %d",
//                TABLE_NAME, id
//        );
//        Cursor cursor = db.rawQuery(query, null);
//        boolean isSuccess = cursor.getCount() > 0;
//        cursor.close();
//        return isSuccess;
//    }
//
//    public int delete(int id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(TABLE_NAME, "id = ? ", new String[]{String.valueOf(id)});
//    }
//
//    public List<Favorite> selectAll() {
//        List<Favorite> favoriteList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = String.format("SELECT * FROM %s", TABLE_NAME);
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            do {
//                // TODO: append query results as favorite list
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return favoriteList;
//    }
//}
