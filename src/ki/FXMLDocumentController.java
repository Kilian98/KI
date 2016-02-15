package ki;

import intelligence.Intelligence;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {

    @FXML
    private MenuBar TopMenu;
    @FXML
    private Menu menuFile;
    @FXML
    private TextArea tbHistory;
    @FXML
    private Button btnSend;
    @FXML
    private TextField tbEingabe;
    @FXML
    private MenuItem menuItemClose;

    Intelligence ki = new Intelligence();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tbEingabe.requestFocus();
            }
        });

    }

    void send(String text) {
        appendLine("Du:\t" + text);
        appendLine(ki.newInput(text));
    }

    void appendLine(String text) {
        tbHistory.appendText(text + "\n");
        tbHistory.setScrollTop(Double.MAX_VALUE);
    }

    @FXML
    private void btnSendOnAction(ActionEvent event) {
        send(tbEingabe.getText());
        tbEingabe.setText("");
    }

    @FXML
    private void btnEingabeOnKey(ActionEvent event) {
        send(tbEingabe.getText());
        tbEingabe.setText("");
        System.out.println("enter pressed");
    }

    @FXML
    private void close(ActionEvent event) {
        System.out.println("closed");
        System.exit(0);
    }

}
