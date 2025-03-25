package pe.edu.upeu.sysasisgui.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.sysasisgui.modelo.Asistencia;
import pe.edu.upeu.sysasisgui.modelo.Estudiante;
import pe.edu.upeu.sysasisgui.servicio.AsistenciaServicio;
import pe.edu.upeu.sysasisgui.servicio.EstudianteServicio;

@Controller
public class AsistenciaControl {

    @FXML
    private Button btnGuardar;

    @FXML
    private TableView<Estudiante> tableRegAsis;
    @FXML
    private TableView<Asistencia> tableListAsis;

    @Autowired
    private EstudianteServicio estudianteService;

    private ObservableList<Estudiante> estudiantes;

    @FXML
    private void initialize(){
        estudianteService.cargarDatos();
        listarEstudiantes();
    }

    private void listarEstudiantes(){
        TableColumn<Estudiante, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        TableColumn<Estudiante, Boolean> asistenciaColumn = new TableColumn<>("Asistencia");

        asistenciaColumn.setCellValueFactory(cellData ->cellData.getValue().estadoProperty());
        asistenciaColumn.setCellFactory(tc -> new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();
            {
                checkBox.setOnAction(e -> {
                    Estudiante estudiante =
                            getTableView().getItems().get(getIndex());
                    estudiante.setEstado(checkBox.isSelected());
                });
            }
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(item);
                    setGraphic(checkBox);
                }
            }
        });
        estudiantes = FXCollections.observableArrayList(estudianteService.getEntidad());
        tableRegAsis.getColumns().addAll(nameColumn, asistenciaColumn);
        tableRegAsis.setItems(estudiantes);
    }

}
