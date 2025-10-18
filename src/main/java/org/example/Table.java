package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Table<T> {
    private final String name;
    private final Class<T> modelClass;
    private final List<Page<T>> pages;
    private final ObjectMapper objectMapper;
    private final FileWriter walWriter;
    //private BPlusTree<Object, T> index;


    public Table(String name, Class<T> modelClass) throws IOException {
        this.name = name;
        this.modelClass = modelClass;
        this.pages = new ArrayList<>();
        // write ahead log. wal
        this.objectMapper = new ObjectMapper();
        this.walWriter = new FileWriter(name + ".wal", true);
    }

    public String getName(){
        return name;
    }

    public void insert(T data) throws IOException {

        String json = objectMapper.writeValueAsString(data);
        int dataSize = json.getBytes().length;

        writeToWAL(json);

        for(Page<T> page : pages){
            if(page.canInsert(data, dataSize)){
                page.insert(data, dataSize);
                return;
            }
        }

        String newPageFile = name + "-page-" + (pages.size() + 1) + ".tbl";
        Page<T> newPage = new Page<>(newPageFile);
        newPage.insert(data, dataSize);
        pages.add(newPage);
    }

    private void writeToWAL(String json) throws IOException {
        walWriter.write("INSERT: " + json + "\n");
        walWriter.flush();
    }

    private void commitWAL() throws IOException {
        walWriter.write("COMMIT\n");
        walWriter.flush();
    }

    public List<Page<T>> getPages() {
        return pages;
    }
}
