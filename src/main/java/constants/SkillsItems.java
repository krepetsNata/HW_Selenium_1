package constants;

public enum SkillsItems {
    JAVA("Java"),
    RUBY("Ruby");

    private String textSkills;

    SkillsItems(String textSkills) {
        this.textSkills = textSkills;
    }

    public String getTextSkills() {
        return textSkills;
    }
}
