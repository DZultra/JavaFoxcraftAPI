package net.dzultra.jfa.punishments;

import net.dzultra.jfa.exceptions.JsoupConnectionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LiteBansScraper {

    private static final String BASE =
            "https://www.mcfoxcraft.com/newbans/";

    public static List<Punishment> fetchPunishments(
            String uuid
    ) {
        List<Punishment> records = new ArrayList<>();
        Set<String> visitedPages = new HashSet<>();

        String nextPageUrl =
                BASE + "history.php?uuid=" + uuid;

        while (nextPageUrl != null) {

            if (!visitedPages.add(nextPageUrl)) {
                break; // stop infinite loops
            }

            Document doc;
            try {
                doc = Jsoup.connect(nextPageUrl)
                        .userAgent("Mozilla/5.0")
                        .get();
                System.out.println("Sending Request to URL: " + nextPageUrl);
            } catch (IOException e) {
                throw new JsoupConnectionException(
                        e, nextPageUrl
                );
            }

            Elements rows =
                    doc.select("table tbody tr");

            for (Element row : rows) {

                Elements tds = row.select("td");
                if (tds.size() < 6) continue;

                /* ---------- TYPE ---------- */

                Element typeLink =
                        tds.get(0)
                                .selectFirst("a");

                String type =
                        typeLink != null
                                ? typeLink.text().trim()
                                : tds.get(0).text().trim();

                /* ---------- PLAYER ---------- */

                String punishedPlayer =
                        tds.get(1)
                                .selectFirst("span")
                                .text();

                String punishedUuid =
                        extractUuidFromAvatar(
                                tds.get(1)
                        );

                /* ---------- MODERATOR ---------- */

                String moderator =
                        tds.get(2)
                                .selectFirst("span")
                                .text();

                String moderatorUuid =
                        extractUuidFromAvatar(
                                tds.get(2)
                        );

                /* ---------- OTHER FIELDS ---------- */

                String reason = tds.get(3).text();
                String date = tds.get(4).text();
                String expires = tds.get(5).text();

                records.add(new Punishment(
                        type,
                        punishedPlayer,
                        punishedUuid,
                        moderator,
                        moderatorUuid,
                        reason,
                        date,
                        expires
                ));
            }

            /* ---------- PAGINATION ---------- */

            Element next =
                    doc.selectFirst(
                            "a.litebans-pager-right.litebans-pager-active"
                    );

            if (next != null) {
                String newUrl =
                        BASE + next.attr("href");

                if (newUrl.equals(nextPageUrl)) {
                    break;
                }

                nextPageUrl = newUrl;
            } else {
                nextPageUrl = null;
            }
        }

        return records;
    }

    /* ---------- UUID FROM AVATAR ---------- */

    private static String extractUuidFromAvatar(
            Element td
    ) {
        Element img = td.selectFirst("img");

        if (img == null) return null;

        String src = img.attr("src");

        // Example:
        // https://cravatar.eu/avatar/<uuid>/25

        int start = src.indexOf("avatar/");
        if (start == -1) return null;

        start += 7; // length of "avatar/"

        int end = src.indexOf('/', start);
        if (end == -1) return null;

        return src.substring(start, end);
    }
}



