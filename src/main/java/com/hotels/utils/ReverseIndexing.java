package com.hotels.utils;

import java.util.ArrayList;
import java.util.HashMap;

import com.hotels.crawlers.Hotel;

public class ReverseIndexing {
    public static ArrayList<Hotel> reverseIndexing(String input) {
        ArrayList<Hotel> hotels = CSVFile.load_hotels_description("hotel_details.csv");
        // create a hashmap of words and their corresponding hotels from descriptions
        HashMap<String, ArrayList<Hotel>> word_hotel_map = new HashMap<String, ArrayList<Hotel>>();
        for (Hotel hotel : hotels) {
            String[] words = hotel.description.split(" ");
            for (String word : words) {
                if (word_hotel_map.containsKey(word)) {
                    word_hotel_map.get(word).add(hotel);
                } else {
                    ArrayList<Hotel> hotel_list = new ArrayList<Hotel>();
                    hotel_list.add(hotel);
                    word_hotel_map.put(word, hotel_list);
                }
            }
        }
        ArrayList<Hotel> filtered_hotels = new ArrayList<Hotel>();
        String[] query_words = input.split(" ");
        for (String query_word : query_words) {
            if (word_hotel_map.containsKey(query_word)) {
                for (Hotel hotel : word_hotel_map.get(query_word)) {
                    if (!filtered_hotels.contains(hotel)) {
                        filtered_hotels.add(hotel);
                    }
                }
            }
        }
        return filtered_hotels;
    }
}
