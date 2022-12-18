public class HandGear extends AbstractGear {

  public HandGear(String name, int attack) {
    super(name, attack, 0);
    this.type = GearType.HANDGEAR;
  }

  @Override
  public Gear combineInternal(String newName, int attack, int defense) {
    return new HandGear(newName, attack);
  }

}
