package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import com.example.demo.strategy.DiscountContext;
import com.example.demo.strategy.NoDiscountStrategy;
import com.example.demo.strategy.SeasonalSaleStrategy;
import com.example.demo.strategy.StudentDiscountStrategy;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public double calculateDiscount(Game game) {

        DiscountContext context = new DiscountContext();

        if ("STUDENT".equalsIgnoreCase(game.getDiscountType())) {

            context.setStrategy(new StudentDiscountStrategy());

        } else if ("SEASONAL".equalsIgnoreCase(game.getDiscountType())) {

            context.setStrategy(new SeasonalSaleStrategy());

        } else {

            context.setStrategy(new NoDiscountStrategy());
        }

        return context.calculateDiscount(game.getPrice());
    }
}
