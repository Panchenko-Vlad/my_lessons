package ru.poker;

import ru.poker.Classes.Cart;
import ru.poker.Classes.Gamer;
import ru.poker.Classes.Table;
import ru.poker.Interfaces.IGenerator;

import java.util.Scanner;

public class Runner {

    public void generate(final Scanner sc) {
        final Table table = new Table();
        final BaseAction action = new BaseAction(table, new IGenerator() {
            @Override
            public Cart[] generateDeck() {
                return emptyData();
            }

            @Override
            public Gamer[] generateGamers() {
                return inputGamers(sc, table);
            }
        });
        action.initGame();
        progress(sc, action, table);
    }

    // Заполняем колоду пустыми значениями
    public Cart[] emptyData() {
        Cart[] deck = new Cart[52];
        for (int i = 0; i < deck.length; i++) {
            deck[i] = new Cart("", 0, "", false);
        }
        return deck;
    }

    // Создаем игроков
    public Gamer[] inputGamers(Scanner sc, Table table) {
        System.out.print("Введите кол-во игроков за столом: ");
        int sum = sc.nextInt();

        Gamer[] gamers = new Gamer[sum];

        int id = 0;
        while (id < sum) {
            table.inscription(" ВВЕДИТЕ ИНФОРМАЦИЮ ПРО НОВОГО ИГРОКА ");

            System.out.print("Имя: ");
            String name = sc.next();

            System.out.print("Количество денег ($) : ");
            int money = sc.nextInt();

            gamers[id] = new Gamer(++id, name, money, true);
        }
        return gamers;
    }

    // Ввод стартовой суммы на столе (снятие при каждой партии)
    public void inputPurse(Scanner sc, Table table) {
        if (table.getPurse() == 0) {
            table.inscription(" ВВЕДИТЕ СТАРТОВУЮ СУММУ ВХОДА ЗА СТОЛ ");
            System.out.print("Сумма: ");
            table.setPurse(sc.nextInt());
        }
    }

    public void progress(Scanner sc, BaseAction action, Table table) {
        String startGame = "";
        while (!startGame.equals("нет")) {
            inputPurse(sc, table);
            action.progress();
            if (table.checkNotTheEndGame() < 2) {
                table.inscription(" НЕДОСТАТОЧНО КОЛИЧЕСТВА ДЕНЕГ У ИГРОКОВ ИЛИ САМИХ ИГРОКОВ ДЛЯ ПРОДОЛЖЕНИЯ ИГРЫ ");
                return;
            }
            table.exitGamers(sc);
            System.out.print("Начать игру? (да/нет) ");
            startGame = sc.next();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final Runner runner = new Runner();
        runner.generate(sc);
    }
}