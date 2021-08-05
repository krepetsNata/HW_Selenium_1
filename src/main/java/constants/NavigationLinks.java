package constants;

public enum NavigationLinks {
    NEWS("News"),
    REAL_STORIES("Real Stories"),
    MATERIALS("Materials"),
    HARD_SKILLS("Hard Skills"),
    SOFT_SKILLS("Soft Skills"),
    EVENTS("Events");

    private String textLinks;

    NavigationLinks(String textLinks) {
        this.textLinks = textLinks;
    }

    public String getTextLinks() {
        return textLinks;
    }
}
