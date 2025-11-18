package br.com.codexcb.application.mensagens;

public abstract class MessageCreator {

    protected abstract Message factoryMethod();

    public void messageUser(String titulo, String messageText) {
        Message message = factoryMethod();
        message.sendAlert(titulo, messageText);
    }
}
