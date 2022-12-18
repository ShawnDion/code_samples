public class HeadGear extends AbstractGear {

  public HeadGear(String name, int defense) {
    super(name, 0, defense);
    this.type = GearType.HEADGEAR;
  }

  @Override
  public AbstractGear combineInternal(String newName, int attack, int defense) {
    return new HeadGear(newName, defense);
  }

}
