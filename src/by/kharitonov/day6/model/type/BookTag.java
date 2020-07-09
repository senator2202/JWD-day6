package by.kharitonov.day6.model.type;

public enum BookTag {
    ID("id"), TITLE("title"), AUTHORS("authors"),
    YEAR("year"), PAGES("number of pages"),
    PUBLISHING_HOUSE("publishing house");

    private final String tagName;

    BookTag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}
