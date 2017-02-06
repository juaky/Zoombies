import acm.graphics.GImage;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;
import java.util.ArrayList;
import java.util.Random;


public class Principal extends GraphicsProgram {
    public void run () {
        Bola bola;
        ArrayList<Bola> misbolas = new ArrayList<Bola>();
        ArrayList<Bola> bolasasesinas = new ArrayList<Bola>();
       this.setSize(500, 500);
        Random random= new Random();

        bolasasesinas.add(new Bola(random.nextInt(getWidth()-101),random.nextInt(getHeight()-101),new GImage("../images/emojiasesino.png")));

        for (int i = 0; i <20 ; i++) {
            int numimagen= random.nextInt(9)+1;
            misbolas.add(new Bola(random.nextInt(getWidth()-101),random.nextInt(getHeight()-101),new GImage("../images/emoji" + numimagen +".png")));
        }
        for (Bola mibola:misbolas) {
            add(mibola.getImagen(),mibola.getPosx(),mibola.getPoxy());
        }
        add(bolasasesinas.get(0).getImagen(),bolasasesinas.get(0).getPosx(),bolasasesinas.get(0).getPoxy());

        waitForClick();

        Bola tocada=null;
        boolean jugar =true;
        while(jugar) {
            for (Bola mibola : misbolas) {
                mibola.mover(getWidth(), getHeight());
                pause(5);
            }
            for (Bola asesina:bolasasesinas) {
                asesina.mover(getWidth(), getHeight());
                pause(5);
            }


            for (Bola asesina:bolasasesinas) {
                for (Bola mibola : misbolas) {
                    if (mibola.getImagen().getBounds().intersects(asesina.getImagen().getBounds())){
                        tocada=mibola;
                        break;
                    }
                }

                }
                if (tocada!=null){
                    misbolas.remove(tocada);
                    GImage pic = tocada.getImagen();
                    pic.getParent().remove(pic);
                    tocada.setImagen(new GImage("../images/emojiasesino.png"));
                    add(tocada.getImagen(),tocada.getPosx(),tocada.getPoxy());
                    if (tocada!=null) bolasasesinas.add(tocada);
                }
            if (misbolas.isEmpty()) jugar=false;
        }
        partidaFinalizada();
    }

    public void partidaFinalizada(){
        pause(400);
        GImage zoombiefinal = new GImage("../images/zoombie.png");
        add(zoombiefinal,(getWidth()-zoombiefinal.getWidth())/2,(getHeight()-zoombiefinal.getHeight())/2);
        zoombiefinal.setBounds(0,0,50,50);
        while (zoombiefinal.getWidth()<getWidth()){
            zoombiefinal.setBounds(0,0,zoombiefinal.getWidth()+50,zoombiefinal.getHeight()+50);
            pause(30);
        }

        //zoombiefinal.scale(-4);

       /* for (int i = 0; i <0 ; i++) {
            System.out.print("a");
            zoombiefinal.scale(-10);
            pause(50);
        }*/
       /* for (int i = 0; i <10 ; i++) {
            zoombiefinal.scale(10);
            pause(10);
        }*/
    }


    public static void main(String[] args) {
        new Principal().start(args);
    }
}




