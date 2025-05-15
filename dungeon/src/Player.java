public class Player {
    private int level;
    private int exp;
    private int gold;
    private int hp;
    private int strength;
    private int dexterity;
    private int mana;
    private Armor armor;
    private Weapon weapon;
    private Pet pet;

    public Player(int level, int exp, int gold, int hp, int strength, int dexterity, int mana, Armor armor, Weapon weapon, Pet pet) {
        this.level = level;
        this.exp = exp;
        this.gold = gold;
        this.hp = hp;
        this.strength = strength;
        this.dexterity = dexterity;
        this.mana = mana;
        this.armor = armor;
        this.weapon = weapon;
        this.pet = pet;
    }

    public int getLevel() {
        return this.level;
    }

    public void changeExp(int amount) {
        this.exp += amount;

        if (this.exp > 9999) {
            this.exp = 9999;
        }
    }

    public void changeGold(int amount) {
        this.gold += amount;

        if (this.gold > 9999) {
            this.exp = 9999;
        } else if (this.gold < 0) {
            this.gold = 0;
        }
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void changeHp(int amount) {
        this.hp += amount;

        if (this.hp > 999) {
            this.hp = 999;
        } else if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public int getStrength() {
        return this.strength;
    }

    public int getMana() {
        return this.mana;
    }

    public void changeArmor(Armor armor) {
        if (this.armor != null) {
            this.hp -= this.armor.getHpBonus();
        }

        this.armor = armor;
        this.hp += armor.getHpBonus();
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void displayStatusBar() {
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
            System.out.println(this.armor + " +" + this.armor.getHpBonus());
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
            System.out.println(this.weapon + " " + this.weapon.getDamage());
        }

        System.out.print("  GLD: " + this.gold);
        int gldLength = String.valueOf(this.gold).length();

        for(int i = 0; i < 4 - gldLength; ++i) {
            System.out.print(" ");
        }

        System.out.print(" | DEX:");
        int dexLength = String.valueOf(this.dexterity).length();

        for(int i = 0; i < 3 - dexLength; ++i) {
            System.out.print(" ");
        }

        System.out.print(this.dexterity + " | PET: ");
        if (this.pet == null) {
            System.out.println("None");
        } else {
            System.out.println(this.pet);
        }

        System.out.println("+-----------+---------+---------------------+");
    }
}
