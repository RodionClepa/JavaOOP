package FileInspector;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileInfoJava extends FileInfo{
    private String[] types = {"private void","private int","private double","private boolean","private String","private char","private byte","private short","private long","private float","public void","public int","public double","public boolean","public String","public char","public byte","public short","public long","public float","protected void","protected int","protected double","protected boolean","protected String","protected char","protected byte","protected short","protected long","protected float","private static void","private abstract void","private static int","private abstract int","private static double","private abstract double","private static boolean","private abstract boolean","private static String","private abstract String","private static char","private abstract char","private static byte","private abstract byte","private static short","private abstract short","private static long","private abstract long","private static float","private abstract float","public static void","public abstract void","public static int","public abstract int","public static double","public abstract double","public static boolean","public abstract boolean","public static String","public abstract String","public static char","public abstract char","public static byte","public abstract byte","public static short","public abstract short","public static long","public abstract long","public static float","public abstract float","protected static void","protected abstract void","protected static int","protected abstract int","protected static double","protected abstract double","protected static boolean","protected abstract boolean","protected static String","protected abstract String","protected static char","protected abstract char","protected static byte","protected abstract byte","protected static short","protected abstract short","protected static long","protected abstract long","protected static float","protected abstract float"};
    @Override
    public void getDetailInfo(String filePathString){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePathString));
            int lineCount = 0;
            String line;
            int classCount = 0;
            int methodCount = 0;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                if (line.startsWith("class ") || line.startsWith("public class ") || line.startsWith("private class ")) {
                    classCount++;
                }
                for (int i = 0; i < types.length; i++) {
                    if (line.replaceAll("^\\s+", "").startsWith(types[i])) {
                        methodCount++;
                        break;
                    }
                }                
                
            }

            reader.close();
            System.out.println("Line count: " + lineCount);
            System.out.println("Class count: " + classCount);
            System.out.println("Method count: " + methodCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
