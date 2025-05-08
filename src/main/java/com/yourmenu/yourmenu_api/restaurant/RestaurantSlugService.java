package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.shared.utils.SlugFormater;
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
        String slug = SlugFormater.toSlug(name);
        int count = 1;
        while(!isFree(slug)){ //a melhor forma de fazer isso é fazendo uma só consulta no bd que retorna o último slug registrado dentro do padrão do slug
            if(count == 1) slug = slug + '-';
            slug = SlugFormater.removeLastNumber(slug) + count++;
        }
        return slug;
    }
    public boolean isFree(String slug) {
        return restaurantRepository.findBySlug(slug) == null;
    } //essa função se tornará desnecessária
}
