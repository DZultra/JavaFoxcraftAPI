package net.dzultra.jfa.punishments;

import net.dzultra.jfa.exceptions.JsoupConnectionException;
import net.dzultra.jfa.exceptions.PunishmentFetchException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LiteBansScraper {

    private static final String BASE = "https://www.mcfoxcraft.com/newbans/";

    public static List<Punishment> fetchPunishments(String uuid) {
        List<Punishment> records = new ArrayList<>();
        String nextPageUrl = BASE + "history.php?uuid=" + uuid;

        while (nextPageUrl != null) {
            Document doc;
            try {
                doc = Jsoup.connect(nextPageUrl).userAgent("Mozilla/5.0").get();
            } catch(IOException e) {
                throw new JsoupConnectionException(e, nextPageUrl);
            }

            Elements rows = doc.select("table tbody tr"); // All Punishment Rows
            for (Element row : rows) {
                Element firstTd = row.selectFirst("td");
                if (firstTd == null) throw new PunishmentFetchException(row);

                Element link = firstTd.selectFirst("a[href]");
                if (link == null) throw new PunishmentFetchException(row);

                String href = link.attr("href");
                String type = link.text().trim();
                String infoUrl = BASE + href;

                Punishment record = fetchPunishmentDetails(type, infoUrl);
                records.add(record);
            }

            Element next = doc.selectFirst("a.litebans-pager-right.litebans-pager-active");
            if (next != null) {
                nextPageUrl = BASE + next.attr("href");
            } else {
                nextPageUrl = null;
            }
        }

        return records;
    }

    private static Punishment fetchPunishmentDetails(String type, String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
        } catch (IOException e) {
            throw new JsoupConnectionException(e, url);
        }

        Elements rows = doc.select("table tr");

        String punishedPlayer = null;
        String punishedUuid = null;
        String moderator = null;
        String moderatorUuid = null;
        String reason = null;
        String date = null;
        String expires = null;
        String originServer = null;

        for (Element row : rows) {
            Elements tds = row.select("td"); // All Info to the Punishment
            if (tds.size() < 2) continue;

            String key = tds.get(0).text();
            Element valueTd = tds.get(1);

            switch (key) {
                case "Player" -> {
                    Element a = valueTd.selectFirst("a[href]");
                    if (a != null) {
                        punishedPlayer = a.text();
                        String href = a.attr("href");
                        punishedUuid = extractUuid(href);
                    }
                }
                case "Moderator" -> {
                    Element a = valueTd.selectFirst("a[href]");
                    if (a != null) {
                        moderator = a.text();
                        String href = a.attr("href");
                        moderatorUuid = extractUuid(href);
                    }
                }
                case "Reason" -> reason = valueTd.text();
                case "Date" -> date = valueTd.text();
                case "Expires" -> expires = valueTd.text();
                case "Origin Server" -> originServer = valueTd.text();
            }
        }

        return new Punishment(
                type, punishedPlayer, punishedUuid,
                moderator, moderatorUuid, reason,
                date, expires, originServer
        );
    }

    private static String extractUuid(String href) {
        int index = href.indexOf("uuid=");
        if (index == -1) return null;

        String uuidPart = href.substring(index + 5);

        // Remove suffix like :issued
        int colon = uuidPart.indexOf(':');
        if (colon != -1) {
            uuidPart = uuidPart.substring(0, colon);
        }

        return uuidPart;
    }
}

