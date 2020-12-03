import java.util.ArrayList;
import java.util.HashMap;

public class Solution6 {

    HashMap<String, Page> pageMap;

    class Page implements Comparable<Page> {
        int id;
        double queryScore, linkScore, matchingScore;
        String html;

        Page(int id, String html) {
            this.id = id;
            this.html = html;
        }

        String getUrl() {
            String[] tags = html.split("<");
            for (String tag : tags) {
                if (tag.startsWith("meta property")) {
                    String[] tokens = tag.split("\"");
                    for (String tok : tokens) {
                        if (tok.startsWith("https")) return tok;
                    }
                }
            }
            return null;
        }

        ArrayList<String> getLinkUrls() {
            ArrayList<String> urls = new ArrayList<>();
            String[] tags = html.split("<");
            for (String tag : tags) {
                if (tag.startsWith("a href")) {
                    String[] tokens = tag.split("\"");
                    for (String tok : tokens) {
                        if (tok.startsWith("https")) urls.add(tok);
                    }
                }
            }
            return urls;
        }

        void getQueryScore(String query) {
            String[] words = html.split("\\b");
            for (String word : words) {
                for (String w : word.split("\\d+")) {
                    if (w.equalsIgnoreCase(query)) queryScore++;
                }
            }
        }

        void distributeLinkScore() {
            ArrayList<String> externalLinks = getLinkUrls();
            int numLinks = externalLinks.size();
            for (String externalLink : externalLinks) {
                Page extPage = pageMap.get(externalLink);
                if (extPage != null) extPage.linkScore += queryScore / numLinks;
            }
        }

        void getMatchingScore() {
            matchingScore = queryScore + linkScore;
        }

        @Override
        public int compareTo(Page o) {
            double diff = matchingScore - o.matchingScore;
            if (diff > 0) return -1;
            if (diff < 0) return 1;
            return id - o.id;
        }
    }

    public int solution(String word, String[] pages) {
        pageMap = new HashMap<>();

        int n = pages.length;
        for (int i = 0; i < n; i++) {
            Page p = new Page(i, pages[i]);
            String url = p.getUrl();
            pageMap.put(url, p);
        }

        for (Page p : pageMap.values()) {
            p.getQueryScore(word);
        }
        for (Page p : pageMap.values()) {
            p.distributeLinkScore();
        }
        for (Page p : pageMap.values()) {
            p.getMatchingScore();
        }

        Page max = new Page(Integer.MAX_VALUE, null);
        for (Page p : pageMap.values()) {
            if (p.compareTo(max) < 0) max = p;
        }
        return max.id;
    }

}
