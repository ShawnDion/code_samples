import java.util.ArrayList;

interface Battle {

    void printFightInfo();

    void calculateAndPrintResult();

    void chooseGear(Character c);

    void setChar1(Character char1);

    void setChar2(Character char2);

    void setGears(ArrayList<AbstractGear> gears);

    Character getChar1();

    Character getChar2();

    ArrayList<AbstractGear> getGears();

}
