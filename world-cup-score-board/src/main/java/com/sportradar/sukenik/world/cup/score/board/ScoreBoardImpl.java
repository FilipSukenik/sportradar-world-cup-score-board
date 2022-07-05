package com.sportradar.sukenik.world.cup.score.board;

import java.util.List;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

import com.sportradar.sukenik.world.cup.score.board.dependency.injection.DefaultDependencyFactory;
import com.sportradar.sukenik.world.cup.score.board.dependency.injection.DependencyFactory;
import com.sportradar.sukenik.world.cup.score.board.dto.GameDto;
import com.sportradar.sukenik.world.cup.score.board.exception.TechnicalException;

public class ScoreBoardImpl implements ScoreBoard {

    private final ScoreBoard scoreBoardService;

    public ScoreBoardImpl() {

        DependencyFactory dependencyFactory = new DefaultDependencyFactory();
        dependencyFactory.init();
        scoreBoardService = dependencyFactory.getDependency(ScoreBoard.class);
    }

    public ScoreBoardImpl(DependencyFactory dependencyFactory) {

        dependencyFactory.init();
        scoreBoardService = dependencyFactory.getDependency(ScoreBoard.class);
    }

    @Override
    public GameDto startGame(String homeTeamName, String awayTeamName) {

        return callMethod(() -> scoreBoardService.startGame(homeTeamName, awayTeamName));
    }

    @Override
    public boolean finishGameById(Integer gameId) {

        return callMethod(() -> scoreBoardService.finishGameById(gameId));
    }

    @Override
    public boolean finishGame(GameDto gameDto) {

        return callMethod(() -> scoreBoardService.finishGame(gameDto));
    }

    @Override
    public boolean updateGameScore(Integer gameId, int newHomeTeamScore, int newAwayTeamScore) {

        return callMethod(() -> scoreBoardService.updateGameScore(gameId, newHomeTeamScore, newAwayTeamScore));
    }

    @Override
    public List<GameDto> getSummary() {

        return callMethod(scoreBoardService::getSummary);
    }

    <O> O callMethod(@NotNull Supplier<O> methodSupplier) {

        Assert.notNull(methodSupplier, "methodSupplier cannot be null");

        try {
            return methodSupplier.get();
        } catch (IllegalArgumentException | TechnicalException e) {
            throw e;
        } catch (Exception e) {
            throw new TechnicalException(String.format("Exception was thrown during execution of %s method", getMethodName()), e);
        }
    }

    String getMethodName() {

        return new Throwable()
                .getStackTrace()[3]
                .getMethodName();
    }
}
