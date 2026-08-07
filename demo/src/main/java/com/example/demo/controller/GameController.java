package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Game;
import com.example.demo.service.GameService;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String listGames(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "games/list";
    }

    @GetMapping("/add")
    public String addGameForm(Model model) {
        model.addAttribute("game", new Game());
        return "games/add";
    }

    @PostMapping("/save")
    public String saveGame(@ModelAttribute Game game) {
        gameService.saveGame(game);
        return "redirect:/games";
    }

    @GetMapping("/edit/{id}")
    public String editGame(@PathVariable Long id, Model model) {

        Game game = gameService.getGameById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid game Id: " + id));

        model.addAttribute("game", game);

        return "games/edit";
    }

    @PostMapping("/update/{id}")
    public String updateGame(
            @PathVariable Long id,
            @ModelAttribute Game game) {

        game.setId(id);
        gameService.saveGame(game);

        return "redirect:/games";
    }

    @GetMapping("/delete/{id}")
    public String deleteGameForm(
            @PathVariable Long id,
            Model model) {

        Game game = gameService.getGameById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid game Id: " + id));

        model.addAttribute("game", game);

        return "games/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id) {

        gameService.deleteGame(id);

        return "redirect:/games";
    }
}
