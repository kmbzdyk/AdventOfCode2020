import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdventOfCode {
    public static void main(String[]args){
        FileProcessing fp = new FileProcessing();
        System.out.println(dayFiveSecondPuzzle(fp.readFileAsStringList("aoc05.txt")));
    }

    public static int dayFiveSecondPuzzle(List<String> inputList) {
        List<Integer> boardingPasses = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            int rowDown = 0;
            int rowUp = 127;
            int columnDown = 0;
            int columnUp = 7;
            int currentRow = 0;

            for (int j = 0; j < inputList.get(i).length(); j++){
                char letter = inputList.get(i).charAt(j);

                if (String.valueOf(letter).equals("F")) {
                    rowUp -= (rowUp - rowDown) / 2 + 1;

                    if (rowUp == rowDown) {
                        currentRow = rowUp;
                    }
                } else if (String.valueOf(letter).equals("B")) {
                    rowDown += (rowUp - rowDown)/2 + 1;

                    if (rowUp == rowDown) {
                        currentRow = rowUp;
                    }
                } else if (String.valueOf(letter).equals("L")) {
                    columnUp -= (columnUp - columnDown) / 2 + 1;

                    if (columnUp == columnDown) {
                        boardingPasses.add(currentRow * 8 + columnUp);
                    }
                } else if (String.valueOf(letter).equals("R")) {
                    columnDown += (columnUp - columnDown) / 2 + 1;

                    if (columnUp == columnDown) {
                        boardingPasses.add(currentRow * 8 + columnUp);
                    }
                }
            }
        }
        Collections.sort(boardingPasses);

        for (int i = 0; i < boardingPasses.size()-1; i++) {
            if (boardingPasses.get(i) != boardingPasses.get(i+1)-1) {
                return boardingPasses.get(i)+1;
            }
        }

        return 0;
    }

    public static int dayFiveFirstPuzzle(List<String> inputList) {
        List<Integer> boardingPasses = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            int rowDown = 0;
            int rowUp = 127;
            int columnDown = 0;
            int columnUp = 7;
            int currentRow = 0;

            for (int j = 0; j < inputList.get(i).length(); j++){
                char letter = inputList.get(i).charAt(j);

                if (String.valueOf(letter).equals("F")) {
                    rowUp -= (rowUp - rowDown) / 2 + 1;

                    if (rowUp == rowDown) {
                        currentRow = rowUp;
                    }
                } else if (String.valueOf(letter).equals("B")) {
                    rowDown += (rowUp - rowDown)/2 + 1;

                    if (rowUp == rowDown) {
                        currentRow = rowUp;
                    }
                } else if (String.valueOf(letter).equals("L")) {
                    columnUp -= (columnUp - columnDown) / 2 + 1;

                    if (columnUp == columnDown) {
                        boardingPasses.add(currentRow * 8 + columnUp);
                    }
                } else if (String.valueOf(letter).equals("R")) {
                    columnDown += (columnUp - columnDown) / 2 + 1;

                    if (columnUp == columnDown) {
                        boardingPasses.add(currentRow * 8 + columnUp);
                    }
                }
            }
        }

        int max = 0;
        for (int j = 0; j < boardingPasses.size(); j++) {
            if (boardingPasses.get(j) > max) {
                max = boardingPasses.get(j);
            }
        }

        return max;
    }

    public static int dayFourSecondPuzzle(List<String> inputList) {
        int numberOfValid = 0;
        String currentPassport = "";

        for (int i = 0; i <= inputList.size(); i++) {
            if (!(i == inputList.size() || inputList.get(i).isEmpty())) {
                currentPassport += inputList.get(i) + " ";
            } else {
                if (currentPassport.contains("byr:") && currentPassport.contains("iyr:") && currentPassport.contains("eyr:") &&
                        currentPassport.contains("hgt:") && currentPassport.contains("hcl:") && currentPassport.contains("ecl:") &&
                        currentPassport.contains("pid:")) {
                    boolean isValid = true;
                    String[] splittedFields = currentPassport.split("\\s+");

                    for (int j = 0; j < splittedFields.length; j++) {
                        String currentField = splittedFields[j].substring(splittedFields[j].indexOf(":") + 1).trim();

                        if (splittedFields[j].contains("byr:")) {
                            int year = Integer.valueOf(currentField);

                            if (!(year >= 1920 && year <= 2002)) {
                                isValid = false;
                                break;
                            }
                        } else if (splittedFields[j].contains("iyr:")) {
                            int year = Integer.valueOf(currentField);

                            if (!(year >= 2010 && year <= 2020)) {
                                isValid = false;
                                break;
                            }
                        } else if (splittedFields[j].contains("eyr:")) {
                            int year = Integer.valueOf(currentField);

                            if (!(year >= 2020 && year <= 2030)) {
                                isValid = false;
                                break;
                            }
                        } else if (splittedFields[j].contains("hgt:")) {
                            if (currentField.contains("cm")) {
                                int fieldNumber = Integer.valueOf(currentField.replace("cm",""));

                                if (!(fieldNumber >= 150 && fieldNumber <= 193)) {
                                    isValid = false;
                                    break;
                                }
                            } else if (currentField.contains("in")) {
                                int fieldNumber = Integer.valueOf(currentField.replace("in",""));

                                if (!(fieldNumber >= 59 && fieldNumber <= 76)) {
                                    isValid = false;
                                    break;
                                }
                            } else {
                                isValid = false;
                                break;
                            }
                        } else if (splittedFields[j].contains("hcl:")) {
                            if (!(currentField.contains("#") && currentField.length() == 7)) {
                                isValid = false;
                                break;
                            }
                        } else if (splittedFields[j].contains("ecl:")) {
                            if (!(currentField.equals("amb") || currentField.equals("blu") || currentField.equals("brn") || currentField.equals("gry") ||
                            currentField.equals("grn") || currentField.equals("hzl") || currentField.equals("oth"))) {
                                isValid = false;
                                break;
                            }
                        } else if (splittedFields[j].contains("pid:")) {
                            if (!(currentField.length() == 9 && currentField.matches("[0-9]+"))) {
                                isValid = false;
                                break;
                            }
                        }
                    }

                    if (isValid) {
                        numberOfValid++;
                    }
                }

                currentPassport = "";
            }
        }

        return numberOfValid;
    }

    public static int dayFourFirstPuzzle(List<String> inputList) {
        int numberOfValid = 0;
        String currentPassport = "";

        for (int i = 0; i <= inputList.size(); i++) {
            if (!(i == inputList.size() || inputList.get(i).isEmpty())) {
                    currentPassport += inputList.get(i) + " ";
            } else {
                if (currentPassport.contains("byr:") && currentPassport.contains("iyr:") && currentPassport.contains("eyr:") &&
                currentPassport.contains("hgt:") && currentPassport.contains("hcl:") && currentPassport.contains("ecl:") &&
                currentPassport.contains("pid:")) {
                    numberOfValid++;
                }

                currentPassport = "";
            }
        }

        return numberOfValid;
    }

    public static int dayThreeSecondPuzzle(List<String> inputList) {
        int[] slopesRight = {1,3,5,7,1};
        int[] slopesDown = {1,1,1,1,2};
        int result = 1;

        for (int i = 0; i < slopesRight.length; i++) {
            int numberOfTrees = 0;
            int position = 0;

            for (int j = 0; j < inputList.size(); j+=slopesDown[i]) {
                if (String.valueOf(inputList.get(j).charAt(position)).equals("#")) {
                    numberOfTrees++;
                }

                position += slopesRight[i];
                position %= inputList.get(j).length();
            }

            result *= numberOfTrees;
        }

        return result;
    }

    public static int dayThreeFirstPuzzle(List<String> inputList) {
        int position = 0;
        int numberOfTrees = 0;

        for (int i = 0; i < inputList.size(); i++) {
            if (String.valueOf(inputList.get(i).charAt(position)).equals("#")) {
                numberOfTrees++;
            }

            position += 3;
            position %= inputList.get(i).length();
        }

        return numberOfTrees;
    }

    public static int dayTwoSecondPuzzle(List<String> inputList) {
        int countValid = 0;

        for (int i = 0; i < inputList.size(); i++) {
            String[] splittedEntries = inputList.get(i).split("\\s+");

            if (splittedEntries.length == 3) {
                String[] policy = splittedEntries[0].split("-");
                String letterToCheck = splittedEntries[1].split(":")[0];

                if (Character.toString(splittedEntries[2].charAt(Integer.valueOf(policy[0])-1)).equals(letterToCheck) && !Character.toString(splittedEntries[2].charAt(Integer.valueOf(policy[1])-1)).equals(letterToCheck) ||
                        !Character.toString(splittedEntries[2].charAt(Integer.valueOf(policy[0])-1)).equals(letterToCheck) && Character.toString(splittedEntries[2].charAt(Integer.valueOf(policy[1])-1)).equals(letterToCheck)) {
                    countValid++;
                }
            }
        }

        return countValid;
    }
    
    public static int dayTwoFirstPuzzle(List<String> inputList) {
        int countValid = 0;

        for (int i = 0; i < inputList.size(); i++) {
            String[] splittedEntries = inputList.get(i).split("\\s+");
            String[] policy = splittedEntries[0].split("-");
            String letterToCheck = splittedEntries[1].split(":")[0];
            int countLetters = splittedEntries[2].length() - splittedEntries[2].replaceAll(letterToCheck,"").length();

            if (countLetters >= Integer.valueOf(policy[0]) && countLetters <= Integer.valueOf(policy[1])) {
                countValid++;
            }
        }

        return countValid;
    }

    public static int dayOneSecondPuzzle(List<Integer> expenses) {
        for (int i = 0; i < expenses.size(); i++) {
            for (int j = 0; j < expenses.size(); j++) {
                for (int k = 0; k < expenses.size(); k++) {
                    if ((expenses.get(j) + expenses.get(i) + expenses.get(k)) == 2020) {

                        return expenses.get(j) * expenses.get(i) * expenses.get(k);
                    }
                }
            }
        }

        return 0;
    }

    public static int dayOneFirstPuzzle(List<Integer> expenses) {
        for (int i = 0; i < expenses.size(); i++) {
            for (int j = 0; j < expenses.size(); j++) {
                if (expenses.get(j) == (2020 - expenses.get(i))) {

                    return expenses.get(j) * expenses.get(i);
                }
            }
        }

        return 0;
    }
}
