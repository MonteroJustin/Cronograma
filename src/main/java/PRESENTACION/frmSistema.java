/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PRESENTACION;

import Entidades.Cronograma;
import Entidades.Feriado;
import Entidades.Grupo;
import Entidades.Modulo;
import Entidades.Personal;
import Entidades.Profesor;
import Entidades.Programa;
import LogicaNegocio.CronogramaBL;
import LogicaNegocio.FeriadoBL;
import LogicaNegocio.GrupoBL;
import LogicaNegocio.ModuloBL;
import LogicaNegocio.PersonalBL;
import LogicaNegocio.ProfesorBL;
import LogicaNegocio.ProgramaBL;
import LogicaNegocio.UsuarioBL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class frmSistema extends javax.swing.JFrame {

    //Atributos GLOBALES del formulario
    DefaultTableModel model; //Modelo para gestionar la tabla del form
    frmInicio frmIni;
    frmProfesores frmPro;

    UsuarioBL usuarioBL;
    ProfesorBL profesorBL;
    ProgramaBL programaBL;
    GrupoBL grupoBL;
    ModuloBL moduloBL;
    FeriadoBL feriadoBL;
    PersonalBL personalBL;
    CronogramaBL cronogramaBL;

    int idProfesor = 0;
    int idPrograma = 0;
    int idGrupo = 0;

    /**
     * Llena la tabla de cronogramas con los datos recuperados de la base de
     * datos. Los datos se formatean y se cargan en el modelo de la tabla.
     *
     * @throws SQLException si ocurre un error de base de datos.
     * @throws Exception si ocurre una excepción general.
     */
    public frmSistema() {
        initComponents();
        profesorBL = new ProfesorBL();
        programaBL = new ProgramaBL();
        grupoBL = new GrupoBL();
        moduloBL = new ModuloBL();
        feriadoBL = new FeriadoBL();
        personalBL = new PersonalBL();

        ArrayList<Profesor> profesores;
        ArrayList<Programa> programas;
        ArrayList<Grupo> grupos;
        ArrayList<Modulo> modulos = new ArrayList<Modulo>();
        ArrayList<Feriado> feriados = new ArrayList<Feriado>();
        ArrayList<Personal> Personales = new ArrayList<Personal>();

        Map<String, Integer> mapProfesores = new HashMap<>();
        Map<String, Integer> mapProgramas = new HashMap<>();
        Map<String, Integer> mapGrupos = new HashMap<>();

        // <editor-fold desc="ComboBox´s">
        // <editor-fold desc="ComboBox Profesores">
        try {
            profesores = profesorBL.listarRegistros();
            for (Profesor p : profesores) {
                String nombreCompleto = p.getNombre() + " " + p.getApe1() + " " + p.getApe2();
                cbProfesores.addItem(nombreCompleto);
                mapProfesores.put(nombreCompleto, p.getId_profesor()); // Agrega la correspondencia al mapa
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbProfesores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreSeleccionado = (String) cbProfesores.getSelectedItem();
                int idSeleccionado = mapProfesores.get(nombreSeleccionado); //recupero el id del seleccionado
                idProfesor = idSeleccionado;
                System.out.println("ID seleccionado: " + idProfesor);
            }
        });
        //Cuadrar el formulario
        setLocationRelativeTo(null);
        // </editor-fold>
        // <editor-fold desc="ComboBox Programas">
        try {
            programas = programaBL.listarRegistros();
            for (Programa p : programas) {
                String nombrePrograma = p.getPrograma();
                cbProgramas.addItem(nombrePrograma);
                mapProgramas.put(nombrePrograma, p.getIdPrograma()); // Agrega la correspondencia al mapa
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbProgramas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreSeleccionado = (String) cbProgramas.getSelectedItem();
                int idSeleccionado = mapProgramas.get(nombreSeleccionado); // Recupera el id del programa seleccionado
                idPrograma = idSeleccionado;
                System.out.println("ID seleccionado: " + idPrograma);
            }
        });
        // </editor-fold>
        // <editor-fold desc="ComboBox Grupo">
        try {
            grupos = grupoBL.listarRegistros();
            for (Grupo g : grupos) {
                String nombreGrupo = g.getGrupo();
                cbGrupo.addItem(nombreGrupo);
                mapGrupos.put(nombreGrupo, g.getId_grupo()); // Agrega la correspondencia al mapa
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbGrupo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreSeleccionado = (String) cbGrupo.getSelectedItem();
                int idSeleccionado = mapGrupos.get(nombreSeleccionado); // Recupera el id del programa seleccionado
                idGrupo = idSeleccionado;
                System.out.println("ID seleccionado: " + idGrupo);
            }
        });
        // </editor-fold>
        // </editor-fold>

        try {
            llenarCronogramas();
        } catch (Exception ex) {
            Logger.getLogger(frmSistema.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Llena la tabla de cronogramas con los datos recuperados de la base de
     * datos. Los datos se formatean y se cargan en el modelo de la tabla.
     *
     * @throws SQLException si ocurre un error de base de datos.
     * @throws Exception si ocurre una excepción general.
     */
    public void llenarCronogramas() throws SQLException, Exception {
        ArrayList<Cronograma> cronogramas;
        cronogramaBL = new CronogramaBL();
        model = (DefaultTableModel) tblCronogramas.getModel();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MMM/yyyy");
        SimpleDateFormat formatos = new SimpleDateFormat("yyyy");

        try {
            // Objeto que representa a una FILA
            Object[] row = new Object[17];

            // Recuperar datos de los Cronogramas
            cronogramas = cronogramaBL.listarRegistros();
            for (Cronograma c : cronogramas) {
                Date anio1 = java.sql.Date.valueOf(c.getAnio()); // Convertir LocalDate a Date
                Date fechaInicioDate = java.sql.Date.valueOf(c.getFechaInicio()); // Convertir LocalDate a Date
                Date fechaFinDate = java.sql.Date.valueOf(c.getFechaFin()); // Convertir LocalDate a Date
                row[0] = c.getId_cronograma();
                row[1] = c.getId_profesor().getNombre() + " " + c.getId_profesor().getApe1() + " " + c.getId_profesor().getApe2();
                row[2] = c.getId_programa().getPrograma();
                row[3] = c.getId_modulo().getModulo();
                row[4] = c.getId_grupo().getGrupo();
                row[5] = formatos.format(anio1);
                row[6] = c.getHorasDia();
                row[7] = formato.format(fechaInicioDate);
                row[8] = formato.format(fechaFinDate);
                row[9] = c.isL() ? 1 : 0;
                row[10] = c.isK() ? 1 : 0;
                row[11] = c.isM() ? 1 : 0;
                row[12] = c.isJ() ? 1 : 0;
                row[13] = c.isV() ? 1 : 0;
                row[14] = c.isS() ? 1 : 0;
                row[15] = c.isD() ? 1 : 0;
                row[16] = c.isEstado() ? 1 : 0;

                // Cargar los datos de Cronograma a la tabla, uno a uno
                model.addRow(row);
            }

        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }
    }

    public void getResult(int result) {
        if (result == 1) {
            btnAsignar.setEnabled(false);
        } else if (result == 2) {
            btnAsignar.setEnabled(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        btnAsignar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCronogramas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        dcFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbProfesores = new javax.swing.JComboBox<>();
        cbProgramas = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbGrupo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();
        btnSalir3 = new javax.swing.JButton();
        btnSalir2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtHorasDias = new javax.swing.JTextField();
        rK = new javax.swing.JCheckBox();
        rL = new javax.swing.JCheckBox();
        rJ = new javax.swing.JCheckBox();
        rM = new javax.swing.JCheckBox();
        rD = new javax.swing.JCheckBox();
        rS = new javax.swing.JCheckBox();
        rV = new javax.swing.JCheckBox();
        rBorrado = new javax.swing.JCheckBox();
        rEstado = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAsignar.setText("Asignar Encargado");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });

        tblCronogramas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Profesor", "Programa", "Modulo", "Grupo", "Año", "Horas", "Fecha Inicio", "Fecha Fin", "L", "K", "M", "J", "V", "S", "D", "Estado"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCronogramas);
        if (tblCronogramas.getColumnModel().getColumnCount() > 0) {
            tblCronogramas.getColumnModel().getColumn(0).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblCronogramas.getColumnModel().getColumn(1).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblCronogramas.getColumnModel().getColumn(2).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(2).setPreferredWidth(250);
            tblCronogramas.getColumnModel().getColumn(3).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblCronogramas.getColumnModel().getColumn(4).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(4).setPreferredWidth(50);
            tblCronogramas.getColumnModel().getColumn(5).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblCronogramas.getColumnModel().getColumn(6).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(6).setPreferredWidth(10);
            tblCronogramas.getColumnModel().getColumn(7).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(7).setPreferredWidth(50);
            tblCronogramas.getColumnModel().getColumn(8).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(8).setPreferredWidth(50);
            tblCronogramas.getColumnModel().getColumn(9).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(9).setPreferredWidth(5);
            tblCronogramas.getColumnModel().getColumn(10).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(10).setPreferredWidth(5);
            tblCronogramas.getColumnModel().getColumn(11).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(11).setPreferredWidth(5);
            tblCronogramas.getColumnModel().getColumn(12).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(12).setPreferredWidth(5);
            tblCronogramas.getColumnModel().getColumn(13).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(13).setPreferredWidth(5);
            tblCronogramas.getColumnModel().getColumn(14).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(14).setPreferredWidth(5);
            tblCronogramas.getColumnModel().getColumn(15).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(15).setPreferredWidth(5);
            tblCronogramas.getColumnModel().getColumn(16).setResizable(false);
            tblCronogramas.getColumnModel().getColumn(16).setPreferredWidth(30);
        }

        jLabel2.setText("Cronogramas:");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel1.setText("Selecciona Profesor:");

        jLabel6.setText("Selecciona Programa:");

        cbProfesores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProfesoresActionPerformed(evt);
            }
        });

        cbProgramas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProgramasActionPerformed(evt);
            }
        });

        jLabel7.setText("Selecciona Grupo:");

        cbGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGrupoActionPerformed(evt);
            }
        });

        jLabel8.setText("Fecha de Inicio");

        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnSalir1.setText("Feriados");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });

        btnSalir3.setText("Programas");
        btnSalir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir3ActionPerformed(evt);
            }
        });

        btnSalir2.setText("Modulos");
        btnSalir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir2ActionPerformed(evt);
            }
        });

        jLabel9.setText("Horas al Dia:");

        txtHorasDias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHorasDiasKeyTyped(evt);
            }
        });

        rK.setText("K");

        rL.setText("L");

        rJ.setText("J");

        rM.setText("M");

        rD.setText("D");

        rS.setText("S");
        rS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSActionPerformed(evt);
            }
        });

        rV.setText("V");

        rBorrado.setText("Borrado");

        rEstado.setText("Estado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(btnSalir3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnSalir2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(cbProfesores, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(8, 8, 8)
                                                                .addComponent(jLabel1)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(cbProgramas, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addComponent(jLabel6)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(cbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(jLabel7)
                                                                .addGap(24, 24, 24)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(dcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(10, 10, 10)
                                                                .addComponent(jLabel8)))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtHorasDias, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 340, Short.MAX_VALUE)
                                                .addComponent(btnAsignar))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(rL)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rK)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rM)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rJ)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rV)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rS)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rD)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rBorrado, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnGenerar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(26, 26, 26))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel1)
                                                                        .addComponent(jLabel6)
                                                                        .addComponent(jLabel7)
                                                                        .addComponent(jLabel8)
                                                                        .addComponent(jLabel9))
                                                                .addGap(28, 28, 28))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(cbProgramas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(cbProfesores, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(dcFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(txtHorasDias, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(cbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(rL)
                                                        .addComponent(rK)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(rM)
                                                                .addComponent(rJ)
                                                                .addComponent(rV)
                                                                .addComponent(rS)
                                                                .addComponent(rD)
                                                                .addComponent(rBorrado)
                                                                .addComponent(rEstado)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)))
                                .addComponent(jLabel2)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSalir3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSalir2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        frmPro = new frmProfesores();
        frmPro.toFront();
        frmPro.setVisible(true);
        this.dispose();

    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        frmIni = new frmInicio();
        frmIni.toFront();
        frmIni.setVisible(true);
        this.dispose();
    }

    private void cbProfesoresActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cbProgramasActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cbGrupoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    /**
     * Genera un cronograma de clases basado en diversos factores como módulos,
     * feriados y disponibilidad de profesores.
     *
     * @param evt El evento que desencadena la generación del cronograma.
     */
    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        //Comvierte la fecha de inicio date a una fecha actual de tipo local date
        // <editor-fold desc="Obtener datos">

        ArrayList<Modulo> modulos = new ArrayList<Modulo>();
        ArrayList<Feriado> feriados = new ArrayList<Feriado>();
        ArrayList<Personal> Personales = new ArrayList<Personal>();
        ArrayList<Cronograma> cronogramas = new ArrayList<Cronograma>();

        //Vector de modulos
        try {
            modulos = moduloBL.listarRegistros();
        } catch (SQLException ex) {
            Logger.getLogger(frmSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Vector de feridados
        try {
            feriados = feriadoBL.listarRegistros();
        } catch (SQLException ex) {
            Logger.getLogger(frmSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Vector de personales
        try {
            //se le manda el id del profe para verificar solamente los dias libres de ese profe
            Personales = personalBL.listarRegistros(idProfesor);
        } catch (SQLException ex) {
            Logger.getLogger(frmSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Vector de cronogramas
        try {
            cronogramas = cronogramaBL.listarRegistros();
        } catch (SQLException ex) {
            Logger.getLogger(frmSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        // </editor-fold>
        //Para saber la fecha en que iniciara el modulo y la que termina
        LocalDate feInicioCrono = null;
        LocalDate feFinCrono = null;
        //Saco la fecha que se ingreso en el cronograma
        Date fechaInicio = dcFechaInicio.getDate();
        LocalDate fechaActual = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        double totalHorasTotales = 0.0;
        double horasDia = 0.0;
        for (Modulo m : modulos) {
            double totalHoras = m.getTotalHoras();
            horasDia = Double.parseDouble(txtHorasDias.getText());
            System.out.println("Total de horas de todos los módulos: " + totalHorasTotales);
            double totalLecciones = totalHoras / horasDia;
            // Redondea para arriba si es necesario
            totalLecciones = Math.ceil(totalLecciones);
            System.out.println("Total de lecciones: " + totalLecciones);
            int primerClase = 0;
            totalLecciones++;
            while (totalLecciones != 0) {
                //Obtenemos el dia de la semana de la fecha actual
                DayOfWeek diaSemana = fechaActual.getDayOfWeek();
                System.out.println("La fecha " + fechaActual + " es un " + diaSemana);

                //Bandera para saber si puede seguir validando que se puedan dar clases
                boolean continua = true;
                //Para almacenar los feriados
                LocalDate feriadoInicio = null;
                LocalDate feriadoFin = null;
                //Para averiguar la fecha de inicio de modulo

                //Verifica si ese dia puede dar clases
                //<editor-fold desc="Filtro de dias de la semana">
                switch (diaSemana) {
                    case MONDAY:
                        if (!rL.isSelected()) {
                            continua = false;
                            break;
                        }
                        break;
                    case TUESDAY:
                        if (!rK.isSelected()) {
                            continua = false;
                            break;
                        }
                        break;
                    case WEDNESDAY:
                        if (!rM.isSelected()) {
                            continua = false;
                            break;
                        }
                        break;
                    case THURSDAY:
                        if (!rJ.isSelected()) {
                            continua = false;
                            break;
                        }
                        break;
                    case FRIDAY:
                        if (!rV.isSelected()) {
                            continua = false;
                            break;
                        }
                        break;
                    case SATURDAY:
                        if (!rS.isSelected()) {
                            continua = false;
                            break;
                        }
                        break;
                    case SUNDAY:
                        if (!rD.isSelected()) {
                            continua = false;
                            break;
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
                //</editor-fold>

                if (continua == true) {
                    //<editor-fold desc="Filtro de dias feriados para todos">
                    for (Feriado f : feriados) {
                        feriadoInicio = f.getFechaInicio();
                        feriadoFin = f.getFechaFin();
                        if (fechaActual.isAfter(feriadoInicio) && fechaActual.isBefore(feriadoFin)
                                || fechaActual.equals(feriadoInicio) || fechaActual.equals(feriadoFin)) {
                            //No puede dar clases porque es feriado
                            continua = false;
                        }
                    }
                    // </editor-fold>
                    if (continua == true) {
                        //<editor-fold desc="Filtro de vacaciones por profesor">
                        for (Personal p : Personales) {
                            feriadoInicio = p.getFechaInicio();
                            feriadoFin = p.getFechaFin();
                            if (fechaActual.isAfter(feriadoInicio) && fechaActual.isBefore(feriadoFin)
                                    || fechaActual.equals(feriadoInicio) || fechaActual.equals(feriadoFin)) {
                                //No puede dar clases porque es feriado
                                continua = false;
                            }
                        }
                        // </editor-fold>
                        if (continua == true) {
                            if (primerClase == 0) { //para saber cuando se pueda ingresar la fecha de inicio
                                primerClase++;
                                //primero guardo la fecha en que se inicio el curso
                                feInicioCrono = fechaActual;

                            }
                            if (totalLecciones == 1) {
                                feFinCrono = fechaActual;
                                //<editor-fold desc="Cargo el cronograma">
                                Cronograma cronograma = new Cronograma();

                                Profesor profesor = new Profesor();
                                profesor.setId_profesor(idProfesor);
                                cronograma.setId_profesor(profesor);

                                Programa programa = new Programa();
                                programa.setIdPrograma(idPrograma);
                                cronograma.setId_programa(programa);

                                Grupo grupo = new Grupo();
                                grupo.setId_grupo(idGrupo);
                                cronograma.setId_grupo(grupo);

                                Modulo modulo = new Modulo();
                                modulo.setId_modulo(m.getId_modulo());
                                cronograma.setId_modulo(modulo);

                                cronograma.setAnio(feInicioCrono);

                                cronograma.setHorasDia(horasDia);

                                cronograma.setFechaInicio(feInicioCrono);
                                cronograma.setFechaFin(feFinCrono);

                                cronograma.setL(rL.isSelected());
                                cronograma.setK(rK.isSelected());
                                cronograma.setM(rM.isSelected());
                                cronograma.setJ(rJ.isSelected());
                                cronograma.setV(rV.isSelected());
                                cronograma.setS(rS.isSelected());
                                cronograma.setD(rD.isSelected());

                                cronograma.setEstado(rEstado.isSelected());
                                cronograma.setBorrado(rBorrado.isSelected());
                                //</editor-fold>
                                //Se crea el Insert
                                int resultado = 0;
                                try {
                                    resultado = cronogramaBL.insert(cronograma);
                                } catch (Exception ex) {
                                    Logger.getLogger(frmSistema.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                totalLecciones--;
                                //Dia de liquidacion por eso se le agregan 2
                                fechaActual = fechaActual.plusDays(2);
                                break;
                            } else {
                                totalLecciones--;
                                fechaActual = fechaActual.plusDays(1);
                            }
                        } else {
                            //No se pudo dar clases porque el profe tiene una situacion particular
                            fechaActual = fechaActual.plusDays(1);
                        }
                    } else {
                        //No se pudo dar clases porque es feriado
                        fechaActual = fechaActual.plusDays(1);
                    }
                } else {
                    //No se pudo dar clases porque no era un dia en que se daban lecciones
                    fechaActual = fechaActual.plusDays(1);
                }

            }

        }
        try {
            llenarCronogramas();
        } catch (Exception ex) {
            Logger.getLogger(frmSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnSalir3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnSalir2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txtHorasDiasKeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        //Si no es dígito no entra el caracter
        if (!Character.isDigit(caracter)) {
            evt.consume();//Consume la tecla presiona!
        }
    }

    private void rSActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JButton btnSalir2;
    private javax.swing.JButton btnSalir3;
    private javax.swing.JComboBox<String> cbGrupo;
    private javax.swing.JComboBox<String> cbProfesores;
    private javax.swing.JComboBox<String> cbProgramas;
    private com.toedter.calendar.JDateChooser dcFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox rBorrado;
    private javax.swing.JCheckBox rD;
    private javax.swing.JCheckBox rEstado;
    private javax.swing.JCheckBox rJ;
    private javax.swing.JCheckBox rK;
    private javax.swing.JCheckBox rL;
    private javax.swing.JCheckBox rM;
    private javax.swing.JCheckBox rS;
    private javax.swing.JCheckBox rV;
    private javax.swing.JTable tblCronogramas;
    private javax.swing.JTextField txtHorasDias;
    // End of variables declaration                   

    /* void getResult(int result) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}
