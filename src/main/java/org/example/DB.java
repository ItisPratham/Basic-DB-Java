package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB {
    private final Map<String, Table> tables;

    public DB() {
        this.tables = new HashMap<>();
    }

    // check whether the table with same name exist or not
    public <T> void addTable(String name, Table <T> table) {
        if(tables.containsKey(name)){
            throw new IllegalArgumentException("Table already exists: " + name);
        }
        tables.put(name, table);
    }

    public List<String> getTableNames(){
        return new ArrayList<>(tables.keySet());
    }

    public Table getTable(String name){
        //need to do it
        return tables.get("string");
    }

    public <T> Table<T> getTable(String name, Class<T> modelClass) {
        Table<?> table = tables.get(name);
        if (table == null) {
            throw new IllegalArgumentException("Table not found: " + name);
        }
        return (Table<T>) table;
    }

    public <T> void createTable(String name, Class<T> modelClass) throws IOException {
        Table<T> table = new Table<>(name, modelClass);
        addTable(name, table);
    }

}
