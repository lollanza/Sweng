package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.IState;
import it.unimi.di.sweng.esame.model.Lesson;
import it.unimi.di.sweng.esame.view.InputView;
import org.jetbrains.annotations.NotNull;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyInputPresenter implements InputPresenter {
    private final InputView view;
    private final IState model;

    public MyInputPresenter(InputView view, IState model) {
        this.view = view;
        this.model = model;
        view.addHandlers(this);
    }

    @Override
    public void action(@NotNull String text, @NotNull String text1) {
        try {
            String[] parte1 = text.split(",");
            String[] parte2 = text1.split(",");
            if (parte1.length != 2) throw new IllegalArgumentException("Invalid first input format");
            if (parte2.length != 2) throw new IllegalArgumentException("Invalid second input format");
            LocalTime time = LocalTime.parse(parte2[0].trim(), DateTimeFormatter.ofPattern("H:mm"));
            DayOfWeek day = DayOfWeek.valueOf(parte1[1].trim());
            Lesson lesson = new Lesson(parte1[0].trim(), day, time, Integer.parseInt(parte2[1].trim()));
            model.insertLesson(lesson);
            view.showSuccess();
        } catch (IllegalArgumentException e) {
            if (e.getMessage().startsWith("No enum constant java.time.DayOfWeek"))
                view.showError("Invalid day");
            else
                view.showError(e.getMessage());
        } catch (DateTimeException e) {
            view.showError("Invalid time format");
        }
    }
}
