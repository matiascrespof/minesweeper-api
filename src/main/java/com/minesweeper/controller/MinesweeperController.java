package com.minesweeper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/game/startNewGame", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameSession> startNewGame() {
		GameSession sessionGame = null;
		HttpStatus status = HttpStatus.OK;
		logger.info("Starting a new game");
		try {
			User mockUser = new User("1", "user 1");
			sessionGame = gameService.newGame(mockUser);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<GameSession>(sessionGame, status);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/game/getGame/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameSession> getGame(@PathVariable("gameId") String gameId) {
		GameSession sessionGame = null;
		HttpStatus status = HttpStatus.OK;
		logger.info("Getting Game with ID:", gameId);
		try {
			sessionGame = gameService.getGame(gameId);
		} catch (Exception e) {
			logger.error("Error getting Game with ID:", gameId);
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<GameSession>(sessionGame, status);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/game/revealSquare", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameSession> revealSquare(@RequestBody GameSessionMove gameMove) {
		GameSession sessionGame = null;
		HttpStatus status = HttpStatus.OK;
		logger.info("Getting Game with ID:", gameMove.getGameId());
		try {
			sessionGame = gameService.revealPosition(gameMove.getGameId(), gameMove.getRowP(), gameMove.getColumP());
		} catch (Exception e) {
			logger.error("Error getting Game with ID:", gameMove.getGameId(), e);
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<GameSession>(sessionGame, status);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/game/flagSquare", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameSession> flagSquare(@RequestBody GameSessionMove gameMove) {
		GameSession sessionGame = null;
		HttpStatus status = HttpStatus.OK;
		logger.info("Getting Game with ID:", gameMove.getGameId());
		try {
			sessionGame = gameService.revealPosition(gameMove.getGameId(), gameMove.getRowP(), gameMove.getColumP());
		} catch (Exception e) {
			logger.error("Error getting Game with ID:", gameMove.getGameId(), e);
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<GameSession>(sessionGame, status);
	}

}
