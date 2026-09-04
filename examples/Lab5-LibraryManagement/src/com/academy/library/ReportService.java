package com.academy.library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class ReportService {

    private final LibraryService libraryService;

    public ReportService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void displaySummaryReport() {
        // compute totalBooks, borrowedBooks, availableBooks, totalMembers
        // findMostPopularCategory(); print Reports block matching solution format
        int totalBooks = libraryService.getBooks().size();
        int borrowedBooks = libraryService.getBorrowRecords().size();
        int availableBooks = totalBooks - borrowedBooks;
        int totalMembers = libraryService.getMembers().size();

        System.out.println("Reports");
        System.out.println("Books : " + totalBooks);
        System.out.println("Borrowed : " + borrowedBooks);
        System.out.println("Available : " + availableBooks);
        System.out.println("Members: " + totalMembers);
        System.out.println("Most Popular Category : " + findMostPopularCategory());
    }

    public Path exportReportToFile(String fileName) throws IOException {
        // Bonus / full-path feature — implement after core borrow/return/summary TODOs.
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
        return Path.of(fileName);
    }

    private String findMostPopularCategory() {
        // max entry by value from getCategoryBookCount(); orElse "N/A"
        TreeMap<String, Integer> map = libraryService.getCategoryBookCount();

        if(map == null || map.isEmpty()) {
            return "N/A";
        }

        String category = null;
        int mostPopular = 0;
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if(value > mostPopular) {
                mostPopular = value;
                category = entry.getKey();
            }
        }

        return category;
    }
}
