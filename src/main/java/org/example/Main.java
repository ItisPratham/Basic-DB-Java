package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // need to create a file system
        final int MAX_SIZE = 10; // temporary reducing it for testing -> change it to 4096 aka 4KB
        final String EXTENSION = ".txt";
        List<File> fileList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        File dir = new File("storage_files");
        if (!dir.exists()) dir.mkdir();

        if (dir.listFiles() != null) {
            for (File f : Objects.requireNonNull(dir.listFiles())) {
                if (f.isFile()) fileList.add(f);
            }
        }

        boolean flag = true;

        while (flag) {
            System.out.println("FILE SYSTEM - CRUD Operations");
            System.out.println("1: Create new file");
            System.out.println("2: Read an existing file");
            System.out.println("3: Update contents of an existing file");
            System.out.println("4: Delete a file");
            System.out.println("5: Exit\n\n");

            int choice = sc.nextInt();

            switch (choice){
                case 1: // CREATE
                    sc.nextLine();
                    System.out.println("Enter the name of the file to be created: ");
                    String baseFileName = sc.nextLine();

                    boolean fileExists = fileList.stream().anyMatch(f -> f.getName().startsWith(baseFileName));

                    if (fileExists) System.out.println("File already exists in the directory.");
                    else {
                        StringBuilder paragraph = new StringBuilder();
                        String line;
                        System.out.println("Enter your paragraph (press Enter twice to finish):");

                        while (true) {
                            line = sc.nextLine();
                            if (line.isEmpty()) {
                                break;
                            }
                            paragraph.append(line).append("\n");
                        }

                        byte[] dataBytes = paragraph.toString().getBytes(StandardCharsets.UTF_8);
                        int totalSize = dataBytes.length;
                        int fileCount = 0;
                        int offset = 0;

                        while (offset < totalSize) {
                            int chunkSize = Math.min(MAX_SIZE, totalSize - offset);
                            String fileName;

                            if (fileCount == 0 && totalSize <= MAX_SIZE) fileName = baseFileName + EXTENSION;
                            else fileName = baseFileName + "_" + fileCount + EXTENSION;

                            File partFile = new File(dir, fileName);
                            try (FileOutputStream fos = new FileOutputStream(partFile)) {
                                fos.write(dataBytes, offset, chunkSize);
                                System.out.println("File created: " + partFile.getName() + " of size: " + chunkSize + " bytes");
                                fileList.add(partFile);
                            } catch (IOException E) {
                                System.out.println("An error has occurred while creating this file.");
                            }
                            offset += chunkSize;
                            fileCount++;
                        }
                        if (fileCount > 1) System.out.println("Content split into " + fileCount + " files.");
                    }
                    break;
                case 2: // READ
                    break;
                case 3: // UPDATE
                    break;
                case 4: // DELETE
                    break;
                case 5: // EXIT
                    System.out.println("Exiting..");
                    flag = false;
                    break;
                default:
                    System.out.println("Enter valid INPUT");
                    break;
            }
            System.out.println("\n");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }

        }
    }


}