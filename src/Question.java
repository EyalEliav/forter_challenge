import java.util.Arrays;

public class Question {
    public static int countUniqueNames(String billFirstName, String billLastName, String shipFirstName, String shipLastName, String billNameOnCard) {
        NicknamesDb nickDB = new NicknamesDb("src/db/names.csv");
        LastNamesDb lastDB = new LastNamesDb("src/db/lastnames.csv");
        billFirstName = billFirstName.toLowerCase();
        billLastName = billLastName.toLowerCase();
        shipFirstName = shipFirstName.toLowerCase();
        shipLastName = shipLastName.toLowerCase();
        billNameOnCard = billNameOnCard.toLowerCase();
        //counting the couples of equal names
        int sameNamesCouples = 0;
        boolean f = true;
        //comparing the bill name and ship name
        if (lastDB.nameEquals(billLastName, shipLastName)) {
            //assuming middle name (or names) are always after the first name
            if (!nickDB.nameEquals(billFirstName.split("\\s+")[0], shipFirstName.split("\\s+")[0]))
                f = false;
            if (billFirstName.split("\\s+").length > 1 && shipFirstName.split("\\s+").length > 1) {
                //assuming that if a middle names (or a part of them) was given, so they will be equals (according to the shorter middle name)
                for (int i = 1; i < Math.min(billFirstName.split("\\s+").length, shipFirstName.split("\\s+").length); i++) {
                    if (!nickDB.nameEquals(billFirstName.split("\\s+")[i], shipFirstName.split("\\s+")[i]))
                        f = false;
                }
            }
        } else {
            f = false;
        }
        //found a couple of names that are equal
        if (f)
            sameNamesCouples++;
        //comparing the ship name to the on-card name
        //assuming last name appears before or after the first and middle name (and not between them)
        boolean flag1 = true;
        String[] cardName = billNameOnCard.split("\\s+");
        int minLength = Math.min(cardName.length - shipLastName.split("\\s+").length, shipFirstName.split("\\s+").length);
        //checking if the lastname appears on the beginning of the full name
        if (lastDB.nameEquals(String.join(" ", Arrays.copyOfRange(cardName, 0, shipLastName.split("\\s+").length)), shipLastName)) {
            for (int i = shipLastName.split("\\s+").length; i < minLength; i++) {
                if (!nickDB.nameEquals(cardName[i], shipFirstName.split("\\s+")[i - shipLastName.split("\\s+").length])) {
                    flag1 = false;
                    break;
                }
            }
        }
            //checking if the lastname appears at the end of the full name
        else if (lastDB.nameEquals(String.join(" ", Arrays.copyOfRange(cardName, cardName.length - shipLastName.split("\\s+").length, cardName.length)), shipLastName)) {
            for (int i = 0; i < minLength; i++)
                if (!nickDB.nameEquals(cardName[i], shipFirstName.split("\\s+")[i])) {
                    flag1 = false;
                    break;
                }
        } else {
            flag1 = false;
        }
        //found a couple of names that are equal
        if (flag1)
            sameNamesCouples++;
        //checking if the bill name and the on card name are equal
        boolean flag2 = true;
        minLength = Math.min(cardName.length - billLastName.split("\\s+").length, billFirstName.split("\\s+").length);
        //checking if the lastname appears on the beginning of the full name
        if (lastDB.nameEquals(String.join(" ", Arrays.copyOfRange(cardName, 0, billLastName.split("\\s+").length)), billLastName))
            for (int i = billLastName.split("\\s+").length; i < minLength; i++) {
                if (!nickDB.nameEquals(cardName[i], billFirstName.split("\\s+")[i - billLastName.split("\\s+").length])) {
                    flag2 = false;
                    break;
                }
            }
            //checking if the lastname appears at the end of the full name
        else if (lastDB.nameEquals(String.join(" ", Arrays.copyOfRange(cardName, cardName.length - billLastName.split("\\s+").length, cardName.length)), billLastName)) {
            for (int i = 0; i < minLength; i++)
                if (!nickDB.nameEquals(cardName[i], billFirstName.split("\\s+")[i])) {
                    flag2 = false;
                    break;
                }
        } else {
            flag2 = false;
        }
        //found a couple of names that are equal
        if (flag2)
            sameNamesCouples++;
        switch (sameNamesCouples) {
            case 0:
                return 3;
            case 1:
                return 2;
            //only happens when 2 of the names has different middle name and one (who is private name equals to both of the other names) does not
            //example : countUniqueNames("Deborah S","Egli","Deborah D","Egli","Egli Deborah")
            case 2:
                return 2;
            case 3:
                return 1;
            default:
                return -1;
        }
    }
}
