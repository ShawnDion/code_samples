public interface Gear {

  // setter of name, no return
  void setName(String name);

  // setter of GearType
  void setType(GearType type);

  // setter of attack, no return
  void setAttack(int attack);

  // setter of defense
  void setDefense(int defense);

  // Factory pattern for building combined Gear object
  Gear combineInternal(String newName, int attack, int defense);

  // combine gear object when spots are full
  Gear combineGear(AbstractGear other);

  // getter of name
  String getName();

  // getter of GearType
  GearType getType();

  // getter of attack
  int getAttack();

  // getter of defense
  int getDefense();

}
