package com.yourmenu.yourmenu_api.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Locale;

@Service
public class RestaurantSlugService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public String generateSlug(String name) {
        if (name == null || name.isEmpty()){
            return null;
        }
        String slug = toSlug(name);
        int count = 1;
        while(!isFree(slug)){ //a melhor forma de fazer isso é fazendo uma só consulta no bd que retorna o último slug registrado dentro do padrão do slug
            if(count == 1) slug = slug + '-';
            slug = removeLastNumber(slug) + count++;
        }
        return slug;
    }
    public boolean isFree(String slug) {
        return restaurantRepository.findBySlug(slug) == null;
    } //essa função se tornará desnecessária

    //as funções abaixo poderiam estar em uma classe de utils
    private String removeLastNumber(String slug) {
        return slug.replaceFirst("\\d+$", "");
    }

    private String toSlug(String input) {
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
    private String normalize(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
