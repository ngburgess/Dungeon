public class Weapon {
    private String name;
    private String rarity;
    private int cost;
    private String damage;
    private int crit;

    public Weapon(String name, String rarity, int cost, String damage, int crit) {
        this.name = name;
        this.rarity = rarity;
        this.cost = cost;
        this.damage = damage;
        this.crit = crit;
    }

    public String getDamage() {
        return this.damage;
    }

    public String toString() {
        return switch (this.rarity) {
            case "Common" -> AnsiCodes.GRAY + this.name + AnsiCodes.RESET;
            case "Uncommon" -> AnsiCodes.GREEN + this.name + AnsiCodes.RESET;
            case "Rare" -> AnsiCodes.BLUE + this.name + AnsiCodes.RESET;
            case "Epic" -> AnsiCodes.PURPLE + this.name + AnsiCodes.RESET;
            case "Legendary" -> AnsiCodes.GOLD + this.name + AnsiCodes.RESET;
            default -> this.name;
        };
    }
}
