package by.kharitonov.day6.model.entity;

import java.util.*;

public class Book {
    private String id;
    private String title;
    private List<String> authors;
    private int year;
    private int pages;
    private String publishingHouse;

    private Book() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return String.join(", ", authors);
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    private String getMainAuthor() {
        return authors.isEmpty() ? "" : authors.get(0);
    }

    public static Builder newBuilder() {
        return new Book().new Builder();
    }

    public class Builder {
        private Builder() {
            Book.this.id = "";
            Book.this.title = "";
            Book.this.authors = new ArrayList<>();
            Book.this.publishingHouse = "";
        }

        public Builder setId(String id) {
            Book.this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            Book.this.title = title;
            return this;
        }

        public Builder setAuthors(String... authors) {
            Book.this.authors = new ArrayList<>();
            Book.this.authors.addAll(Arrays.asList(authors));
            return this;
        }

        public Builder setYear(int year) {
            Book.this.year = year;
            return this;
        }

        public Builder setPages(int pages) {
            Book.this.pages = pages;
            return this;
        }

        public Builder setPublishingHouse(String publishingHouse) {
            Book.this.publishingHouse = publishingHouse;
            return this;
        }

        public Book build() {
            if (Book.this.id.isEmpty()) {
                UUID uuid = UUID.randomUUID();
                Book.this.id = uuid.toString();
            }
            return Book.this;
        }
    }

    public static class BookAuthorsComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            if (o1.authors.size() == o2.authors.size()) {
                return o1.getMainAuthor().compareTo(o2.getMainAuthor());
            }
            return o1.authors.size() - o2.authors.size();
        }
    }

    public static class BookIdComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }

    public static class BookPagesComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getPages() - o2.getPages();
        }
    }

    public static class BookPublishingHouseComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getPublishingHouse().compareTo(o2.getPublishingHouse());
        }
    }

    public static class BookTitleComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }

    public static class BookYearComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getYear() - o2.getYear();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        boolean result = true;
        if (year != book.year ||
                pages != book.pages ||
                !id.equals(book.id) ||
                !title.equals(book.title) ||
                !authors.equals(book.authors) ||
                !publishingHouse.equals(book.publishingHouse)) {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + year;
        result = 31 * result + pages;
        result = 31 * result + publishingHouse.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id:\"").append(id).append('\"');
        sb.append(", name:\"").append(title).append('\"');
        sb.append(", authors:").append(authors);
        sb.append(", year:").append(year);
        sb.append(", pages:").append(pages);
        sb.append(", publishingHouse:\"").append(publishingHouse).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
