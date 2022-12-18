public abstract class AbstractGear implements Gear {
  private String name;
  protected GearType type;
  private int attack;
  private int defense;

  public AbstractGear(String name, int attack, int defense) {
    String[] nameList = name.split(" ");

    if (attack < 0 || defense < 0) {
      throw new IllegalArgumentException("Defense and Attack inputs cannot be negative integers.");
    }

    if (nameList.length != 2) {
      throw new IllegalArgumentException("Name input needs to be two-word format: 'Adjective + Noun'.");
    }

    this.name = name;
    this.attack = attack;
    this.defense = defense;
  }

  @Override
  public String toString() {
    return this.getName() + " -- attack strength: " + this.getAttack() +
            ", defense strength: " + this.getDefense();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof AbstractGear)) {
      return false;
    }

    // cast Object-typed 'obj' into AbstractGear-typed other
    AbstractGear other = (AbstractGear) obj;

    // compare name, attack and defense values of two gears
    if (this.name == other.name && this.attack == other.attack && this.defense == other.defense) {
      return true;
    } else {
      return false;
    }
  }

  public abstract Gear combineInternal(String newName, int attack, int defense);

  public Gear combineGear(AbstractGear other) throws IllegalArgumentException {

    // throw exceptions if they have different gear types
    if (this.getType() != other.getType()) {
      throw new IllegalArgumentException("Gears are NOT of the same type.");
    }
    
    // get name of this gear and turn it into array
    String[] arrOfThisName = this.getName().split(" ");

    // generate new variables to initiate new combined gear
    String newString = arrOfThisName[0] + "," + other.getName();

    int attack = this.getAttack() + other.getAttack();
    int defense = this.getDefense() + other.getDefense();

    return combineInternal(newString, attack, defense);

//    if (this.getType() == GearType.HEADGEAR) {
//      return new HeadGear(newString, defense);
//    } else if (this.getType() == GearType.HANDGEAR) {
//      return new HandGear(newString, attack);
//    } else {
//      return new FootGear(newString, attack, defense);
//    }
  }

  @Override
  public void setName(String name) {
    String[] nameList = name.split(" ");

    if (nameList.length != 2){
      throw new IllegalArgumentException("Name input needs to be two-word format: 'Adjective + Noun'.");
    }

    this.name = name;
  }

  @Override
  public void setType(GearType type) {
    this.type = type;
  }

  @Override
  public void setAttack(int attack) {
    if (attack < 0){
      throw new IllegalArgumentException("Attack inputs cannot be negative integers.");
    }
    this.attack = attack;
  }

  @Override
  public void setDefense(int defense) {
    if (defense < 0){
      throw new IllegalArgumentException("Defense inputs cannot be negative integers.");
    }
    this.defense = defense;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public GearType getType() {
    return this.type;
  }

  @Override
  public int getAttack() {
    return this.attack;
  }

  @Override
  public int getDefense() {
    return this.defense;
  }
}
