package com.libin.factory.reflex_factory;

/**
 * Created by doudou on 2017/3/6.
 */

public abstract class JiLiFactory {

    public abstract <T extends JiLiCar> T createJiliCar(Class<T> clz);
}
