package com.gnstkd95.cafego;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by alfo6-7 on 2018-04-23.
 */

public class CafeSearchFragment extends Fragment {

    RecyclerAdapter recyclerAdapter;
    RecyclerView recycler;
    ArrayList<Item> items = new ArrayList<>();
    String ClientID = "9Xe8GEPqliKUfH8io6so";
    String ClientSecret = "xKnikvcb8K";
    String LocalUrl = "openapi.naver.com/v1/search/local.xml";
    String ImgUrl = "openapi.naver.com/v1/search/image.xml";
    ImageView iv_clickCafeSearch;
    EditText edit_CafeSearch;
    XmlPullParser httpParser;
    String search;
    String Imgsearch;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cafesearch,container,false);
        iv_clickCafeSearch = view.findViewById(R.id.iv);
        edit_CafeSearch = view.findViewById(R.id.edit);
        iv_clickCafeSearch.setOnClickListener(onClickListener);

        recycler = view.findViewById(R.id.recycler);
        recyclerAdapter = new RecyclerAdapter(view.getContext(), items);
        recycler.setAdapter(recyclerAdapter);


        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        MapPaser();
        items.clear();

        }
    };

    public void MapPaser(){

        new Thread(){

            @Override
            public void run() {
                super.run();

                try {
                    search = edit_CafeSearch.getText().toString();
                    HttpRequestManager httpManager = new HttpRequestManager();

                    // 건네줄 변수 설정
                    HttpRequestManager.VariableList variables = new HttpRequestManager.VariableList(HttpRequestManager.CHARSET_UTF8);
                    variables.add("query", search+"카페");

                    // 요청 세부사항 설정
                    HttpRequestManager.URLConnectionData data = httpManager.setRequestDetails(HttpRequestManager.METHOD_GET, HttpRequestManager.PROTOCOL_HTTPS, LocalUrl, variables);
                    httpManager.setRequestHeader(data, "X-Naver-Client-Id", ClientID);
                    httpManager.setRequestHeader(data, "X-Naver-Client-Secret", ClientSecret);

                    // 요청 시작
                    String result = httpManager.request(data);

                    Log.i("console-test", result);

                    /* HTTP GET 요청 완료 */

                    /* 파일 내용(String) -> InputStreamReader로 변환 */
                    byte[] buffer = result.getBytes("UTF-8");
                    ByteArrayInputStream stream = new ByteArrayInputStream(buffer);
                    InputStreamReader reader = new InputStreamReader(stream);

                    /* 파싱 작업 시작 */
                    httpParser = XmlPullParserFactory.newInstance().newPullParser();
                    httpParser.setInput(reader);

                    int eventType = httpParser.next();
                    Item item = null;
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:
                                break;
                            case XmlPullParser.START_TAG:

                                String name = httpParser.getName();
                                if (name.equals("item")){
                                    item = new Item();
                                }else if (httpParser.getName().equals("category")) { // 카테고리
                                    httpParser.next();

                                    if (item != null) item.setCategory(httpParser.getText());

                                }
                                else if (httpParser.getName().equals("title")) { // 카페이름
                                    httpParser.next();

                                    if (item != null){
                                        Spanned mtitle = Html.fromHtml(httpParser.getText());
                                        Imgsearch = mtitle.toString();
                                        item.setTitle(Imgsearch);
                                    }

                                }else if (httpParser.getName().equals("link")) { // 링크
                                    httpParser.next();

                                    if (item != null) item.setLink(httpParser.getText());

                                }else if (httpParser.getName().equals("address")) { // 지번주소
                                    httpParser.next();

                                    if (item != null) item.setAddress(httpParser.getText());

                                }else if (httpParser.getName().equals("roadAddress")) { // 도로명주소
                                    httpParser.next();

                                    if (item != null) item.setRoadAddress(httpParser.getText());

                                }else if (httpParser.getName().equals("telephone")) { // 전화번호
                                    httpParser.next();

                                    if (item != null) item.setTelephone(httpParser.getText());

                                }else if (httpParser.getName().equals("mapx")) { // x좌표
                                    httpParser.next();

                                    if (item != null) item.setMapx(Integer.parseInt(httpParser.getText()));

                                }
                                else if (httpParser.getName().equals("mapy")) { // y좌표
                                    httpParser.next();

                                    if (item != null) item.setMapy(Integer.parseInt(httpParser.getText()));

                                }
                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                String tag = httpParser.getName();
                                if (tag.equals("item")) {
                                    items.add(item);

                                    //리스트뷰 갱신 1
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            recyclerAdapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                                break;
                        }
                        eventType = httpParser.next();
                    }//while 문의 끝..
                    for (int i = 0; i < items.size(); i++) {
                        parseNaverPage((items.get(i).getTitle()), items.get(i));

                    }
                    getActivity().runOnUiThread(new Runnable() {@Override public void run() {
                        recyclerAdapter.notifyDataSetChanged();
                    }});
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }


            }
        }.start();

    }

    public void parseNaverPage(String Imgsearch ,Item item) throws IOException, XmlPullParserException {
        Log.i("console", Imgsearch);

        /* HTTP GET 요청 -> 네이버 영화 검색 API를 통해 XML 데이터를 받아오기 */
        // HttpRequestManager 객체 생성
        HttpRequestManager httpManager = new HttpRequestManager();

        // 건네줄 변수 설정
        HttpRequestManager.VariableList variables = new HttpRequestManager.VariableList(HttpRequestManager.CHARSET_UTF8);
        variables.add("query", Imgsearch);

        // 요청 세부사항 설정
        HttpRequestManager.URLConnectionData data = httpManager.setRequestDetails(HttpRequestManager.METHOD_GET, HttpRequestManager.PROTOCOL_HTTPS, ImgUrl, variables);
        httpManager.setRequestHeader(data, "X-Naver-Client-Id", ClientID);
        httpManager.setRequestHeader(data, "X-Naver-Client-Secret", ClientSecret);

        // 요청 시작
        String result = httpManager.request(data);

//        Log.i("console-test", result);

        /* HTTP GET 요청 완료 */

        /* 파일 내용(String) -> InputStreamReader로 변환 */
        byte[] buffer = result.getBytes("UTF-8");
        ByteArrayInputStream stream = new ByteArrayInputStream(buffer);
        InputStreamReader reader = new InputStreamReader(stream);

        /* 파싱 작업 시작 */
        XmlPullParser httpParser = XmlPullParserFactory.newInstance().newPullParser();
        httpParser.setInput(reader);

        int eventType = httpParser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG && httpParser.getName().equals("thumbnail")) {
                httpParser.next();

                if (httpParser.getText() != null && item != null) {

                    item.setThumbnail(httpParser.getText());
                    Log.i("console-test", httpParser.getText());

                }
                break;
            }
//            else if (item.userRating == -1.0f && eventType == XmlPullParser.START_TAG && httpParser.getName().equals("userRating")) {
//                httpParser.next();
//
//                item.setUserRating(Float.parseFloat(httpParser.getText()));
//                break;
//            }
            eventType = httpParser.next();
        }
    }



}//CAfeSearchFragment..
