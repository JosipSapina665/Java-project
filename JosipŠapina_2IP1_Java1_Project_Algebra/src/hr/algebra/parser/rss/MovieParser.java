/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.parser.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import hr.algebra.model.Movie;
import java.util.Arrays;
import hr.algebra.model.Actor;
import hr.algebra.model.Director;

/**
 *
 * @author josip
 */
public class MovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=1";
    private static final String EXT = "jpg";
    private static final String DIR = "assets";

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.RFC_1123_DATE_TIME;

    private static final String SPLITER = ",";


    public MovieParser() {
    }

    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        try (InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);
            Optional<TagType> tagType = Optional.empty();
            Movie movie = null;
            StartElement startElement;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (tagType.isPresent()) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            switch (tagType.get()) {
                                case ITEM:
                                    movie = new Movie();
                                    movies.add(movie);
                                    break;
                                case TITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                    break;
                                case DESCRIPTION:
                                    if (movie != null && !data.isEmpty()) {
                                        String cut = cutData(data);
                                        movie.setDescription(cut);
                                    }
                                    break;

                                case DIRECTORS:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDirectors(directorParse(data));
                                    }
                                    break;
                                case ACTORS:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setActors(actorParse(data));
                                    }
                                    break;
                                case DURATION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDuration(Integer.parseInt(data));
                                    }
                                    break;
                                case GENRE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setGenre(data);
                                    }
                                    break;
                                case PUB_DATE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setPubDate(LocalDateTime.parse(data, DATE_FORMAT));
                                    }
                                    break;
                                case IMAGE_PATH:
                                    if (movie != null && !data.isEmpty()) {
                                        handlePicture(movie, data);
                                    }
                                    break;
                            }
                        }
                        break;
                }
            }
        }
        return movies;
    }

    private enum TagType {
        ITEM("item"),
        TITLE("orignaziv"),
        PUB_DATE("pubDate"),
        DESCRIPTION("description"),
        DIRECTORS("redatelj"),
        ACTORS("glumci"),
        DURATION("trajanje"),
        GENRE("zanr"),
        IMAGE_PATH("plakat");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }

    private static void handlePicture(Movie movie, String pictureUrl) {

        try {
            String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }
            String pictureName = UUID.randomUUID() + ext;
            String localPicturePath = DIR + File.separator + pictureName;

            FileUtils.copyFromUrl(pictureUrl, localPicturePath);
            movie.setImagePath(localPicturePath);
        } catch (IOException ex) {
            Logger.getLogger(MovieParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static List<Actor> actorParse(String data) {
        List<Actor> list = new ArrayList<>();
        Arrays.asList(data.trim().split(SPLITER)).forEach(e -> list.add(new Actor(e)));
        return list;
    }

    private static List<Director> directorParse(String data) {
        List<Director> list = new ArrayList<>();
        Arrays.asList(data.trim().split(SPLITER)).forEach(e -> list.add(new Director(e)));
        return list;
    }

//    private static String cutData(String data) {
//        return data.substring(data.indexOf(">") + 1, data.lastIndexOf("</"));
//    }
    private static String cutData(String data) {
        return data.replaceAll("\\<.*?\\>", "");
    }
}
