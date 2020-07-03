package by.kharitonov.day6.entity;

import by.kharitonov.day6.exception.BookProjectException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public static Builder newBuilder() {
        return new Book().new Builder();
    }

    public class Builder {
        private Builder() {
            Book.this.id = "";
            Book.this.title = "";
            Book.this.authors = new ArrayList<>();
            Book.this.year = 0;
            Book.this.pages = 0;
            Book.this.publishingHouse = "";
        }

        public Builder setTitle(String title) {
            Book.this.title = title;
            return this;
        }

        public Builder setAuthors(String... authors) {
            Book.this.authors = new ArrayList<>();
            for (String author : authors) {
                Book.this.authors.add(author);
            }
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

        public Book build() throws BookProjectException {
            UUID uuid = UUID.randomUUID();
            Book.this.id = uuid.toString();
            if (Book.this.title.isEmpty() ||
                    Book.this.authors.isEmpty() ||
                    Book.this.publishingHouse.isEmpty() ||
                    Book.this.pages == 0 ||
                    Book.this.year == 0) {
                throw new BookProjectException("Book data is incomplete!");
            }
            return Book.this;
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
        result = 31 * result + authors.hashCode();
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
