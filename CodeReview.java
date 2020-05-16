import com.sun.org.slf4j.internal.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CodeReview {
    public static void main(String[] args) throws Exception {

    }

    public void constantsExample() {
        final String VALUE_9 = "9"; // bad practice
        final String DOT_PNG = ".png"; //bad practice
    }


    public void setDrivers() {
        User user = new User();
        List<String> drivers = new LinkedList<String>();
        List<String> nonDrivers = new LinkedList<String>();

        (user != null) && (user.getAge() >= 18) ? drivers.add(user.getFirstname()) : nonDrivers.add(user.getFirstname())
    }




    public int getLargestNumber() {
        int largestNumber = 100;
        //some expensive compute operation
        return largestNumber;
    }

    public int expensiveFunctionExample() {
        int currNum = 0;
        if (getLargestNumber() > currNum)
            currNum = getLargestNumber();
        for (int i=0; i < getLargestNumber(); ++i)
            System.out.println(getLargestNumber());

        return currNum;
    }

    public void stringExample() {
        String oneMillionHello = "";
        for (int i = 0; i < 1000000; i++) {
            oneMillionHello = oneMillionHello + "Hello!";
        }
        System.out.println(oneMillionHello.substring(0, 6));



    }

    public String buildUserQuery() {
        User user = new User();
        String sql = "Insert Into Users (name, email, pass, address)";
        sql += " values ('" + user.getFirstname();
        sql += "', '" + user.getLastname();
        sql += "', '" + user.getEmail();
        sql += "', '" + user.getAddress();
        sql += "')";

        return sql;
    }

    public void closeResourcesExample () {
        FileInputStream inputStream = null;
        try {
            File file = new File("./tmp.txt");
            inputStream = new FileInputStream(file);

        } catch (FileNotFoundException e) {
           // do nothing
        } finally {
            //do nothing
        }
    }

    private List<Integer> getIndexArray(String[] StringArray, String value){
        List<Integer> list = new LinkedList<Integer>();
        int index = 0;
        for (String i : StringArray){
            if (i.equals(value)){
                list.add(index);
            }
            index += 1;
        }
        return list;
    }

    public String[] getNextMove(int x, int y, String moveByPreviousDrone, boolean isRecharging){
        // The core logic is scan, steer(toward star/empty), thrust 1 and repeat.
        // 2 special cases where we halt the cycle: 1st when drone is recharging; 2nd When previous drone thrusted

        Random randGenerator = new Random();
        int steerRandomChoice;
        List<String> moveHistory  = new LinkedList<String>();
        String surrounding = "";
        String trackAction = "";
        String trackActionQuantum = "";
        String[] returnList = new String[2];
        final String thrust = "thrust";
        final String steer = "steer";
        final String scan = "scan";
        final String pass = "pass";
        final String state_stars = "stars";
        final String state_empty = "empty";

        if (isRecharging){ //If the drone is recharging we are passing until it gets fully charged
            trackAction = "pass";
            trackActionQuantum = "";

        }else if (surrounding.equals("") || moveHistory.get(moveHistory.size() - 1).equals(thrust) || moveByPreviousDrone.equals(thrust)) { //
            trackAction = scan;
            trackActionQuantum = "";

        } else if (moveHistory.get(moveHistory.size() - 1).equals(steer)){ //Thrust just after steer
            trackAction = thrust;
            trackActionQuantum = "1";

        } else if (moveHistory.get(moveHistory.size() - 1).equals(scan)){ //Steer just after scan
            trackAction = steer;
            int directionIndex;
            String[] surroundinglist = surrounding.split("");
            List<Integer> indexOfStars = new LinkedList<Integer>();

            int index = 0;
            for (String i : surroundinglist){
                if (i.equals(state_stars)){
                    indexOfStars.add(index);
                }
                index += 1;
            }

            List<Integer> indexOfEmpty = getIndexArray(surroundinglist, state_empty);

            int index2 = 0;
            for (String i : surroundinglist){
                if (i.equals(state_stars)){
                    indexOfStars.add(index2);
                }
                index2 += 1;
            }

            if (indexOfStars.size() > 0){ // If any stars available nearby steer randomly towards one of them
                steerRandomChoice = randGenerator.nextInt(indexOfStars.size());
                directionIndex = indexOfStars.get(steerRandomChoice);
                trackActionQuantum = Direction.values()[directionIndex].name();
            } else if (indexOfEmpty.size() > 0) { // Else if any empty square available nearby steer randomly towards one of them
                steerRandomChoice = randGenerator.nextInt(indexOfEmpty.size());
                directionIndex = indexOfEmpty.get(steerRandomChoice);
            } else{
                steerRandomChoice = randGenerator.nextInt(8);
                directionIndex = steerRandomChoice;
            }
            trackActionQuantum = Direction.values()[directionIndex].name();

        } else {
            trackAction = scan;
            trackActionQuantum = "";
        }

        returnList[0] = trackAction;
        returnList[1] = trackActionQuantum;

        return returnList;

    }
}
