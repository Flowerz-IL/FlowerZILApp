package com.flowerzapi.providers_dashboard_app.model.localDB;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converters {

    @TypeConverter
    public static List<String> fromString(String value) {
        return new ArrayList<>(Arrays.asList(value.split(",")));
    }

    @TypeConverter
    public static String fromArrayList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String item : list) { sb.append(item).append(','); }
        return sb.toString();
    }
}
