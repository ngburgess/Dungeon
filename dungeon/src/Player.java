public class Player {
    private int level;
    private int exp;
    private int gold;
    private int hp;
    private int strength;
    private int mana;
    private Armor armor;
    private Weapon weapon;
    private Pet pet;

    public Player(int level, int exp, int gold, int hp, int strength, int mana, Armor armor, Weapon weapon, Pet pet) {
        this.level = level;
        this.exp = exp;
        this.gold = gold;
        this.hp = hp;
        this.strength = strength;
        this.mana = mana;
        this.armor = armor;
        this.weapon = weapon;
        this.pet = pet;
    }

    public void printStatusBar() {
        System.out.println("+-----------+---------+---------------------+");
        System.out.print("  LVL: " + this.level + "    | HP:");
        int hpLength = String.valueOf(this.hp).length();

        for(int i = 0; i < 4 - hpLength; ++i) {
            System.out.print(" ");
        }

        System.out.print(this.hp + " | ARM: ");
        if (this.armor == null) {
            System.out.println("None");
        } else {
            System.out.println(this.armor);
        }

        System.out.print("  EXP: " + this.exp);
        int expLength = String.valueOf(this.exp).length();

        for(int i = 0; i < 4 - expLength; ++i) {
            System.out.print(" ");
        }

        System.out.print(" | STR:");
        int strLength = String.valueOf(this.strength).length();

        for(int i = 0; i < 3 - strLength; ++i) {
            System.out.print(" ");
        }

        System.out.print(this.strength + " | WPN: ");
        if (this.weapon == null) {
            System.out.println("None");
        } else {
            System.out.println(this.weapon);
        }

        System.out.print("  GLD: " + this.gold);
        int gldLength = String.valueOf(this.gold).length();

        for(int i = 0; i < 4 - gldLength; ++i) {
            System.out.print(" ");
        }

        System.out.print(" | MNA:");
        int mnaLength = String.valueOf(this.mana).length();

        for(int i = 0; i < 3 - mnaLength; ++i) {
            System.out.print(" ");
        }

        System.out.print(this.mana + " | PET: ");
        if (this.pet == null) {
            System.out.println("None");
        } else {
            System.out.println(this.pet);
        }

        System.out.println("+-----------+---------+---------------------+");
    }
}
