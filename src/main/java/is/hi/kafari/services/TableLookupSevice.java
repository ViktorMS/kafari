package is.hi.kafari.services;

/**
 *
 * @author Símon Örn Reynisson <sor7@hi.is>
 * @date November 2017
 *
 * Flettir upp í JSON skrá upplýsingum um köfun.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import org.json.*;

public class TableLookupSevice {

    /**
     *
     * @param depth ---- Dýpt köfunar.
     * @param diveTime ---- tími köfunar.
     * @param letter --- Bókstafur sem bætir x tima við köfun skv. töflum.
     * @return ArrayList af strengjum á eftirfarandi formi: {Bókstafur,
     * Decompression 3m, decompression 6m, 12m, 15m}
     * @throws org.json.JSONException
     */
    public static ArrayList<String> lookup(int depth, int diveTime, String letter) throws JSONException, FileNotFoundException {
        ArrayList<String> results = new ArrayList();
        String data = readFile("toflur.json");
        JSONObject jobj = new JSONObject(data);
        JSONArray tafla = getTable(jobj, depth);
        JSONArray current;
        if (letter != null) {
            diveTime += addTime(depth, letter);
        }

        for (int i = 0; i < tafla.length(); i++) {
            current = tafla.getJSONArray(i);
            if (diveTime <= current.getInt(0)) {
                //Bæta við bókstaf
                results.add(current.getString(2));
                //Bæta við decompression
                ArrayList<String> deco = parseDeco(current.getInt(1));
                for (String s : deco) {
                    results.add(s);
                }

                break;
            }
        }
        if (depth <= 9 && results.isEmpty()) {
            current = tafla.getJSONArray(tafla.length() - 1);
            //Bæta við bókstaf
            results.add(current.getString(2));
            //Bæta við decompression
            ArrayList<String> deco = parseDeco(current.getInt(1));
            for (String s : deco) {
                results.add(s);
            }

        } else {
            if (results.isEmpty()) {
                results = null;
            }
        }

        return results;

    }

    /**
     * Hjálparfall sem setur upplýsingar um decompression á læsilegt form.
     *
     * @param number : Táknar decompression þar sem hvert sæti táknar margfeldi
     * af 5; þannig er 11 = 1*5 mín á 6 metrum, 1*5 mín á 3 metrum.
     * @return ArrayList af öllum stigum deco þar sem sætin eru í hækkandi röð,
     * fyrsta = 3 m, annað sæti = 6 m osfrv.
     */

    private static ArrayList<String> parseDeco(int number) {
        ArrayList<String> results = new ArrayList();
        String s = Integer.toString(number);
        int num;
        for (int i = s.length(); i > 0; i--) {
            num = Integer.parseInt(s.substring(i - 1, i));
            num *= 5;//Decompression tími er alltaf margfeldi af 5. 
            results.add(Integer.toString(num));
        }

        return results;
    }

    /**
     *
     * @param depth Dýpt köfunar á bilinu 6-57.
     * @param letter Bókstafur eftir fyrri kafanir.
     * @return int sem bæta á við tíma köfunar. Ef dýpt er út fyrir leyfilegt
     * bil skilar fallið 0.
     */
    private static int addTime(int depth, String letter) throws JSONException, FileNotFoundException {

        if (depth >= 60) {
            return 0;
        }
        String data = readFile("addtime.json");
        JSONObject obj = new JSONObject(data);
        int tableIndex = (depth - 6) / 3;  //Töflur eru altaf á 3 metra fresti og byrja í 6 metrum. 

        JSONArray table = obj.getJSONArray(Integer.toString(tableIndex));
        int entryIndex;
        //Fylkið er raðað svo fáum röðun bókstafa útfrá ASCII
        if (letter.equals("Z")) {
            entryIndex = 15;
        } else {
            entryIndex = ((int) letter.charAt(0) - 65);

        }

        return table.getInt(entryIndex);
    }

    /**
     * Les in skrá og skilar innihaldinu sem streng.
     *
     * @param filename Nafn skráar eða absolute path
     * @return strengur með innihaldi skráarinnar.
     */
    private static String readFile(String filename) throws FileNotFoundException {
        String s = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            s = sb.toString();

        } catch (Exception ex) {
            throw new FileNotFoundException();
        }

        return s;
    }

    /**
     *
     * @param o JSON object sem inniheldur allar töflur.
     * @return JSONArray af gildum fyrir rétta töflu. JSONArray inniheldur mörg
     * önnur JSONArray sem eru á forminu [int maxTime, int deco, char letter].
     * Skilar @code(null) ef dýptin er út fyrir töfluna.
     */
    private static JSONArray getTable(JSONObject o, int depth) throws JSONException {

        int i = 0;
        JSONObject j = o.getJSONObject("tables").getJSONObject(Integer.toString(i));
        int tableDepth = (Integer) j.get("depth");

        while (true) {
            //Tafla byggir á max dýpt, þ.a.l. skila töflunni um leið og köfunardýpt er minni eða jöfn dýpt köfunar. 
            if (depth <= tableDepth) {
                return o.getJSONObject("tables").getJSONObject(Integer.toString(i)).getJSONArray("table");
            }

            i++;
            //i=19 er út fyrir töfluna. 
            if (i == 19) {
                return null;
            }
            j = o.getJSONObject("tables").getJSONObject(Integer.toString(i));
            tableDepth = (Integer) j.get("depth");

        }

    }

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        
      
        ArrayList<String> zazz = lookup(57,15,null);
        for(String s:zazz){
            System.out.println(s);
        }
        
        
        /*
        for(int i = 0; i<jarr.length();i++){
            System.out.println("Keyword: "+ jarr.getString(i));
        }
        
     */
    //}
}
