package org.example;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Page<T>{

    private final List<T> records;
    private int sizeInBytes = 0;
    private static final int MAX_SIZE = 500;
    private final String filePath;

    public Page(String filePath) {
        this.filePath = filePath;
        this.records = new ArrayList<>();
    }
    public boolean canInsert(T data, int dataSize) {
        return (sizeInBytes + dataSize) <= MAX_SIZE;
    }

    public void insert(T data, int dataSize) {
        if (!canInsert(data, dataSize)) {
            throw new IllegalStateException("Page size limit exceeded!");
        }
        records.add(data);
        sizeInBytes += dataSize;
    }

    public List<T> getRecords() {
        return records;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

//    public Page<T> getPage(String path){
//        // get that page file
//        return new Page<T>("test");
//    }
// This shall be in table records
}
/*

1
1
2
1
2
3
 */
/*

account table
id -> PK
account_id -> index
customer_name

{
"id": 1,
"account_id"
}

 */
//account-1.tbl
//{"id": 1, "account_id": 1000, "customer_name": "Nidip", "balance": 1000.0}
//{"id": 2, "account_id": 1001, "customer_name": "Pratham", "balance": 2000.0}
//
//account-2.tbl
//{"id": 3, "account_id": 1002, "customer_name": "jaiveer", "balance": 1000.0}