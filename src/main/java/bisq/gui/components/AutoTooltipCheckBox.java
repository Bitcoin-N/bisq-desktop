package bisq.gui.components;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Skin;

import com.sun.javafx.scene.control.skin.CheckBoxSkin;

import static bisq.gui.components.TooltipUtil.showTooltipIfTruncated;

public class AutoTooltipCheckBox extends CheckBox {

    public AutoTooltipCheckBox() {
        super();
    }

    public AutoTooltipCheckBox(String text) {
        super(text);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new AutoTooltipCheckBoxSkin(this);
    }

    private class AutoTooltipCheckBoxSkin extends CheckBoxSkin {
        public AutoTooltipCheckBoxSkin(CheckBox checkBox) {
            super(checkBox);
        }

        @Override
        protected void layoutChildren(double x, double y, double w, double h) {
            super.layoutChildren(x, y, w, h);
            showTooltipIfTruncated(this, getSkinnable());
        }
    }
}