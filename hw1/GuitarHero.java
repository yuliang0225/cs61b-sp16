public class GuitarHero {

    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final double CONCERT_A = 440.0;

    public static void main(String[] args) {
        synthesizer.GuitarString[] gs = new synthesizer.GuitarString[keyboard.length()];

        for (int i=0; i<keyboard.length(); i++) {
            gs[i] = new synthesizer.GuitarString( CONCERT_A * Math.pow(2, (i - 24) / 12.0) );
        }

        int index = -1;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                index = keyboard.indexOf(key);
                if (index >= 0) {
                    gs[index].pluck();
                }
            }

            if (index >= 0) {
                StdAudio.play(gs[index].sample());
                gs[index].tic();
            }
        }

    }

}
