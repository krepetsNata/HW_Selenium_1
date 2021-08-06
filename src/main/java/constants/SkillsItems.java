package constants;

public enum SkillsItems {
    JAVA("Java"),
    RUBY("Ruby");

    private String textSkill;

    SkillsItems(String textSkill) {
        this.textSkill = textSkill;
    }

    public String getTextSkill() {
        return textSkill;
    }
}
