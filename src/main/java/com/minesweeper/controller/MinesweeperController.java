package com.minesweeper.controller;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minesweeper.domain.GameSession;
import com.minesweeper.domain.GameSessionMove;
import com.minesweeper.domain.User;
import com.minesweeper.service.GameService;

@RestController
public class MinesweeperController {

	@Autowired
	GameService gameService;
	Logger logger = LoggerFactory.getLogger(MinesweeperController.class);

	/**
	 * 
	 * @param cache
	 * @param cpp
	 * @return
	 */
	@RequestMapping(value = "/game/startNewGame", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameSession> startNewGame(@RequestBody Map<String, String> userName) {
		GameSession sessionGame = null;
		HttpStatus status = HttpStatus.OK;
		String name = userName.get("name");
		logger.info("Starting a new game user Name {}", name);
		try {
			User mockUser = new User(name, name);
			sessionGame = gameService.newGame(mockUser);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<GameSession>(sessionGame, status);
	}

	@RequestMapping(value = "/game/getGame/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameSession> getGame(@PathVariable("gameId") String gameId) {
		GameSession sessionGame = null;
		HttpStatus status = HttpStatus.OK;
		logger.info("Getting Game with ID: {}", gameId);
		try {
			sessionGame = gameService.getGame(gameId);
		} catch (Exception e) {
			logger.error("Error getting Game with ID: {}", gameId);
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<GameSession>(sessionGame, status);
	}

	@RequestMapping(value = "/game/revealSquare", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameSession> revealSquare(@RequestBody @Valid GameSessionMove gameMove) {
		GameSession sessionGame = null;
		HttpStatus status = HttpStatus.OK;
		logger.info("revealing square on game with ID: {} on row {} column {}", gameMove.getGameId(), gameMove.getRowP(),
				gameMove.getColumP());
		try {
			sessionGame = gameService.revealPosition(gameMove.getGameId(), gameMove.getRowP(), gameMove.getColumP());
		} catch (Exception e) {
			logger.error("Error getting Game with ID: {}", gameMove.getGameId(), e);
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<GameSession>(sessionGame, status);
	}

	@RequestMapping(value = "/game/flagSquare", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameSession> flagSquare(@RequestBody @Valid GameSessionMove gameMove) {
		GameSession sessionGame = null;
		HttpStatus status = HttpStatus.OK;
		logger.info("flagging square on game with ID: {} on row {} column {}", gameMove.getGameId(), gameMove.getRowP(),
				gameMove.getColumP());
		try {
			sessionGame = gameService.flagSquare(gameMove.getGameId(), gameMove.getRowP(), gameMove.getColumP());
		} catch (Exception e) {
			logger.error("Error getting Game with ID: {}", gameMove.getGameId(), e);
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<GameSession>(sessionGame, status);
	}

}
