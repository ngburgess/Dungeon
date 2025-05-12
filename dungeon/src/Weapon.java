public class Weapon {
    private String name;
    private int strengthBonus;
    private String rarity;

    public Weapon(String name, int strengthBonus, String rarity) {
        this.name = name;
        this.strengthBonus = strengthBonus;
        this.rarity = rarity;
    }

    public String toString() {
        return this.name;
    }
}
