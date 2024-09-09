package Behavioral;


/**
 *
 O Command é um design pattern comportamental que transforma uma solicitação em um objeto independente que contém todas as informações sobre a solicitação. Isso permite que você parametrize objetos com operações, atrasar ou enfileirar a execução de uma solicitação, e suportar operações que podem ser desfeitas.
 */
public interface Command {
    void execute();
}

class Light {
    public void turnOn() {
        System.out.println("The light is on");
    }

    public void turnOff() {
        System.out.println("The light is off");
    }
}


class TurnOnLightCommand implements Command {
    private Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

class ClientA {
    public static void main(String[] args) {
        Light light = new Light();

        Command turnOn = new TurnOnLightCommand(light);
        Command turnOff = new TurnOffLightCommand(light);

        RemoteControl remote = new RemoteControl();

        // Ligar a luz
        remote.setCommand(turnOn);
        remote.pressButton();

        // Desligar a luz
        remote.setCommand(turnOff);
        remote.pressButton();
    }
}
