public class Monster {
    private String name;
    private int level;
    private int hp;
    private int strength;
    private int dexterity;

    public Monster(String name, int level, int hp, int strength, int dexterity) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.strength = strength;
        this.dexterity = dexterity;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void changeHp(int amount) {
        this.hp += amount;

        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public int getStrength() {
        return this.strength;
    }

    public int getDexterity() {
        return this.dexterity;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
