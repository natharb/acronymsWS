package br.edu.ifpb.sistemasdistribuidos.controller;

import br.edu.ifpb.sistemasdistribuidos.beans.AcronimoBean;
import br.edu.ifpb.sistemasdistribuidos.beans.ResponseBean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Nath on 11/7/2015.
 */

@Controller
@RequestMapping("/acronym")
public class AcronymController {

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody ResponseBean getAcronimoService(@RequestParam(value="acronimo",
            required=true) String acronimoSolicitado) {

        ResponseBean response = new ResponseBean();

        JSONObject json = new JSONObject();

        ArrayList<AcronimoBean> listaSignificados = new ArrayList();

        try {
            json = readJsonFromUrl("https://natharb.github.io/acronimo/acronimos.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(null != json) {

            JSONArray arr = json.getJSONArray("valores");
            for (int i = 0; i < arr.length(); i++)
            {
                String acronimo = arr.getJSONObject(i).getString("acronimo");
                String significado = arr.getJSONObject(i).getString("significado");

                AcronimoBean acronimoBean = new AcronimoBean();
                acronimoBean.setAcronimo(acronimo);
                acronimoBean.setSignificado(significado);

                listaSignificados.add(acronimoBean);
            }

        } else {
            response.setStatus(-1);
            response.setAcronimo("ERROR");
            response.setSignificado("Erro ao obter o significado");
        }

        for (AcronimoBean acronimo: listaSignificados) {

            if(acronimo.getAcronimo().equals(acronimoSolicitado)) {

                response.setAcronimo(acronimoSolicitado);
                response.setSignificado(acronimo.getSignificado());
                response.setStatus(1);
                return response;

            }

        }

        response.setStatus(-1);
        response.setAcronimo("NAO ENCONTRADO");
        response.setSignificado("Nao encontrado o significado");

        return response;
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
