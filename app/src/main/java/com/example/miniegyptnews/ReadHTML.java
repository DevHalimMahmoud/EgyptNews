package com.example.miniegyptnews;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ReadHTML extends AsyncTask<Void,Void,String> {

    private String sHTML;
    private String sourceName;
    ProgressDialog dialog;
    Context context;
    public ReadHTML(String sHTML,String sourceName,Context context){
        this.sHTML = sHTML;
        this.sourceName = sourceName;
        dialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
    }

    private StringBuilder check(Element element){
        StringBuilder res = null;
        if(sourceName.equals("Almasryalyoum.com")){
            for(Element x : element.children()){
                if(x.className().equals("")) {
                    res.append(x.text()).append("\n");
                }
            }
        }
        return res;
    }
    @Override
    protected String doInBackground(Void... voids) {
        Document doc;
        StringBuilder res = new StringBuilder();
        try {
            doc = Jsoup.connect(sHTML).get();
            Element element = null;
            if (sourceName.equals("Youm7.com")) {
                element = doc.getElementById("articleBody");
                for(Element x : element.children()){
                    res.append(x.text()).append("\n").append("\n");
                }
            }
            else if (sourceName.equals("Masrawy.com")) {
                for(Element x : doc.getElementsByClass("news").select("p")){
                    if(x.className().equals("")){
                        if(x.text().equals("عدد المصابين")) break;
                        res.append(x.text()).append("\n").append("\n");
                    }
                }
            }
            else if(sourceName.equals("Almasryalyoum.com")){
                element = doc.getElementById("NewsStory");
                for(Element x : element.children()){
                    if(x.className().equals("")) {
                        res.append(x.text()).append("\n").append("\n");
                    }
                }
            }
            else if(sourceName.equals("RT")){
                for(Element x : doc.select("p")){
                    //Log.d("amr",x.tagName());
                    if(x.text().contains("المصدر:")) break;
                    if(x.className().equals(""))
                        res.append(x.text()).append("\n").append("\n");
                }
            }
            else if(sourceName.equals("Kooora365.com")){
                for(Element x : doc.getElementById("the-post").children().select("p")){
                    Log.d("amr",x.tagName());
                //    if(x.className().equals(""))
                        res.append(x.text()).append("\n").append("\n");
                }
            }

             else if(sourceName.equals("Skynewsarabia.com")){
                for(Element x : doc.getElementsByClass("article-body").select("p")){
                    Log.d("amr",x.tagName());
                //    if(x.className().equals(""))
                        res.append(x.text()).append("\n").append("\n");
                }
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.dismiss();
    }
}
