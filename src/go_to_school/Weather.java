package go_to_school;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;



public class Weather {
	int rainProbability;
	int temperature;
	public Weather() throws IOException {
		
		
		//api ��û�� ���� ��¥ & �ð�
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd HHmm");
		Date time1 = new Date();
		String time2 = format1.format(time1);
		String[] dayTime = time2.split(" ");
		String day = dayTime[0];
		String time = dayTime[1];
		int intTime = Integer.parseInt(time);
		String strTime;
		if (intTime>=215 && intTime<515) strTime = "0200";
		else if (intTime>=515 && intTime<815) strTime = "0500";
		else if (intTime>=815 && intTime<1115) strTime = "0800";
		else if (intTime>=1115 && intTime<1415) strTime = "1100";
		else if (intTime>=1415 && intTime<1715) strTime = "1400";
		else if (intTime>=1715 && intTime<2015) strTime = "1700";
		else if (intTime>=2015 && intTime<2315) strTime = "2000";
		else strTime = "2300";
		
		//������ ��ǥ
		String nx = "62";
		String ny = "124";
		
		//api ��û
		String serviceKey = "92l0CFpkVVj0GrLOhNo1bB4Msk1DYtB%2Ftqass5AI3UXekDxKMvvScfgOEEGYpOxb2oFENsRwzez7Ch9lFBD3fw%3D%3D";
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*��������ȣ*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*�� ������ ��� ��*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*��û�ڷ�����(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(day, "UTF-8")); /*15�� 12�� 1�� ��ǥ*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(strTime, "UTF-8")); /*06�� ��ǥ(���ô���)*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*���������� X ��ǥ��*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*�������� Y ��ǥ*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String[] js =  sb.toString().split("\"");
        int indexPOP = Arrays.asList(js).indexOf("POP") + 12;
        rainProbability = Integer.parseInt(js[indexPOP]);
        int indexT3H = Arrays.asList(js).indexOf("T3H") + 12;
        temperature = Integer.parseInt(js[indexT3H]);
        if (rainProbability>30) {
        	Client.requirementsArray.add(String.format("��� %s���Ȯ��%", rainProbability));
        }
        if (temperature>25) {
        	Client.requirementsArray.add(String.format("���� ���� %s��", temperature));
        }
        else if(temperature>15) {
        	Client.requirementsArray.add(String.format("���� ���� %s��", temperature));
        }
        else if(temperature>5) {
        	Client.requirementsArray.add(String.format("��Ʈ ���� %s��", temperature));
        }
        else {
        	Client.requirementsArray.add(String.format("�е� ���� %s��", temperature));
        }
    }

}

