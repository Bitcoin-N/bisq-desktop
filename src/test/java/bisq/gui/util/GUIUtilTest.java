package bisq.gui.util;

import bisq.common.GlobalSettings;
import bisq.common.locale.Res;
import bisq.common.locale.TradeCurrency;

import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import static bisq.common.locale.TradeCurrencyMakers.bitcoin;
import static bisq.common.locale.TradeCurrencyMakers.euro;
import static bisq.core.user.PreferenceMakers.empty;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static org.junit.Assert.assertEquals;

public class GUIUtilTest {

    @Before
    public void setup() {
        Locale.setDefault(new Locale("en", "US"));
        GlobalSettings.setLocale(new Locale("en", "US"));
    }

    @Test
    public void testTradeCurrencyConverter() {
        Map<String, Integer> offerCounts = new HashMap<String, Integer>() {{
            put("BTC", 11);
            put("EUR", 10);
        }};
        StringConverter<TradeCurrency> tradeCurrencyConverter = GUIUtil.getTradeCurrencyConverter(
                Res.get("shared.oneOffer"),
                Res.get("shared.multipleOffers"),
                offerCounts
        );

        assertEquals("✦ BTC (BTC) - 11 offers", tradeCurrencyConverter.toString(bitcoin));
        assertEquals("★ Euro (EUR) - 10 offers", tradeCurrencyConverter.toString(euro));
    }

    @Test
    public void testCurrencyListWithOffersConverter() {
        Res.setBaseCurrencyCode("BTC");
        Res.setBaseCurrencyName("Bitcoin");
        StringConverter<CurrencyListItem> currencyListItemConverter = GUIUtil.getCurrencyListItemConverter(Res.get("shared.oneOffer"),
                Res.get("shared.multipleOffers"),
                empty);

        assertEquals("✦ BTC (BTC) - 10 offers", currencyListItemConverter.toString(make(CurrencyListItemMakers.bitcoinItem.but(with(CurrencyListItemMakers.numberOfTrades, 10)))));
        assertEquals("★ Euro (EUR) - 0 offers", currencyListItemConverter.toString(make(CurrencyListItemMakers.euroItem)));
        assertEquals("★ Euro (EUR) - 1 offer", currencyListItemConverter.toString(make(CurrencyListItemMakers.euroItem.but(with(CurrencyListItemMakers.numberOfTrades, 1)))));

    }
}