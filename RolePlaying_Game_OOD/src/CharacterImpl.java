import java.util.ArrayList;
import java.util.List;

public class CharacterImpl implements Character {
    private String name;
    private int attack;
    private int defense;
    private HeadGear headGear;
    private ArrayList<HandGear> handGears;
    private ArrayList<FootGear> footGears;
    private final static int GEARLIMIT = 2;

    // constructor
    // all gear spots are initialized to be empty
    public CharacterImpl(String name, int attack, int defense){
        if (attack < 0 || defense < 0){
            throw new IllegalArgumentException("Defense and Attack inputs cannot be negative integers.");
        }
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.headGear = null;
        this.handGears = new ArrayList<>();
        this.footGears = new ArrayList<>();
    }

    // constructor filling all the gear spots
    public CharacterImpl(String name, int attack, int defense, HeadGear headGear, ArrayList<HandGear> handGears, ArrayList<FootGear> footGears){
        if (attack < 0 || defense < 0){
            throw new IllegalArgumentException("Defense and Attack inputs cannot be negative integers.");
        }
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.headGear = headGear;
        this.handGears = handGears;
        this.footGears = footGears;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAttack() {
        return this.attack;
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public HeadGear getHeadGear() {
        return this.headGear;
    }

    @Override
    public ArrayList<HandGear> getHandGears() {
        return this.handGears;
    }

    @Override
    public ArrayList<FootGear> getFootGears() {
        return this.footGears;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public void setHeadGear(HeadGear headgear) {
        this.headGear = headgear;
    }

    @Override
    public void setHandGears(ArrayList<HandGear> handGears) {
        this.handGears = handGears;
    }

    @Override
    public void setFootGears(ArrayList<FootGear> footGears) {
        this.footGears = footGears;
    }

    @Override
    public void equipGear(AbstractGear gr) {

        // modify HeadGear spot if the type is HEADGEAR
        if (gr.getType() == GearType.HEADGEAR) {

            HeadGear gear = (HeadGear) gr;
            // combine gear if it is full
            if (this.isFullOfHeadGear()){
                this.setHeadGear((HeadGear) this.headGear.combineGear(gear));
            } else {
                this.headGear = gear;
            }

        // modify HandGear list if the type is HAND GEAR
        } else if (gr.getType() == GearType.HANDGEAR) {

            // type casting
            HandGear gear = (HandGear) gr;
            // create a new item list
            ArrayList<HandGear> itemList = new ArrayList<>();

            // combine gear if it is full
            if (this.isFullOfHandGear()) {
                HandGear newGear = (HandGear) this.getHandGears().get(0).combineGear(gear);
                itemList = new ArrayList<>(List.of(newGear, this.getHandGears().get(1)));
                this.setHandGears(itemList);
                return;
            }

            // add existing gear if there is already one
            if (this.getHandGears().size() == 1) {
                itemList.add(this.getHandGears().get(0));
            }
            // add argument gear to the end of list
            itemList.add(gear);
            // set hand gears using the new list
            this.setHandGears(itemList);

        // modify FootGear list if the type is FOOT GEAR
        } else if (gr.getType() == GearType.FOOTGEAR) {

            // type casting
            FootGear gear = (FootGear) gr;
            // create a new item list
            ArrayList<FootGear> itemList = new ArrayList<>();

            // combine gear if it is full
            if (this.isFullOfFootGear()) {
                FootGear newGear = (FootGear) this.getFootGears().get(0).combineGear(gear);
                itemList = new ArrayList<>(List.of(newGear, this.getFootGears().get(1)));
                this.setFootGears(itemList);
                return;
            }

            // add existing gear if there is already one
            if (this.getFootGears().size() == 1) {
                itemList.add(this.getFootGears().get(0));
            }
            // add argument gear to the end of list
            itemList.add(gear);
            // set hand gears using the new list
            this.setFootGears(itemList);
        }
    }

    public int calculateAttackTotal() {
        int attackSum = this.attack;
        for (HandGear hg : this.handGears) {
            attackSum += hg.getAttack();
        }
        for (FootGear fg : this.footGears) {
            attackSum += fg.getAttack();
        }
        return attackSum;
    }

    public int calculateDefenseTotal() {
        int defenseSum = this.defense;
        if (this.headGear != null) {
            defenseSum += this.headGear.getDefense();
        }
        for (FootGear fg : this.footGears) {
            defenseSum += fg.getDefense();
        }
        return defenseSum;
    }

    @Override
    public boolean isFullOfHeadGear() {
        if (this.getHeadGear() != null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isFullOfHandGear() {
        if (this.getHandGears().size() >= GEARLIMIT) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isFullOfFootGear() {
        if (this.getFootGears().size() >= GEARLIMIT) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isAllFull() {
        if (this.isFullOfHeadGear()
                && this.isFullOfHandGear() && this.isFullOfFootGear()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAllAvailable() {
        if (this.getHeadGear() == null
                && this.getHandGears().size() < GEARLIMIT && this.getFootGears().size() < GEARLIMIT) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAllEmpty() {
        if (this.getHeadGear() == null
                && this.getHandGears().size() == 0 && this.getFootGears().size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String out;

        if (this.isAllEmpty()) {
            out = this.name + " has no gears yet.\n";
        } else {
            out = this.name + " equips the following gears:\n";
        }

        if (this.headGear != null) {
            out += "~ Head Gear ~\n" + this.headGear + "\n";
        }

        if (this.handGears.size() > 0) {
            out += "~ Hand Gear(s) ~\n";
            for (HandGear hg : this.handGears) {
                out += hg.toString() + "\n";
            }
        }

        if (this.footGears.size() > 0) {
            out += "~ Foot Gear(s) ~\n";
            for (FootGear fg : this.footGears) {
                out += fg.toString() + "\n";
            }
        }

        out += this.name + "'s Attack: " + this.attack + ", Defense: " + this.defense;
        return out;
    }
}
