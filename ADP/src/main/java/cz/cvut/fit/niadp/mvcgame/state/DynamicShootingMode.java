package cz.cvut.fit.niadp.mvcgame.state;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;

public class DynamicShootingMode implements IShootingMode {
    protected int missilesToShoot = MvcGameConfig.MIN_MISSILES;

    @Override
    public void shoot(AbsCannon cannon) {

        int halfMissiles = this.missilesToShoot / 2;
        boolean isEven = this.missilesToShoot % 2 == 0;

        // Aim up initially
        for (int i = 0; i < halfMissiles; i++) {
            cannon.aimUp();
        }

        // Shoot and aim down, except for the last missile
        for (int i = 0; i < this.missilesToShoot - 1; i++) {
            cannon.primitiveShoot();
            cannon.aimDown();
        }

        // Adjust aiming if the number of missiles is even
        if (isEven) {
            cannon.aimDown();
        }

        // Shoot the last missile
        cannon.primitiveShoot();

        // Reset aiming position
        for (int i = 0; i < halfMissiles; i++) {
            cannon.aimUp();
        }
    }

    @Override
    public String getName() {
        return DynamicShootingMode.class.getSimpleName().replace("ShootingMode", "");
    }

    @Override
    public int getMissilesToShoot() {
        return this.missilesToShoot;
    }

    public void increaseMissiles() {
        if (this.missilesToShoot + 1 < MvcGameConfig.MAX_MISSILES) {
            this.missilesToShoot += 1;
        }
    }

    public void decreaseMissiles() {
        if (this.missilesToShoot - 1 >= MvcGameConfig.MIN_MISSILES) {
            this.missilesToShoot -= 1;
        }
    }
}
