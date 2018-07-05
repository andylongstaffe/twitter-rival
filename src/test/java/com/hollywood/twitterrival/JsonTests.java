package com.hollywood.twitterrival;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.hollywood.twitterrival.model.Tweet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class JsonTests {

    Gson gson;
    JsonParser parser;
    Gson gsonWithDate;

    class Car {
        private String brand;
        private String doors;

        @Override
        public String toString() {
            return "Car{" +
                    "brand='" + brand + '\'' +
                    ", doors='" + doors + '\'' +
                    '}';
        }
    }

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
        parser = new JsonParser();
        gsonWithDate = new GsonBuilder().setDateFormat("EEE MMM dd HH:mm:ss Z yyyy").create();
    }

    @Test
    public void basic() {
        String json = "{\"brand\":\"Jeep\", \"doors\": 3}";
        Car car = gson.fromJson(json, Car.class);
        Assert.assertEquals("Car{brand='Jeep', doors='3'}", car.toString());
        Assert.assertEquals("Jeep", car.brand);
    }

    @Test
    public void shouldWorkWithExtraFieldsInJson() {
        String json = "{\"brand\":\"Jeep\", \"doors\": 3, \"gears\": 5}";
        Car car = gson.fromJson(json, Car.class);
        Assert.assertEquals("Car{brand='Jeep', doors='3'}", car.toString());
    }

    @Test
    public void shouldWorkWithoutObjectDeserialization() {
        String json = "{\"brand\":\"Jeep\", \"doors\": 3}";
        JsonElement jsonTree = parser.parse(json);
        String brand = "";
        int doors = 0;
        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            JsonElement brandElem = jsonObject.get("brand");
            if (brandElem.isJsonPrimitive()) {
                brand =  brandElem.getAsString();
            }
            doors = jsonObject.get("doors").getAsInt();
        }
        Assert.assertEquals("Jeep", brand);
        Assert.assertEquals(3, doors);
    }

    @Test
    public void shouldReadJsonFromFile() throws Exception {
        Car car = gson.fromJson(new FileReader("src/test/resources/car.json"), Car.class);
        Assert.assertEquals("Car{brand='Jeep', doors='3'}", car.toString());
        Assert.assertEquals("Jeep", car.brand);
    }

    @Test
    public void shouldReadJsonFromFileWithNesting() throws Exception {
        JsonElement base = parser.parse(new FileReader("src/test/resources/car.json"));
        String bodyMaterial = base.getAsJsonObject().get("body").getAsJsonObject().get("material").getAsString();
        Assert.assertEquals("aluminium", bodyMaterial);
    }

    @Test
    public void shouldHandleDatesCorrectly() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
        String json = "{ \"created_at\": \"Thu Jul 05 07:03:30 +0000 2018\", \"id\": 12, \"text\": \"Hello there\" }";
        Tweet tweet = gsonWithDate.fromJson(json, Tweet.class);
        Assert.assertEquals(formatter.parse("Thu Jul 05 07:03:30 +0000 2018"), tweet.getCreatedTime());
    }

    @Test
    public void shouldReadMultipleObjectsFromFile() throws Exception {
        Tweet[] tweets = gsonWithDate.fromJson(new FileReader("src/main/resources/tweets.json"), Tweet[].class);
        System.out.println("found " + tweets.length + " tweets");
        for ( Tweet tweet : tweets ) {
            System.out.println(tweet);
        }
        Assert.assertEquals(5, tweets.length);

        // Using TypeToken into a Collection
        Type TweetListType = new TypeToken<Collection<Tweet>>(){}.getType();
        Collection<Tweet> tweetsCollection = gsonWithDate.fromJson(new FileReader("src/main/resources/tweets.json"), TweetListType);
        System.out.println("found " + tweetsCollection.size() + " tweets");
        for ( Tweet tweet : tweetsCollection ) {
            System.out.println(tweet);
        }
        Assert.assertEquals(5, tweetsCollection.size());

    }

    @Test
    public void shouldDealWithNestedObjects() {

    }
}
