public class FootGear extends AbstractGear {

  public FootGear(String name, int attack, int defense) {
    super(name, attack, defense);
    this.type = GearType.FOOTGEAR;
  }

  @Override
  public Gear combineInternal(String newName, int attack, int defense) {
    return new FootGear(newName, attack, defense);
  }
}
