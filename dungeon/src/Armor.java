public class Armor {
    private String name;
    private int hpBonus;
    private String rarity;

    public Armor(String name, int hpBonus, String rarity) {
        this.name = name;
        this.hpBonus = hpBonus;
        this.rarity = rarity;
    }

    public String toString() {
        return this.name;
    }
}
