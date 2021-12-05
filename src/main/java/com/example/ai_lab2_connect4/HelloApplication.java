package com.example.ai_lab2_connect4;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HelloApplication extends Application {

    private static final int TILE_SIZE = 80;
    private static final int COLS = 7;
    private static final int ROWS = 6;

    private boolean redMove = true;
    private Disc[][] discGrid = new Disc[COLS][ROWS];
    private int[][] mappedArray = new int[COLS][ROWS];
    private Pane discRoot = new Pane();
    /* @Override
     public void start(Stage stage) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
         Scene scene = new Scene(fxmlLoader.load(), 320, 240);
         stage.setTitle("Hello!");
         stage.setScene(scene);
         stage.show();
     }
     */

    public static void main(String[] args) {
        launch(args);
    }

    private Parent creatContent() {
        Pane root = new Pane();
        Button reset = new Button("Show Tree!");
        BorderPane borderPane = new BorderPane();
        ToolBar tb = new ToolBar();

        Label humanScore = new Label("Human Score: " + 0);
        Label aiScore = new Label("AI Score: " + 100);

        tb.getItems().addAll(reset, humanScore, aiScore);

        root.getChildren().addAll(discRoot);

        Shape gridShape = makeGrid();
        borderPane.setBottom(tb);
        borderPane.setCenter(gridShape);
        root.getChildren().addAll(borderPane);
        root.getChildren().addAll(makeColumns());


        return root;
    }

    private Shape makeGrid() {
        Shape shape = new Rectangle((COLS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setCenterX(TILE_SIZE / 2);
                circle.setCenterY(TILE_SIZE / 2);
                circle.setTranslateX(j * (TILE_SIZE + 5) + TILE_SIZE / 4);
                circle.setTranslateY(i * (TILE_SIZE + 5) + TILE_SIZE / 4);

                shape = shape.subtract(shape, circle);
            }
        }
        shape.setFill(Color.BLUE);

        return shape;
    }

    private List<Rectangle> makeColumns() {
        List<Rectangle> list = new ArrayList<>();

        for (int i = 0; i < COLS; i++) {
            Rectangle rect = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
            rect.setTranslateX(i * (TILE_SIZE + 5) + TILE_SIZE / 4);
            //rect.setTranslateY(40);
            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> {
                rect.setFill(Color.BLUE);
                rect.setOpacity(0.4);
            });
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            final int tempCol = i;
            rect.setOnMouseClicked(e -> placeDisc(new Disc(redMove), tempCol));

            list.add(rect);
        }

        return list;
    }

    private static class Disc extends Circle {
        private final boolean isRed;

        public Disc(boolean isRed) {
            super(TILE_SIZE / 2, isRed ? Color.RED : Color.YELLOW);
            this.isRed = isRed;

            setCenterX(TILE_SIZE / 2);
            setCenterY(TILE_SIZE / 2);
        }
    }

    private void placeDisc(Disc disc, int column) {
        int row = ROWS - 1;
        do {
            if (!getDisc(column, row).isPresent())
                break;

            row--;
        } while (row >= 0);

        if (row < 0)
            return;

        discGrid[column][row] = disc;
        discRoot.getChildren().add(disc);
        disc.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4);

        if(redMove){
            mappedArray[column][row] = 2;
        }else{
            mappedArray[column][row] = 1;
        }

        final int currentRow = row;

        /*
        System.out.println("column is: " + column + " row is: " + row);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(mappedArray[j][i] + " ");
            }
            System.out.println();
        }
        */

        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(row * (TILE_SIZE + 5) + TILE_SIZE / 4);
        animation.setOnFinished(e -> {
            if (gameEnded(column, currentRow)) {
                gameOver();
            }

            redMove = !redMove;
        });
        animation.play();


    }

    private Optional<Disc> getDisc(int column, int row) {
        if (column < 0 || column >= COLS
                || row < 0 || row >= ROWS)
            return Optional.empty();

        return Optional.ofNullable(discGrid[column][row]);
    }

    private boolean gameEnded(int column, int row) {
        List<Point2D> vertical = IntStream.rangeClosed(row - 3, row + 3)
                .mapToObj(r -> new Point2D(column, r))
                .collect(Collectors.toList());

        List<Point2D> horizontal = IntStream.rangeClosed(column - 3, column + 3)
                .mapToObj(c -> new Point2D(c, row))
                .collect(Collectors.toList());

        Point2D topLeft = new Point2D(column - 3, row - 3);
        List<Point2D> diagonal1 = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> topLeft.add(i, i))
                .collect(Collectors.toList());

        Point2D botLeft = new Point2D(column - 3, row + 3);
        List<Point2D> diagonal2 = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> botLeft.add(i, -i))
                .collect(Collectors.toList());

        return checkRange(vertical) || checkRange(horizontal)
                || checkRange(diagonal1) || checkRange(diagonal2);
    }

    private boolean checkRange(List<Point2D> points) {
        int chain = 0;

        for (Point2D p : points) {
            int column = (int) p.getX();
            int row = (int) p.getY();

            Disc disc = getDisc(column, row).orElse(new Disc(!redMove));
            if (disc.isRed == redMove) {
                chain++;
                if (chain == 4) {
                    return true;
                }
            } else {
                chain = 0;
            }
        }

        return false;
    }

    private void gameOver() {
        System.out.println("Winner: " + (redMove ? "Human" : "AI"));
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(creatContent()));
        stage.show();
    }


}