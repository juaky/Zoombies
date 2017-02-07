import acm.graphics.GImage;
import acm.program.GraphicsProgram;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Principal extends GraphicsProgram {
    public void run () {
        Bola bola;
        ArrayList<Bola> misbolas = new ArrayList<Bola>();  //Conjunto de emojis normales
        ArrayList<Bola> bolasasesinas = new ArrayList<Bola>(); //Conjunto de emojis zoombies
        this.setSize(500, 500);
        Random random= new Random();

        //Añadir un emoji asesino
        bolasasesinas.add(new Bola(random.nextInt(getWidth()-101),random.nextInt(getHeight()-101),new GImage("../images/emojiasesino.png")));
        add(bolasasesinas.get(0).getImagen(),bolasasesinas.get(0).getPosx(),bolasasesinas.get(0).getPoxy());

        //Añadir emojis normales
        for (int i = 0; i <20 ; i++) {
            int numimagen= random.nextInt(9)+1;
            misbolas.add(new Bola(random.nextInt(getWidth()-101),random.nextInt(getHeight()-101),new GImage("../images/emoji" + numimagen +".png")));
        }
        for (Bola mibola:misbolas) {
            add(mibola.getImagen(),mibola.getPosx(),mibola.getPoxy());
        }

        waitForClick();
        Bola tocada;
        boolean jugar =true;
        while(jugar) {
            moverListaBolas(misbolas);
            moverListaBolas(bolasasesinas);
            tocada = comprobarTocada(misbolas,bolasasesinas);
            jugar = bolaTocada(tocada,misbolas,bolasasesinas);
        }
        partidaFinalizada();
    }


    /**
     * Mueve los emojis de una lista de emojis
     * @param listabolas Lista de emojis que contiene los emojis a mover
     */
    public void moverListaBolas(ArrayList<Bola> listabolas){
        for (Bola mibola : listabolas) {
            mibola.mover(getWidth(), getHeight());
            pause(5);
        }
    }


    /**
     * Comprueba si se tocan 2 emojis de listas diferentes
     * @param misbolas Lista con los emojis normales
     * @param bolasasesinas Lista con los emojis zoombies
     * @return Si se tocan, devuelve el emoji de la lista normal
     */
    public Bola comprobarTocada(ArrayList<Bola> misbolas,ArrayList<Bola> bolasasesinas){
        for (Bola asesina:bolasasesinas) {
            for (Bola mibola : misbolas) {
                if (mibola.getImagen().getBounds().intersects(asesina.getImagen().getBounds())){
                    return mibola;
                }
            }
        }
        return null;
    }


    /**
     * Se intercambia el emoji de la lista normal a la de zoombies
     * @param tocada Emoji tocado de la lista normal
     * @param misbolas Lista de emojis normales
     * @param bolasasesinas Lista de emojis zoombies
     * @return Comprueba si se han eliminado todos los emojis de la lista normal
     */
    public boolean bolaTocada(Bola tocada,ArrayList<Bola> misbolas, ArrayList<Bola> bolasasesinas ){
        if (tocada!=null){
            misbolas.remove(tocada);
            GImage pic = tocada.getImagen();
            pic.getParent().remove(pic);
            tocada.setImagen(new GImage("../images/emojiasesino.png"));
            add(tocada.getImagen(),tocada.getPosx(),tocada.getPoxy());
            bolasasesinas.add(tocada);
        }
        if (misbolas.isEmpty()) return false;
        return true;
    }


    /**
     * Muestra Pantalla final de partida
     */
    public void partidaFinalizada(){
        pause(400);
        GImage zoombiefinal = new GImage("../images/zoombie.png");
        add(zoombiefinal,(getWidth()-zoombiefinal.getWidth())/2,(getHeight()-zoombiefinal.getHeight())/2);
        zoombiefinal.setBounds(0,0,50,50);
        while (zoombiefinal.getWidth()<getWidth()){
            zoombiefinal.setBounds(0,0,zoombiefinal.getWidth()+50,zoombiefinal.getHeight()+50);
            pause(30);
        }
    }


    public static void main(String[] args) {
        new Principal().start(args);
    }
}




