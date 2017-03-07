package com.libin.factory.reflex_factory;

/**
 * Created by doudou on 2017/3/6.
 */

public class JiliCarFactory extends JiLiFactory {
    private static JiliCarFactory mFactory;

    private JiliCarFactory() {
    }

    public static JiliCarFactory getInstance(){
        if(mFactory != null){
            return mFactory;
        }

        mFactory = new JiliCarFactory();

        return mFactory;
    }

    @Override
    public <T extends JiLiCar> T createJiliCar(Class<T> clz) {
        JiLiCar jiLiCar = null;
        try {
            jiLiCar = (JiLiCar) Class.forName(clz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return (T) jiLiCar;
    }
}
