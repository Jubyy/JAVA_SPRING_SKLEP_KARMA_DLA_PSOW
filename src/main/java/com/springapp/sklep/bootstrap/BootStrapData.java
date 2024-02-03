package com.springapp.sklep.bootstrap;

import com.springapp.sklep.domain.Product;
import com.springapp.sklep.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final ProductRepository productRepository;

    public BootStrapData(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Product karma1 = new Product("PEDIGREE Adult 15kg (średnie rasy) ","Pedigree","Zdrowa i zbilansowana dieta odgrywa ważną rolę w życiu Twojego psa. PEDIGREE Adult z wołowiną i drobiem to idealne zaspokojenie istotnych potrzeb dorosłego psa","https://fera.pl/images/thumbnails/960/1214/detailed/102/5900951019845.jpg", 49L, 125F,15F);
        Product karma2 = new Product("Brit Premium by Nature Adult L ","Brit","Brit Premium by Nature Adult L z wysoką zawartością białka, dla dorosłych psów dużych ras, lekkostrawna, 45% mięsa kurczaka , zawiera przeciwutleniacze i chroni chrząstki gwarantując stabilność stawów","https://shop-cdn-m.mediazs.com/bilder/5/800/82042_pla_brit_premiumbynature_adult_l_15kg_5.jpg", 102L, 185F,15F);
        Product karma3 = new Product("ROYAL CANIN Maxi Adult ","Royal Canin","Royal Canin Maxi Adult to karma sucha przeznaczona dla bardzo dużych psów.Potrzeby żywieniowe dużych psów bardzo różnią się od potrzeb mniejszych psów. ","https://media.os.fressnapf.com/products-v2/5/e/b/3/5eb365b38fb052fea06544decdf6e74fcb743267_1003112004__pl_PL__maxiadult__1____Kopie.jpg?f=webp&t=prod_m", 25L, 250F,15F);
        Product karma4 = new Product("CHAPPI sucha karma dla psów z wołowiną, drobiem i warzywami","Chappi","Chappi z wołowina, kurczakiem i warzywami to chrupiąca sucha karma dla Twojego psa. ","https://fera.pl/images/thumbnails/1018/1286/detailed/65/5998749128329F1.jpg", 200L, 170F,27F);
        productRepository.save(karma1);
        productRepository.save(karma2);
        productRepository.save(karma3);
        productRepository.save(karma4);
        System.out.println("Started in Bootstrap");

    }
}
