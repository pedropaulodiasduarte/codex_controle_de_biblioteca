package br.com.codexcb.application.mensagens;

import javafx.scene.control.Alert;

public class AlertInformationMessage implements Message{
    @Override
    public void sendAlert(String titulo, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // Remove o cabeçalho
        alert.setContentText(message);
        alert.showAndWait(); // Exibe o popup e espera o usuário fechar
    }
}
