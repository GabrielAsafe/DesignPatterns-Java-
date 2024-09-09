package Behavioral;


import java.util.ArrayList;
import java.util.List;


/**
 * O padrão de design Observer é um padrão comportamental que define uma dependência um-para-muitos entre objetos,
 * de tal forma que quando um objeto muda de estado, todos os seus dependentes são notificados e atualizados automaticamente.
 * Esse padrão é muito útil em cenários onde você deseja que diferentes partes de um sistema sejam atualizadas automaticamente quando o estado de um objeto muda.
 */


//Define a interface que os observadores precisam implementar para receber atualizações.
interface Observer {
    void update(float temperature);
}


//Define a interface que a estação meteorológica (o sujeito observado) precisa implementar. Isso inclui métodos para adicionar, remover e notificar observadores.
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature;

    public WeatherStation() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}


class PhoneDisplay implements Observer {
    private String name;

    public PhoneDisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature) {
        System.out.println(name + " recebeu atualização: Temperatura agora é " + temperature + "°C");
    }
}

class ComputerDisplay implements Observer {
    private String name;

    public ComputerDisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature) {
        System.out.println(name + " recebeu atualização: Temperatura agora é " + temperature + "°C");
    }
}


class ObserverPatternExample {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        Observer phoneDisplay1 = new PhoneDisplay("Telefone 1");
        Observer phoneDisplay2 = new PhoneDisplay("Telefone 2");
        Observer computerDisplay = new ComputerDisplay("Computador");

        weatherStation.addObserver(phoneDisplay1);
        weatherStation.addObserver(phoneDisplay2);
        weatherStation.addObserver(computerDisplay);

        weatherStation.setTemperature(25.0f);
        weatherStation.setTemperature(30.0f);

        // Remover um dos observadores
        weatherStation.removeObserver(phoneDisplay2);

        weatherStation.setTemperature(28.0f);
    }
}
