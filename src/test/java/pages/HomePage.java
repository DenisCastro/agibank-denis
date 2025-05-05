package pages;

import org.openqa.selenium.By;

public class HomePage {

    public static final By imgAgiBlog = By.xpath("//div[@id=\"ast-desktop-header\"]");
    public static final By btnOpenSearchTxt = By.xpath("//div[@class=\"ast-search-icon\"]");
    public static final By txtSearch = By.xpath("//input[@id=\"search-field\"]");
    public static final By btnSearchAction = By.xpath("//div[@class=\"ast-search-menu-icon slide-search ast-dropdown-active\"]");
    public static final By txtResultadosEncontrados = By.xpath("//section[@class=\"ast-archive-description\"]/h1");
    public static final By listArtigos = By.xpath("//div[@class=\"ast-row\"]");

}
