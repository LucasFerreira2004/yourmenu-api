package com.yourmenu.yourmenu_api.shared.utils;

import java.text.Normalizer;
import java.util.Locale;

public class SlugFormater {
    public static String  removeLastNumber(String slug) {
        return slug.replaceFirst("\\d+$", "");
    }

    public static String toSlug(String input) {
        if (input == null || input.isBlank()) return "";

        String normalized = normalize(input);
        String slug = normalized
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9\\s-]", "")   // remove tudo que não é letra, número, espaço ou hífen
                .replaceAll("\\s+", "-")           // substitui espaços por hífen
                .replaceAll("-{2,}", "-")           // evita múltiplos hífens consecutivos
                .replaceAll("^-|-$", "");           // remove hífen do início/fim

        return slug;
    }

    //remove acentuação e caracteres como 'ç'
    public static String normalize(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
