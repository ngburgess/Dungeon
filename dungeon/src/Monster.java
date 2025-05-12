public class Monster {
    private String name;
    private int level;
    private int hp;
    private int strength;

    public Monster(String name, int level, int hp, int strength) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.strength = strength;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHp() {
        return this.hp;
    }

    public int getStrength() {
        return this.strength;
    }

    public String toString() {
        return this.name;
    }
}
