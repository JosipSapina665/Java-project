/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 *
 * @author josip
 */
public class MovieTableModel extends AbstractTableModel {

    private final String[] COLUMN_NAME = {"IdMovie", "title", "genre"};
    private List<Movie> movies;

    public MovieTableModel(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAME.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getTitle();
            case 2:
                return movies.get(rowIndex).getGenre();
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAME[column];
    }

}
