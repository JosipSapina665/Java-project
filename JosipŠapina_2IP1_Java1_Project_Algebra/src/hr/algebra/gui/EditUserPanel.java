/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.gui;

import hr.algebra.dal.ActorRepository;
import hr.algebra.dal.ActorRepositoryImpl;
import hr.algebra.dal.DirectorRepository;
import hr.algebra.dal.DirectorRepositoryImpl;
import hr.algebra.dal.MovieActorRepository;
import hr.algebra.dal.MovieActorRepositoryImpl;
import hr.algebra.dal.MovieDirectorRepository;
import hr.algebra.dal.MovieDirectorRepositoryImpl;
import hr.algebra.dal.MovieRepository;
import hr.algebra.dal.MovieRepositoryImpl;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.model.Movie;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import hr.algebra.model.MovieTableModel;
import hr.algebra.utils.IconUtils;
import hr.algebra.utils.MessageUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author josip
 */
public class EditUserPanel extends javax.swing.JPanel {

    private MovieRepository movieRepository;
    private MovieActorRepository movieActorRepository;
    private MovieDirectorRepository movieDirectorRepository;
    private DirectorRepository directorRepository;
    private ActorRepository actorRepository;

    private MovieTableModel movieTableModel;

    private Movie selectedMovie;
    private DefaultListModel<Actor> actorModel = new DefaultListModel<>();
    private DefaultListModel<Director> directorModel = new DefaultListModel<>();

    private List<JTextComponent> validationFields;

    /**
     * Creates new form EditUserPanel
     */
    public EditUserPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lsActors = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lsDirectors = new javax.swing.JList<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbImage = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbMovies = new javax.swing.JTable();
        tfPubDate = new javax.swing.JTextField();
        tfTitle = new javax.swing.JTextField();
        tfGenre = new javax.swing.JTextField();
        tfDuration = new javax.swing.JTextField();
        btnSingOut = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1200, 720));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel2.setText("Title:");

        jLabel3.setText("Genre:");

        jLabel5.setText("Image:");

        taDescription.setEditable(false);
        taDescription.setColumns(20);
        taDescription.setLineWrap(true);
        taDescription.setRows(5);
        jScrollPane1.setViewportView(taDescription);

        jLabel6.setText("Description:");

        jScrollPane2.setViewportView(lsActors);

        jLabel7.setText("Directors:");

        jScrollPane5.setViewportView(lsDirectors);

        jLabel9.setText("Publish date:");

        jLabel11.setText("Duration:");

        jLabel12.setText("Actors:");

        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hr/algebra/gui/image.jpeg"))); // NOI18N

        tbMovies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbMovies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMoviesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbMovies);

        tfPubDate.setEditable(false);

        tfTitle.setEditable(false);

        tfGenre.setEditable(false);

        tfDuration.setEditable(false);

        btnSingOut.setText("Sing out");
        btnSingOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSingOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfPubDate, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tfGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(tfDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(lbImage)))))
                .addContainerGap(121, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSingOut, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSingOut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfPubDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(87, 87, 87)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbMoviesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMoviesMouseClicked
        showMovies();
    }//GEN-LAST:event_tbMoviesMouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        init();
    }//GEN-LAST:event_formComponentShown

    private void btnSingOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSingOutActionPerformed
        try {
            UserFrame userFrame = new UserFrame();
            Login login = new Login();
            login.setVisible(true);
            userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(EditUserPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSingOutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSingOut;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbImage;
    private javax.swing.JList<Actor> lsActors;
    private javax.swing.JList<Director> lsDirectors;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTable tbMovies;
    private javax.swing.JTextField tfDuration;
    private javax.swing.JTextField tfGenre;
    private javax.swing.JTextField tfPubDate;
    private javax.swing.JTextField tfTitle;
    // End of variables declaration//GEN-END:variables

    private void init() {

        try {
            initValidation();
            initRepo();
            initTable();
        } catch (Exception ex) {
            Logger.getLogger(EditUserPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to load form!");
            System.exit(1);
        }
    }

    private void initRepo() throws Exception {
        movieRepository = RepositoryFactory.getRepoFactory(MovieRepositoryImpl.class);
        actorRepository = RepositoryFactory.getRepoFactory(ActorRepositoryImpl.class);
        directorRepository = RepositoryFactory.getRepoFactory(DirectorRepositoryImpl.class);
        movieActorRepository = RepositoryFactory.getRepoFactory(MovieActorRepositoryImpl.class);
        movieDirectorRepository = RepositoryFactory.getRepoFactory(MovieDirectorRepositoryImpl.class);
    }

    private void initValidation() throws Exception {
        validationFields = Arrays.asList(tfTitle, tfGenre, tfPubDate, tfDuration, taDescription);
    }

    private void initTable() throws Exception {
        tbMovies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbMovies.setAutoCreateRowSorter(true);
        movieTableModel = new MovieTableModel(movieRepository.getMany());
        tbMovies.setModel(movieTableModel);
    }

    private void showMovies() {
        int selectedRow = tbMovies.getSelectedRow();
        int rowIndex = tbMovies.convertRowIndexToModel(selectedRow);
        int id = (int) movieTableModel.getValueAt(rowIndex, 0);

        try {
            Optional<Movie> optMovie = movieRepository.getOne(id);
            if (optMovie.isPresent()) {
                selectedMovie = optMovie.get();
                fillAll(selectedMovie);
            }
        } catch (Exception ex) {
            Logger.getLogger(EditUserPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to fetch movie!");
        }
    }

    private void fillAll(Movie movie) throws Exception {
        clearForm();
        tfTitle.setText(movie.getTitle());
        tfGenre.setText(movie.getGenre());
        tfDuration.setText(String.valueOf(movie.getDuration()));
        tfPubDate.setText(movie.getPubDate().format(Movie.DATE_FORMAT));
        taDescription.setText(movie.getDescription());
        setActors(movie.getId());
        setDirectors(movie.getId());

        if (movie.getImagePath() != null && Files.exists(Paths.get(movie.getImagePath()))) {
            setIcon(lbImage, new File(movie.getImagePath()));
        }
    }

    private void setActors(int id) {
        if (selectedMovie != null) {
            try {
                actorModel.clear();
                movieActorRepository.getMovieActors(id).forEach(actorModel::addElement);
                lsActors.setModel(actorModel);
            } catch (Exception ex) {
                Logger.getLogger(EditUserPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setDirectors(int id) {
        if (selectedMovie != null) {
            try {
                directorModel.clear();
                movieDirectorRepository.getMovieDirectors(id).forEach(directorModel::addElement);
                lsDirectors.setModel(directorModel);
            } catch (Exception ex) {
                Logger.getLogger(EditUserPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setIcon(JLabel lbImage, File file) {
        try {
            lbImage.setIcon(IconUtils.createIcon(file, lbImage.getWidth(), lbImage.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(EditUserPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to load image!!");
            lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hr/algebra/gui/image.jpeg")));
        }
    }

    private void clearForm() {
        validationFields.forEach((vf) -> {
            vf.setText("");
        });
        lsActors.clearSelection();
        lsDirectors.clearSelection();
        clearImage();
    }

    private void clearImage() {
        try {
            lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hr/algebra/gui/image.jpeg")));
        } catch (Exception e) {
        }

    }
}
