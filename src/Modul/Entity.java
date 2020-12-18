package Modul;

public abstract class Entity {
    private float posX;
    private float posY;
    private Manager manager;

    public Entity(float posX, float posY, Manager manager) {
        this.posX = posX;
        this.posY = posY;
        this.manager = manager;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public abstract void update();
}
