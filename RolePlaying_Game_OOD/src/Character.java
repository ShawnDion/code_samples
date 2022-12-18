import java.util.ArrayList;

public interface Character {

  // getter for character's name
  String getName();

  // getter for character's attack strength
  int getAttack();

  // getter for character's defense strength
  int getDefense();

  // getter for character's headgear
  HeadGear getHeadGear();

  // getter for character's list of hand gears
  ArrayList<HandGear> getHandGears();

  // getter for character's list of foot gears
  ArrayList<FootGear> getFootGears();

  // setter for character's name
  void setName(String name);

  // setter for character's attack strength
  void setAttack(int attack);

  // setter for character's defense strength
  void setDefense(int defense);

  // setter for character's headgear
  void setHeadGear(HeadGear headgear);

  // setter for character's list of hand gears
  void setHandGears(ArrayList<HandGear> handGears);

  // setter for character's list of foot gears
  void setFootGears(ArrayList<FootGear> footGears);

  // equip new gear chosen by the character
  // return nothing but just modify attribute
  void equipGear(AbstractGear gr);

  // return true if headgear spot is full, false otherwise
  boolean isFullOfHeadGear();

  // return true if hand gears list is full, false otherwise
  boolean isFullOfHandGear();

  // return true if foot gears list is full, false otherwise
  boolean isFullOfFootGear();

  boolean isAllFull();

  boolean isAllAvailable();

  boolean isAllEmpty();
}
