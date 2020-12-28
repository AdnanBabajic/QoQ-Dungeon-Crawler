package services;

import repository.entity.Game;

public interface GameService {
    String welcomeScreen();
    Game startGame();
    Game findGame(Integer id);
    Game getMoves(Integer id);
    Game fightMonsters(Integer id);
}
