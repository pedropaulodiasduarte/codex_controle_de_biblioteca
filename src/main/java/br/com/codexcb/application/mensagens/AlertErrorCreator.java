package br.com.codexcb.application.mensagens;

public class AlertErrorCreator extends MessageCreator{
    @Override
    protected Message factoryMethod() {
        return new AlertErrorMessage();
    }

}
