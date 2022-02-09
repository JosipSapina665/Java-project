/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author josip
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {

    //public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.RFC_1123_DATE_TIME;
    public static DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("E, dd MMM yyyy HH:mm:ss a")
            .toFormatter(Locale.GERMANY);

    @XmlAttribute
    private int id;
    private String title;
    private String description;

    @XmlElement(name = "duration")
    private int duration;

    private String genre;

    @XmlElement(name = "img")
    private String imagePath;

    @XmlElement(name = "pubdate")
    @XmlJavaTypeAdapter(PublishedDateAdapter.class)
    private LocalDateTime pubDate;

    @XmlElementWrapper
    @XmlElement(name = "director")
    private List<Director> directors;

    @XmlElementWrapper
    @XmlElement(name = "actor")
    private List<Actor> actors;

    public Movie() {
        actors = new ArrayList<>();
        directors = new ArrayList<>();
    }

    public Movie(String title, String description, int duration, String genre, String imagePath, LocalDateTime pubDate) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.genre = genre;
        this.imagePath = imagePath;
        this.pubDate = pubDate;
    }

    public Movie(int id, String title, String description, int duration, String genre, String imagePath, LocalDateTime pubDate) {
        this(title, description, duration, genre, imagePath, pubDate);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDateTime pubDate) {
        this.pubDate = pubDate;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return title + "     -      Genre: " + genre + "    -   Publish date: "
                + pubDate + "     -       Duration: " + duration + " min";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Movie other = (Movie) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.title);
        return hash;
    }
}
