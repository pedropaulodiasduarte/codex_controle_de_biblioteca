package br.com.codexcb.application.mensagens;

public class AlertInformationCreator extends MessageCreator{
    @Override
    protected Message factoryMethod() {
        return new AlertInformationMessage();
    }
}
