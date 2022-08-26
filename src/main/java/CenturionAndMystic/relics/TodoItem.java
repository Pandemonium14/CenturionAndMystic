package CenturionAndMystic.relics;

import CenturionAndMystic.CenturionAndMystic;

import static CenturionAndMystic.CentAndMysMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, CenturionAndMystic.Enums.CENTURION_COLOR);
    }
}
