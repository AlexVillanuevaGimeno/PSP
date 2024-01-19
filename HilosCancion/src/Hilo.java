    import java.util.ArrayList;

    public class Hilo {
        int idHilo;
        protected Hilo sucesor;
        protected int count;
        protected ArrayList<Hilo> hilos;

        public ArrayList<Hilo> getHilos() {
            return hilos;
        }

        public void setHilos(ArrayList<Hilo> hilos) {
            this.hilos = hilos;
        }

        @Override
        public String toString() {
            return "Hilo{" +
                    "idHilo=" + idHilo +
                    '}';
        }

        public Hilo() {
        }
        public Hilo(int idHilo) {
            this.idHilo = idHilo;
            this.count = 0;

        }

//        public static int estrofasPorHilo(int estrofas, int hilos){
//            int numEstrofasPorHilo = estrofas / hilos;
//            return numEstrofasPorHilo;
//        }
        public synchronized void run(){
            switch (idHilo){

            }
        }
    }
