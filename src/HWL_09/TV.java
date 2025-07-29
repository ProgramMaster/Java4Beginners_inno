package HWL_09;

import java.lang.String;
import java.lang.*;

// Класс Телевизор
class TV {
    // Поля класса
    private final String brand;
    private final double diagonal;
    private int channel;
    private int volume;
    private boolean isOn;

    // Конструктор
    public TV(String brand, double diagonal) {
        this.brand = brand;
        this.diagonal = diagonal;
        this.channel = 1;
        this.volume = 50;
        this.isOn = false;
    }

    // Геттеры и сеттеры
    public String getBrand() {
        return brand;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        if (channel > 0 && channel <= 100) {
            this.channel = channel;
        } else {
            System.out.println("Недопустимый канал. Должен быть от 1 до 100");
        }
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.volume = volume;
        } else {
            System.out.println("Громкость должна быть от 0 до 100");
        }
    }

    public boolean isOn() {
        return isOn;
    }

    // Методы управления телевизором
    public void turnOn() {
        isOn = true;
        System.out.println(brand + " включен");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(brand + " выключен");
    }

    public void channelUp() {
        if (isOn) {
            channel = (channel % 100) + 1;
            System.out.println("Канал установлен на " + channel);
        }
    }

    public void channelDown() {
        if (isOn) {
            channel = (channel - 1 == 0) ? 100 : channel - 1;
            System.out.println("Канал установлен на " + channel);
        }
    }

    public void volumeUp() {
        if (isOn && volume < 100) {
            volume++;
            System.out.println("Громкость: " + volume);
        }
    }

    public void volumeDown() {
        if (isOn && volume > 0) {
            volume--;
            System.out.println("Громкость: " + volume);
        }
    }

    @Override
    public String toString() {
        return "Телевизор " + brand +
                ", диагональ: " + diagonal +
                "\", состояние: " + (isOn ? "вкл" : "выкл") +
                ", канал: " + channel +
                ", громкость: " + volume;
    }
}

