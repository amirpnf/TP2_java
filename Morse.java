public class Morse {
    public static void main (String[] args) {
        String res = join(args, " Stop. ");
        System.out.println(res);
    }

    /* public static String join(String[] array, String separator) {
        var build = new StringBuilder();
        var sep = "";
        for(var i : array) {
            build.append(sep).append(i);
            sep = separator;
        }
        build.append(sep);
        return build.toString();
    } */

    public static String join(String[] array, String separator) {
        var res = array[0] + separator;
        for (int i = 1; i < array.length; i++) {
            res = res + array[i] + separator;
        }
        return res;
    }

}