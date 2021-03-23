package com.example.notesj.db;

import android.annotation.SuppressLint;
import android.provider.BaseColumns;

import java.util.Locale;

public class NotesContract {
    public static final class NotesEntry implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DAY_OF_WEEK = "day_of_week";
        public static final String COLUMN_PRIORITY = "priority";

        public static final String TYPE_TEXT = "TEXT";
        public static final String TYPE_INTEGER = "INTEGER";

        @SuppressLint("ConstantLocale")
        public static final String CREATE_COMMAND = String.format(Locale.getDefault(),"CREATE TABLE IF NOT EXISTS %s" +
                "(%s %s PRIMARY KEY AUTOINCREMENT, %s %s, %s %s, %s %s, %s %s)",
                TABLE_NAME, _ID, TYPE_INTEGER, COLUMN_TITLE, TYPE_TEXT, COLUMN_DESCRIPTION,
                TYPE_TEXT, COLUMN_DAY_OF_WEEK, TYPE_INTEGER, COLUMN_PRIORITY, TYPE_INTEGER);

        public static final String DROP_COMMAND = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
