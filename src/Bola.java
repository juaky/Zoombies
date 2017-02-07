import acm.graphics.GImage;
import java.util.Random;

/**
 * Clase Bola
 */
public class Bola {
        private int posx;
        private int poxy;
        private GImage imagen;

    /**
     * Constructor dela Bola
     * @param posx posición eje x
     * @param poxy posición eje y
     * @param imagen imagen de la bola
     */
    public Bola(int posx, int poxy, GImage imagen) {
        this.posx = posx;
        this.poxy = poxy;
        this.imagen = imagen;
    }


    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPoxy() {
        return poxy;
    }

    public void setPoxy(int poxy) {
        this.poxy = poxy;
    }

    public GImage getImagen() {
        return imagen;
    }

    public void setImagen(GImage imagen) {
        this.imagen = imagen;
    }


    /**
     * Realiza un movimiento aleatorio de la bola sin salirse del Canvas
     * @param limitex  Límite máximo x del Canvas
     * @param limitey  Límite màximo y del Canvas
     */
    public void mover(int limitex, int limitey){
        Random random= new Random();
        int x=0,y=0;
        do {
            x=random.nextInt(11);
            y=random.nextInt(11);
            if (x<=5) x=-x; else x-=5 ;
            if (y<=5) y=-y;else y-=5 ;
        } while(((this.getImagen().getX()+x+this.getImagen().getWidth())>=limitex)||((this.getImagen().getX()+x)<0)
                ||(((this.getImagen().getY()+y+this.getImagen().getHeight())>=limitey)||((this.getImagen().getY()+y)<0)));
        this.getImagen().move(x,y);
    }
}


