package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        DB db = new DB();

        db.createTable("accounts", Account.class);

        Table<Account> accountTable = db.getTable("accounts", Account.class);

        Account acc = new Account(100,"Nidip", 1000.0);
        accountTable.insert(acc);

    }

    /*private static final int MAX_SIZE = 4096; // 4KB page size in bytes
    private static final String EXTENSION = ".dat";
    private static final String baseFileName = "DemoFile";
    private static final String WAL_FILE = "wal.log";
    private static final File dir = new File("storage_files");

    private static final Map<Integer, String> pageCache = new HashMap<>();
    private static int pageCount = 0;

    public static void main(String[] args) throws IOException {
        if (!dir.exists()) dir.mkdir();

        String paragraph = "An encyclopedia is a reference work or compendium providing summaries of knowledge, either general or special, in a particular field or discipline. Encyclopedias are divided into articles or entries that are arranged alphabetically by article name or by thematic categories, or else are hyperlinked and searchable.[4] Encyclopedia entries are longer and more detailed than those in most dictionaries. Generally speaking, encyclopedia articles focus on factual information concerning the subject named in the article's title; this is unlike dictionary entries, which focus on linguistic information about words, such as their etymology, meaning, pronunciation, use, and grammatical forms.\n" +
                "\n" +
                "Encyclopedias have existed for around 2,000 years and have evolved considerably during that time as regards language (written in a major international or a vernacular language), size (few or many volumes), intent (presentation of a global or a limited range of knowledge), cultural perspective (authoritative, ideological, didactic, utilitarian), authorship (qualifications, style), readership (education level, background, interests, capabilities), and the technologies available for their production and distribution (hand-written manuscripts, small or large print runs, Internet). As a valued source of reliable information compiled by experts, printed versions found a prominent place in libraries, schools and other educational institutions.\n" +
                "\n" +
                "In the 21st century, the appearance of digital and open-source versions such as Wikipedia (together with the wiki website format) has vastly expanded the accessibility, authorship, readership, and variety of encyclopedia entries.";

        writeToDB(paragraph);

        String result = readFromDB();
        System.out.println("DB Content:\n" + result);

    }

    private static void writeToDB(String paragraph) throws IOException {
        // Step 1: Append to WAL
        try (FileOutputStream walStream = new FileOutputStream(WAL_FILE, true)) {
            walStream.write(paragraph.getBytes(StandardCharsets.UTF_8));
        }

        // Step 2: Chunk paragraph into pages (≤4KB per UTF-8 encoded chunk)
        int offset = 0;
        while (offset < paragraph.length()) {
            StringBuilder chunkBuilder = new StringBuilder();
            int currentSize = 0;

            while (offset < paragraph.length()) {
                char ch = paragraph.charAt(offset);
                byte[] charBytes = String.valueOf(ch).getBytes(StandardCharsets.UTF_8);

                if (currentSize + charBytes.length > MAX_SIZE) break;

                chunkBuilder.append(ch);
                currentSize += charBytes.length;
                offset++;
            }

            String pageContent = chunkBuilder.toString();

            // Check if already in memory
            if (!pageCache.containsKey(pageCount)) {
                File pageFile = new File(dir, baseFileName + "_" + pageCount + EXTENSION);
                if (pageFile.exists()) {
                    // Load from disk if already exists
                    try (BufferedReader reader = new BufferedReader(new FileReader(pageFile))) {
                        StringBuilder existing = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            existing.append(line).append("\n");
                        }
                        pageCache.put(pageCount, existing.toString());
                    }
                } else {
                    // Write new page
                    pageCache.put(pageCount, pageContent);
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(pageFile))) {
                        writer.write(pageContent);
                        System.out.println("Page created: " + pageFile.getName() +
                                " | Size: " + currentSize + " bytes");
                    }
                }
            }

            pageCount++;
        }
    }

    private static String readFromDB() throws IOException {
        StringBuilder result = new StringBuilder();

        for (int i = 0; ; i++) {
            String pageData;

            if (pageCache.containsKey(i)) {
                pageData = pageCache.get(i);
            } else {
                File pageFile = new File(dir, baseFileName + "_" + i + EXTENSION);
                if (!pageFile.exists()) break;

                try (BufferedReader reader = new BufferedReader(new FileReader(pageFile))) {
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line).append("\n");
                    }
                    pageData = builder.toString();
                    pageCache.put(i, pageData); // cache it
                }
            }

            result.append(pageData);
        }

        return result.toString();
    } */
}
