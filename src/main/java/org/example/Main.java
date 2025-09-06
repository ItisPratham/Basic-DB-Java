package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // need to create a file system
        final int MAX_SIZE = 4096;
        List<File> fileList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        File dir = new File("storage_files");
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (dir.listFiles() != null) {
            for (File f : Objects.requireNonNull(dir.listFiles())) {
                if (f.isFile()) {
                    fileList.add(f);
                }
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
                case 1:
                    sc.nextLine();
                    System.out.println("Enter the name of the file to be created: ");
                    String fileName = sc.nextLine();

                    File newFile = new File(dir, fileName);

                    if(newFile.exists()){
                        System.out.println("File already exists in the directory.");
                    }
                    else{
                        try {
                            if (newFile.createNewFile()) {
                                fileList.add(newFile);
                                System.out.println("File created successfully");
                                System.out.println("Enter your paragraph (press Enter twice to finish):");

                                StringBuilder paragraph = new StringBuilder();
                                String line;

                                while (true) {
                                    line = sc.nextLine();
                                    if (line.isEmpty()) {
                                        break;
                                    }
                                    paragraph.append(line).append("\n");
                                }
                                try (FileWriter writer = new FileWriter(newFile)) {
                                    writer.write(paragraph.toString());
                                    System.out.println("Paragraph written to file successfully.");
                                }
                            }
                            else {
                                System.out.println("Failed to create the file. Please try again..");
                            }
                        } catch (IOException e) {
                            System.out.println("An error has occurred while creating this file.");
                        }
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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