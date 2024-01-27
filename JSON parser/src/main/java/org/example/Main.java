package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {

        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        System.out.println(list);
    }

    public static String readString(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static List<Employee> jsonToList(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(json);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        List<Employee> list = new ArrayList<>();
        for (Object obj : jsonArray) {
            Employee employee = gson.fromJson(obj.toString(), Employee.class);
            list.add(employee);
        }
        return list;
    }
}