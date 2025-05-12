public class Pet {
    private String name;
    private int level;
    private int hp;
    private int strength;

    public Pet(String name, int level, int hp, int strength) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.strength = strength;
    }

    public String toString() {
        return this.name;
    }
}
