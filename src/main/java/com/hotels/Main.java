package com.hotels;

import java.util.ArrayList;
import java.util.List;

import com.hotels.crawlers.Hotel;
import com.hotels.utils.Assistant;
import com.hotels.utils.CSVFile;
import com.hotels.utils.SpellChecker;
import com.hotels.utils.WordCompletion;

public class Main {
    public static void search_hotel() {
        // load hotels from csv
        ArrayList<Hotel> hotels = CSVFile.load_hotels("hotel_details.csv");
        String[] hotelNames = new String[hotels.size()];
        for (int i = 0; i < hotels.size(); i++) {
            hotelNames[i] = hotels.get(i).name;
        }
        while(true) {
            System.out.println("Enter hotel name:");
            String hotelName = System.console().readLine();
            // search by hotel name
            // check if hotel name is in hotelNames
            boolean found = false;
            for (String h : hotelNames) {
                if (h.equalsIgnoreCase(hotelName)) {
                    found = true;
                    break;
                }
            }
            // if hotel name is found, show hotel details
            if(found) {
                System.out.println("Hotel details");
                // show hotel details
                for (Hotel hotel : hotels) {
                    if (hotel.name.equalsIgnoreCase(hotelName)) {
                        Hotel.print(hotel);
                    }
                }
                return;
            }
            else {
                // autocomplete
                ArrayList<String> suggestions = WordCompletion.autocomplete(hotelNames, hotelName);
                if(suggestions.size() == 0) {
                    // System.out.println("No suggestions found");
                    // SpellChecker.autocorrect(hotelNames, hotelName);
                    SpellChecker spellCheckerInstance = new SpellChecker();
                    spellCheckerInstance.loadVocabulary(hotelNames);
                    List<String> word_suggestions = spellCheckerInstance.suggestWords(hotelName, 1);
                    if(word_suggestions.size() == 0) {
                        // System.out.println("Hotel not found");
                    }
                    else {
                        System.out.println("Did you mean:");
                        for(String suggestion: word_suggestions) {
                            System.out.println(suggestion);
                        }
                    }
                }
                else {
                    System.out.println("Hotel not found, did you mean:");
                    for(String suggestion: suggestions) {
                        System.out.println(suggestion);
                    }
                }
            }
        }
    }
    public static void search_city() {
        String[] cities = {"New York", "Los Angeles", "Windsor", "Toronto", "Vancouver", "Montreal", "Calgary", "Ottawa", "Edmonton", "Quebec City"};
        while(true) {
            System.out.println("Enter destination city:");
            String city = System.console().readLine();
            // search by city
            // check if city is in cities
            boolean found = false;
            for (String c : cities) {
                if (c.equalsIgnoreCase(city)) {
                    found = true;
                    break;
                }
            }
            // if city is found, show hotels in that city
            if(found) {
                System.out.println("Hotels in " + city);
                // show hotels in that city
                ArrayList<Hotel> hotels = CSVFile.load_hotels("hotel_details.csv");
                for (Hotel hotel : hotels) {
                    if (hotel.location.equalsIgnoreCase(city)) {
                        Hotel.print(hotel);
                    }
                }
                return;
            }
            else {
                // autocomplete
                ArrayList<String> suggestions = WordCompletion.autocomplete(cities, city);
                if(suggestions.size() == 0) {
                    // System.out.println("No suggestions found");
                    // SpellChecker.autocorrect(cities, city);
                    SpellChecker spellCheckerInstance = new SpellChecker();
                    spellCheckerInstance.loadVocabulary(cities);
                    List<String> word_suggestions = spellCheckerInstance.suggestWords(city, 1);
                    if(word_suggestions.size() == 0) {
                        // System.out.println("City not found");
                    }
                    else {
                        System.out.println("Did you mean:");
                        for(String suggestion: word_suggestions) {
                            System.out.println(suggestion);
                        }
                    }
                }
                else {
                    System.out.println("City not found, did you mean:");
                    for(String suggestion: suggestions) {
                        System.out.println(suggestion);
                    }
                }
            }
        }
    }

    public static void main_menu() {
        while(true) {
            System.out.println("Welcome to Elite Suites");
            System.out.println("====Database Search====");
            System.out.println("1. Search by Hotel Name");
            System.out.println("2. Search by City");
            System.out.println("3. Search by Price");
            System.out.println("4. Search by Rating");
            // System.out.println("5. Search by Date Range");
            System.out.println("5. Book hotel(Crawl new data)");
            System.out.println("6. AI Search");
            
            // System.out.println("7. Crawl new data");
            System.out.println("8. Exit");
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(System.console().readLine());
            switch(choice) {
                case 1:
                    // System.out.println("Enter Hotel Name: ");
                    // String hotelName = System.console().readLine();
                    // search by hotel name
                    search_hotel();
                    break;
                case 2:
                    // System.out.println("Enter City: ");
                    // String city = System.console().readLine();
                    // search by city
                    search_city();
                    break;
                case 3:
                    System.out.println("Enter Price Range: ");
                    String priceRange = System.console().readLine();
                    // search by price
                    break;
                case 4:
                    System.out.println("Enter Rating: ");
                    String rating = System.console().readLine();
                    // search by rating
                    break;
                case 5:
                    System.out.println("Enter Date Range: ");
                    String dateRange = System.console().readLine();
                    // search by date range
                    break;
                case 6:
                    // AI Search
                    Assistant demo = new Assistant();
                    String fileData = CSVFile.readFile("hotel_details.csv");
                    demo.runConversation(fileData);
                    break;
                case 7:
                    // Crawl new data
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    public static void main(String[] args) {
        main_menu();
    }
}