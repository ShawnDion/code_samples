import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.util.Collections.*;

public class BattleImpl implements Battle {
  private Character char1;
  private Character char2;
  private ArrayList<AbstractGear> listOfGears;

  // constructor
  public BattleImpl(Character char1, Character char2, ArrayList<AbstractGear> listOfGears) {
    if (listOfGears.size() != 10) {
      throw new IllegalArgumentException("Exactly 10 gears are necessary to be chosen by characters.");
    }

    this.char1 = char1;
    this.char2 = char2;
    this.listOfGears = listOfGears;
  }

  @Override
  public void printFightInfo() {
    for (int i = 0; i < 10; i++) {
      if (i % 2 == 0) {
        this.chooseGear(this.char1);
      } else if (i % 2 == 1) {
        this.chooseGear(this.char2);
      }
      System.out.println("***** Round " + (i+1) + " *****\n" + this.char1 + "\n" + this.char2 + "\n");
    }
    this.calculateAndPrintResult();
  }

  @Override
  public void calculateAndPrintResult() {

    int char1TotalAttack = ((CharacterImpl) this.char1).calculateAttackTotal();
    int char1TotalDefense = ((CharacterImpl) this.char1).calculateDefenseTotal();

    int char2TotalAttack = ((CharacterImpl) this.char2).calculateAttackTotal();
    int char2TotalDefense = ((CharacterImpl) this.char2).calculateDefenseTotal();

    int char1Damage = char2TotalAttack - char1TotalDefense;
    int char2Damage = char1TotalAttack - char2TotalDefense;

    System.out.println("\n---------- RESULT ----------");
    // print char1 info
    System.out.println("\nPlayer 1 has " + char1TotalAttack + " attack points and "
            + char1TotalDefense + " defense strengths.");
    // print char2 info
    System.out.println("\nPlayer 2 has " + char2TotalAttack + " attack points and "
            + char2TotalDefense + " defense strengths.");
    // print the calculated result
    System.out.println("\nThe battle ends with Player 1 having " + char1Damage
            + " units of damage and Player 2 having " + char2Damage + " unit of damage.\n");

    if (char1Damage < char2Damage) {
      System.out.println(this.char1.getName() + " won the battle.\n");
    } else if (char2Damage < char1Damage) {
      System.out.println(this.char2.getName() + " won the battle.\n");
    } else {
      System.out.println("The battle is tied.\n");
    }
  }

  @Override
  public void chooseGear(Character c) {

    // initiate variable to assign the chosen gear after screening
    AbstractGear chosenGear;
    ArrayList<AbstractGear> chosenGears = new ArrayList<>();

    if (c.isAllFull() || c.isAllAvailable()) {
      chosenGear = this.findGear(this.listOfGears);

    } else {

      if (c.isFullOfHeadGear()) {
        // see if there are another type available
        // use function of extractList to select
        if (!c.isFullOfHandGear() && !c.isFullOfFootGear()) {
          chosenGears = this.extractList(this.listOfGears, GearType.HANDGEAR, GearType.FOOTGEAR);
        } else if (c.isFullOfHandGear()) {
          chosenGears = this.extractList(this.listOfGears, GearType.FOOTGEAR);
        } else if (c.isFullOfFootGear()) {
          chosenGears = this.extractList(this.listOfGears, GearType.HANDGEAR);
        }

      // cater for the remaining conditions with Headgear spot
      } else {
          if (c.isFullOfHandGear() && c.isFullOfFootGear()) {
            chosenGears = this.extractList(this.listOfGears, GearType.HEADGEAR);
          } else if (c.isFullOfHandGear()) {
            chosenGears = this.extractList(this.listOfGears, GearType.HEADGEAR, GearType.FOOTGEAR);
          } else if (c.isFullOfFootGear()) {
            chosenGears = this.extractList(this.listOfGears, GearType.HEADGEAR, GearType.HANDGEAR);
          }
      }
      chosenGear = this.findGear(chosenGears);
    }
    // equip argument character with chosen gear and remove from remaining battle
    c.equipGear(chosenGear);
    this.listOfGears.remove(chosenGear);
  }

  public ArrayList<AbstractGear> extractList(ArrayList<AbstractGear> gearList, GearType gearType) {
    ArrayList<AbstractGear> selectedGearList = new ArrayList<>();
    for (AbstractGear gr: gearList) {
      if (gr.getType() == gearType) {
        selectedGearList.add(gr);
      }
    }
    return selectedGearList;
  }

  public ArrayList<AbstractGear> extractList(ArrayList<AbstractGear> gearList, GearType gearType1, GearType gearType2) {
    ArrayList<AbstractGear> selectedGearList = new ArrayList<>();
    for (AbstractGear gr: gearList) {
      if (gr.getType() == gearType1 || gr.getType() == gearType2) {
        selectedGearList.add(gr);
      }
    }
    return selectedGearList;
  }

  // compare weapon by rules and return the correct one
  public AbstractGear findGear(ArrayList<AbstractGear> gearList) {

    // create a variable to contain the selected gear
    AbstractGear selectedGear;
    ArrayList<AbstractGear> gearsOfAttack = new ArrayList<>();
    ArrayList<AbstractGear> gearsOfDefense = new ArrayList<>();

    // sort to select the gears with the highest attack out
    Collections.sort(gearList, new AttackComparator());
    int maxAttack = gearList.get(0).getAttack();
    for (int i = 0; i < gearList.size(); i++) {
      if (gearList.get(i).getAttack() >= maxAttack) {
        gearsOfAttack.add(gearList.get(i));
      }
    }

    // size is larger than 1
    // sort to select the gears with the highest defense out
    if (gearsOfAttack.size() > 1) {
      Collections.sort(gearsOfAttack, new DefenseComparator());
      int maxDef = gearsOfAttack.get(0).getDefense();
      for (int i = 0; i < gearsOfAttack.size(); i++) {
        if (gearsOfAttack.get(i).getDefense() >= maxDef) {
          gearsOfDefense.add(gearsOfAttack.get(i));
        }
      }
    } else {
      gearsOfDefense = gearsOfAttack;
    }

    // select a random one if there are still more than one gear in list
    if (gearsOfDefense.size() > 1) {
      // create a random object from Random class
      selectedGear = gearsOfDefense.get(new Random().nextInt(gearsOfDefense.size()));
    } else {
      // select the only gear left if there is only one gear left
      selectedGear = gearsOfDefense.get(0);
    }
    return selectedGear;
  }

  @Override
  public void setChar1(Character char1) {
    this.char1 = char1;
  }

  @Override
  public void setChar2(Character char2) {
    this.char2 = char2;
  }

  @Override
  public void setGears(ArrayList<AbstractGear> gears) {
    if (gears.size() != 10) {
      throw new IllegalArgumentException("Exactly 10 gears are necessary to be chosen by characters.");
    }
    this.listOfGears = gears;
  }

  @Override
  public Character getChar1() {
    return this.char1;
  }

  @Override
  public Character getChar2() {
    return this.char2;
  }

  @Override
  public ArrayList<AbstractGear> getGears() {
    return this.listOfGears;
  }
}
