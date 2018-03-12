package bisq.gui.components;

import bisq.common.util.Tuple2;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import javafx.geometry.Pos;



import bisq.gui.util.GUIUtil;

public class ColoredDecimalPlacesWithZerosText extends HBox {

    public ColoredDecimalPlacesWithZerosText(String number, int numberOfZerosToColorize) {
        super();

        if (numberOfZerosToColorize <= 0) {
          getChildren().addAll(new Text(number));
        } else if (number.contains(GUIUtil.RANGE_SEPARATOR)) {
            String[] splitNumber = number.split(GUIUtil.RANGE_SEPARATOR);
            Tuple2<Label, Label> numbers = getSplittedNumberNodes(splitNumber[0], numberOfZerosToColorize);
            getChildren().addAll(numbers.first, numbers.second);

            getChildren().add(new Text(GUIUtil.RANGE_SEPARATOR));

            numbers = getSplittedNumberNodes(splitNumber[1], numberOfZerosToColorize);
            getChildren().addAll(numbers.first, numbers.second);
        } else {
            Tuple2<Label, Label> numbers = getSplittedNumberNodes(number, numberOfZerosToColorize);
            getChildren().addAll(numbers.first, numbers.second);
        }
        setAlignment(Pos.CENTER);
    }

    private Tuple2<Label, Label> getSplittedNumberNodes(String number, int numberOfZeros) {
        String placesBeforeZero = number.split("0{1," + Integer.toString(numberOfZeros) + "}$")[0];
        String zeroDecimalPlaces = number.substring(placesBeforeZero.length());
        Label first = new AutoTooltipLabel(placesBeforeZero);
        Label last = new Label(zeroDecimalPlaces);
        last.getStyleClass().add("zero-decimals");

        return new Tuple2<>(first, last);
    }
}