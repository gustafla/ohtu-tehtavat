package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface KirjanpitoInterface {
    public void lisaaTapahtuma(String tapahtuma);
    public ArrayList<String> getTapahtumat();
}
