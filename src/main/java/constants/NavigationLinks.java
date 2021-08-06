package constants;

public enum NavigationLinks {
    NEWS("News"),
    REAL_STORIES("Real Stories"),
    MATERIALS("Materials"),
    HARD_SKILLS("Hard Skills"),
    SOFT_SKILLS("Soft Skills"),
    EVENTS("Events");

    private String textLink;

    NavigationLinks(String textLink) {
        this.textLink = textLink;
    }

    public String getTextLink() {
        return textLink;
    }
}
