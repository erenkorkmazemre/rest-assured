package restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import restapi.objects.LocalInfo;
import restapi.objects.LoginInfo;
import restapi.objects.UserInfo;

import java.io.IOException;
import java.util.HashMap;

public class ReadData extends Datas {

    private HashMap<String, UserInfo> dataMap;
    private JSONObject testData;

    public void readTestData() {

        try {
            objectMapper = new ObjectMapper();
            dataMap = new HashMap<>();
            String jsonTemp = objectMapper.readTree(readJsonData()).get("newUserInformation").toString();
            dataMap = objectMapper.readValue(jsonTemp, new TypeReference<HashMap<String, LocalInfo>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getTestData(String filter) {

        if (dataMap == null)
            readTestData();
        testData = new JSONObject(dataMap.get(filter));
        return testData;
    }
}
