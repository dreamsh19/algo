class Solution1 {

    public String solution(String new_id) {

        new_id = new_id.toLowerCase();

        new_id = new_id.replaceAll("[^\\w\\-\\.]", "");

        new_id = new_id.replaceAll("\\.+", ".");

        if (new_id.startsWith(".")) new_id = new_id.substring(1);

        if (new_id.endsWith(".")) new_id = new_id.substring(0, new_id.length() - 1);

        if (new_id.isEmpty()) new_id = "a";

        new_id = new_id.substring(0, Math.min(15, new_id.length()));
        if (new_id.endsWith(".")) new_id = new_id.substring(0, new_id.length() - 1);

        char c = new_id.charAt(new_id.length() - 1);
        while (new_id.length() <= 2) {
            new_id += c;
        }
        
        return new_id;
    }
}
