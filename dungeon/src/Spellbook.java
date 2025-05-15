import java.util.ArrayList;

public class Spellbook {
    private ArrayList<Spell> spellbook;

    public Spellbook() {
        spellbook = new ArrayList<>();
    }

    public void addSpell(Spell spell) {
        spellbook.add(spell);
    }

    private String convertToRoman(int num) {
        String[] roman = {"", "I", "II", "III", "IV", "V"};
        return roman[num];
    }

    public void displaySpellbook() {
        for (Spell spell : this.spellbook) {
            System.out.println("  " + (this.spellbook.indexOf(spell)+1) + "-" + spell + " " + convertToRoman(spell.getLevel()) + " (" + spell.getManaCost() + " Mana)");
        }
        for (int i = 0; i < 3-this.spellbook.size(); i++) {
            System.out.println();
        }
        System.out.println("  Enter-Cancel");
    }
}
