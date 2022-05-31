package food;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Food {

    private String foodID;
    private String foodName;
    private String foodDescription;
    private double foodPrice;
    private int foodCalorie;
    private String foodType;

    public Food() {
    }

    private boolean addFoodCheck() {
        if (foodName == null || foodName.length() < 5 || foodName.length() > 30) {
            return false;
        }
        if (foodDescription == null) {
            return false;
        }
        String[] words = foodDescription.split(" ");
        if (words.length < 5 || words.length > 50) {
            return false;
        }
        if (foodCalorie > 1500) {
            return false;
        }
        if ("Kid Food".equals(foodType) && foodCalorie >= 800) {
            return false;
        }
        if (foodPrice < 5 || foodPrice > 150) {
            return false;
        }
        if (foodCalorie > 1000 && foodPrice >= 100) {
            return false;
        }
        return true;
    }

    private boolean updateFoodCheck(Food food) {
        if (!food.addFoodCheck()) {
            return false;
        }
        if (this.foodPrice * 1.1 < food.foodPrice) {
            return false;
        }
        if (this.foodCalorie != food.foodCalorie) {
            return false;
        }
        if ("Kid Food".equals(food.foodType) && !food.foodType.equals(this.foodType)) {
            return false;
        }
        return true;
    }

    private void writeFoodToFile(Food food) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("food.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        bufferedWriter.write("foodID:" + food.getFoodID());
        bufferedWriter.write("\nfoodName:" + food.getFoodName());
        bufferedWriter.write("\nfoodDescription:" + food.getFoodDescription());
        bufferedWriter.write("\nfoodPrice:" + food.getFoodPrice());
        bufferedWriter.write("\nfoodCalorie:" + food.getFoodCalorie());
        bufferedWriter.write("\nfoodType:" + food.getFoodType());
        bufferedWriter.close();
    }

    public boolean addFood() {
        if (!addFoodCheck()) {
            return false;
        }
        try {
            writeFoodToFile(this);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean updateFood(Food food) {
        if (!updateFoodCheck(food)) {
            return false;
        }
        try {
            writeFoodToFile(food);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodCalorie() {
        return foodCalorie;
    }

    public void setFoodCalorie(int foodCalorie) {
        this.foodCalorie = foodCalorie;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public static void main(String[] args) {
        Food food = new Food();
        food.setFoodID("1");
        food.setFoodName("SicuanHotpot");
        food.setFoodDescription("Very good spicy delicious nice");
        food.setFoodPrice(100);
        food.setFoodCalorie(1000);
        food.setFoodType("Adult Food");
        System.out.println(food.addFood());
        Food food2 = new Food();
        food2.setFoodID("1");
        food2.setFoodName("SicuanHotpot");
        food2.setFoodDescription("Very good spicy delicious nice");
        food2.setFoodPrice(110);
        food2.setFoodCalorie(1000);
        food2.setFoodType("Adult Food");
        System.out.println(food.updateFood(food2));
    }
}

