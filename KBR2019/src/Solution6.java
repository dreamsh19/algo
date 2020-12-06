import java.util.ArrayList;
import java.util.HashMap;

public class Solution6 {

    HashMap<String, Page> pageMap;

    class Page implements Comparable<Page> {
        int id;
        double queryScore, linkScore, matchingScore;
        String html;
        String[] tags;

        Page(int id, String html) {
            this.id = id;
            this.html = html;
            tags = html.split(">\\s*<|<|>");
        }

        String getUrl() {
            for (String tag : tags) {
                if (tag.startsWith("meta property")) {
                    for (String tok : tag.split("\"")) {
                        if (tok.startsWith("https://")) return tok;
                    }
                }
            }
            return null;
        }

        ArrayList<String> getExternalUrls() {
            ArrayList<String> urls = new ArrayList<>();
            for (String tag : tags) {
                if (tag.startsWith("a href")) {
                    for (String tok : tag.split("\"")) {
                        if (tok.startsWith("https://")) urls.add(tok);
                    }
                }
            }
            return urls;
        }

        void getQueryScore(String query) {
            for (String tag : tags) {
                for (String w : tag.split("[^a-zA-Z]+")) {
                    if (w.equalsIgnoreCase(query)) queryScore++;
                }
            }
        }


        void distributeLinkScore() {
            ArrayList<String> externalLinks = getExternalUrls();
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
            if (o == null) return 1;
            double diff = matchingScore - o.matchingScore;
            if (diff > 0) return 1;
            if (diff < 0) return -1;
            return o.id - id;
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

        Page max = null;
        for (Page p : pageMap.values()) {
            if (p.compareTo(max) > 0) max = p;
        }
        return max.id;
    }

}
