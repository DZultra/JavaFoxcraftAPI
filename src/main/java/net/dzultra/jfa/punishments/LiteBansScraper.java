package net.dzultra.jfa.punishments;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LiteBansScraper {

    private static final String BASE_URL = "https://www.mcfoxcraft.com/newbans/history.php?uuid=";

    public static List<Punishment> fetchAllPunishments(String uuid) throws IOException, InterruptedException {
        List<Punishment> allPunishments = new ArrayList<>();
        String before = null;

        while (true) {
            // Build the URL for this page
            String url = BASE_URL + uuid;
            if (before != null) {
                url += "&before=" + before;
            }

            // Fetch the page
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0") // avoid simple bot blocks
                    .timeout(10_000)
                    .get();

            // Parse the table rows
            Elements rows = doc.select("table tbody tr");
            if (rows.isEmpty()) break; // no more punishments

            for (Element row : rows) {
                Element link = row.selectFirst("a[href*=info.php]");
                String href = link != null ? link.attr("href") : "";

                int id = extractId(href);
                String type = extractType(href);

                String playerName = row.select("td:nth-child(2) span").text();
                String playerUUID = extractUUID(row.select("td:nth-child(2) img").attr("src"));
                String moderatorName = row.select("td:nth-child(3) span").text();
                String moderatorUUID = extractUUID(row.select("td:nth-child(3) img").attr("src"));
                String reason = row.select("td:nth-child(4)").text();
                String date = row.select("td:nth-child(5)").text();
                String expires = row.select("td:nth-child(6)").text();

                Punishment p = new Punishment(type, id, playerName, playerUUID, moderatorName, moderatorUUID, reason, date, expires);
                allPunishments.add(p);
            }

            // Find next page cursor
            Element nextLink = doc.selectFirst("a.litebans-pager-right.litebans-pager-active");
            if (nextLink == null) break; // no more pages

            before = extractBefore(nextLink.attr("href"));

            // Optional: polite delay
            Thread.sleep(150);
        }

        return allPunishments;
    }

    // Helper to extract numeric ID from info.php?type=ban&id=12345
    private static int extractId(String href) {
        try {
            String[] parts = href.split("&");
            for (String part : parts) {
                if (part.startsWith("id=")) return Integer.parseInt(part.substring(3));
            }
        } catch (Exception ignored) {}
        return -1;
    }

    // Helper to extract type from info.php?type=ban&id=12345
    private static String extractType(String href) {
        try {
            String[] parts = href.split("&");
            for (String part : parts) {
                if (part.startsWith("type=")) return part.substring(5);
            }
        } catch (Exception ignored) {}
        return "";
    }

    // Extract UUID from avatar URL: https://cravatar.eu/avatar/<uuid>/25
    private static String extractUUID(String src) {
        try {
            String[] parts = src.split("/");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("avatar") && i + 1 < parts.length) return parts[i + 1];
            }
        } catch (Exception ignored) {}
        return "";
    }

    // Extract the 'before' timestamp from the URL
    private static String extractBefore(String href) {
        try {
            href = URLDecoder.decode(href, StandardCharsets.UTF_8);
            String[] parts = href.split("&");
            for (String part : parts) {
                if (part.startsWith("before=")) return part.substring(7);
            }
        } catch (Exception ignored) {}
        return null;
    }

    // Example usage
    public static void main(String[] args) throws IOException, InterruptedException {
        String uuid = "662d93f1c0b84c80b36a6375cb6bd39a";
        List<Punishment> punishments = fetchAllPunishments(uuid);
        System.out.println("Total punishments: " + punishments.size());
        punishments.forEach(System.out::println);
    }

    public record Punishment(
            String type,         // Ban, Mute, Warning, Kick
            int id,              // Numeric punishment ID
            String playerName,
            String playerUUID,
            String moderatorName,
            String moderatorUUID,
            String reason,
            String date,
            String expires
    ) {}
}

